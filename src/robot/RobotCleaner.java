package robot;

public class RobotCleaner extends Robot {

	public RobotCleaner(World world) {
		super(world);
	}

	public void clean() {
		World world = this.getWorld();
		world.takeDirtyPaper(this.getRow(), this.getCol());
		this.setWorld(world);
	}

	@Override
	public void travel() {
		World world = this.getWorld();
		int col = this.getCol();
		for (int row = this.getRow(); row < world.getRow(); row++) {
			for (; col < world.getCol(); col++) {
				this.moveTo(row, col);
				this.clean();
			}
			col = 0;
		}
		this.setWorld(world);
	}

}
