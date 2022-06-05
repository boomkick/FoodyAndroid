package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;

public class AdapterCartFood extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Order> orderList;
    private Database database;

    public AdapterCartFood(Context context, int layout, List<Order> orderList, Database database) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
        this.database = database;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout,null);
        Order order = orderList.get(i);
        Food food = getFoodById(order.getFood_id());

        TextView txtName = view.findViewById(R.id.textViewNameFoodCart);
        TextView txtGia = view.findViewById(R.id.textViewPriceCart);
        TextView txtSoLuong = view.findViewById(R.id.textViewQuantityCart);

        txtName.setText(food.getName());
        txtGia.setText(Integer.toString(order.getPrice()));
        //Toast.makeText(context, Integer.toString(order.getPrice()), Toast.LENGTH_SHORT).show();
        txtSoLuong.setText(Integer.toString(order.getQuantity()));

        return view;
    }
    public Food getFoodById(int food_id){
        Cursor dataFood = database.GetData("SELECT * FROM food where idFood = "+Integer.toString(food_id));
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
}
