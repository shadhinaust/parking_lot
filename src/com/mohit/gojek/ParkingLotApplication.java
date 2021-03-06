package com.mohit.gojek;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import com.mohit.gojek.connection.ConnectionProvider;

public class ParkingLotApplication {

	private static final String SPACE = " ";
	private static final String EXIT_COMMAND = "EXIT";

	public static void main(String[] args) {
		ConnectionProvider.createTables();
		ConnectionProvider.cleanupData();
		if (args.length > 0) {
			readFile(args[0]);
		} else {
			readConsole();
		}
		ConnectionProvider.cleanupData();
		System.exit(0);
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
		CommandExecutor commandExecutor = new CommandExecutor();
		String command;
		while (true) {
			System.out.print("$ ");
			command = scanner.nextLine();
			if (command.equalsIgnoreCase(EXIT_COMMAND)) {
				break;
			}
			commandExecutor.executeCommand(command.split(SPACE));
		}
	}
}
