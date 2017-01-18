package robot;

public class RobotCleanerDistracted extends RobotCleaner {

	private boolean shouldClean = false;

	public RobotCleanerDistracted(World world) {
		super(world);
	}

	@Override
	public void clean() {
		if (shouldClean) {
			super.clean();
		}
		shouldClean = !shouldClean;
	}

}
