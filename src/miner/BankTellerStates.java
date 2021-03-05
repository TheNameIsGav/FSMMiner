package miner;

public class BankTellerStates {
	public static class SleepingOnTheJob extends State<BankTeller>{
		private static final SleepingOnTheJob instance = new SleepingOnTheJob();
		
		public SleepingOnTheJob() {}
		
		public static SleepingOnTheJob getInstance() {
			return instance;
		}
		@Override
		public void Enter(BankTeller g) {
			System.out.println("In the bedroom about to sleep");
			
		}

		@Override
		public void Exit(BankTeller g) {
			System.out.println("Time to wake up and help a customer!");
		}

		@Override
		public void Execute(BankTeller g) {
			System.out.println("Teller sleeping");
			if(g.mLoc == Location.BANK) {
				g.changeState(HelpingMiner.getInstance());
			}
		}
	}
	
	public static class HelpingMiner extends State<BankTeller>{
		private static final HelpingMiner instance = new HelpingMiner();
		
		public HelpingMiner() {}
		
		public static HelpingMiner getInstance() {
			return instance;
		}
		@Override
		public void Enter(BankTeller g) {
			System.out.println("Beginning to help a customer");
			
		}

		@Override
		public void Exit(BankTeller g) {
			System.out.println("Going to the back to sleep");
			
		}

		@Override
		public void Execute(BankTeller g) {
			if(g.mLoc == Location.BANK) {
				System.out.println("Teller Helping Someone");
			} else {
				g.changeState(SleepingOnTheJob.getInstance());
			}
		}
	}
}
