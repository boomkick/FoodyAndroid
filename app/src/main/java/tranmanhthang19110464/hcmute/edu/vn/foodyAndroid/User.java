package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

public class User {
    private String id;
    private String Name;
    private String Birth;
    private String Address;
    private int ImgUser;
    private int isBuyer;


    public User(String id, String name, String birth, String address, int imgUser, int isBuyer) {
        this.id = id;
        Name = name;
        Birth = birth;
        Address = address;
        ImgUser = imgUser;
        this.isBuyer = isBuyer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getImgUser() {
        return ImgUser;
    }

    public void setImgUser(int imgUser) {
        ImgUser = imgUser;
    }

    public int getIsBuyer() {
        return isBuyer;
    }

    public void setIsBuyer(int isBuyer) {
        this.isBuyer = isBuyer;
    }
}
