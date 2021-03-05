package miner;

import miner.BankTellerStates.*;

public class BankTeller {
	public State<BankTeller> currentState = SleepingOnTheJob.getInstance();
	public Location location = Location.HOME;
	public Location mLoc = Location.HOME;
	
	public void update(Location l) {
		mLoc = l;
		if (currentState != null)
		{
			currentState.Execute(this);
		}
		
	}
	
	public void changeState(State<BankTeller> newState)
	{
		if (currentState == null || newState == null)
		{
			System.out.println("null change State on id: ");
		}
		
		currentState.Exit(this);
		
		currentState = newState;
		
		currentState.Enter(this);
	}
}
