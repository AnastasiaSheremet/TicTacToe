package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ConsoleIO implements Input, Output {
	private static ConsoleIO instance = new ConsoleIO();
    private BufferedReader reader;
    private PrintStream out;

    private ConsoleIO() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        out = System.out;
    }

    @Override
    public String inputString(String message) {
        System.out.println(message);
        try {
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("IOException");
			return null;
		}
    }

    @Override
	public Integer inputInteger(String message) {
    	System.out.println(message);
        String s;
		try {
        	s = reader.readLine();
            return Integer.parseInt(s);
        } catch (NumberFormatException | IOException | NullPointerException e) {
            return 0;
        }

	}


    @Override
    public void showMessage(String message) {
        out.print(message);
    }

    @Override
    public void showError(String message) {
        out.println("\u001B[31m"+message+"\u001B[30m");
    }

    @Override
    public void showSuccess(String message) {
        out.println("\u001B[32m"+message+"\u001B[30m");
    }

    public static ConsoleIO getInstance() {
        return instance;
    }

	
}
