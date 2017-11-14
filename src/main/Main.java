package main;

import events.EventFile;
import events.EventManager;
import server.ServerMainThread;

public class Main {

	public static void main(String args[]) {

		System.out.println("Main Running");

		EventManager eventManager = new EventManager();
		
		eventManager.registerEvent(new EventFile("/text"));

		ServerMainThread smt = new ServerMainThread(8080, eventManager);
		Thread serverMainThread = new Thread(smt);
		serverMainThread.setName("ServerMainThread");
		serverMainThread.start();
		
		
		
		try {
			serverMainThread.join(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
