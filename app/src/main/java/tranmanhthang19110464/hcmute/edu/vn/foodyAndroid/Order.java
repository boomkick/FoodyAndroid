package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

public class Order {
    private int user_id;
    private int restaurant_id;
    private int food_id;
    private int quantity;
    private int price;
    private int isConfirm;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Order(int user_id, int restaurant_id, int food_id, int quantity, int price, int isConfirm) {
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;
        this.food_id = food_id;
        this.quantity = quantity;
        this.price = price;
        this.isConfirm = isConfirm;
    }
    public Order(){}

    @Override
    public String toString() {
        return "Order{" +
                "user_id=" + user_id +
                ", restaurant_id=" + restaurant_id +
                ", food_id=" + food_id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", isConfirm=" + isConfirm +
                '}';
    }
}
