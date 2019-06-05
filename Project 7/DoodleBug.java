public class DoodleBug extends Creature
{
	private int starveCount = 0; //keep track of starveCount

	private static final int Breeding_Time = 8;

	//call the super class with this world
	public DoodleBug(World world, int x, int y )
	{
		super(world, x, y, Breeding_Time);
	}
	//returns a string representation of the doodlebug
	public String GetType()
	{
		return "doodlebug";
	}

	//checks if the doodlebug can eat
	protected boolean checkEat()
	{
		//if an ant is eaten don't move
		if     (eatAnt(x + 1, y))     return false;
		else if(eatAnt(x - 1, y))     return false;
		else if(eatAnt(x - 1, y - 1)) return false;
		else if(eatAnt(x - 1, y + 1)) return false;
		else if(eatAnt(x, y + 1))     return false;
		else if(eatAnt(x, y - 1))     return false;
		else if(eatAnt(x + 1, y - 1)) return false;
		else if(eatAnt(x + 1, y + 1)) return false;

		starveCount++;  //if doodlebug hasnt eaten then increment the starveCount
		return true;
	}

	//check if the doodlebug is starving
	protected void checkStarve()
	{
		if(starveCount == 3)
				world.SetToNull(x, y);
	}

	//reset the starveCount
	private void setStarveCount()
	{
		this.starveCount = 0;
	}

	//eat the eat in the specified coordinates
	private boolean eatAnt(int NewX, int NewY)
	{
		Creature NewC = world.GetCreature(NewX,NewY);
		if(world.IsInGrid(NewX, NewY) && NewC != null && NewC.GetType() == "ant")
		{
			starveCount = 0;
			world.SetToNull(x, y);
			world.SetToNull(NewX, NewY);
			world.SetCreature(NewX, NewY, this);
			this.x = NewX;
			this.y = NewY;
			return true;
		}

		return false;  //if it cannot be eaten, then return false
	}


//create a new DoodleBug in the specified location
	protected void Multiply(int newX, int newY)
	{
		DoodleBug new1 = new DoodleBug(world, newX, newY);
		new1.setStarveCount();
		world.SetCreature(newX, newY, new1 );
		return;
	}



}
