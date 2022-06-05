package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.R;

public class registerActivity extends AppCompatActivity {
    EditText PhoneUsers;
    EditText Password;
    EditText RePassword;
    EditText Name;
    EditText Address;
    CheckBox checkBox;
    Button btnSignup;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        PhoneUsers = (EditText) findViewById(R.id.editTextPhoneRegister);
        Password = (EditText) findViewById(R.id.editTextPassResgister);
        RePassword = (EditText) findViewById(R.id.editTextRepassRegister);
        Name = (EditText) findViewById(R.id.editTextNameRegister);
        Address = (EditText) findViewById(R.id.editTextAddRegister);
        checkBox = (CheckBox) findViewById(R.id.checkBoxAccept);
        btnSignup = (Button) findViewById(R.id.buttonRegister);
        database = new Database(this);

        btnSignup.setEnabled(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                btnSignup.setEnabled(true);
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = PhoneUsers.getText().toString();
                String pass = Password.getText().toString();
                String repass = RePassword.getText().toString();
                String name = Name.getText().toString();
                String add = Address.getText().toString();

                if(phone.isEmpty() || pass.isEmpty() || repass.isEmpty() || name.isEmpty() || add.isEmpty()){
                    Toast.makeText(registerActivity.this, "Fields can't Empty", Toast.LENGTH_SHORT).show();
                }else {
                    if(pass.equals(repass)){
                        if(checkUserPhone(phone)==false){
                            database.QueryData("Insert into users values(null,'"+name+"','','"+add+"','"+phone+"','"+pass+"',null,1)");
                            Intent intent = new Intent(registerActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(registerActivity.this, "Phone number already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(registerActivity.this, "Password and RePassword are  not same", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });
    }
    public Boolean checkUserPhone(String phone){
        Cursor cursor = database.GetData("Select * from users where phone='"+phone+"'");
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}