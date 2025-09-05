package ObserverPattern;
import java.util.ArrayList;
import DataModels.Order;

public class OrderProcessor {
    private ArrayList<OrderObserver> observers = new ArrayList<>();
    
    public void register(OrderObserver observer) {
        observers.add(observer);
    }
    public void unregister(OrderObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Order order){
        for(OrderObserver observer : observers){
            observer.update(order);
        }
    }

    public void processOrder(Order order) {
        System.out.println("Process Order" + order.orderId());
        // ... ตรรกะการประมวลผลคำสั่งอื่นๆ ...
        System.out.println("Order processed successfully.");
        // แจ้งเตือนผู้ติดตามทั้งหมด
        notifyObservers(order);
    }
}