package com.abas.spiel.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public enum Option {
	SCISSOR, ROCK, PAPER;

	private static final List<Option> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();

	private static final Random RANDOM = new Random();

	public static Option randomMove() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

	public static boolean isValid(String move) {
		try {
			return EnumSet.allOf(Option.class).contains(Enum.valueOf(Option.class, move.toUpperCase()));
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Return an int value +1 if moveOne greater than moveTwo -1 if less tan and 0
	 * if both are equal
	 * 
	 * @param moveOne
	 * @param moveTwo
	 */
	public static int compare(Option moveOne, Option moveTwo) {

		if (moveOne.equals(moveTwo)) {
			return 0;
		} else if ((Option.SCISSOR.equals(moveOne) && Option.PAPER.equals(moveTwo))
				|| (Option.ROCK.equals(moveOne) && Option.SCISSOR.equals(moveTwo))
				|| (Option.PAPER.equals(moveOne) && Option.ROCK.equals(moveTwo))) {
			return 1;
		} else {
			return -1;
		}
	}
}