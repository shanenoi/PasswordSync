package synchronization;


import apiClient.*;


public class StreamData {
	
	private String host;
	private String hash_id;
	
	private String metaData(
			String privateKey,
			String method,
			String key,
			String value
		) {
			return (String.format(
					"/field/%s&%s&%s&%s",
					privateKey,
					method, key, 
					(value != null) ? value:"null"
			));
		}
	
	public StreamData(String hash_id, String host) {
		this.hash_id = hash_id;
		this.host = host;
		
	}
	
	public String getPassword(String name) {
		//System.out.print(this.host + 
		//		metaData(this.hash_id, "get", name, null));
		return new HttpConnection(
						this.host + 
						metaData(this.hash_id, "get", name, null)
					).getContent();
	}
	
	public String updatePassword(String name, String value) {
		//System.out.print(this.host +
		//		metaData(this.hash_id, "update", name, value));
		return new HttpConnection(
						this.host +
						metaData(this.hash_id, "update", name, value)
					).getContent();
	}
	
	public String deletePassword(String name) {
		//System.out.print(this.host +
		//		metaData(this.hash_id, "delete", name, null));
		return new HttpConnection(
						this.host +
						metaData(this.hash_id, "delete", name, null)
					).getContent();
	}

}
