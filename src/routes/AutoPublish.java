package routes;

import server.FileTransfer;
import server.HttpRequest;
import server.HttpRespond;

public class AutoPublish {

	public static boolean autoPublish(HttpRequest req, HttpRespond resp) {
		return autoPublishJS(req, resp) || autoPublishCSS(req, resp);
	}

	/**
	 * Auto publish .js files
	 * @param req
	 * @param resp
	 * @return boolean true if the file has been published
	 */
	public static boolean autoPublishJS(HttpRequest req, HttpRespond resp) {
		String url = clearUrl(req.getURL());

		if (url.endsWith(".js")) {
			resp.addContent(new FileTransfer(url));
			return true;
		}
		return false;
	}

	/**
	 * Auto publish .css files
	 * @param req
	 * @param resp
	 * @return
	 */
	public static boolean autoPublishCSS(HttpRequest req, HttpRespond resp) {
		String url = clearUrl(req.getURL());

		if (url.endsWith(".css")) {
			resp.setContentType("text/css");
			resp.addContent(new FileTransfer(url));
			return true;
		}
		return false;
	}

	/**
	 * Strips away the first leading / to auto publish the file
	 * @param url
	 * @return
	 */
	private static String clearUrl(String url) {
		return url.replaceFirst("\\/", "");
	}

}
