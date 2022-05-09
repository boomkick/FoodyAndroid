package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class billActivity extends AppCompatActivity {

    ListView listViewbestselling;
    ArrayList<Food> arrayListFood;
    AdapterBill adapterBill ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        listViewbestselling = (ListView) findViewById(R.id.listview_bill);
        arrayListFood = new ArrayList<Food>();

        arrayListFood.add(new Food("Cơm gà",R.drawable.comga,10000));
        arrayListFood.add(new Food("Bún bò", R.drawable.bunbo,20000));

        adapterBill  = new AdapterBill(billActivity.this,R.layout.list_bill,arrayListFood);
        listViewbestselling.setAdapter(adapterBill);
    }
}