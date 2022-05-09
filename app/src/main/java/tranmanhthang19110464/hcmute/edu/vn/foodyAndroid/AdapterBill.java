package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterBill extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Food> foodList;

    public AdapterBill(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
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
        Food food = foodList.get(i);

        TextView txtName = view.findViewById(R.id.textView_food_bill);
        TextView txtGia = view.findViewById(R.id.giatien_bill);

        txtName.setText(food.getName());
        txtGia.setText(food.getPrice());

        return view;
    }
}
