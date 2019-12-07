public class Player {
	public String name;
	public int balance = 0;
	
	public Player (String name)
	{
		this.name = name;
	}
	
	public int getBalance()
	{
		return balance;
	}
	
	public void deposit(int amount) {
		balance += amount;
	}
	
	public void bankrupt() {
		balance = 0;
	}
}
