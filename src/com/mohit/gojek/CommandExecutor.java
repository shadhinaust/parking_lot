package com.mohit.gojek;

public class CommandExecutor {

	private static final String CREATE_PARKING_SLOT = "create_parking_lot";
	private static final String PARK_A_CAR = "park";
	private static final String LEAVE_A_SLOT = "leave";
	private static final String STATUS = "status";
	private static final String REGISTRATION_NUMBERS_FOR_COLOR = "registration_numbers_for_cars_with_colour";
	private static final String SLOT_NUMBERS_FOR_COLOR = "slot_numbers_for_cars_with_colour";
	private static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER = "slot_number_for_registration_number";

	private static final int COMMAND = 0;
	private static final int FIRST_ARGUMENT = 1;
	private static final int SECOND_ARGUMENT = 2;

	public void executeCommand(String[] args) {

		String command = args[COMMAND];

		if (command.equals(CREATE_PARKING_SLOT)) {
			int totalSlot = Integer.valueOf(args[FIRST_ARGUMENT]);
		} else if (command.equals(PARK_A_CAR)) {
			String registrationNumber = args[FIRST_ARGUMENT];
			String color = args[SECOND_ARGUMENT];
		} else if (command.equals(LEAVE_A_SLOT)) {
			int numberOfSlot = Integer.valueOf(args[FIRST_ARGUMENT]);
		} else if (command.equals(STATUS)) {

		} else if (command.equals(REGISTRATION_NUMBERS_FOR_COLOR)) {
			String color = args[FIRST_ARGUMENT];
		} else if (command.equals(SLOT_NUMBERS_FOR_COLOR)) {
			String color = args[FIRST_ARGUMENT];
		} else if (command.equals(SLOT_NUMBER_FOR_REGISTRATION_NUMBER)) {
			String registrationNumber = args[FIRST_ARGUMENT];
		}
	}

}
