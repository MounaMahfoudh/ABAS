package com.abas.spiel.common;

import java.util.EnumSet;

public enum Key {
	HELP, RESULT, QUIT;

	public static boolean isValid(String input) {
		try {
			return EnumSet.allOf(Key.class).contains(Enum.valueOf(Key.class, input.toUpperCase()));
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}