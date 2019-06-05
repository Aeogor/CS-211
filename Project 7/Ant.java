//Ant class
public class Ant extends Creature
{
	//keep track of the Breeding_Time
	private static final int Breeding_Time = 3;

	public Ant(World world, int x, int y)
	{
		super(world, x, y, Breeding_Time);
	}

	//returns a string representation of the ant
	public String GetType()
	{
		return "ant";
	}
  //never eats if position is available so return true always
	protected boolean checkEat()
	{
		return true;
	}
  //set a new creature if Multiply is called
	protected void Multiply(int newX, int newY)
	{
		world.SetCreature(newX, newY, new Ant(world, newX, newY));
		return;
	}
  //ants never starve, so return
	protected void checkStarve()
	{
		return;
	}


}
