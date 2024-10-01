package EIDocs;

public class FactoryDesignPattern {
	//Factory that gives different notification
	public static abstract class Notification{
		public abstract void showNotif();
	}
	
	public static class Email extends Notification{

		@Override
		public void showNotif() {
			System.out.println("Email recieved");
		}
		
	}
	
	public static class SMS extends Notification{

		@Override
		public void showNotif() {
			// TODO Auto-generated method stub
			System.out.println("SMS Recieved");
		}
		
	}
	
	public static class WhatsApp extends Notification{

		@Override
		public void showNotif() {
			// TODO Auto-generated method stub
			System.out.println("WhatsApp Recieved");
		}
		
	}
	
	public static class Client{
		private Notification notif;
		
		public Client(int type) {
			if (type==1) {
				notif = new Email();
			}
			else if (type==2) {
				notif = new SMS();
			}
			else if (type==3) {
				notif = new WhatsApp();
			}	
		}
		
		public Notification getNotif() {
			return notif;
		}
	}
	
	public class Main{
		public static void main(String args[]) {
			for(int i=1;i<4;i++) {
				Client msg1 = new Client(i);
				Notification ping = msg1.getNotif();
				ping.showNotif();
			}
			
			
		}
	}
}
