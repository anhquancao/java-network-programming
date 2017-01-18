package robot;

public class TestRobot {
	
	public void testWorld(World world) {

		System.out.println(world);
		world.isDirty(2, 3);

		world.placeDirtyPaper(2, 3);
		System.out.println(world);
		System.out.println(world.isDirty(2, 3));

		world.takeDirtyPaper(2, 3);
		System.out.println(world);
		world.isDirty(2, 3);
	}

	public void testPolluteAllRight(World world) {
		PolluterStraightAHead polluterStraightAHead = new PolluterStraightAHead(world, 2);
		polluterStraightAHead.travel();
		System.out.println(world);
	}

	public void testPolluterJumper(World world) {
		PolluterJumper polluterJumper = new PolluterJumper(world, 2);
		polluterJumper.travel();
		System.out.println(world);
	}

	public void testRobotCleaner(World world) {
		System.out.println(world);
		RobotCleaner robotCleaner = new RobotCleaner(world);
		robotCleaner.travel();
		System.out.println(world);
	}

	public void testRobotCleanerDistracted(World world) {
		System.out.println(world);
		RobotCleanerDistracted robotCleanerDistracted = new RobotCleanerDistracted(world);
		robotCleanerDistracted.travel();
		System.out.println(world);
	}

	public static void main(String[] args) {
		World world = new World();

		TestRobot testRobot = new TestRobot();
		// testRobot.testWorld(world);
		testRobot.testPolluteAllRight(world);
		// testRobot.testPolluterJumper(world);

		World pollutedWorld = new World();
		for (int row = 0; row < pollutedWorld.getRow(); row++) {
			for (int col = 0; col < pollutedWorld.getCol(); col++) {
				pollutedWorld.placeDirtyPaper(row, col);
			}
		}
		// testRobot.testRobotCleaner(pollutedWorld);
		// testRobot.testRobotCleanerDistracted(pollutedWorld);

	}
}
