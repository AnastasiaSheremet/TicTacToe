package tictactoe;

import java.util.Arrays;

import io.ConsoleIO;

public class Cell {
	private String[] arrCell = {" ", " ", " ", "|"};
	
	void printCell() {
		for (int i = 0; i < arrCell.length; i++) {
			ConsoleIO.getInstance().showMessage(arrCell[i]);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arrCell);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (!Arrays.equals(arrCell, other.arrCell))
			return false;
		return true;
	}

	
	public String[] getArrCell() {
		return arrCell;
	}

	public void setArrCell(String[] arrCell) {
		this.arrCell = arrCell;
	}
	
	public void setArrCell0(String cell) {
		this.arrCell[0] = cell;
	}
	
	public void setArrCell1(String cell) {
		this.arrCell[1] = cell;
	}

	@Override
	public String toString() {
		return Arrays.toString(arrCell);
	}

	
	
}
