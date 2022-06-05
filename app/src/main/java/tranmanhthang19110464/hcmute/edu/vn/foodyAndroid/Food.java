package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;


public class Food {
    private int id;
    private int restaurantId;
    private String name;
    private String descibe;
    private int image;
    private int price;


    public Food(int id, int restaurantId, String name, String descibe, int image, int price) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.descibe = descibe;
        this.image = image;
        this.price = price;
    }

    public Food(String name, int img_selling, int price) {
        name = name;
        this.image = img_selling;
        this.price = price;
    }
    public Food(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getDescibe() {
        return descibe;
    }

    public void setDescibe(String descibe) {
        this.descibe = descibe;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public int getImg_selling() {
        return image;
    }

    public void setImg_selling(int img_selling) {
        this.image = img_selling;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
