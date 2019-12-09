public class Player {
	public String name;
	public int balance = 0;
	
	public Player (String name)
	{
		this.name = name;
	}
	
	public void deposit(int amount) {
		balance += amount;
	}
	
	public void bankrupt() {
		balance = 0;
	}
	
	public int getBalance()
	{
		return balance;
	}
	
	public String getName() {
		return name;
	}
}
