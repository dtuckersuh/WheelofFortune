import javax.swing.JLabel;

public class Player {
	public String name;
	public int balance = 0;
	private JLabel playerPanel;

	public Player(String name) {
		this.name = name;
	}

	public void addLabel(JLabel playerPanel) {
		this.playerPanel = playerPanel;
	}

	public void deposit(int amount) {
		balance += amount;
		playerPanel.setText("$" + balance);
	}
	
	public void withdraw(int amount) {
		balance -= amount;
		playerPanel.setText("$" + balance);
	}

	public void bankrupt() {
		balance = 0;
	}

	public int getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}
}
