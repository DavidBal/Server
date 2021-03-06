package routes;

import server.HttpRequest;
import server.HttpRespond;

public abstract class Router {

	/**
	 * @description The URL that this event is linked to
	 */
	private String url;

	public String getURL() {
		return this.url;
	}

	/**
	 * 
	 */
	protected HttpRequest httpRequest;

	/**
	 * 
	 */
	protected HttpRespond httpRespond;

	/**
	 * Constructor
	 * 
	 * @param url
	 */
	protected Router(String url) {
		this.url = url;
	}

	/**
	 * This function need to get implemnted
	 */
	protected abstract void task();

	public void runEvent(HttpRequest httpRequest, HttpRespond httpRespond) {
		this.httpRequest = httpRequest;
		this.httpRespond = httpRespond;

		this.task();
	}
}
