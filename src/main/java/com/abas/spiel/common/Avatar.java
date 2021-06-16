package com.abas.spiel.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public enum Avatar {
	CAT, DOG, OWL;

	/**
	 * Returns a random value for Player different from parameter sPlayer
	 * 
	 * @param sPlayer
	 * @return
	 */
	public static Avatar randomPlayer(String sPlayer) {
		List<Avatar> VALUES = Collections.unmodifiableList(
				Arrays.asList(EnumSet.complementOf(EnumSet.of(Enum.valueOf(Avatar.class, sPlayer.toUpperCase())))
						.toArray(new Avatar[Avatar.values().length - 1])));
		Random RANDOM = new Random();
		int SIZE = VALUES.size();
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

	/**
	 * Check if Input parameter is a valid Avatar
	 * @param sInput
	 * @return
	 */
	public static boolean isValid(String sInput) {
		try {
			return EnumSet.allOf(Avatar.class).contains(Enum.valueOf(Avatar.class, sInput.toUpperCase()));
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}