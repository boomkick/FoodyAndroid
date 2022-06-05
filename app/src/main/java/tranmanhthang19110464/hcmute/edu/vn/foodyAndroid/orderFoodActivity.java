package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;

public class orderFoodActivity extends AppCompatActivity {
    int idNhaHang, idFood, idUser, soLuong;
    TextView txtName, txtGia, txtQuantity, txtTotalPrice;
    ImageView imgFood;
    ImageButton btnIncrease, btnDecrease, btnEnd;
    Button btnAddToCart;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_food);

        Intent intent = getIntent();
        idNhaHang = intent.getIntExtra("idNhaHang",1);
        idFood = intent.getIntExtra("idFood",1);
        idUser = Global.user_id;

        txtName = (TextView) findViewById(R.id.textViewNameOrderFood);
        txtGia = (TextView) findViewById(R.id.textViewPriceOrderFood);
        txtTotalPrice = (TextView) findViewById(R.id.textViewTotalPriceOrderFood);
        imgFood = (ImageView)findViewById(R.id.imageViewOrderFood);
        btnIncrease = (ImageButton)findViewById(R.id.buttonIncreaseOrderFood);
        btnDecrease = (ImageButton)findViewById(R.id.buttonDecreaseOrderFood);
        btnEnd = (ImageButton)findViewById(R.id.imageButtonEnd);
        btnAddToCart = (Button)findViewById(R.id.buttonAddToCart);
        txtQuantity = (TextView) findViewById(R.id.textViewQuantityOrderFood);

        database = new Database(orderFoodActivity.this);
        soLuong = Integer.parseInt(txtQuantity.getText().toString());
        Food food = getFoodById(idFood);
        txtName.setText(food.getName());
        txtGia.setText(Integer.toString(food.getPrice())+"đ");
        txtTotalPrice.setText("Tổng tiền");
        imgFood.setImageResource(food.getImg_selling());

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soLuong = soLuong + 1;
                txtQuantity.setText(Integer.toString(soLuong));
                txtTotalPrice.setText(Integer.toString(soLuong * food.getPrice())+"đ");
            }
        });
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soLuong == 0){
                    Toast.makeText(orderFoodActivity.this, "Số món ăn bị âm rồi bạn ơi!!!", Toast.LENGTH_SHORT).show();
                }else {
                    soLuong = soLuong - 1;
                    txtQuantity.setText(Integer.toString(soLuong));
                    txtTotalPrice.setText(Integer.toString(soLuong * food.getPrice())+"đ");
                }
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soLuong == 0){
                    Toast.makeText(orderFoodActivity.this, "Chưa thêm món thì sao mà thêm vào giỏ bạn ơi!!!", Toast.LENGTH_SHORT).show();
                }else{
                    Order order = new Order(idUser, idNhaHang, idFood, soLuong,(soLuong * food.getPrice()),0);
                    addOrder(order);
                    finish();
                }
            }
        });
    }
    public Food getFoodById(int food_id){
        Database database = new Database(this);
        Cursor dataFood = database.GetData("SELECT * FROM food where idFood = " + food_id);
        Food food = new Food();
        while(dataFood.moveToNext()) {
            food = new Food(
                    dataFood.getInt(0),
                    dataFood.getInt(1),
                    dataFood.getString(2),
                    dataFood.getString(3),
                    dataFood.getInt(4),
                    dataFood.getInt(5)
            );
        }
        return  food;
    }
    public boolean addOrder(Order order){
        if(order==null){
            return false;
        }else if(1==1){
            //Kiem tra trung id => return false
        }
        database.QueryData("Insert into ordertable (user_id, restaurant_id, food_id, quantity, price, isConfirm) Values("+ order.getUser_id() +","+ order.getRestaurant_id() +","+ order.getFood_id() +","+ order.getQuantity() +","+ order.getPrice() +","+ order.getIsConfirm() +")");
        return true;
    }
}