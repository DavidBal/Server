package server;

import java.io.BufferedReader;
import java.io.IOException;
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

	public static HttpRequest createRequest(BufferedReader reader) throws IOException {
		String tmp = "";
		String[] split;

		// GET/POST url HTTP/1.1
		tmp = reader.readLine();

		split = tmp.split(" ");

		while (tmp.isEmpty() == false) {
			tmp = reader.readLine();
			
			System.out.println(tmp);
			System.out.println(tmp.isEmpty());
		}

		
		System.out.println("Ende des Reads!!");
		
		HttpRequest req = new HttpRequest(split[0], split[1]);

		return req;

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
