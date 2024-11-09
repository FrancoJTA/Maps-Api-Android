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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private LatLng currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private int LOCATION_PERMISSION_REQUEST_CODE=1;
    public Stack<LatLng> po=new Stack<>();
    List<LatLng> SantaCruz=new ArrayList<>();
    List<LatLng> LaPaz=new ArrayList<>();
    List<LatLng> Pando=new ArrayList<>();
    List<LatLng> Beni=new ArrayList<>();
    List<LatLng> Tarija=new ArrayList<>();
    List<LatLng> Sucre=new ArrayList<>();
    List<LatLng> Potosi=new ArrayList<>();
    List<LatLng> Oruro=new ArrayList<>();
    List<LatLng> Cochabamba=new ArrayList<>();
    Marker ma=null;
    private List<Polyline> lines = new ArrayList<>();

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
// Santa Cruz
        LatLng plaza24DeSeptiembre = new LatLng(-17.7833, -63.1821);
        mymap.addMarker(new MarkerOptions().position(plaza24DeSeptiembre).title("Plaza 24 de Septiembre").icon(customIcon));
        SantaCruz.add(plaza24DeSeptiembre);

        LatLng biocentroGuembe = new LatLng(-17.7958, -63.1604);
        mymap.addMarker(new MarkerOptions().position(biocentroGuembe).title("Biocentro Güembé").icon(customIcon));
        SantaCruz.add(biocentroGuembe);

        LatLng lomasDeArena = new LatLng(-17.9135, -63.1998);
        mymap.addMarker(new MarkerOptions().position(lomasDeArena).title("Lomas de Arena").icon(customIcon));
        SantaCruz.add(lomasDeArena);

        LatLng jardínBotánico = new LatLng(-17.7520, -63.1554);
        mymap.addMarker(new MarkerOptions().position(jardínBotánico).title("Jardín Botánico").icon(customIcon));
        SantaCruz.add(jardínBotánico);

        LatLng catedralSantaCruz = new LatLng(-17.7828, -63.1818);
        mymap.addMarker(new MarkerOptions().position(catedralSantaCruz).title("Catedral Metropolitana").icon(customIcon));
        SantaCruz.add(catedralSantaCruz);

// La Paz
        LatLng plazaMurillo = new LatLng(-16.5000, -68.1500);
        mymap.addMarker(new MarkerOptions().position(plazaMurillo).title("Plaza Murillo").icon(customIcon));
        LaPaz.add(plazaMurillo);

        LatLng valleDeLaLuna = new LatLng(-16.5645, -68.1097);
        mymap.addMarker(new MarkerOptions().position(valleDeLaLuna).title("Valle de la Luna").icon(customIcon));
        LaPaz.add(valleDeLaLuna);

        LatLng telefericoRojo = new LatLng(-16.5100, -68.1237);
        mymap.addMarker(new MarkerOptions().position(telefericoRojo).title("Teleférico Rojo").icon(customIcon));
        LaPaz.add(telefericoRojo);

        LatLng lagoTiticaca = new LatLng(-15.7652, -69.4200);
        mymap.addMarker(new MarkerOptions().position(lagoTiticaca).title("Lago Titicaca").icon(customIcon));
        LaPaz.add(lagoTiticaca);

        LatLng tiwanaku = new LatLng(-16.5537, -68.6738);
        mymap.addMarker(new MarkerOptions().position(tiwanaku).title("Tiwanaku").icon(customIcon));
        LaPaz.add(tiwanaku);

// Cochabamba
        LatLng cristoDeLaConcordia = new LatLng(-17.3935, -66.1459);
        mymap.addMarker(new MarkerOptions().position(cristoDeLaConcordia).title("Cristo de la Concordia").icon(customIcon));
        Cochabamba.add(cristoDeLaConcordia);

        LatLng parqueTunari = new LatLng(-17.3603, -66.2618);
        mymap.addMarker(new MarkerOptions().position(parqueTunari).title("Parque Nacional Tunari").icon(customIcon));
        Cochabamba.add(parqueTunari);

        LatLng lagunaAlalay = new LatLng(-17.4076, -66.1403);
        mymap.addMarker(new MarkerOptions().position(lagunaAlalay).title("Laguna Alalay").icon(customIcon));
        Cochabamba.add(lagunaAlalay);

        LatLng incachaca = new LatLng(-17.2817, -66.1984);
        mymap.addMarker(new MarkerOptions().position(incachaca).title("Incachaca").icon(customIcon));
        Cochabamba.add(incachaca);

        LatLng canterasDeSillar = new LatLng(-17.4833, -66.1333);
        mymap.addMarker(new MarkerOptions().position(canterasDeSillar).title("Canteras de Sillar").icon(customIcon));
        Cochabamba.add(canterasDeSillar);

