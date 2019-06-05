import java.awt.*;

/**  Initial attempt at creating some code to get things going for Project 7
  *
  * This code contains 2 classes
  *   Island - the 20x20 area for the Creatures
  *   Beetle - ojects that not quite what is required but give me a good starting point
  */

public class Proj7Slingu2
{
  public static void main (String[] args)
  {
      int delaytime = 500;
      //command line input
       int debug = 0;

        if(args.length > 1 && args[0].equals("-d")){
              delaytime = Integer.parseInt(args[1]);
        }
        System.out.print("\n ---------------------");
        System.out.printf("\n|Delaytime Information: " + delaytime );
        System.out.println("\n ---------------------\n");

    // create my island
    GridDisplay disp = new GridDisplay(20,20);
    World island = new World(disp);

    // create 5 beetles
    for (int i = 0 ; i < 5 ; i++)
    {
      int X1 = (int)(Math.random() * island.getRows() );
      int Y1 = (int)(Math.random() * island.getCols() );
      DoodleBug b = new DoodleBug(island, X1, Y1);
      island.SetCreature( X1, Y1, b);

    }
    //create 100 ants
    for (int i = 0 ; i < 100 ; i++)
    {
      boolean placed = true;
      while(placed){ //recreates them if the specified location is already taken
        int X2 = (int)(Math.random() * island.getRows() );
        int Y2 = (int)(Math.random() * island.getCols() );
          if(island.isOccupied(X2, Y2) == false){
              Ant a = new Ant(island, X2, Y2);
              island.SetCreature( X2, Y2,a);
              placed = false;
          }
      }
    }

    // run simulation for 50 days
    for (int i = 0 ; i < 500 ; i++)
    {
      GridDisplay.mySleep ( delaytime );
      island.nextDay();
    }
    return;
  }
}

class World
{
  private Creature[][] grid;
  private int dayCount;
  private int rows;
  private int cols;
  private GridDisplay disp;



  public World (GridDisplay NewDisp)
  {
    rows = 20;
    cols = 20;
    disp = NewDisp;
    grid = new Creature[rows][cols];

    for (int i = 0 ; i < rows ; i++)
      for (int j = 0 ; j < cols ; j++)
         grid[i][j] = null;



    dayCount = 0;
  }

  public int getRows()
  {
    return rows;
  }

  public int getCols()
  {
    return cols;
  }
  //function to check if the specified location is already occupied
  public boolean isOccupied (int i, int j)
  {
    if (grid[i][j] == null)
      return false;
    else
      return true;
  }

  public void nextDay ()
  {
    dayCount++;
    System.out.println ("DayCount: " + dayCount);

          resetSimulation();		  //THIS IS SO THAT SIMULATION ONLY HAPPENS ONCE ON A CREATURE
          simulate("doodlebug");	//simulates all the doodlebugs first
          simulate("ant");		    //then simulates the ants
  }

	public void resetSimulation()
	{
		for(int i = 0; i < this.getRows(); i++)
		{
			for(int j = 0; j < this.getCols(); j++)
			{
				if(grid[i][j] != null)
					grid[i][j].resetSimulation(); //SETS ALL CREATURE SIMULATION TO FALSE
			}
		}
	}

	public void simulate(String type)
	{
		for(int row = 0; row < this.getRows(); row++)
		{
			for(int col = 0; col < this.getCols(); col++)
			{
         //SIMILATES THE specified creature only if it is not already simulated
				 if(grid[row][col] != null && grid[row][col].GetType().equals(type) && grid[row][col].checkIfMoved() == false)
					    grid[row][col].simulate();
			}
		}
	}

	//return the creature at that specified point in the grid
	public Creature GetCreature(int x, int y)
	{
		if(!IsInGrid(x, y))
        return null;

		return grid[x][y];
	}

	//set the given bug to the specified point in the grid
	public void SetCreature(int x, int y, Creature bug)
	{
		grid[x][y] = bug;
    if((bug.GetType()).equals("doodlebug"))  //if it is a doodlebug
        disp.setColor (x, y, Color.RED);

    else  //if it is an ant
        disp.setColor (x, y, Color.BLUE);
	}

  //set the curent location of the bug to null
  public void SetToNull(int x, int y)
	{
		grid[x][y] = null;
    disp.setColor (x, y, Color.WHITE);
	}

  //checks whether the given point is in the grid or not!
	public boolean IsInGrid(int x, int y)
	{
		if(x >= this.getRows() || x < 0 || y >= this.getCols() || y < 0) return false;

		return true;
	}



}
