package tictactoe;

import io.ConsoleIO;

public class UserGameMode extends Game {

	private static ConsoleIO io = ConsoleIO.getInstance();

	@Override
	protected void createUsers() {
		for (int i = 0; i < users.length; i++) {
			io.getInstance().showMessage("User " + (i + 1) + " ");
			users[i] = new User();
			if (users[i].getName().equals("")) {
				users[i].setName("Player_" + (i + 1));
				io.showMessage("Your name is " + users[i].getName() + "\n\n");
			}
		}
		int count = 0;
		while (users[0].getName().equals(users[1].getName()) || users[1].getName().equals("")) {
			String name = io.inputString("This name has already existed. Please, choose another name.");
			users[1].setName(name);
			count++;
			if (count == 1) {
				users[1].setName(users[0].getName() + "_Second");
				io.showMessage("Your name is " + users[1].getName() + "\n\n");
			}
		}
	}

	protected void playTwoPlayersMode() {
		int count = 0;
		createUsers();
		initField();
		printField();
		while (count < Math.pow(DIMENSION, 2)) {
			selectCoordinate((count % 2 == 0) ? USER_1 : USER_2,
					(count % 2 == 0) ? users[0].getName() : users[1].getName());
			printField();
			if (isWin((count % 2 == 0) ? USER_1 : USER_2)) {
				io.showMessage(
						"\nUSER " + ((count % 2 == 0) ? users[0].getName() : users[1].getName()) + " HAS WON!!!\n");
				printField();
				break;
			}
			count++;
		}
		if (!isWin(USER_1) && !isWin(USER_2)) {
			io.showMessage("\nDRAW!!!\n\n");
		}
	}
}