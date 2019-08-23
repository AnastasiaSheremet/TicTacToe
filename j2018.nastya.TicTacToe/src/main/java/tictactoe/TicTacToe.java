package tictactoe;

import io.ConsoleIO;

public class TicTacToe {
	private static ConsoleIO io;
//	public static final int DIMENSION = io.getInstance().inputInteger("Input Dimension");
	private static final int DIMENSION = 3;
	private static Cell[] field = new Cell[DIMENSION * DIMENSION];
	private static final String USER_1 = "X";
	private static final String USER_2 = "O";
	private static User[] users = new User[2];

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

	public static boolean isCoordinateValid(int coordinate) {
		boolean valid = true;
		if (coordinate < 1 || coordinate > Math.pow(DIMENSION, 2)) {
			valid = false;
		}
		return valid;
	}

	public static boolean isBusy(int coordinate) {
		boolean busy = false;
		if (!field[coordinate - 1].getArrCell()[1].equals(" ")) {
			busy = true;
		}
		return busy;
	}

	public static void selectCoordinate(String user, String userName) {
		int coordinate = io.getInstance().inputInteger(
				"User " + userName + ", input number which is correspoding to the value in the field cell");
		if (!isCoordinateValid(coordinate)) {
			io.getInstance().showMessage("Incorrect input. Try to select coordinate again.\n");
			selectCoordinate(user, userName);
		}
		if (!isBusy(coordinate)) {
			field[coordinate - 1].setArrCell0(" ");
			field[coordinate - 1].setArrCell1(user);
		} else {
			io.getInstance().showMessage("Cell is busy. Try to select coordinate again.\n ");
			selectCoordinate(user, userName);
		}
	}

	public static void createUsers() {
		for (int i = 0; i < users.length; i++) {
			users[i] = new User();
			if (users[i].getName().equals("")) {
				String name = io.getInstance().inputString("Please, enter any name.\n ");
				users[i].setName(name);
			}
		}
		if (users[0].getName().equals(users[1].getName())) {
			String name = io.getInstance()
					.inputString("This name has already existed. Please, choose another name.\n ");
			users[1].setName(name);
		}
	}

	public static void playTwoPlayersMode() {
		int count = 0;
		createUsers();
		initField();
		printField();
		while (count <= Math.pow(DIMENSION, 2)) {
			selectCoordinate((count % 2 == 0) ? USER_1 : USER_2,
					(count % 2 == 0) ? users[0].getName() : users[1].getName());
			printField();
			count++;
			if (isWin((count % 2 == 0) ? USER_1 : USER_2)) {
				io.getInstance().showMessage(
						"\n USER " + ((count % 2 == 0) ? users[0].getName() : users[1].getName()) + " HAS WON!!!\n");
				printField();
				break;
			}
		}
	};

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
