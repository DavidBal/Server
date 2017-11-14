package events;

import server.FileTransfer;

public class EventFile extends Event {

	public EventFile(String url) {
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
