package com.mohit.gojek;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.stream.Stream;

public class ParkingLotApplication {

	private static final String SPACE = " ";
	
	public static void main(String[] args) throws SQLException {
		System.out.println("Connecting...");
		if (args.length > 0) {
			fileStream(args[0]);
		} else {
			readConsole();
		}
	}

	private static void fileStream(String fileName) {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
			Stream<String> lines = br.lines().map(str -> str.toUpperCase());
			System.out.println("<!-----Read all lines by using BufferedReader-----!>");
			lines.forEach(System.out::println);
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private static void readConsole() {
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		while (true) {
			if (command.equalsIgnoreCase("EXIT")) {
				break;
			}
			String[] args = command.split(SPACE);
			System.out.println("Current command: " + command);
		}
	}
}
