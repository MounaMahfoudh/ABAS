package com.abas.spiel;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.abas.spiel.common.Avatar;
import com.abas.spiel.common.Key;
import com.abas.spiel.common.Option;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) {

		Console console = System.console();
		if (console != null) {

			console.writer()
					.println("============Welcome to \"scissor rock paper\" from ABAS.============\n" + "Press "
							+ Key.HELP + " to check the rules.\n" + "Press " + Key.RESULT + " to view the results.\n"
							+ "Press " + Key.QUIT + " to exit the terminal.\n");

			boolean bExit = false;
			String sPlayer = null;
			String sComputerPlayer = null;
			String move = null;
			Scanner scanner = new Scanner(console.reader());
			Map<String, Integer> results = new HashMap<String, Integer>();

			do {
				console.writer().println("Choose your Avatar: [CAT, DOG, OWL]");
				sPlayer = scanner.nextLine();
				bExit = this.isExit(sPlayer, console, results);
			} while (!Avatar.isValid(sPlayer) && !bExit);

			if (!bExit) {
				console.writer().println("You are playing as: " + sPlayer);
				sPlayer = sPlayer.toUpperCase();
				sComputerPlayer = Avatar.randomPlayer(sPlayer).toString();

				// Set initial values
				results.put(sPlayer, 0);
				results.put(sComputerPlayer, 0);
			}

			while (!bExit) {
				console.writer().println("Choose one option: [SCISSOR, ROCK , PAPER]");
				move = scanner.nextLine();
				bExit = this.isExit(move, console, results);

				if (!bExit) {
					if (!Option.isValid(move) && !Key.isValid(move)) {
						console.writer().println(
								"Non valid option! Press HELP for guidelines, RESULT to view the results or QUIT to exit.");
					} else if (Option.isValid(move)) {
						this.play(console, sPlayer, sComputerPlayer, move, results);
					}
				}
			}

			// Print Results before Quit
			this.printResults(console, results);

		} else {
			System.out.println("There is no console available. Refer to README.md for running this apploication.");
		}
	}

	/**
	 * Play one turn
	 * 
	 * @param console
	 * @param sPlayer
	 * @param sComputerPlayer
	 * @param move
	 * @param results
	 */
	void play(Console console, String sPlayer, String sComputerPlayer, String move,
			Map<String, Integer> results) {
		console.writer().println(sPlayer + " =>" + move);
		Option computerMove = Option.randomMove();
		Option playerMove = Enum.valueOf(Option.class, move.toUpperCase());
		console.writer().println(sComputerPlayer + " =>" + computerMove);
		int result = Option.compare(computerMove, playerMove);
		if (result == 1) {
			results.put(sComputerPlayer, results.get(sComputerPlayer) + 1);
		} else if (result == -1) {
			results.put(sPlayer, results.get(sPlayer) + 1);
		}
		console.writer().println("		" + sPlayer + " " + results.get(sPlayer) + " | " + sComputerPlayer + " "
				+ results.get(sComputerPlayer));
	}

	/**
	 * Check if Player pressed help, results or wants to exit
	 * 
	 * @param sInput
	 * @param console
	 * @return
	 */
	private boolean isExit(String sInput, Console console, Map<String, Integer> results) {
		try {
			Key typedKey = Enum.valueOf(Key.class, sInput.toUpperCase());

			if (Key.HELP.equals(typedKey)) {
				// print help
				this.printHelp(console);

			} else if (Key.RESULT.equals(typedKey)) {
				this.printResults(console, results);
			}
			return Key.QUIT.equals(typedKey);

		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private void printResults(Console console, Map<String, Integer> results) {

		console.writer().println("Results:============================================================");
		for (Map.Entry<String, Integer> entry : results.entrySet()) {
			String player = entry.getKey();
			Integer value = entry.getValue();
			console.writer().println("			" + player + " score= " + value);
		}

	}

	/**
	 * Print Help for Game Rock Paper Scissor
	 * 
	 * @param console
	 */
	private void printHelp(Console console) {

		console.writer().println("Rock Paper Scissor guideline:");
		console.writer().println(
				"In turns both players make a draw either Rock, Paper or Scissor. The outcome of the draws is summarized in the table below.");
		console.writer().println("                | Scissor |  Rock  | Paper	");
		console.writer().println("Scissor         |    X    |   -    |   +   	");
		console.writer().println("Rock            |    +    |   X    |   -   	");
		console.writer().println("Paper           |    -    |   +    |   X   	");
		console.writer().println();

	}

}
