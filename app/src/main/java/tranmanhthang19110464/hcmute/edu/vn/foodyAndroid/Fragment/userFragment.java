package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.ChangePassword;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Global;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.InfoUserActivity;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.MainActivity;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.R;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.User;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.loginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userFragment extends Fragment {
    Button btnDangXuat;
    Button btnDangNhap;
    Button btnInfo;
    Button btnDoiMK;
    Button btnLsDon;

    Database database;
    TextView TenUser;
    User users;
    int id = Global.user_id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userFragment newInstance(String param1, String param2) {
        userFragment fragment = new userFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TenUser = (TextView) view.findViewById(R.id.textName);
        btnDangXuat = (Button) view.findViewById(R.id.buttonDangXuat);
        btnDangNhap = (Button) view.findViewById(R.id.buttonDangNhap);
        btnInfo = (Button) view.findViewById(R.id.textViewinfo);
        btnDoiMK = (Button) view.findViewById(R.id.textViewDoiMK);
        btnLsDon = (Button) view.findViewById(R.id.textViewLs);

        database = new Database(getActivity());



        if(id==-1){
            btnDangXuat.setVisibility(View.GONE);
            btnInfo.setVisibility(View.GONE);
            btnDoiMK.setVisibility(View.GONE);
            btnLsDon.setVisibility(View.GONE);

            btnDangNhap.setVisibility(View.VISIBLE);
        }else{
            btnDangNhap.setVisibility(View.GONE);

            btnDangXuat.setVisibility(View.VISIBLE);
            btnInfo.setVisibility(View.VISIBLE);
            btnDoiMK.setVisibility(View.VISIBLE);
            btnLsDon.setVisibility(View.VISIBLE);

            getDataUser(id);
            TenUser.setText(users.getName());
        }

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoUserActivity.class);
                startActivity(intent);
            }
        });

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });


        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.user_id=-1;
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                //Intent intent = new Intent(getActivity(), restaurantActivity.class);
                getActivity().finish();
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), loginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    public void getDataUser(int id) {
        Cursor cursor = database.GetData("Select * from users where id='"+id+"'");
        cursor.moveToNext();
        String name = cursor.getString(1);
        String birth = cursor.getString(2);
        String add = cursor.getString(3);
        String phone = cursor.getString(4);
        String pass = cursor.getString(5);
        int img = cursor.getInt(6);
        int isBuyer = cursor.getInt(7);
        users = new User(id,name,birth,add,phone,pass,img,isBuyer);
    }
}