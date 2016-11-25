package softwareEngineering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lanking {

	static int[] savepoint = new int[10];
	static String[] savename = new String[10];
	static int[] savelank = new int[10];
	static int i, check = 0;
	static int nowpoint;
	static int nowlank;
	static String nowname;
	static int[] newpoint = new int[10];
	static String[] newname = new String[10];
	static int[] newlank = new int[10];

	public Lanking(int nowpoint){
		this.nowpoint = nowpoint;
		fileio();
		pointget();
		lankprint();
		newlanksave();
	}
	
	/*
	public static void main(String args[]){
		fileio();
		pointget();
		lankprint();
		newlanksave();
	}
*/
	public static void fileio() {
		int[] lank = new int[10];
		int point;
		i = 0;
		
		Scanner scan = null;
		try {
			scan = new Scanner(new File("Lanking.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		while (scan.hasNext()) {
			savename[i] = scan.next();
			savelank[i] = scan.nextInt();
			savepoint[i++] = scan.nextInt();
			check = 1;
		}

		// 파일 입출력통해서 파일 출력
		// 포인트 가져오기
		// 파일 입력 가져와서 변수에 저장
		// 랭킹 비교해서 출력
		// 랭킹 저장
	}

	public static void pointget() {
		Scanner scan = null;
		System.out.println("이름을 입력하세요 : ");
		nowname = scan.next();
	}

	public static void lankprint() {
		int nowlank = 1;
		int j;
		for (j = 0; j <= i; j++) {
			if (check != 1)
				break;
			if (nowpoint <= savepoint[j])
				continue;
			else {
				if(savename[j]==null)
					break;
				if(j==0)
					break;					
				nowlank = savelank[j];
				if (nowpoint == savepoint[j - 1])
					nowlank++;
				break;
			}
		}

		if(j==i)
			nowlank=savelank[j-1]+1;
		
		for (j = 0; j < i + 1 && j < 10; j++) {
			if (j == nowlank - 1 || check != 1) {
				newname[j] = nowname;
				newlank[j] = nowlank;
				newpoint[j] = nowpoint;
			} else if (j >= nowlank) {
				newname[j] = savename[j - 1];
				newlank[j] = savelank[j - 1] + 1;
				newpoint[j] = savepoint[j - 1];
			} else if (j < nowlank) {
				newname[j] = savename[j];
				newlank[j] = savelank[j];
				newpoint[j] = savepoint[j];
			}
		}
	}

	public static void newlanksave() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("Lanking.dat"));
		} catch (IOException e) {
		}
		
		for (int j = 0; j < i + 1 && j < 10; j++) {
			System.out.println(newname[j] + " " + newlank[j] + " " + newpoint[j]);
			out.print(newname[j] + " ");
			out.print(newlank[j] + " ");
			out.println(newpoint[j]);
		}
		out.close();

	}


}
