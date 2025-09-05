package DataModels;
import java.util.List;

public  record Order(String orderId, List<Product> products, String customerEmail) {
    public double gettotalPrice(){
        double total = 0 ;
        for(Product p : products){
            total += p.price();
        }
        return total;
    }

    public String getTotalPrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalPrice'");
    }
}