// Oruro
        LatLng virgenDelSocavon = new LatLng(-17.9674, -67.1100);
        mymap.addMarker(new MarkerOptions().position(virgenDelSocavon).title("Virgen del Socavón").icon(customIcon));
        Oruro.add(virgenDelSocavon);

        LatLng salarDeCoipasa = new LatLng(-19.0003, -68.1764);
        mymap.addMarker(new MarkerOptions().position(salarDeCoipasa).title("Salar de Coipasa").icon(customIcon));
        Oruro.add(salarDeCoipasa);

        LatLng lagunaUruUru = new LatLng(-17.9838, -67.0667);
        mymap.addMarker(new MarkerOptions().position(lagunaUruUru).title("Laguna Uru Uru").icon(customIcon));
        Oruro.add(lagunaUruUru);

        LatLng parqueAvaroa = new LatLng(-18.2801, -67.5747);
        mymap.addMarker(new MarkerOptions().position(parqueAvaroa).title("Parque Eduardo Avaroa").icon(customIcon));
        Oruro.add(parqueAvaroa);

        LatLng museoMineria = new LatLng(-17.9631, -67.1069);
        mymap.addMarker(new MarkerOptions().position(museoMineria).title("Museo de la Minería").icon(customIcon));
        Oruro.add(museoMineria);

// Potosí
        LatLng cerroRico = new LatLng(-19.5883, -65.7531);
        mymap.addMarker(new MarkerOptions().position(cerroRico).title("Cerro Rico").icon(customIcon));
        Potosi.add(cerroRico);

        LatLng casaMoneda = new LatLng(-19.5886, -65.7561);
        mymap.addMarker(new MarkerOptions().position(casaMoneda).title("Casa de la Moneda").icon(customIcon));
        Potosi.add(casaMoneda);

        LatLng salarDeUyuni = new LatLng(-20.1338, -67.4891);
        mymap.addMarker(new MarkerOptions().position(salarDeUyuni).title("Salar de Uyuni").icon(customIcon));
        Potosi.add(salarDeUyuni);

        LatLng lagunaColorada = new LatLng(-22.2028, -67.7736);
        mymap.addMarker(new MarkerOptions().position(lagunaColorada).title("Laguna Colorada").icon(customIcon));
        Potosi.add(lagunaColorada);

        LatLng torotoro = new LatLng(-18.1321, -65.7695);
        mymap.addMarker(new MarkerOptions().position(torotoro).title("Parque Nacional Torotoro").icon(customIcon));
        Potosi.add(torotoro);

// Tarija
        LatLng casaDorada = new LatLng(-21.5365, -64.7318);
        mymap.addMarker(new MarkerOptions().position(casaDorada).title("Casa Dorada").icon(customIcon));
        Tarija.add(casaDorada);

        LatLng valleConcepcion = new LatLng(-21.5069, -64.7092);
        mymap.addMarker(new MarkerOptions().position(valleConcepcion).title("Valle de la Concepción").icon(customIcon));
        Tarija.add(valleConcepcion);

        LatLng represaSanJacinto = new LatLng(-21.5141, -64.7336);
        mymap.addMarker(new MarkerOptions().position(represaSanJacinto).title("Represa San Jacinto").icon(customIcon));
        Tarija.add(represaSanJacinto);

        LatLng miradorLomaSanJuan = new LatLng(-21.5311, -64.7314);
        mymap.addMarker(new MarkerOptions().position(miradorLomaSanJuan).title("Mirador de la Loma de San Juan").icon(customIcon));
        Tarija.add(miradorLomaSanJuan);

        LatLng parqueNacionalSama = new LatLng(-21.4489, -64.7116);
        mymap.addMarker(new MarkerOptions().position(parqueNacionalSama).title("Parque Nacional Sama").icon(customIcon));
        Tarija.add(parqueNacionalSama);

// Chuquisaca (Sucre)
        LatLng casaDeLaLibertad = new LatLng(-19.0422, -65.2592);
        mymap.addMarker(new MarkerOptions().position(casaDeLaLibertad).title("Casa de la Libertad").icon(customIcon));
        Sucre.add(casaDeLaLibertad);

        LatLng parqueCretacico = new LatLng(-19.0495, -65.2672);
        mymap.addMarker(new MarkerOptions().position(parqueCretacico).title("Parque Cretácico").icon(customIcon));
        Sucre.add(parqueCretacico);

        LatLng plaza25DeMayo = new LatLng(-19.0433, -65.2592);
        mymap.addMarker(new MarkerOptions().position(plaza25DeMayo).title("Plaza 25 de Mayo").icon(customIcon));
        Sucre.add(plaza25DeMayo);

        LatLng castilloLaGlorieta = new LatLng(-19.0415, -65.2764);
        mymap.addMarker(new MarkerOptions().position(castilloLaGlorieta).title("Castillo de La Glorieta").icon(customIcon));
        Sucre.add(castilloLaGlorieta);

        LatLng miradorRecoleta = new LatLng(-19.0423, -65.2565);
        mymap.addMarker(new MarkerOptions().position(miradorRecoleta).title("Mirador de la Recoleta").icon(customIcon));
        Sucre.add(miradorRecoleta);

