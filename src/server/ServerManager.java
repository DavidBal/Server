package server;

import java.io.File;

/**
 * 
 * @author davidbaldauf
 * Keeps Track of all the Server operations
 */
public class ServerManager {
	
	public void buildEnvironment() {
		File pub = new File("public");
		if(pub.isDirectory() == false) {
			pub.mkdirs();
		}
		
		
	}
	
}
