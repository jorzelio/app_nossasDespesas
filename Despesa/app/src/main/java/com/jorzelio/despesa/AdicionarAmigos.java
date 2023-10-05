package com.jorzelio.despesa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AdicionarAmigos extends AppCompatActivity {

    private RecyclerView amigos_adicionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionaramigos);

        amigos_adicionados = findViewById(R.id.lista_amigos);

        //Configura adapter
        AdapterAmigos adapterAmigos = new AdapterAmigos();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        amigos_adicionados.setLayoutManager(layoutManager);
        amigos_adicionados.setHasFixedSize(true);
        amigos_adicionados.setAdapter(adapterAmigos);

    }

}