// Pando
        LatLng plazaGermanBusch = new LatLng(-11.0227, -68.7664);
        mymap.addMarker(new MarkerOptions().position(plazaGermanBusch).title("Plaza Germán Busch").icon(customIcon));
        Pando.add(plazaGermanBusch);

        LatLng ríoAcre = new LatLng(-11.0231, -68.7576);
        mymap.addMarker(new MarkerOptions().position(ríoAcre).title("Río Acre").icon(customIcon));
        Pando.add(ríoAcre);

        LatLng reservaManuripi = new LatLng(-11.7000, -67.6667);
        mymap.addMarker(new MarkerOptions().position(reservaManuripi).title("Reserva de Manuripi").icon(customIcon));
        Pando.add(reservaManuripi);

        LatLng puertoEvoMorales = new LatLng(-11.0355, -68.7586);
        mymap.addMarker(new MarkerOptions().position(puertoEvoMorales).title("Puerto Evo Morales").icon(customIcon));
        Pando.add(puertoEvoMorales);

        LatLng plazaBolivar = new LatLng(-11.0228, -68.7690);
        mymap.addMarker(new MarkerOptions().position(plazaBolivar).title("Plaza Bolívar").icon(customIcon));
        Pando.add(plazaBolivar);

// Beni
        LatLng lagunaSuarez = new LatLng(-14.8358, -64.9038);
        mymap.addMarker(new MarkerOptions().position(lagunaSuarez).title("Laguna Suárez").icon(customIcon));
        Beni.add(lagunaSuarez);

        LatLng parqueItenez = new LatLng(-12.5000, -64.6667);
        mymap.addMarker(new MarkerOptions().position(parqueItenez).title("Parque Iténez").icon(customIcon));
        Beni.add(parqueItenez);

        LatLng ríoMamore = new LatLng(-14.5833, -64.9000);
        mymap.addMarker(new MarkerOptions().position(ríoMamore).title("Río Mamoré").icon(customIcon));
        Beni.add(ríoMamore);

        LatLng catedralTrinidad = new LatLng(-14.8357, -64.8998);
        mymap.addMarker(new MarkerOptions().position(catedralTrinidad).title("Catedral de Trinidad").icon(customIcon));
        Beni.add(catedralTrinidad);

        LatLng museoKennethLee = new LatLng(-14.8400, -64.9004);
        mymap.addMarker(new MarkerOptions().position(museoKennethLee).title("Museo Kenneth Lee").icon(customIcon));
        Beni.add(museoKennethLee);


        mymap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker!=ma){
                    ma=marker;
                    clearPreviousRoute();
                }
                LatLng p=marker.getPosition();
                    po.add(p);
                    if(currentLocation!=null){
                        po.add(currentLocation);
                        drawRoute(po);
                    }

                return false;
            }
        });

        drawPolygono(Beni);
        drawPolygono(LaPaz);
        drawPolygono(Cochabamba);
        drawPolygono(Sucre);
        drawPolygono(SantaCruz);
        drawPolygono(Tarija);
        drawPolygono(Oruro);
        drawPolygono(Potosi);
        drawPolygono(Pando);

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
//        LatLng tarija = new LatLng(-21.53500, -64.73000);
//        mymap.addMarker(new MarkerOptions().position(tarija).title("Tarija").icon(customIcon));
//        po.add(tarija);
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
    private void clearPreviousRoute() {
        for (Polyline polyline : lines) {
            polyline.remove();
        }
        lines.clear();
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
                            currentLocation= new LatLng(location.getLatitude(),location.getLongitude());
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
        if (!points.isEmpty() && points.size() != 1) {
            PolylineOptions polylineOptions = new PolylineOptions()
                    .add(points.pop())
                    .add(points.peek())
                    .width(10)
                    .color(ContextCompat.getColor(this, R.color.black));

            // Añade cada polilínea a la lista y al mapa
            Polyline polyline = mymap.addPolyline(polylineOptions);
            lines.add(polyline);

            drawRoute(points); // Llamada recursiva para seguir dibujando la ruta
        }
    }

    private void drawPolygono(List<LatLng> points) {
        PolygonOptions polygonOptions = new PolygonOptions()
                .addAll(points)
                .strokeWidth(10)
                .strokeColor(ContextCompat.getColor(this,R.color.black));
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