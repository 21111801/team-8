package ��ŷ;

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

	public static void main(String[] args) {
		fileio();
		pointget();
		lankprint();
		newlanksave();
	}

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

		// ���� ��������ؼ� ���� ���
		// ����Ʈ ��������
		// ���� �Է� �����ͼ� ������ ����
		// ��ŷ ���ؼ� ���
		// ��ŷ ����
	}

	public static void pointget() {
		nowpoint = 1250;
		nowname = "������";
	}

	public static void lankprint() {
		int nowlank = 1;
		for (int j = 0; j < i; j++) {
			if (check != 1)
				break;
			if (nowpoint <= savepoint[j])
				j++;
			else {
				nowlank = savelank[j - 1];
				if (nowpoint == savepoint[j - 1])
					nowlank++;
				break;
			}
		}

		for (int j = 0; j < i + 1 && j < 10; j++) {
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
