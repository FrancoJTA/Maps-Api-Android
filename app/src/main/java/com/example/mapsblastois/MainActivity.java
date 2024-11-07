package com.example.mapsblastois;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mymap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mymap=googleMap;

        BitmapDescriptor customIcon = resizeIcon(R.drawable.nube, 100, 70);

        LatLng santaCruz = new LatLng(-17.78629, -63.18117);
        mymap.addMarker(new MarkerOptions().position(santaCruz).title("Santa Cruz").icon(customIcon));

        LatLng laPaz = new LatLng(-16.50000, -68.11929);
        mymap.addMarker(new MarkerOptions().position(laPaz).title("La Paz").icon(customIcon));


        LatLng cochabamba = new LatLng(-17.39050, -66.15740);
        mymap.addMarker(new MarkerOptions().position(cochabamba).title("Cochabamba").icon(customIcon));

        LatLng sucre = new LatLng(-19.03333, -65.26278);
        mymap.addMarker(new MarkerOptions().position(sucre).title("Sucre").icon(customIcon));

        LatLng potosi = new LatLng(-19.58333, -65.75920);
        mymap.addMarker(new MarkerOptions().position(potosi).title("Potosí").icon(customIcon));

        LatLng tarija = new LatLng(-21.53500, -64.73000);
        mymap.addMarker(new MarkerOptions().position(tarija).title("Tarija").icon(customIcon));

        LatLng beni = new LatLng(-13.28929, -65.39725);
        mymap.addMarker(new MarkerOptions().position(beni).title("Beni").icon(customIcon));

        LatLng pando = new LatLng(-11.04703, -68.16818);
        mymap.addMarker(new MarkerOptions().position(pando).title("Pando").icon(customIcon));

        LatLng oruro = new LatLng(-17.96500, -66.34900);
        mymap.addMarker(new MarkerOptions().position(oruro).title("Oruro").icon(customIcon));

        mymap.moveCamera(CameraUpdateFactory.newLatLng(santaCruz));
        Stack<LatLng> p2=new Stack<>();
        p2.add(oruro);
        p2.add(potosi);
        p2.add(santaCruz);
        drawRoute(p2);
        List<LatLng> p1=new ArrayList<>();
        p1.add(sucre);
        p1.add(santaCruz);
        p1.add(tarija);
        p1.add(pando);
        drawPolygono(p1);
    }

    private void drawRoute(Stack<LatLng> points) {
        if(!points.isEmpty() && points.size()!=1){
            PolylineOptions polylineOptions = new PolylineOptions()
                    .add(points.pop())
                    .add(points.peek())
                    .width(10)
                    .color(ContextCompat.getColor(this,R.color.black));
            mymap.addPolyline(polylineOptions);
            drawRoute(points);
        }
    }

    private void drawPolygono(List<LatLng> points) {
        PolygonOptions polygonOptions = new PolygonOptions()
                .addAll(points)
                .strokeWidth(5)
                .strokeColor(ContextCompat.getColor(this,R.color.white));
        mymap.addPolygon(polygonOptions);
    }

    private BitmapDescriptor resizeIcon(int xd, int width, int height) {
        Drawable drawable = ContextCompat.getDrawable(this, xd);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), xd);

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

        return BitmapDescriptorFactory.fromBitmap(resizedBitmap);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}