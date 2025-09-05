package StrategyPattern;
import DataModels.Order;

public class PercentageDiscount implements DiscountStrategy{
    private final double Percentage;

    public PercentageDiscount(double Percentage){
        this.Percentage = Percentage;
    }

    @Override
    public double applyDiscount(Order order) {
        return order.gettotalPrice() * (1.0 - Percentage /100.0);
    }
    
}
