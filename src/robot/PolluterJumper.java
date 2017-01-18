package robot;

public class PolluterJumper extends RobotPollution {
	private int deltaX;

	public PolluterJumper(World world, int deltaX) {
		super(world);
		this.deltaX = deltaX;
	}

	@Override
	public void travel() {
		World world = this.getWorld();
		int count = 1;
		for (int row = this.getRow(); row < world.getRow(); row++) {
			int col = (count * deltaX) % world.getCol();
			this.moveTo(row, col);
			this.pollute();
			count += 1;
		}
		this.setWorld(world);
	}

}
