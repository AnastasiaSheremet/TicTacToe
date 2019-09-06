package tictactoe;

public class ComputerGameMode extends Game {

	@Override
	protected void createUsers() {
		users[0] = new User();
		if (users[0].getName().equals("") || users[0].getName().equals("Computer")) {
//				String name = io.getInstance().inputString("Please, enter any name different from \"Computer\".\n ");
			users[0].setName("Default");
		}
		users[1] = new User("Computer");
	}

	void playWithComputerMode() {
		int count = 0;
		createUsers();
		initField();
		printField();
		while (count < Math.pow(DIMENSION, 2) - 1) {
			selectCoordinate(USER_1, users[0].getName());
			printField();
			if (isWin(USER_1)) {
				io.getInstance().showMessage("\nUSER " + users[0].getName() + " HAS WON!!!\n");
				printField();
				break;
			}
			count++;
			io.getInstance().showMessage("\nComputer's turn\n");
			if (count == 1) {
				selectFirstStep(USER_2);
			} else {
				if (checkWin(USER_1) != -1 && checkWin(USER_2) == -1) {
					field[checkWin(USER_1)].setArrCell0(" ");
					field[checkWin(USER_1)].setArrCell1(USER_2);
				} else if ((checkWin(USER_1) == -1 && checkWin(USER_2) != -1)
						&& (checkWin(USER_1) != -1 && checkWin(USER_2) != -1)) {
					field[checkWin(USER_2)].setArrCell0(" ");
					field[checkWin(USER_2)].setArrCell1(USER_2);
				} else {
					selectFirstStep(USER_2);
				}
			}
			printField();
			if (isWin(USER_2)) {
				io.getInstance().showMessage("\nUSER " + users[1].getName() + " HAS WON!!!\n");
				printField();
				break;
			}
			count++;
		}
		if (!isWin(USER_1) && !isWin(USER_2)) {
			io.getInstance().showMessage("\nDRAW!!!\n\n");
		}
	}
}
