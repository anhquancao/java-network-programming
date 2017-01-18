package robot;

public abstract class RobotPollution extends Robot {
	
	public RobotPollution(World world) {
		super(world);
	}

	public void pollute() {
		World world = this.getWorld();
		world.placeDirtyPaper(this.getRow(), this.getCol());
		this.setWorld(world);
	}

}
