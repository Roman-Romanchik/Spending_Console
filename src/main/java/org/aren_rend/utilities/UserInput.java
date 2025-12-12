package org.aren_rend.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
			System.out.println("> ");

			return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Input error: " + e.getMessage(), e);
        }
    }
}