package coreManagement;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import synchronization.StreamData;
import java.util.Scanner;
import java.awt.Toolkit;
import java.io.Console;
import cryptography.*;
import theme.Theme;
import apiClient.*;

public class Controller {

	private static Scanner inp = new Scanner(System.in);
	private static Theme loginTheme = new Theme();
	private static Crypto crypto = new Crypto();
	private static StreamData data;

	private static String host = "http://127.0.0.1:5000";
	private static String[] listApp;
	private static String[] loginData;
	private static String private_key;

	private static void copy(String theString) {
		StringSelection selection = new StringSelection(theString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}

	private static String[] getUserEntrance() {
		String[] data = { "", "" };
		System.out.print(loginTheme.name());
		data[0] = inp.nextLine();
		data[1] = getPassword(loginTheme.password());
		return data;
	}

	private static String getPassword(String mess) {
		Console console = System.console();
		char[] passwordArray = console.readPassword(mess);
		return new String(passwordArray);

	}

	private static boolean checkUserLogin(String private_key) {
		HttpConnection connection = new HttpConnection(host + "/getNames/" + private_key);
		String temp = connection.getContent();
		if ("wrong private key!".compareTo(temp) == 0) {
			return false;
		}
		listApp = temp.split("<br/>");
		return true;
	}

	public static void main(String[] args) {
		/*
		 * [===================] Login [===================]
		 */
		loginData = getUserEntrance();
		private_key = crypto.encrypt(loginData[0], loginData[1]);
		while (!checkUserLogin(private_key)) {
			System.out.print(loginTheme.loginFailed());
			loginData = getUserEntrance();
			private_key = crypto.encrypt(loginData[0], loginData[1]);
		}
		/*
		 * [===================] Control [===================]
		 */
		int option;
		System.out.print(loginTheme.options());
		data = new StreamData(private_key, host);
		while (true) {
			System.out.print(loginTheme.choose());
			try {
				option = inp.nextInt();
			} catch (Exception e) {
				inp.nextLine();
				System.out.println(loginTheme.status("wrong option"));
				continue;
			}
			switch (option) {
			case 1:
				System.out.print(loginTheme.Oname());
				inp.nextLine();
				String status = data.getPassword(inp.nextLine());
				if (status.equals("wrong key!")) {
					System.out.println(loginTheme.status("wrong key!"));
				} else {
					copy(crypto.decrypt(status, loginData[1]));
				}
				break;
			case 2:
				System.out.print(loginTheme.Oname());
				inp.nextLine();
				System.out.println(
						loginTheme.status(
								data.updatePassword(
										inp.nextLine(),
										crypto.encrypt(
											getPassword(
													loginTheme.Opassword()
											),
											loginData[1]
										)
								)
						)
				);
				break;
			case 3:
				System.out.print(loginTheme.Oname());
				inp.nextLine();
				System.out.println(
						loginTheme.status(
								data.deletePassword(
										inp.nextLine()
								)
						)
				);
				break;
			case 4:
				System.out.print(loginTheme.List(listApp));
				break;
			default:
				System.exit(0);
			}
		}
	}

}
