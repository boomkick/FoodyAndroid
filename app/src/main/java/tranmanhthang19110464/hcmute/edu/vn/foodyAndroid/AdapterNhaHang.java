package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.homeFragment;

public class AdapterNhaHang extends ArrayAdapter<NhaHang> {
    private int layout;
    private homeFragment fragment;
    private ArrayList<NhaHang> NhaHangModelArrayList;
    public AdapterNhaHang(@NonNull homeFragment fragment, ArrayList<NhaHang> NhaHangModelArrayList) {
        super(fragment.getActivity(), 0, NhaHangModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.nhahang_item, parent, false);
        }
        NhaHang nhaHang = getItem(position);
        ImageView imageNhaHang = listItemView.findViewById(R.id.imageViewNhaHang);
        TextView tenNhaHang = listItemView.findViewById(R.id.textViewTenNhaHang);
        TextView rateNhaHang = listItemView.findViewById(R.id.textViewRateNhaHang);
        imageNhaHang.setImageResource(nhaHang.getImgID());
        tenNhaHang.setText(nhaHang.getName());
        rateNhaHang.setText(Double.toString(nhaHang.getRate()));

        return  listItemView;

    }

}
