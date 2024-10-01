package EIDocs;
import java.util.*;

//ObserverDesign
//BehavorialPt2
public class ObserverDesignPatterns {
	public interface Observer{
		public void update(String Item, int Price);
	}
	
	public static interface Subject{
		public void addObserver(Observer observer);
		public void removeObserver(Observer observer);
		public void notifyObserver();
	}
	
	public static class Bidder implements Observer{
		private String Bidder;
		
		public Bidder(String name) {
			this.Bidder = name;
		}
		
		@Override
		public void update(String item, int Price) {
			System.out.println(item + " price is now: " + Price);
		}
		
		public String getBidder() {
			return Bidder;
		}
	}
	
	public static class Item implements Subject{
		private List<Observer> bidders = new ArrayList<> ();
		private String itemName;
		private int highestBid;
		private Observer highestBidder;
		
		//Item name and Starting Bid
		public Item(String item, int starting) {
			this.itemName = item;
			this.highestBid = starting;
		}
		
		public void placeBid(int Price, Bidder bidder) {
			if (Price > highestBid) {
				highestBid = Price;
				highestBidder = bidder;
				System.out.println(bidder.getBidder() + "has the highest bid of " + Price);
				notifyObserver();
			}
			else {
				System.out.println("Bid is less than or equal to highest bid");
				notifyObserver();
			}
		}

		@Override
		public void addObserver(Observer observer) {
			bidders.add(observer);
		}

		@Override
		public void removeObserver(Observer observer) {
			bidders.remove(observer);
		}

		@Override
		public void notifyObserver() {
			for (Observer bidder: bidders) {
				bidder.update(itemName, highestBid);
			}
		}
		
		public String getItem() {
			return itemName;
		}
		
		public int getHighBid() {
			return highestBid;
		}
		
		public Observer getHighBidder() {
			return highestBidder;
		}
		
	}
	
	public class Main{
		public static void main(String[] args) {
			Item item1 = new Item("Guitar",20000);
			
			Bidder bid1 = new Bidder("John ");
			Bidder bid2 = new Bidder("Paul ");
			Bidder bid3 = new Bidder("Ringo" );
			Bidder bid4 = new Bidder("George ");
			
			item1.addObserver(bid1);
			item1.addObserver(bid2);
			item1.addObserver(bid3);
			item1.addObserver(bid4);
			
			item1.placeBid(20100, bid4);
			System.out.println();
			item1.placeBid(20500, bid2);
			System.out.println();
			item1.placeBid(21000, bid1);
			System.out.println();
			item1.placeBid(20200, bid3);
			
		}
	}
}
