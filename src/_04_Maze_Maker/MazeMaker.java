package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int x = randGen.nextInt(w);
		int y = randGen.nextInt(h);

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(x, y));

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);

		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> neighbors = getUnvisitedNeighbors(currentCell);

		// C. if has unvisited neighbors,
		if (neighbors.size() != 0) {

			// C1. select one at random.
			int rnd = randGen.nextInt(neighbors.size());
			System.out.println("random" + rnd);
			Cell c = neighbors.get(rnd);

			// C2. push it to the stack
			uncheckedCells.push(c);

			// C3. remove the wall between the two cells
			removeWalls(currentCell, c);

			// C4. make the new cell the current cell and mark it as visited
			currentCell = c;

			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {

				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();

				// D1b. make that the current cell

				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX() + 1) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		} else if (c1.getX() == c2.getX() - 1) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		} else if (c1.getY() == c2.getY() + 1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		} else if (c1.getY() == c2.getY() - 1) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		if (c.getX() != 0 && !maze.getCell(c.getX() - 1, c.getY()).hasBeenVisited())
			cells.add(maze.getCell(c.getX() - 1, c.getY()));
		if (c.getX() != maze.getWidth() - 1 && !maze.getCell(c.getX() + 1, c.getY()).hasBeenVisited())
			cells.add(maze.getCell(c.getX() + 1, c.getY()));
		if (c.getY() != 0 && !maze.getCell(c.getX(), c.getY() - 1).hasBeenVisited())
			cells.add(maze.getCell(c.getX(), c.getY() - 1));
		if (c.getY() != maze.getHeight() - 1 && !maze.getCell(c.getX(), c.getY() + 1).hasBeenVisited())
			cells.add(maze.getCell(c.getX(), c.getY() + 1));
		return cells;
	}
}
