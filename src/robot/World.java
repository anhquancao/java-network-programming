package robot;

import java.util.Arrays;

public class World {
	private int row;
	private int col;
	boolean[][] matrix;

	public World() {
		this(10, 10);
	}

	public World(int row, int col) {
//		System.out.println("New world with " + row + " rows and " + col + " cols is created.");
		this.row = row;
		this.col = col;
		this.matrix = new boolean[this.row][this.col];
	}

	void placeDirtyPaper(int row, int col) {
//		System.out.println("Place dirty paper at row " + row + " and column " + col);
		this.matrix[row][col] = true;
	}

	void takeDirtyPaper(int row, int col) {
//		System.out.println("Take dirty paper at row " + row + " and column " + col);
		this.matrix[row][col] = false;
	}

	boolean isDirty(int row, int col) {
//		System.out.println("Row " + row + " column " + col + " is dirty: " + this.matrix[row][col]);
		return this.matrix[row][col];
	}

	@Override
	public String toString() {
		String returnStr = "";
		for (int r = 0; r < this.row; r++) {
			for (int c = 0; c < this.col; c++) {
				returnStr += this.matrix[r][c] ? "o " : ". ";
			}
			returnStr += "\n";
		}
		return returnStr;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(boolean[][] matrix) {
		this.matrix = matrix;
	}
	
	

}
