package EIDocs;

//ChainOfResponsibilites
//Behavorial Pt1
public class ChainOfResponsibilities {

    // Main Handler
    public static abstract class Handler {

        protected Handler handler;

        public void nextHandler(Handler handler) {
            this.handler = handler;
        }

        public abstract void Request(Order order);
    }

    public static class Order {
        private boolean payment;
        private boolean address;
        private boolean inventory;

        public Order(boolean payment, boolean address, boolean inventory) {
            this.payment = payment;
            this.address = address;
            this.inventory = inventory;  
        }

        public boolean PaymentCheck() {
            return payment;
        }

        public boolean InventoryCheck() {
            return inventory;
        }

        public boolean AddressCheck() {
            return address;
        }
    }

    //Concrete Handlers

    public static class InventoryHandler extends Handler {

        public void Request(Order order) {
            if (order.InventoryCheck()) {
                System.out.println("Inventory Available");
                if (handler != null) {
                    handler.Request(order);
                }
            } else {
                System.out.println("Items not available");
            }
        }
    }

    public static class AddressHandler extends Handler {

        public void Request(Order order) {
            if (order.AddressCheck()) {
                System.out.println("Address Valid");
                if (handler != null) {
                    handler.Request(order);
                }
            } else {
                System.out.println("Enter valid address");
            }
        }
    }

    public static class PaymentHandler extends Handler {

        public void Request(Order order) {
            if (order.PaymentCheck()) {
                System.out.println("Payment Successful");
            } else {
                System.out.println("Payment Failed");
            }
        }
    }

    // Main class
    public static class Main {
        public static void main(String[] args) {

            Order order1 = new Order(true, true, true);
            Order order2 = new Order(true, false, true);
            Order order3 = new Order(false,false,false);

            Handler inventory = new InventoryHandler();
            Handler address = new AddressHandler();
            Handler payment = new PaymentHandler();

            inventory.nextHandler(address);
            address.nextHandler(payment);

            inventory.Request(order1);
            System.out.println();
            inventory.Request(order2);
            System.out.println();
            inventory.Request(order3);
        }
    }
}
