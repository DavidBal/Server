package server;

import java.util.LinkedHashMap;

public class HttpRequest {

	private String requestMethod;
	private String url;

	LinkedHashMap<String, String> parameter = new LinkedHashMap<String, String>();

	public String getURL() {
		return url;
	}

	public HttpRequest(String requestMethod, String url) {
		this.requestMethod = requestMethod;

		this.url = this.extractUrlParameter(url);
	}

	/**
	 * 
	 * @param url
	 * @return the param cleared url
	 */
	private String extractUrlParameter(String url) {
		// rename
		System.out.println(url);

		if (url.contains("\\?")) {
			String[] up = url.split("\\?");

			String[] param = up[1].split("\\&");

			for (int i = 0; i < param.length; i++) {
				String[] tmp = param[i].split("\\=");
				parameter.put(tmp[0], tmp[1]);
			}
			return up[0];
		}
		return url;
	}

	public String toString() {
		String s = "RequestMethode: " + this.requestMethod + "; Url: " + this.url + "\n";

		s += "Parameter: " + this.parameter.toString() + "\n";

		return s;
	}
}
