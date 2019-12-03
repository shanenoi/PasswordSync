package apiClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

public class HttpConnection {
	
	public String link;
	private URL url = null;
	private InputStreamReader reader = null;
	
	public HttpConnection(String link) {
		this.link = link;
	}
	
	public String getContent() {
		String content = "";
		try {
			url = new URL(this.link);
		} catch (MalformedURLException e1) {
			return "404";
		}
		try {
			reader = new InputStreamReader(url.openStream());
			int temp = 0;
			while((temp=reader.read()) != -1) {
				content += (char)temp;
			}
			reader.close();
			return content;
		}catch (IOException e) {
			return e.getMessage();
		}
	}
	
}
