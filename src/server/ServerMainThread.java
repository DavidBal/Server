package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import routes.RouterManager;

public class ServerMainThread implements Runnable {

	int port;
	
	boolean exit;
	
	RouterManager eventManager;
	
	public ServerMainThread(int port, RouterManager eventManager) {
		this.port = port;
		this.exit = false;
		this.eventManager = eventManager;
	}
	
	
	public void run() {
		try {
			mainLoop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	private void mainLoop() throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		
		
		while( this.exit == false ) {
			
			Socket cs = serverSocket.accept();
			
			WorkerThread wt = new WorkerThread(cs, eventManager);
			Thread workerThread = new Thread(wt);
			workerThread.start();
			
			
		}
		
		serverSocket.close();
	}
	
	
}
