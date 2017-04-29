package com.hotncold.hotncold.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hotncold.hotncold.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by Lenovo on 29.04.2017.
 */

public class DetailsMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void manageFavourite() {
        //change empty to full heart
        try {
            Class res = R.drawable.class;
            Field field = res.getField("ic_heart_full.xml");
            int drawableId = field.getInt(null);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }
        ImageButton heart = (ImageButton) findViewById(R.id.heart);
        heart.setImageResource(drawableId);
        
        //add location to favourites
    }
    public void deleteFavourite() {

    }

    /**
     * This is where we show the location. The coordinates of the passed object are marked.
     * To get the coordinates and the name, the passed JSON data needs to be examined
     */
    @Override
    public void onMapReady(GoogleMap map) {
        double coord1 = 0;
        double coord2 = 0;
        String name = "Marker";
        try {
            JSONObject location = new JSONObject(getIntent().getStringExtra("location"));
            String[] coords = location.getString("coordinates").split(",");
            coord1 = Double.parseDouble(coords[0]);
            coord2 = Double.parseDouble(coords[1]);
            name = location.getString("NAME");
        } catch (JSONException e) {
            //TODO: back to last activity
        }
        map.addMarker(new MarkerOptions().position(new LatLng(coord1, coord2)).title(name));
    }
}
