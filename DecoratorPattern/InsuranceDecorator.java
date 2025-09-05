package DecoratorPattern;
import DataModels.Order;
import FactoryMethodPattern.Shipment;

public class InsuranceDecorator extends ShipmentDecorator{
    private final Order order;

    public InsuranceDecorator(Shipment wrappedShipment,Order order) {
        super(wrappedShipment);
        this.order = order;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "+ Insurance";
    }

    @Override
    public double getCost() {
        return super.getCost() * (order.gettotalPrice()*0.1);
    }
    
}
