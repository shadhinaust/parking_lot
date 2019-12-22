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
	private static final String EXIT_COMMAND = "EXIT";

	public static void main(String[] args) {
		if (args.length > 0) {
			readFile(args[0]);
		} else {
			readConsole();
		}
	}

	private static void readFile(String fileName) {
		CommandExecutor commandExecutor = new CommandExecutor();
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName));
			Stream<String> lines = bufferedReader.lines().map(command -> command);
			lines.forEach(command -> {
				commandExecutor.executeCommand(command.split(SPACE));
			});
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private static void readConsole() {
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		CommandExecutor commandExecutor = new CommandExecutor();
		while (true) {
			if (command.equalsIgnoreCase(EXIT_COMMAND)) {
				break;
			}
			commandExecutor.executeCommand(command.split(SPACE));
		}
	}
}
