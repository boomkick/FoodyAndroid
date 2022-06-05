package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterFood extends BaseAdapter {

    private Context context;
    private int layout, idNhaHang;
    private List<Food> foodList;


    public AdapterFood(Context context, int layout, List<Food> foodList, int idNH) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
        this.idNhaHang = idNH;
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
        TextView txtName = view.findViewById(R.id.ten_mon_an_ban_chay);
        TextView txtGia = view.findViewById(R.id.gia_mon_an_ban_chay);
        ImageView imgFood = view.findViewById(R.id.img_mon_an_ban_chay);
        txtName.setText(food.getName());
        txtGia.setText(Integer.toString(food.getPrice())+"đ");
        imgFood.setImageResource(food.getImg_selling());
        ImageButton btnAdd = view.findViewById(R.id.buttonAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Global.user_id!=-1){
                    Intent intent = new Intent(context, orderFoodActivity.class);
                    intent.putExtra("idNhaHang",idNhaHang);
                    intent.putExtra("idFood",food.getId());
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "Bạn cần đăng nhập trước khi đặt món", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,loginActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        return view;
    }
}
