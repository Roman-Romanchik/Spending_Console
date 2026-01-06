package org.aren_rend.utilities;

public class Validator {
    private final boolean isValid = true;
	public boolean isString(String string) {
		if(string.matches("[a-zA-Z]+")) {
			return isValid;
		}
		return !isValid;
	}

	public boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
			return isValid;
		} catch (NumberFormatException e) {
			return !isValid;
		}
	}
}