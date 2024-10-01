package Ei_MarsRover;
import java.util.*;

//Command Interface
interface Command{
	public void execute();
}

abstract class Direction{
	abstract Direction L();
	abstract Direction R();
	abstract Position M(Position position);
	public Direction getDir(String str) {
		switch(str) {
		case "N": return new North();
		case "S": return new South();
		case "E": return new East();
		case "W": return new West();
		}
		return null;
		
	}
}

//Position class
class Position{
	int x;
	int y;
	
	public Position(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	
	public int getY() { return y; }
	
	public String getPos() {
		return "("+x+","+y+")";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Position position = (Position) obj;
	    return x == position.x && y == position.y;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(x, y);
	}

}

//North
class North extends Direction{

	@Override
	Direction L() {
		return new West();
	}

	@Override
	Direction R() {
		return new East();
	}

	@Override
	Position M(Position position) {
		return new Position(position.getX(),position.getY()+1);
	}
	
	public String toString() {
		return "North";
	}
	
}

//South
class South extends Direction{

	@Override
	Direction R() {
		return new West();
	}

	@Override
	Direction L() {
		return new East();
	}

	@Override
	Position M(Position position) {
		return new Position(position.getX(),position.getY()-1);
	}
	
	public String toString() {
		return "South";
	}
	
}
//East
class East extends Direction{

	@Override
	Direction L() {
		return new North();
	}

	@Override
	Direction R() {
		return new South();
	}

	@Override
	Position M(Position position) {
		return new Position(position.getX()+1,position.getY());
	}
	
	public String toString() {
		return "East";
	}
	
}

//West
class West extends Direction{

	@Override
	Direction L() {
		return new South();
	}

	@Override
	Direction R() {
		return new North();
	}

	@Override
	Position M(Position position) {
		return new Position(position.getX()-1,position.getY());
	}
	
	public String toString() {
		return "West";
	}
	
}

class Grid{
	private int x;
	private int y;
	private Set<Position> obstacles;
	
	public Grid(int x, int y, Set<Position> obstacles) {
		this.x = x;
		this.y = y;
		this.obstacles = obstacles;
	}
	
	public boolean withinBounds(Position position) {
		return position.getX() >= 0 && position.getX() < y && position.getY() >=0 && position.getY() < x;
	}
	
	public boolean isObstacle(Position p) {
		return obstacles.contains(p);
	}
}

public class Rover {
	public Position p;
	public Direction d;
	public Grid g;
	
	public Rover(Position p, Direction d, Grid g) {
		this.p = p;
		this.d = d;
		this.g = g;
	}
	
	public void M() {
		Position newP = d.M(p);
		if(!g.isObstacle(newP)&&g.withinBounds(newP)) {
			p = newP;
		} else {
			System.out.println("Obstacle/Boundary Detected. Cannot move further");
		}
	}
	
	public void L() {
		d = d.L();
	}
	
	public void R() {
		d = d.R();
	}
	
	public String getString() {
		return "Rover is at " + p.getPos() + " facing " + d.toString() + ".";
	}
}

class Move implements Command{
	private Rover rover;
	
	public Move(Rover rover) {
		this.rover = rover;
	}
	public void execute() {
		rover.M();
	}	
}

class LeftTurn implements Command{
	private Rover rover;
	
	public LeftTurn(Rover rover) {
		this.rover = rover;
	}
	public void execute() {
		rover.L();
	}	
}

class RightTurn implements Command{
	private Rover rover;
	
	public RightTurn(Rover rover) {
		this.rover = rover;
	}
	public void execute() {
		rover.R();
	}	
}

class CommandInterface {
	public List<Command> commands;
	
	public CommandInterface(List<Command> commands) {
		this.commands = commands;
	}
	
	public void executeCommands() {
		for (Command c: commands) {
			c.execute();
		}
	}
}

class Main{
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		//Initializing grid
		System.out.println("Enter grid height: ");
		int h = scanner.nextInt();
		System.out.println("Enter grid width: ");
		int w = scanner.nextInt();
		
		Set<Position> obstacles = new HashSet<>();
		//Adding Obstacles
		System.out.println("Enter number of obstacles: ");
		int noObstacles = scanner.nextInt();
		for (int i=0;i<noObstacles;i++) {
			System.out.println("Enter obstacle: ");
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			obstacles.add(new Position(x,y));
		}
		//Grid instance
		Grid grid = new Grid(h,w,obstacles);
		
		//Starting point + Direction
		System.out.println("Enter starting X: ");
		int startX = scanner.nextInt();
		System.out.println("Enter starting Y: ");
		int startY = scanner.nextInt();
		Position start = new Position(startX, startY); //Starting position
		scanner.nextLine();
		System.out.println("Enter starting direction: ");
		
		String dir = scanner.nextLine();
		Direction direction = new North(); //Assuming North is the default
		direction = direction.getDir(dir);
		
		//Initialized rover
		Rover rover = new Rover(start, direction, grid);
		
		//Getting Commands
		System.out.println("Enter commands in one string:");
		String c_in = scanner.nextLine();
		List<Command> commands = new ArrayList<>();
		for (char i: c_in.toCharArray()) {
			switch (i) {
			case 'M': commands.add(new Move(rover)); break;
			case 'L': commands.add(new LeftTurn(rover)); break;
			case 'R': commands.add(new RightTurn(rover)); break;
			}
		}
		
		CommandInterface controller = new CommandInterface(commands);
		controller.executeCommands();
		
		System.out.println(rover.getString());
	}
}
