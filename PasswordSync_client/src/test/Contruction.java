package test;

import cryptography.*;

public class Contruction {
	
	public static void main(String[] args) throws Exception {
		Crypto crypto = new Crypto();
		System.out.print(crypto.decrypt("=GQs4Gkhz1ZRY9cbPV=S", "hello"));
	}

}
