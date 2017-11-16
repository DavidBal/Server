package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class HttpRespond {
	long contentLength = 0;

	PrintWriter out;

	public HttpRespond(PrintWriter out) {
		this.out = out;
	}

	int statusCode;
	String statusCodeText = null;

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;

		if (statusCodeText == null) {
			switch (statusCode / 100) {
			case 1:
				this.statusCodeText = "Informational";
				break;
			case 2:
				// Success
				this.statusCodeText = "OK";
				break;
			case 3:
				// Redirection
				this.statusCodeText = "redirect";
				break;
			case 4:
				// Client Error
				this.statusCodeText = "Client Error";
				break;
			case 5:
				// Server Error
				this.statusCodeText = "Server Error";
				break;
			default:
				// TODO Throw Exception
			}
		}

	}

	String contentType = "";

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	private ArrayList<String> content = new ArrayList<String>();

	public void addContent(String html) {
		this.contentLength += html.length() + 1;

		this.content.add(html);
	}

	private FileTransfer file = null;

	public void addContent(FileTransfer file) {
		if (file.isFileReadable() == false) {
			this.setStatusCode(500);
			this.contentLength = 0;
			//TODO better Error Mangment
			System.out.println("Error - Send file " + file.getFilePath() +  " failed!");
		} else {
			this.contentLength += file.getFileSize();
			this.file = file;
		}
	}

	public void send() {
		// Header
		this.out.println("HTTP/1.1 " + this.statusCode);
		Date currentDate = new Date();
		this.out.println("Date: " + currentDate);
		this.out.println("Server: DavidsProject");
		this.out.println("Content-Length: " + this.contentLength);
		// can be change
		this.out.println("Content-Type: " + this.contentType + "; charset=UTF-8");
		this.out.println("Connection: Closed");
		// Marks the End of the Header
		this.out.println();
		// Content
		for (String s : this.content) {
			// System.out.println(s);
			this.out.println(s);
		}

		if (file != null) {
			try {
				file.sendFile(this.out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
