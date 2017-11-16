package main;

import routes.RouterFile;
import routes.RouterManager;
import server.ServerMainThread;

public class Main {

	public static void main(String args[]) {

		System.out.println("Main Running");

		RouterManager eventManager = new RouterManager();
		
		eventManager.registerEvent(new RouterFile("/text"));

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
