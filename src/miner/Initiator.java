package miner;

public class Initiator {

	public static void main(String[] args) throws InterruptedException {
		Initiator i = new Initiator();
		i.run();
	}
	
	private void run() throws InterruptedException
	{
		Miner m = new Miner();
		BankTeller b = new BankTeller();
		for (int i = 0; i < 20; i++)
		{
			Thread.sleep(1000);
			m.update();
			b.update(m.location);
		}
		
	}

}
