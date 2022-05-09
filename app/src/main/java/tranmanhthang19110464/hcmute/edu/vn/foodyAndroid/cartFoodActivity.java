package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class cartFoodActivity extends AppCompatActivity {


    ListView listViewbestselling;
    ArrayList<Food> arrayListFood;
    AdapterCartFood adapterCartFood ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_food);

        listViewbestselling = (ListView) findViewById(R.id.listview_cart_food);
        arrayListFood = new ArrayList<Food>();

        arrayListFood.add(new Food("Cơm gà",R.drawable.comga,10000));
        arrayListFood.add(new Food("Bún bò", R.drawable.bunbo,20000));

        adapterCartFood = new AdapterCartFood(cartFoodActivity.this,R.layout.list_food_cart,arrayListFood);
        listViewbestselling.setAdapter(adapterCartFood);
    }
}