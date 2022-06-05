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
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.R;

public class loginActivity extends AppCompatActivity {

    Database database;
    private EditText editPhone;
    private EditText editPass;
    private Button btnLogin;
    private TextView ForgotPass;
    private TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editPhone = (EditText) findViewById(R.id.editTextPhone);
        editPass = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.buttonDangNhap);
        btnRegister = (TextView) findViewById(R.id.editTextDangKy);
        ForgotPass = (TextView) findViewById(R.id.editTextQuenMK);
        database = new Database(this);

//        database.QueryData("Insert into users values(null,'Thong','31/01/2001','64/2c','0945',1,null,1)");
//        database.QueryData("Insert into users values(null,'Thong','31/01/2001','64/2c','0946',2,null,1)");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editPhone.getText().toString();
                String pass = editPass.getText().toString();

                if(phone.isEmpty() || pass.isEmpty()){
                    Toast.makeText(loginActivity.this, "Phone or pass is Empty", Toast.LENGTH_SHORT).show();
                }else{
                    if(Login(phone,pass)){
                        Cursor user = database.GetData("Select * from users where phone='"+phone+"'");
                        user.moveToNext();
                        int id = user.getInt(0);
                        Intent intent = new Intent(loginActivity.this,MainActivity.class);
                        intent.putExtra("user_id",id);
                        startActivity(intent);
                        Global.user_id=id;
                        finish();
                    }else{
                        Toast.makeText(loginActivity.this, "Wrong Phone or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
    }


    public Boolean Login(String phone,String pass){
        //try catch
        Cursor cursor = database.GetData("Select * from users where phone='"+phone+"' and password='"+pass+"'");
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}