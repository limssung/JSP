package naver_search;

import java.util.Scanner;

public class Gugudan {

	private int dan;
	private int gop;
	private String result = "";


	public void processGugudan() {
		// 처리
		for (int dan = 2; dan < this.dan + 1; dan++) {
			result += dan + "단" + "\n";
			for (int gop = 1; gop < this.gop + 1; gop++) {
				result += dan + " * " + gop + " = " + (dan * gop) + "\n";
			}
			result += "\n";
		}
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}

	public int getGop() {
		return gop;
	}

	public void setGop(int gop) {
		this.gop = gop;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}