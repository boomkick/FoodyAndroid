package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;

public class restaurantActivity extends AppCompatActivity {
    TextView txtTenNhaHang,txtTenNhaHangTopBar,txtDiaChi,txtGio,txtDanhGia;
    ImageView imgBack,imgNhaHang;
    ListView listViewFoods;
    ArrayList<Food> arrayListFood;
    ArrayList<NhaHang> nhaHangModelArrayList;
    AdapterFood adapterFood;
    NhaHang nhaHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();
        int idNhaHang = intent.getIntExtra("idNhaHang",1);
        nhaHangModelArrayList = getAllRestaurants();
        setInformationNhaHang(idNhaHang);

        listViewFoods = (ListView) findViewById(R.id.listViewMonAnBanChay);
        arrayListFood = new ArrayList<Food>();
//        arrayListFood.add(new Food("Cơm gà",R.drawable.comga,10000));
//        arrayListFood.add(new Food("Bún bò", R.drawable.bunbo,20000));
        arrayListFood = getDataFoodsByRestanrantID(idNhaHang);
        adapterFood = new AdapterFood(restaurantActivity.this,R.layout.menu,arrayListFood);
        listViewFoods.setAdapter(adapterFood);

        imgBack = (ImageView) findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public ArrayList<NhaHang> getAllRestaurants(){
        Database database = new Database(this);
        Cursor dataCongViec = database.GetData("SELECT * FROM restaurant");
        ArrayList<NhaHang> nhaHangArrayList = new ArrayList<NhaHang>();
        while(dataCongViec.moveToNext()) {
            nhaHangArrayList.add(new NhaHang(
                    dataCongViec.getInt(0),
                    dataCongViec.getInt(1),
                    dataCongViec.getString(2),
                    dataCongViec.getString(3),
                    dataCongViec.getString(4),
                    dataCongViec.getDouble(5),
                    dataCongViec.getInt(6)
            ));
        }
        return nhaHangArrayList;
    }
    public  void setInformationNhaHang(int id){
        nhaHang = new NhaHang();
        for (NhaHang nh:nhaHangModelArrayList) {
            if(nh.getId() == id){
                nhaHang = nh;
            }
        }
        txtTenNhaHang = (TextView) findViewById(R.id.textViewNameNhaHangDetail);
        txtTenNhaHangTopBar = (TextView) findViewById(R.id.textViewNameNhaHangDetailBar);
        txtDiaChi = (TextView) findViewById(R.id.textViewAddressNhaHangDetail);
        txtDanhGia = (TextView) findViewById(R.id.textViewRateNhaHangDetail);
        txtGio = (TextView) findViewById(R.id.textViewTimeRestaurantDetail);
        imgNhaHang = (ImageView) findViewById(R.id.imageViewNhaHangDetail);
        txtTenNhaHang.setText(nhaHang.getName());
        txtTenNhaHangTopBar.setText(nhaHang.getName());
        txtGio.setText(nhaHang.getTime());
        txtDanhGia.setText(Double.toString(nhaHang.getRate()));
        txtDiaChi.setText(nhaHang.getAddress());
        imgNhaHang.setImageResource(nhaHang.getImgID());
    }
    public ArrayList<Food> getDataFoods(){
        Database database = new Database(this);
        Cursor dataFood = database.GetData("SELECT * FROM food");
        ArrayList<Food> foodList = new ArrayList<Food>();
        while(dataFood.moveToNext()) {
            foodList.add(new Food(
                    dataFood.getInt(0),
                    dataFood.getInt(1),
                    dataFood.getString(2),
                    dataFood.getString(3),
                    dataFood.getInt(4),
                    dataFood.getInt(5)
            ));
        }
        return  foodList;
    }
    public ArrayList<Food> getDataFoodsByRestanrantID(int idRestaurant){
        Database database = new Database(this);
        Cursor dataFood = database.GetData("SELECT * FROM food where restaurant_id = "+ idRestaurant + "");
        ArrayList<Food> foodList = new ArrayList<Food>();
        while(dataFood.moveToNext()) {
            foodList.add(new Food(
                    dataFood.getInt(0),
                    dataFood.getInt(1),
                    dataFood.getString(2),
                    dataFood.getString(3),
                    dataFood.getInt(4),
                    dataFood.getInt(5)
            ));
        }
        return  foodList;
    }
}
