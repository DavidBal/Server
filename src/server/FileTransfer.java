package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileTransfer {

	private File file;

	public FileTransfer(String pfad) {
		this.file = new File(pfad);
	}

	public long getFileSize() {
		return this.file.length();
	}

	public String getFilePath() {
		return this.file.getPath();
	}

	public boolean isFileReadable() {
		return this.file.exists() && this.file.canRead() && this.file.isFile();
	}

	public void sendFile(PrintWriter out) throws IOException {
		BufferedReader fileIn = new BufferedReader(new FileReader(this.file));

		String line = fileIn.readLine();

		while (line != null) {
			out.println(line);
			line = fileIn.readLine();
		}
		fileIn.close();
	}

}
