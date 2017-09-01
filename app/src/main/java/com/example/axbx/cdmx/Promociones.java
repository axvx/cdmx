package com.example.axbx.cdmx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class Promociones extends AppCompatActivity  implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);

        // data to populate the RecyclerView with

        String[] data= {"Papa Johns 20% en pizzas grandes y extragrandes",
                "Idea Interior Obten 5% en tu compra total",
                "Burguer King, Café Americano chico GRATIS",
                "Farmacias Benavides 10% en medicamentos genéricos",
                "El Tizoncito 20% en Tacos Dorados",
                "Chils 10% de descuento en tus compras",
                "Zona Fitness Precio especial en la anualidad",
                "Cinepolis GRATIS una entrada cada mes",
                "Wingstop $30 de descuento en la compra de $250",
                "Italianis Ahorra 10% en tu cuenta"
        };


        //Arrgelo de imagenes

         Integer [] textureArrayWin = {

                 R.drawable.promopapajohns,
                 R.drawable.promoidea,
                 R.drawable.promoburguerking,
                 R.drawable.promobenavides,
                 R.drawable.promotizoncito,
                R.drawable.promochilis,
                 R.drawable.promozonafitness,
                R.drawable.promocinepolis,
                 R.drawable.promowingstop,
                R.drawable.promoitaliannis,

        };
        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this, data,textureArrayWin);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clivvcked number " + adapter.getItem(position) + ", which is at cell position " + position);

         if(position==0){




             Intent myIntent = new Intent(Promociones.this, Detalle.class);

             Promociones.this.startActivity(myIntent);

         }

    }
}
