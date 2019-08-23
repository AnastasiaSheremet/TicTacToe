package tictactoe;

import io.ConsoleIO;

public class TicTacToe {
	private static ConsoleIO io;
//	public static final int DIMENSION = io.getInstance().inputInteger("Input Dimension");
	public static final int DIMENSION = 3;
	public static Cell[] field = new Cell[DIMENSION * DIMENSION];
	private static String USER_1 = "X";
	private static String USER_2 = "O";

	public static void main(String[] args) {
		playTwoPlayersMode();
	}

	private static void initField() {
		for (int i = 0; i < field.length; i++) {
			field[i] = new Cell();
			field[i].setArrCell0(Integer.valueOf(i + 1).toString());
		}
	}

	private static void printField() {
		for (int i = 0; i < field.length; i++) {
			field[i].printCell();
			if ((i + 1) % DIMENSION == 0) {
				io.getInstance().showMessage("\n");
				for (int j = 0; j < DIMENSION; j++) {
					io.getInstance().showMessage("---+");
				}
				io.getInstance().showMessage("\n");
			}

		}
	}

	public static boolean isBusy(String coordinate) {
		boolean busy = false;
			if (!field[Integer.parseInt(coordinate)-1].getArrCell()[1].equals(" ")) {
 				busy = true;
			}
		return busy;
	}

	public static void selectCoordinate(String user, int userNumber) {
		int coordinate = io.getInstance().inputInteger("User " + userNumber + ", Input coordinate");
		if (!isBusy(String.valueOf(coordinate))) {
			field[coordinate - 1].setArrCell0(" ");
			field[coordinate - 1].setArrCell1(user);
		} else {
			io.getInstance().inputString("Cell is busy. Try to select coordinate again. Press any key to continue");
			selectCoordinate(user, userNumber);
		}
	}

	public static void playTwoPlayersMode() {
		int count = 0;
		initField();
		while (count <= Math.pow(DIMENSION, 2)) {
			printField();
			selectCoordinate(USER_1, 1);
			printField();
			count++;
			if (isWin(USER_1)) {
				io.getInstance().showMessage("\n USER 1 HAS WON!!!\n");
				printField();
				break;
			}
			selectCoordinate(USER_2, 2);
			count++;
			if (isWin(USER_2)) {
				io.getInstance().showMessage("\n USER 2 HAS WON!!!\n");
				printField();
				break;
			}
		}
	}
	
	public static boolean isWin(String user) {
		boolean win = false;
		if (field[0].equals(field[1]) && field[0].equals(field[2])
				|| field[3].equals(field[4]) && field[3].equals(field[5])
				|| field[6].equals(field[7]) && field[6].equals(field[8])
				|| field[0].equals(field[3]) && field[0].equals(field[6])
				|| field[1].equals(field[4]) && field[1].equals(field[7])
				|| field[2].equals(field[5]) && field[2].equals(field[8])
				|| field[0].equals(field[4]) && field[0].equals(field[8])
				|| field[2].equals(field[4]) && field[2].equals(field[6])) {
			win = true;
		}
		return win;
	}
}

