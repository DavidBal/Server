package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import events.Event;
import events.EventManager;

public class WorkerThread implements Runnable {

	Socket socket;

	EventManager eventManager;

	BufferedReader in;
	PrintWriter out;

	public WorkerThread(Socket socket, EventManager eventManager) throws IOException {
		this.socket = socket;
		this.eventManager = eventManager;

		this.out = new PrintWriter(this.socket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}

	public void run() {
		System.out.println("Worker Startet");

		HttpRequest httpreq;
		String tmp = "";
		String[] split;

		// First Line

		try {
			tmp = this.in.readLine();

			split = tmp.split(" ");

			httpreq = new HttpRequest(split[0], split[1]);

			HttpRespond httpRespond = new HttpRespond(this.out);
			System.out.println(httpreq.toString());

			Event e = this.eventManager.findEvent(httpreq.getURL());
			e.runEvent(httpreq, httpRespond);

			httpRespond.send();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
