package tictactoe;

import io.ConsoleIO;

public abstract class Game {
	public static ConsoleIO io = ConsoleIO.getInstance();
	public static final int DIMENSION = 3;
	protected static Cell[] field = new Cell[DIMENSION * DIMENSION];
	protected static final String USER_1 = "X";
	protected static final String USER_2 = "O";
	protected static User[] users = new User[2];

	protected static void initField() {
		for (int i = 0; i < field.length; i++) {
			field[i] = new Cell();
			if ((i + 1) % DIMENSION == 0) {
				field[i] = new RightCell();
			}
			field[i].setArrCell0(Integer.valueOf(i + 1).toString());
		}
	}

	protected static void printField() {
		for (int i = 0; i < field.length; i++) {
			field[i].printCell();
			if ((i + 1) % DIMENSION == 0) {
				io.getInstance().showMessage("\n");
				for (int j = 0; j < DIMENSION; j++) {
					if (i < field.length - DIMENSION) {
						if ((j + 1) % DIMENSION != 0) {
							io.getInstance().showMessage("---+");
						} else {
							io.getInstance().showMessage("---");
						}
					}
				}
				io.getInstance().showMessage("\n");
			}
		}
	}

	private static boolean isCoordinateValid(int coordinate) {
		boolean valid = true;
		if (coordinate < 1 || coordinate > Math.pow(DIMENSION, 2)) {
			valid = false;
		}
		return valid;
	}

	private static boolean isBusy(int coordinate) {
		boolean busy = false;
		if (isCoordinateValid(coordinate)) {
			if (coordinate >= 1 && coordinate <= field.length) {
				if (!field[coordinate - 1].getArrCell()[1].equals(" ")) {
					busy = true;
				}
			}
		}
		return busy;
	}

	protected void selectCoordinate(String user, String userName) {
		int coordinate = io.getInstance().inputInteger(
				"User " + userName + ", input number which is correspoding to the value in the field cell");
		if (isCoordinateValid(coordinate)) {
			if (!isBusy(coordinate)) {
				field[coordinate - 1].setArrCell0(" ");
				field[coordinate - 1].setArrCell1(user);
			} else {
				io.getInstance().showMessage("Cell is busy. Try to select coordinate again.\n");
				selectCoordinate(user, userName);
			}
		} else {
			io.getInstance().showMessage("Incorrect input. Try to select coordinate again.\n");
			selectCoordinate(user, userName);
		}
	}

	protected abstract void createUsers();

	protected void selectFirstStep(String user) {
		int cell;
		do {
			cell = (int) (Math.random() * 9 + 1);
		} while (isBusy(cell));
		field[cell - 1].setArrCell0(" ");
		field[cell - 1].setArrCell1(user);
	}

	protected int checkWin(String user) {
		int cell = -1;
		if (returnCellValue(0).equals(user) && isEqual(0, 1) || returnCellValue(0).equals(user) && isEqual(0, 2)
				|| returnCellValue(1).equals(user) && isEqual(1, 2)) {
			for (int i = 0; i <= 3; i++) {
				if (!isBusy(i + 1)) {
					cell = i;
				}
			}
		}
		if (returnCellValue(3).equals(user) && isEqual(3, 4) || returnCellValue(3).equals(user) && isEqual(3, 5)
				|| returnCellValue(4).equals(user) && isEqual(4, 5)) {
			for (int i = 3; i <= 5; i++) {
				if (!isBusy(i + 1)) {
					cell = i;
				}
			}
		}
		if (returnCellValue(6).equals(user) && isEqual(6, 7) || returnCellValue(6).equals(user) && isEqual(6, 8)
				|| returnCellValue(7).equals(user) && isEqual(7, 8)) {
			for (int i = 6; i <= 8; i++) {
				if (!isBusy(i + 1)) {
					cell = i;
				}
			}
		}
		if (returnCellValue(0).equals(user) && isEqual(0, 3) || returnCellValue(0).equals(user) && isEqual(0, 6)
				|| returnCellValue(3).equals(user) && isEqual(3, 6)) {
			for (int i = 0; i <= 6; i += 3) {
				if (!isBusy(i + 1)) {
					cell = i;
				}
			}
		}
		if (returnCellValue(1).equals(user) && isEqual(1, 4) || returnCellValue(1).equals(user) && isEqual(1, 7)
				|| returnCellValue(4).equals(user) && isEqual(4, 7)) {
			for (int i = 1; i <= 7; i += 3) {
				if (!isBusy(i + 1)) {
					cell = i;
				}
			}
		}
		if (returnCellValue(2).equals(user) && isEqual(2, 5) || returnCellValue(2).equals(user) && isEqual(2, 8)
				|| returnCellValue(5).equals(user) && isEqual(5, 8)) {
			for (int i = 2; i <= 8; i += 3) {
				if (!isBusy(i + 1)) {
					cell = i;
				}
			}
		}
		if (returnCellValue(0).equals(user) && isEqual(0, 4) && (!isBusy(8 + 1))) {
			cell = 8;
		}
		if (returnCellValue(4).equals(user) && isEqual(4, 8) && (!isBusy(0 + 1))) {
			cell = 0;
		}
		if ((returnCellValue(8).equals(user) && isEqual(8, 0) || returnCellValue(2).equals(user) && isEqual(2, 6))
				&& (!isBusy(4 + 1))) {
			cell = 4;
		}
		if (returnCellValue(2).equals(user) && isEqual(2, 4) && (!isBusy(6 + 1))) {
			cell = 6;
		}
		if (returnCellValue(4).equals(user) && isEqual(4, 6) && (!isBusy(2 + 1))) {
			cell = 2;
		}
		return cell;
	}

	protected boolean isWin(String user) {
		boolean win = false;
		if (returnCellValue(0).equals(user) && isEqual(0, 1) && isEqual(0, 2)
				|| returnCellValue(3).equals(user) && isEqual(3, 4) && isEqual(3, 5)
				|| returnCellValue(6).equals(user) && isEqual(6, 7) && isEqual(6, 8)
				|| returnCellValue(0).equals(user) && isEqual(0, 3) && isEqual(0, 6)
				|| returnCellValue(1).equals(user) && isEqual(1, 4) && isEqual(1, 7)
				|| returnCellValue(2).equals(user) && isEqual(2, 5) && isEqual(2, 8)
				|| returnCellValue(0).equals(user) && isEqual(0, 4) && isEqual(0, 8)
				|| returnCellValue(2).equals(user) && isEqual(2, 4) && isEqual(2, 6)) {
			win = true;
		}
		return win;
	}

	protected String returnCellValue(int index) {
		return field[index].getArrCell()[1];
	}

	protected boolean isEqual(int index1, int index2) {
		return returnCellValue(index1).equals(returnCellValue(index2));
	}

}
