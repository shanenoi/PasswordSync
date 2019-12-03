package theme;

public class Theme {
	
	public String name() {
		return 	"\n ╱┌------┐╲\n"
				+ "( │ Name | )\n"
				+ " ╲├------┘╱\n"
				+ "  └╼> ";
	}
	
	public String password() {
		return 	"\n ╱┌----------┐╲\n"
				+ "( │ Password | )\n"
				+ " ╲├----------┘╱\n"
				+ "  └╼> ";
	}
	
	public String loginFailed() {
		return  "\n ╱┌--------------┐╲\n"
				+ "( │ Login Failed | )\n"
				+ " ╲└--------------┘╱\n";
	}
	
	public String options() {
		return  "\n ╱┌-----------------------------┐╲\n"
				+ "( │ [1 ] Get password           | )\n"
				+ "│ │ [2 ] Edit/Update password   | │\n"
				+ "│ │ [3 ] Delete password        | │\n"
				+ "│ │ [4 ] List                   | │\n"
				+ "( │ [99] Exit                   | )\n"
				+ " ╲└-----------------------------┘╱\n";
	}
	
	public String List(String[] apps) {
		String temp = "\n ╱┌------┐╲\n"
					  + "( │ List | )\n"
					  + " ╲├------┘╱\n";
		for (String i: apps) {
			temp += "  └╼> "+i+"\n";
		}
		return temp;
	}
	
	public String status(String stt) {
		return  "\n/////////////\n"
				+ "["+stt+"]\n"
				+ "/////////////";
	}
	
	public String choose() {
		return "\n│Index├╼> ";
	}
	
	public String Oname() {
		return "\n│Name├╼> ";
	}
	
	public String Opassword() {
		return "\n│Password├╼> ";
	}

}
