package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.homeFragment;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.notificationFragment;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.orderFragment;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.userFragment;

public class MainActivity extends AppCompatActivity {
    GridView danhSachNhaHang;
    Database database;
    homeFragment fragmentHome;
    notificationFragment fragmentNotification;
    orderFragment fragmentOrder;
    userFragment fragmentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Fragment
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        fragmentHome = new homeFragment();
        fragmentNotification = new notificationFragment();
        fragmentOrder = new orderFragment();
        fragmentUser = new userFragment();
        replaceFragment(fragmentHome);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        replaceFragment(fragmentHome);

                        break;
                    case R.id.notification:
                        replaceFragment(fragmentNotification);
                        break;
                    case R.id.order:
                        replaceFragment(fragmentOrder);
                        break;
                    case R.id.user:
                        replaceFragment(fragmentUser);
                        break;
                }
                return true;
            }
        });

        //Tao CSDL
        database = new Database(this);
        //createDatabase();
        //Reset CSDL
        database.QueryData("Delete from restaurant");
        database.QueryData("Delete from food");
        addRestaurantstoDB();
        addFoodstoDB();


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    private void createDatabase() {
        database.QueryData("Create table if not exists user(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), birthdate VARCHAR(255), address VARCHAR(255), image INTEGER, isBuyer INTEGER)");
        database.QueryData("Create table if not exists restaurant(idRestaurant INTEGER PRIMARY KEY, user_id INTEGER, name VARCHAR(255), address VARCHAR(255), time VARCHAR(255), rate DOUBLE, image INTEGER)");
        database.QueryData("Create table if not exists food(idFood INTEGER PRIMARY KEY AUTOINCREMENT, restaurant_id INTEGER, name VARCHAR(255), describe VARCHAR(255), image INTEGER, price INTEGER)");
        database.QueryData("Create table if not exists comment(restaurant_id INTEGER, user_id INTEGER, user_name VARCHAR(255), content VARCHAR(255), rate DOUBLE)");
        database.QueryData("Create table if not exists ordertable(idOrder INTEGER PRIMARY KEY AUTOINCREMENT,user_id INTEGER, restaurant_id INTEGER, food_id INTEGER, quantity INTEGER, price INTEGER, isConfirm INTEGER)");
    }
    public boolean addFood(Food food){
        if(food == null){
            return false;
        }else if(1==1){
            //Kiem tra trung id => return false
        }
        int id = food.getId();
        int restaurantId = food.getRestaurantId();
        String name = food.getName();
        String descrt = food.getDescibe();
        int image = food.getImage();
        int price = food.getPrice();
        database.QueryData("Insert into food Values("+ id +","+ restaurantId +",'"+ name +"','"+ descrt +"',"+ image +", "+ price +")");
        return true;
    }
    public void addFoodstoDB(){
        addFood(new Food(0,0,"Gà rán","miếng gà rán ngon",R.drawable.food_burgerking_garan,40000));
        addFood(new Food(1,0,"Coca","nước giải khát coca",R.drawable.food_burgerking_cocajpg,20000));
        addFood(new Food(2,2,"Gà rán","nước giải khát coca",R.drawable.food_burgerking_garan,20000));
        addFood(new Food(3,2,"Coca","nước giải khát coca",R.drawable.food_burgerking_cocajpg,20000));
        addFood(new Food(5,2,"Gà lắc khoai tây","Hỗn hợp",R.drawable.food_kfc_chickenmixpotato,120000));
        addFood(new Food(4,1,"Voucher","nước giải khát coca",R.drawable.food_dookki_voucher,139000));
        addFood(new Food(6,4,"Trà sữa truyền thống","nước giải khát coca",R.drawable.food_tron94_tranchauduongden,39000));
        addFood(new Food(7,4,"Trà sữa hoàng kim","nước giải khát coca",R.drawable.food_tron94_trasuahoangkim,49000));
        addFood(new Food(8,4,"Trà sữa sâm dứa","nước giải khát coca",R.drawable.food_tron94_samdua,39000));
    }
    public boolean addRestaurant(NhaHang nhaHang){
        if(nhaHang==null){
            return false;
        }else if(1==1){
            //Kiem tra trung id => return false
        }
        int id = nhaHang.getId();
        int user_id = nhaHang.getUser_id();
        String name = nhaHang.getName();
        String address = nhaHang.getAddress();
        String time = nhaHang.getTime();
        double rate = nhaHang.getRate();
        int icon = nhaHang.getImgID();
        database.QueryData("Insert into restaurant Values("+ id +","+ user_id +",'"+ name +"','"+ address +"','"+ time +"', "+ rate +", "+icon+")");
        return true;
    }

    private void addRestaurantstoDB() {
        addRestaurant(new NhaHang(0,0,"Burger King","QL0","5:00 22:00", 4.6, R.drawable.restaurant_burgerking_icon));
        addRestaurant(new NhaHang(1,1,"Dookki","QL1","5:00 22:00", 4.5, R.drawable.restaurant_dookki_icon));
        addRestaurant(new NhaHang(2,2,"KFC","22 Đ. Nguyễn Thị Tú, Âp 4، Bình Chánh, Thành phố Hồ Chí Minh 70000","10:00 22:00", 4.6, R.drawable.restaurant_kfc_icon));
        addRestaurant(new NhaHang(3,3,"MC Donald","QL1","5:00 22:00", 4.6, R.drawable.restaurant_mcdonald_icon));
        addRestaurant(new NhaHang(4,4,"Tròn 94","QL1","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(5,5,"Tròn 94","QL1","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(6,6,"Tròn 94","QL1","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(7,7,"Tròn 94","QL1","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(8,8,"Tròn 94","QL1","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
    }


}