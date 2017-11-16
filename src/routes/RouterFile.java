package routes;

import server.FileTransfer;

public class RouterFile extends Router {

	public RouterFile(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	protected void task() {
		FileTransfer ft = new FileTransfer("test.html");
		this.httpRespond.setStatusCode(200);
		this.httpRespond.setContentType("text/html");
		this.httpRespond.addContent(ft);
	}

}
