package EIDocs;

public class ProxyDesignPattern {
	//Structural Design Pattern
	interface BankAccount{
		public void withdraw(int m);
		public int showBal();
	}
	
	//Actual Bank
	public static class Bank implements BankAccount{
		int money;
		
		public Bank(int money) {
			this.money = money;
		}
		
		public void withdraw(int m) {
			if(money < m) {
				System.out.println("Insufficient Funds");
			}
			else {
				money -= m;
				System.out.println("Withdrawal succesful");
			}
		}
		
		public int showBal() {
			return money;
		}
	}
	
	//Card Proxy
	public static class Card implements BankAccount{
		private Bank acc;
		
		public Card(Bank b) {
			this.acc = b;
		}

		@Override
		public void withdraw(int m) {
			// TODO Auto-generated method stub
			System.out.println("Using card: ");
			acc.withdraw(m);
			
		}

		@Override
		public int showBal() {
			// TODO Auto-generated method stub
			return acc.showBal();
		}
		
		
	}
	
	//Cheque Proxy
	public static class Cheque implements BankAccount{
		private Bank acc;
		
		public Cheque(Bank bank) {
			this.acc = bank;
		}
		@Override
		public void withdraw(int m) {
			// TODO Auto-generated method stub
			System.out.println("Withdrawing using cheque...");
			acc.withdraw(m);
		}

		@Override
		public int showBal() {
			// TODO Auto-generated method stub
			return acc.showBal();
		}
		
	}
	
	public static class Main{
		public static void main(String args[]) {
			Bank bankAcc = new Bank(1000);
			
			Card card = new Card(bankAcc);
			
			Cheque cheque = new Cheque(bankAcc);
			
			//Withdraw from bank
			bankAcc.withdraw(300);
			System.out.println("Balance: " + bankAcc.showBal());
			
			card.withdraw(500);
			System.out.println("Balance: " + card.showBal());
			
			cheque.withdraw(100);
			System.out.println("Balance: " + cheque.showBal());
		}
	}
}
