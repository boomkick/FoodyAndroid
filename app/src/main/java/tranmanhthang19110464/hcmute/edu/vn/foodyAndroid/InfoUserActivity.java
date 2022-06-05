package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;

public class InfoUserActivity extends AppCompatActivity {

    EditText txtName;
    TextView txtPhone;
    EditText txtAdd;
    EditText txtBirth;
    Button btnSave;

    Database database;
    User users;
    int id=Global.user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        database = new Database(this);


        txtName = (EditText) findViewById(R.id.textNameInfo);
        txtPhone = (TextView) findViewById(R.id.textPhoneInfo);
        txtAdd = (EditText) findViewById(R.id.textAddInfo);
        txtBirth = (EditText) findViewById(R.id.textBirthInfo);
        btnSave = (Button) findViewById(R.id.buttonSaveInfo);


        getDataUser(id);
        txtName.setText(users.getName());
        txtPhone.setText(users.getPhone());
        txtAdd.setText(users.getAddress());
        txtBirth.setText(users.getBirth());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String diachi = txtAdd.getText().toString();
                String ngaysinh = txtBirth.getText().toString();

                if(name.equals("") || diachi.equals("")){
                    Toast.makeText(InfoUserActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    database.QueryData("Update users set name='"+name+"', address='"+diachi+"',birthdate='"+ngaysinh+"' where id='"+id+"'");
                    Toast.makeText(InfoUserActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    getDataUser(id);
                    finish();
                    startActivity(getIntent());
                }

            }
        });

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