package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.userFragment;

public class ChangePassword extends AppCompatActivity {

    EditText txtPhone;
    EditText txtOldPass;
    EditText txtNewPass;
    EditText txtReNewPass;
    Button btnChangePass;
    Database database;

    userFragment userfragment;
    User users;
    int id=Global.user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtPhone = (EditText) findViewById(R.id.txtPhoneEdit);
        txtOldPass = (EditText) findViewById(R.id.txtOldPassEdit);
        txtNewPass = (EditText) findViewById(R.id.txtNewPassEdit);
        txtReNewPass = (EditText) findViewById(R.id.txtReNewPassEdit);
        btnChangePass = (Button) findViewById(R.id.btnEditChangePass);



        database = new Database(this);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = txtPhone.getText().toString();
                String oldpass = txtOldPass.getText().toString();
                String newpass = txtNewPass.getText().toString();
                String renewpass = txtReNewPass.getText().toString();

                getDataUser(id);
                if(phone.equals(users.getPhone())){
                    if(oldpass.equals(users.getPass())){
                        if(newpass.equals(renewpass)){
                            database.QueryData("Update users set password='"+newpass+"' where id='"+id+"'");
                            Toast.makeText(ChangePassword.this, "Đổi password thành công", Toast.LENGTH_SHORT).show();
                            finish();
//                            startActivity(new Intent(ChangePassword.this,));
                        }else
                            Toast.makeText(ChangePassword.this, "Password mới và nhập lại password mới không trùng nhau", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(ChangePassword.this, "Nhập sai password cũ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ChangePassword.this, "Bạn đã nhập sai số điện thoại", Toast.LENGTH_SHORT).show();
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