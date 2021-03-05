package miner;

import miner.MinerStates.GoHomeAndSleepUntilRested;

public class Miner extends GameEntity
{
	public State<Miner> currentState = GoHomeAndSleepUntilRested.getInstance();
	public Location location = Location.HOME;
	public int goldOnHand = 0;
	public int moneyInBank = 0;
	public int thirstValue = 0;
	public int fatigueValue = 0;
	
	@Override
	public void update() {
		this.thirstValue++;
		if (currentState != null)
		{
			currentState.Execute(this);
		}
		
	}
	
	public void changeState(State<Miner> newState)
	{
		if (currentState == null || newState == null)
		{
			System.out.println("null change State on id: " + this.getID());
		}
		
		currentState.Exit(this);
		
		currentState = newState;
		
		currentState.Enter(this);
	}

	public boolean pocketsFull()
	{
		if (goldOnHand > 3)
			return true;
		return false;
	}
	
	public boolean isFatigued()
	{
		if (fatigueValue > 5)
			return true;
		return false;
	}
	
	public boolean isThirsty()
	{
		if (thirstValue > 5)
			return true;
		return false;
	}
	
	public void addToWealth(int val)
	{
		this.moneyInBank += val;
		if (this.moneyInBank < 0)
			this.moneyInBank = 0;
	}
	
	public void buyAndDrinkWhiskey()
	{
		this.moneyInBank -= 2;
		this.thirstValue = 0;
	}
}