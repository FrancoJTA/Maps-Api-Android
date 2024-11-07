package com.example.mapsblastois;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
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
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private int LOCATION_PERMISSION_REQUEST_CODE=1;
    public Stack<LatLng> po=new Stack<>();

    private GoogleMap mymap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //FUSED
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mymap=googleMap;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);
        }
        mymap.setMyLocationEnabled(true);

        BitmapDescriptor customIcon = resizeIcon(R.drawable.nube, 100, 70);
//
//        LatLng santaCruz = new LatLng(-17.78629, -63.18117);
//        mymap.addMarker(new MarkerOptions().position(santaCruz).title("Santa Cruz").icon(customIcon));
//
//        LatLng laPaz = new LatLng(-16.50000, -68.11929);
//        mymap.addMarker(new MarkerOptions().position(laPaz).title("La Paz").icon(customIcon));
//
//
//        LatLng cochabamba = new LatLng(-17.39050, -66.15740);
//        mymap.addMarker(new MarkerOptions().position(cochabamba).title("Cochabamba").icon(customIcon));
//
//        LatLng sucre = new LatLng(-19.03333, -65.26278);
//        mymap.addMarker(new MarkerOptions().position(sucre).title("Sucre").icon(customIcon));
//
//        LatLng potosi = new LatLng(-19.58333, -65.75920);
//        mymap.addMarker(new MarkerOptions().position(potosi).title("Potosí").icon(customIcon));
//
            LatLng tarija = new LatLng(-21.53500, -64.73000);
            mymap.addMarker(new MarkerOptions().position(tarija).title("Tarija").icon(customIcon));
            po.add(tarija);
//
//        LatLng beni = new LatLng(-13.28929, -65.39725);
//        mymap.addMarker(new MarkerOptions().position(beni).title("Beni").icon(customIcon));
//
//        LatLng pando = new LatLng(-11.04703, -68.16818);
//        mymap.addMarker(new MarkerOptions().position(pando).title("Pando").icon(customIcon));
//
//        LatLng oruro = new LatLng(-17.96500, -66.34900);
//        mymap.addMarker(new MarkerOptions().position(oruro).title("Oruro").icon(customIcon));
//
//        mymap.moveCamera(CameraUpdateFactory.newLatLng(santaCruz));
//        Stack<LatLng> p2=new Stack<>();
//        p2.add(oruro);
//        p2.add(potosi);
//        p2.add(santaCruz);
//        drawRoute(p2);
//        List<LatLng> p1=new ArrayList<>();
//        p1.add(sucre);
//        p1.add(santaCruz);
//        p1.add(tarija);
//        p1.add(pando);
//        drawPolygono(p1);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new com.google.android.gms.tasks.OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!= null){
                            LatLng currentLocation= new LatLng(location.getLatitude(),location.getLongitude());
                            mymap.addMarker(new MarkerOptions().position(currentLocation).title("Mi Ubicacion"));
                            mymap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
                            po.add(currentLocation);
                            drawRoute(po);
                        }else{
                            Toast.makeText(MainActivity.this, "Permiso Aceptado", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }else{
                Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_SHORT).show();
            }

        }
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