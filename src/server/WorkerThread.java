package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import routes.AutoPublish;
import routes.Router;
import routes.RouterManager;

public class WorkerThread implements Runnable {

	Socket socket;

	RouterManager eventManager;

	BufferedReader in;
	PrintWriter out;

	public WorkerThread(Socket socket, RouterManager eventManager) throws IOException {
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
			
			System.out.println(tmp);

			split = tmp.split(" ");

			httpreq = new HttpRequest(split[0], split[1]);

			HttpRespond httpRespond = new HttpRespond(this.out);
			System.out.println(httpreq.toString());

			//Wenn autoPublish nicht funktioniert checke Router
			if(AutoPublish.autoPublish(httpreq, httpRespond) == false) {
				Router e = this.eventManager.findEvent(httpreq.getURL());
				e.runEvent(httpreq, httpRespond);
			}
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
