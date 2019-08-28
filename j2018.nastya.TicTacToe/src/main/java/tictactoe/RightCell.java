package tictactoe;

import java.util.Arrays;

import io.ConsoleIO;

public class RightCell extends Cell {
	private String[] arrCell = { " ", " ", " ", " " };

	protected void printCell() {
		for (int i = 0; i < arrCell.length; i++) {
			ConsoleIO.getInstance().showMessage(arrCell[i]);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(arrCell);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RightCell other = (RightCell) obj;
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

}