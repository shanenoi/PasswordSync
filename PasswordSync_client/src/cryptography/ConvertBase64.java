package cryptography;

import java.util.Base64;

public class ConvertBase64 {
	
	protected String encode(String text) {
		return Base64
				.getEncoder()
				.encodeToString(
						text.getBytes()
				);
	}
	
	protected String decode(String text) {
		return new String(
					Base64.getDecoder().decode(text)
			   );
	}
	
	protected String convert(String text) {
		StringBuffer txt = new StringBuffer(text);
		char temp;
		for (int i=0; i<(int)txt.length()/2.0; i+=2) {
			temp = txt.charAt(i);
			txt.setCharAt(
					i, 
					txt.charAt(
							txt.length()-1-i
					)
			);
			txt.setCharAt(
					txt.length()-1-i,
					temp
			);
		}
		return txt.toString();
	}


}
