import java.awt.*;

public abstract class Creature
{
	protected World world;
	protected int x;
	protected int y;
	protected boolean moved = true;
	protected int steps_moved = 0;
	protected int Breeding_Time;


	public Creature(World world, int x, int y, int Breeding_Time)
	{//set the new creature to this location
		this.world = world;
		this.x = x;
		this.y = y;
		this.Breeding_Time = Breeding_Time;
		moved = true;
	}

	public abstract String GetType();

	protected abstract boolean checkEat();

	protected abstract void Multiply(int newX, int newY);

	protected abstract void checkStarve();

	//check if the creaturn can move in the adjacent locations
	public void Move()
	{

    int newX = -1;
    int newY = -1;
    int check_move = (int)(Math.random() * 8); //get a random integer between 0 and 8
    if (check_move == 0) // attempt to move up
    {
      newX = x; newY = y-1;
    }
    if (check_move == 1) // attempt to move down
    {
      newX = x; newY = y+1;
    }
    if (check_move == 2) // attempt to move left
    {
      newX = x-1; newY = y;
    }
    if (check_move == 3) // attempt to move right
    {
      newX = x+1; newY = y;
    }
		if (check_move == 4) // attempt to move
    {
      newX = x+1; newY = y-1;
    }
		if (check_move == 5) // attempt to move
    {
      newX = x+1; newY = y+1;
    }
		if (check_move == 6) // attempt to move
    {
      newX = x-1; newY = y+1;
    }
		if (check_move == 7) // attempt to move
    {
      newX = x-11; newY = y-1;
    }

		//check space on grid
		if(world.IsInGrid(newX, newY) && world.GetCreature(newX, newY) == null)
		{
			world.SetCreature(newX, newY, this);
			world.SetToNull(x, y);
			x = newX;
			y = newY;
		}

		return;
	}

	//check if the creature can breed and if it can, call the multiply funciton with specified coordinates
	protected void Breed()
	{
		if(world.IsInGrid(x + 1, y) && world.GetCreature(x + 1, y) == null)
		{
			Multiply(x + 1, y);
			return;
		}
		else if(world.IsInGrid(x - 1, y) && world.GetCreature(x - 1, y) == null)
		{
			Multiply(x - 1, y);
			return;
		}
		else if(world.IsInGrid(x, y + 1) && world.GetCreature(x, y + 1) == null)
		{
			Multiply(x, y + 1);
			return;
		}
		else if(world.IsInGrid(x, y - 1) && world.GetCreature(x, y - 1) == null)
		{
			Multiply(x, y - 1);
			return;
		}
		else if(world.IsInGrid(x + 1, y - 1) && world.GetCreature(x + 1, y - 1) == null)
		{
			Multiply(x + 1, y - 1);
			return;
		}
		else if(world.IsInGrid(x + 1, y + 1) && world.GetCreature(x + 1, y + 1) == null)
		{
			Multiply(x + 1, y + 1);
			return;
		}
		else if(world.IsInGrid(x - 1, y - 1) && world.GetCreature(x - 1, y - 1) == null)
		{
			Multiply(x - 1, y - 1);
			return;
		}
		else if(world.IsInGrid(x - 1, y + 1) && world.GetCreature(x - 1, y + 1) == null)
		{
			Multiply(x - 1, y + 1);
			return;
		}
		//if no spots are available
		steps_moved--;
		//it will breed next time
		return;

	}


	public void simulate()
	{
		//don't simulate twice in a round
		if(moved == true) return;
		else moved = true;

		steps_moved++;


		if(checkEat()) //if it can eat an ant, then it shouldnt move
		{
			Move();  //if no ant is eaten, then it should move
		}

		if(steps_moved != 0 && steps_moved % Breeding_Time == 0)
		{
			Breed();  //breed
		}

		checkStarve(); //check if it can starve

		return;
	}

	//indicate that the Creature hasn't moved this round
	public void resetSimulation()
	{
		moved = false;
	}
	//return if the creature has moved or not
	public boolean checkIfMoved ()
	{
		return moved;
	}


}
