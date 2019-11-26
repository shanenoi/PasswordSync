package cryptography;

public class Crypto {
	
	public String encrypt(String text, String password) {
		ConvertBase64 base64 = new ConvertBase64();
		return base64.convert(
					base64.encode(
							text+base64.encode(password)
					)
			   );
	}
	
	public String decrypt(String text, String password) {
		ConvertBase64 base64 = new ConvertBase64();
		return base64.decode(
					base64.convert(text)
			   ).replace(
					   base64.encode(password), ""
			   );
	}

}
