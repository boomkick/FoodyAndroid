package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

public class User {
    private int id;
    private String Name;
    private String Birth;
    private String Address;
    private String phone;
    private String pass;
    private int ImgUser;
    private int isBuyer;


    public User(int id, String name, String birth, String address, String phone, String pass, int imgUser, int isBuyer) {
        this.id = id;
        Name = name;
        Birth = birth;
        Address = address;
        this.phone = phone;
        this.pass = pass;
        ImgUser = imgUser;
        this.isBuyer = isBuyer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
