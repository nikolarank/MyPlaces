package rs.elfak.mosis.nikola.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyPlacesMapActivity extends AppCompatActivity {

    private GoogleMap map;

    static int NEW_PLACE = 1;

    public static final int SHOW_MAP=0;
    public static final int CENTER_PLACE_ON_MAP=1;
    public static final int SELECT_COORDINATES=2;

    private int state=0;
    private boolean selCoorsEnabled=false;
    private LatLng placeLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            Intent mapIntent =getIntent();
            Bundle mapBundle=mapIntent.getExtras();
            if(mapBundle!=null)
            {
                state=mapBundle.getInt("state");
                if(state==CENTER_PLACE_ON_MAP)
                {
                    String placeLat=mapBundle.getString("lat");
                    String placeLon=mapBundle.getString("lon");
                    placeLoc=new LatLng(Double.parseDouble(placeLat),Double.parseDouble(placeLon));
                }
            }
        }
        catch (Exception e)
        {
            Log.d("Error","Error reading state");
        }
        setContentView(R.layout.activity_my_places_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                
            }
        });

        if(state==SHOW_MAP)
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            map.setMyLocationEnabled(true);
        }
        else if(state==SELECT_COORDINATES)
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                public void onMapClick(LatLng latlng) {
                    if (state == SELECT_COORDINATES && selCoorsEnabled) {
                        String lon = Double.toString(latlng.longitude);
                        String lat = Double.toString(latlng.latitude);
                        Intent locationIntent = new Intent();
                        locationIntent.putExtra("lon", lon);
                        locationIntent.putExtra("lat", lat);
                        setResult(Activity.RESULT_OK, locationIntent);
                        finish();
                    }
                }
        });
        else
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLoc,15));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case 1:
                selCoorsEnabled=true;
                Toast.makeText(this,"Select coordinates",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(state==SELECT_COORDINATES && !selCoorsEnabled)
        {
            menu.add(0,1,1,"Select Coordinates");
            menu.add(0,2,2,"Cancel");
        }
        return super.onCreateOptionsMenu(menu);
    }
}
