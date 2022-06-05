package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.fragment.app.FragmentActivity;


import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;

import android.location.Geocoder;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions markerOptions;
    LatLng location;
    Database database;
    NhaHang nhaHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new Database(this);
        Intent data = getIntent();
        int id = data.getIntExtra("idNhaHang",1);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        getRestaurant(id);

        LatLng p1 = getLocationFromAddress(this,nhaHang.getAddress());


        location = p1;
        markerOptions = new MarkerOptions().title(nhaHang.getName()).position(p1)
                .snippet("Giờ đóng cửa:" + nhaHang.getTime());


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

    }


    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    public void getRestaurant(int id) {
        Cursor cursor = database.GetData("Select * from restaurant where idRestaurant='"+id+"'");
        cursor.moveToNext();
        int userId = cursor.getInt(1);
        String name = cursor.getString(2);
        String add = cursor.getString(3);
        String time = cursor.getString(4);
        int img = cursor.getInt(5);
        double rate = cursor.getDouble(6);
        nhaHang = new NhaHang(id,userId,name,add,time,rate,img);

    }
}