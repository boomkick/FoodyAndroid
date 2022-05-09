package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.AdapterNhaHang;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.MainActivity;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.NhaHang;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.R;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.restaurantActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {
    GridView danhSachNhaHang;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        danhSachNhaHang = (GridView) view.findViewById(R.id.gridViewNhaHang);
        addNhaHangVaoGridView();
        setOnClickNhaHangGridView();

    }
    public ArrayList<NhaHang> getAllRestaurants(){
        Database database = new Database(getActivity());
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
    public void addNhaHangVaoGridView() {
        ArrayList<NhaHang> nhaHangModelArrayList = new ArrayList<NhaHang>();
        nhaHangModelArrayList = getAllRestaurants();
        AdapterNhaHang adapter = new AdapterNhaHang(this, nhaHangModelArrayList);

        danhSachNhaHang.setAdapter(adapter);
    }
    public void setOnClickNhaHangGridView(){
        danhSachNhaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), restaurantActivity.class);
                intent.putExtra("idNhaHang",i);
                startActivity(intent);
            }
        });
    }
}