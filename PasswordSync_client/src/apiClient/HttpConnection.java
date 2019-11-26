package apiClient;

import java.net.URL;
import java.io.*;

public class HttpConnection {
	
	public String link;
	private URL url = null;
	private InputStreamReader reader = null;
	
	public String getContent() throws IOException {
		String content = "";
		url = new URL(this.link);
		reader = new InputStreamReader(url.openStream());
		int temp = 0;
		while((temp=reader.read()) != -1) {
			content += (char)temp;
		}
		reader.close();
		return content;
	}
	
	public HttpConnection(String link) {
		this.link = link;
	}
	
}
