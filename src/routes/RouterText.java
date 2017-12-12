package routes;

public class RouterText extends Router {
	
	public RouterText(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This function need to get implemnted
	 */
	protected void task() {
		httpRespond.setStatusCode(200);
		httpRespond.setContentType("text/html");

		httpRespond.addContent("<!DOCTYPE html>");
		httpRespond.addContent("<html>");
		httpRespond.addContent("<head>");
		httpRespond.addContent("<title>404 Not Found</title>");
		httpRespond.addContent("<link rel=\"stylesheet\" type=\"text/css\" href=\"test.css\">");
		httpRespond.addContent("<script src=\"test.js\"></script>");
		httpRespond.addContent("</head>");
		httpRespond.addContent("<body>");
		httpRespond.addContent("<h1>Not Found</h1>");
		httpRespond.addContent("<p>" + this.httpRequest.getURL() + " - has no Event assigend!</p>");
		httpRespond.addContent("</body>");
		httpRespond.addContent("</html>");
	};
}
