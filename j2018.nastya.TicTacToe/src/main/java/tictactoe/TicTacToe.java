package tictactoe;

import io.ConsoleIO;

public class TicTacToe {
	public static ConsoleIO io = ConsoleIO.getInstance();

	public static void main(String[] args) {
		new TicTacToe().start();
	}

	private void start() {
		io.showMessage("Welcome to Tic Tac Toe game!\n");
		String message = "Please select the game mode:\n1 - Two players\n2 - Play with computer\n3 - Exit";
		while (true) {
			switch (io.inputString(message)) {
			case "1":
				(new UserGameMode()).playTwoPlayersMode();
				break;
			case "2":
				(new ComputerGameMode()).playWithComputerMode();
				break;
			case "3":
				io.showMessage("Bye");
				return;
			default:
				io.showMessage("Incorrect input. Please try again.\n");
				continue;
			}
		}
	}

}