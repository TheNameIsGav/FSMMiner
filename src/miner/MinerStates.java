package miner;

public class MinerStates {
	public static class GoHomeAndSleepUntilRested extends State<Miner>{

		private static final GoHomeAndSleepUntilRested instance = new GoHomeAndSleepUntilRested();
		
		public GoHomeAndSleepUntilRested() {}
		
		public static GoHomeAndSleepUntilRested getInstance() {
			return instance;
		}
		
		@Override
		public void Enter(Miner g) {
			g.location = Location.HOME;
			System.out.println("Making my way home");
			
		}

		@Override
		public void Exit(Miner g) {
			System.out.println("What a god darn fantastic nap");
			
		}

		@Override
		public void Execute(Miner g) {
			g.fatigueValue--;
			System.out.println("ZZZZZZ....");
			if(g.fatigueValue <= 0) {
				g.changeState(EnterMineAndDig.getInstance());
			}	
		}
		
	}
	
	public static class EnterMineAndDig extends State<Miner> {

		private static final EnterMineAndDig instance = new EnterMineAndDig();
		
		public EnterMineAndDig() {}
		
		public static EnterMineAndDig getInstance() {
			return instance;
		}
		
		@Override
		public void Enter(Miner g) {
			g.location = Location.MINE;
			System.out.println("On my way to the mine!");
			
		}

		@Override
		public void Exit(Miner g) {
			if(g.pocketsFull()) {
				System.out.println("My pockets are full of gold!");
			}
			if(g.isThirsty()) {
				System.out.println("I've gotten a bit thirsty");
			}
			if(g.isFatigued()) {
				System.out.println("On my home for a phat nap");
			}
			
		}

		@Override
		public void Execute(Miner g) {
			g.goldOnHand++;
			g.fatigueValue++;
			System.out.println("Getting schmoney");
			if(g.pocketsFull()) {
				g.changeState(VisitBankAndDepositGold.getInstance());
				return;
			}
			
			if(g.isFatigued()) {
				g.changeState(GoHomeAndSleepUntilRested.getInstance());
				return;
			}
			
			if(g.isThirsty()) {
				g.changeState(QuenchThirst.getInstance());
				return;
			}
			
		}
		
	}
	
	public static class QuenchThirst extends State<Miner> {

		private static final QuenchThirst instance = new QuenchThirst();
		
		public QuenchThirst() {}
		
		public static QuenchThirst getInstance() {
			return instance;
		}
		
		@Override
		public void Enter(Miner g) {
			g.location = Location.SALOON;
			System.out.println("On my way to the saloon!");
		}

		@Override
		public void Exit(Miner g) {
			System.out.println("Darn, that whiskey was good");
			
		}

		@Override
		public void Execute(Miner g) {
			g.buyAndDrinkWhiskey();
			System.out.println("Drinky Drinky");
			
			if(!g.isThirsty()) {
				g.changeState(EnterMineAndDig.getInstance());
			}
		}
		
	}
	
	public static class VisitBankAndDepositGold extends State<Miner> {

		private static final VisitBankAndDepositGold instance = new VisitBankAndDepositGold();
		
		public VisitBankAndDepositGold() {}
		
		public static VisitBankAndDepositGold getInstance() {
			return instance;
		}
		
		@Override
		public void Enter(Miner g) {
			g.location = Location.BANK;
			System.out.println("Heading to the bank!");
			
		}

		@Override
		public void Exit(Miner g) {
			System.out.println("My wealth is secured");
			
		}

		@Override
		public void Execute(Miner g) {
			g.addToWealth(g.goldOnHand);
			g.goldOnHand = 0;
			System.out.println("Dropped "  + g.moneyInBank + " gold into my 401k");
			g.changeState(EnterMineAndDig.getInstance());	
		}
	}
}
