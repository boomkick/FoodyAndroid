package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

public class NhaHang {
    private int id;
    private int user_id;
    private String name;
    private String address;
    private String time;
    private double rate;
    private int imgID;

    public NhaHang(int id, int user_id, String name, String address, String time, double rate, int imgID) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.address = address;
        this.time = time;
        this.rate = rate;
        this.imgID = imgID;
    }
    public NhaHang(){}
    public NhaHang(String name, double rate, int imgID) {
        this.name = name;
        this.rate = rate;
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NhaHang{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", time='" + time + '\'' +
                ", rate=" + rate +
                ", imgID=" + imgID +
                '}';
    }
}
