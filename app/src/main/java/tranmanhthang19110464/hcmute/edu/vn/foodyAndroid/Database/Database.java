package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context,"FoodyApp.sqlite",null,1);
    }
    //truy vấn không trả kết quả: CREATE, INSERT, UPDATE, DELETE, ...
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    // truy vấn có trả kết quả: SELECT, ...
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table if not exists users(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(255), " +
                "birthdate VARCHAR(255), " +
                "address VARCHAR(255)," +
                "phone VARCHAR(255)," +
                "password VARCHAR(255), " +
                "image INTEGER, " +
                "isBuyer INTEGER)");
        sqLiteDatabase.execSQL("Create table if not exists restaurant(idRestaurant INTEGER PRIMARY KEY, " +
                "user_id INTEGER, " +
                "name VARCHAR(255), " +
                "address VARCHAR(255), " +
                "time VARCHAR(255), " +
                "image INTEGER, " +
                "rate DOUBLE)");
        sqLiteDatabase.execSQL("Create table if not exists food(idFood INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "restaurant_id INTEGER, " +
                "name VARCHAR(255), " +
                "describe VARCHAR(255), " +
                "image INTEGER, " +
                "price INTEGER)");
        sqLiteDatabase.execSQL("Create table if not exists comment(restaurant_id INTEGER, " +
                "user_id INTEGER, " +
                "user_name VARCHAR(255), " +
                "content VARCHAR(255), " +
                "rate DOUBLE)");
        sqLiteDatabase.execSQL("Create table if not exists ordertable(user_id INTEGER, " +
                "restaurant_id INTEGER, " +
                "food_id INTEGER, " +
                "quantity INTEGER, " +
                "price INTEGER, " +
                "isConfirm INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
