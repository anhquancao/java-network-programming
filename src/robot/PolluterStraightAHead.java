package robot;

public class PolluterStraightAHead extends RobotPollution {

	public PolluterStraightAHead(World world, int colDepart) {
		super(world);
		this.setCol(colDepart);
	}

	@Override
	public void travel() {
		World world = this.getWorld();
		for (int row = this.getRow(); row < world.getRow(); row++) {
			this.moveTo(row, this.getCol());
			this.pollute();
		}
		this.setWorld(world);
	}
}
