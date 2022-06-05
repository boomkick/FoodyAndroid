package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;

public class cartFoodActivity extends AppCompatActivity {
    int idUser = Global.user_id;
    Database database;
    ListView listViewbestselling;
    ArrayList<Order> arrayListOrder;
    AdapterCartFood adapterCartFood ;
    TextView txtNameUser, txtTotalPriceCart, txtSoPhan;
    ImageButton imgButtonClose;
    Button btnCancel, btnConfirm;
    User users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_food);

        listViewbestselling = (ListView) findViewById(R.id.listview_cart_food);
        txtNameUser = (TextView) findViewById(R.id.textViewNameUser);
        txtTotalPriceCart = (TextView) findViewById(R.id.textViewTotalPriceCart);
        txtSoPhan = (TextView) findViewById(R.id.so_phan);

        imgButtonClose = (ImageButton) findViewById(R.id.imageButtonClose);
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnConfirm = (Button) findViewById(R.id.buttonConfirm);
        database = new Database(cartFoodActivity.this);

        getDataUser(idUser);


        arrayListOrder = getOrderListByUserId(idUser);
        int totalPrice = 0;
        int sophan=0;
        for (Order order : arrayListOrder) {
            totalPrice = totalPrice + order.getPrice();
            sophan = sophan + order.getQuantity();
        }

        adapterCartFood = new AdapterCartFood(cartFoodActivity.this,R.layout.list_food_cart,arrayListOrder,database);
        listViewbestselling.setAdapter(adapterCartFood);
        txtNameUser.setText(users.getName());
        txtTotalPriceCart.setText(Integer.toString(totalPrice));
        txtSoPhan.setText(Integer.toString(sophan)+ " Phần");
        imgButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOrder(idUser);
                Toast.makeText(cartFoodActivity.this, "Bạn đã hủy đơn hàng!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmOrder(idUser);
                Toast.makeText(cartFoodActivity.this, "Bạn đã đặt hàng thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
    public ArrayList<Order> getOrderListByUserId(int user_id){
        Cursor dataOrder = database.GetData("SELECT * FROM ordertable where user_id = "+ user_id +" AND isConfirm ="+0);
        ArrayList<Order> orderList = new ArrayList<Order>();
        while(dataOrder.moveToNext()) {
            orderList.add(new Order(
                    dataOrder.getInt(0),
                    dataOrder.getInt(1),
                    dataOrder.getInt(2),
                    dataOrder.getInt(3),
                    dataOrder.getInt(4),
                    dataOrder.getInt(5)
            ));
        }
        return  orderList;
    }
    public void deleteOrder(int user_id){
        database.QueryData("Delete from ordertable where user_id = "+user_id+" AND isConfirm = "+0);
    }
    public void confirmOrder(int user_id){
        database.QueryData("Update ordertable set isConfirm = 1 where user_id = "+user_id);
    }

    public void getDataUser(int user_id) {
        Cursor cursor = database.GetData("Select * from users where id='"+user_id+"'");
        cursor.moveToNext();
        String name = cursor.getString(1);
        String birth = cursor.getString(2);
        String add = cursor.getString(3);
        String phone = cursor.getString(4);
        String pass = cursor.getString(5);
        int img = cursor.getInt(6);
        int isBuyer = cursor.getInt(7);
        users = new User(user_id,name,birth,add,phone,pass,img,isBuyer);

    }
}