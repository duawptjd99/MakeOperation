package Loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Processing.Process;

public class Loader {

	public Process load(String fileName) {

		try {
			int stackSegmentSize = 0;
			String[] codes = null;
			File file = new File("exe/" + fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) { // ÀÐ¾î¿È
				String line = scanner.nextLine();
				// System.out.println(line);
				if (!line.isEmpty()) {
					if (line.substring(0, 2).contentEquals("//")) {
					} else if (line.equals(".stack")) {
						stackSegmentSize = this.parseStack(scanner);
					} else if (line.equals(".code")) {
						codes = this.parseCode(scanner);
					}
				}
			}/*
			System.out.println("-----stack-----");
			System.out.println("Size = " + stackSegmentSize);
			System.out.println("-----code-----");
			for (int a = 0; a < stackSegmentSize; a++) {
				System.out.println(codes[a]);
			}*/
			Process process = new Process(codes, stackSegmentSize);
			scanner.close();
			return process;
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private int parseStack(Scanner scanner) {
		String line = scanner.nextLine();
		while (!line.substring(0, 5).equals(".code")) {
			if (!line.substring(0, 2).contentEquals("//")) {
				line = line.replaceAll("[^0-9]", "");
				int temp = Integer.parseInt(line);
				return temp;
			}
			line = scanner.nextLine();
		}
		return 0;
	}

	private String[] parseCode(Scanner scanner) {
		String line = scanner.nextLine();
		String[] code = new String[100];
		int i = 0;
		while (scanner.hasNext()) {
			if (!line.isEmpty()) {
				if (!line.substring(0, 2).equals("//")) {
					code[i] = line;
					i++;
				}
			}
			line = scanner.nextLine();
			code[i] = line;
		}

		return code;
	}
}
