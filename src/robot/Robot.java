package robot;

import java.util.Random;

public abstract class Robot {
	private int col;
	private int row;
	private World world;
	private static Random random = new Random();

	public Robot() {
	}

	public Robot(int row, int col, World world) {
		this.col = col;
		this.row = row;
		this.world = world;
	}

	public Robot(World world) {
		this(Robot.random.nextInt(world.getRow()), Robot.random.nextInt(world.getCol()), world);
	}

	public void moveTo(int row, int col) {
		this.row = row;
		this.col = col;
		// System.out.println("Robot moved to row " + this.row + " and column "
		// + this.col);
	}

	abstract public void travel();

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
