import java.util.List;
import DataModels.*;
import DecoratorPattern.*;
import FactoryMethodPattern.*;
import ObserverPattern.*;
import StrategyPattern.*;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("--- E-commerce System Simulation ---");
        
        // ====== 1. Setup ======
        Product labtop = new Product("P001", "Laptop", 30000.0);
        Product mouse = new Product("P002", "Mouse", 800.0);
        Order myOrder = new Order("ORD-001",List.of(labtop, mouse),"customer@example.com");

        OrderCalculator calculator = new OrderCalculator();
        ShipmentFactory shipmentFactory = new ShipmentFactory();
        OrderProcessor orderProcessor = new OrderProcessor();

        InventoryService inventory = new InventoryService();
        EmailService emailer = new EmailService();
        orderProcessor.register(inventory);
        orderProcessor.register(emailer);

        // ====== 2. คำนวณราคา: ทดลองส่วนลด 10% และแบบลด 500 บาท ======
        System.out.println("\n--- 2. Testing Strategy Pattern (Discounts) ---");
        double originalPrice = myOrder.gettotalPrice();
        System.out.println("OriginalPrice: " + originalPrice);

        DiscountStrategy tenPercentOff = new PercentageDiscount(10);
        double priceAfterPercentage = calculator.calculateFinalPrice(myOrder, tenPercentOff);
        System.out.println("Price with 10% discount: " + priceAfterPercentage);

        DiscountStrategy fivehundredOff = new FixedDiscount(500);
        double priceAfterFixed = calculator.calculateFinalPrice(myOrder, fivehundredOff);
        System.out.println("Price with 500 THB discount: " + priceAfterFixed);

        // ====== 3. สร้างการจัดส่ง: สร้าง StandardShipment ======
        System.out.println("\n--- 3. Testing Factory and Decorator Patterns (Shipment) ---");
        // สร้างการจัดส่งแบบมาตรฐาน
        Shipment standartShipment = shipmentFactory.createShipment("STANDARD");
        System.out.println("Base Shipment: "+ standartShipment.getInfo()+", Cost: "+ standartShipment.getCost());

        // "ห่อ" ด้วยบริการห่อของขวัญ
        Shipment giftWrapped = new GiftWrapDecorator(standartShipment);
        System.out.println("Decorated: "+ giftWrapped.getInfo()+", Cost: "+ giftWrapped.getCost());

        // "ห่อ" ทับด้วยบริการประกันสินค้า
        Shipment fullyLoaded = new InsuranceDecorator(giftWrapped, myOrder);
        System.out.println("Fully Decorated: "+ fullyLoaded.getInfo()+", Cost: "+ fullyLoaded.getCost());

        // ====== 4. พิมพ์สรุปข้อมูล ======
        System.out.println("\n--- 4. Printing Final Summary ---");
        double finalPrice = priceAfterPercentage; // สมมติว่าใช้ส่วนลด 10 %
        double totalCost = finalPrice + fullyLoaded.getCost();
        System.out.println("Final price after discount: "+ finalPrice);
        System.out.println("Final shipment cost: "+ fullyLoaded.getCost());
        System.out.println("TOTAL TO PAY: "+ totalCost);

        // ====== 5. ยืนยันคำสั่งซื้อ ======
        System.out.println("\n--- Processing Order ---");
        orderProcessor.processOrder(myOrder);
    }
}