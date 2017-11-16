package routes;

import server.FileTransfer;
import server.HttpRequest;
import server.HttpRespond;

public class RouteToHtmlFile extends Router {

	protected FileTransfer fileTransfer;
	
	protected RouteToHtmlFile(String url, String filePath) {
		super(url);
		this.fileTransfer = new FileTransfer(filePath);
	}

	@Override
	protected void task() {
		// TODO Auto-generated method stub

	}

	public void runEvent(HttpRequest httpRequest, HttpRespond httpRespond) {
		this.httpRequest = httpRequest;
		this.httpRespond = httpRespond;
		this.httpRespond.setContentType("text/html");
		this.httpRespond.addContent(this.fileTransfer);
		
		this.task();
	}
}
