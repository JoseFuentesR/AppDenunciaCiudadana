package com.example.appdenunciaciudadana.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdenunciaciudadana.R;
import com.example.appdenunciaciudadana.model.Denunciar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DenunciarFragment extends Fragment {
        EditText txt_titulo , txt_direccion;
        Button button;

        FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_denunciar, container, false);

        txt_titulo = view.findViewById(R.id.txt_nuevo_titulo);
        txt_direccion = view.findViewById(R.id.txt_nueva_direccion);
        button = view.findViewById(R.id.btn_confirmar_denuncia);

        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txt_titulo.getText().toString();
                String direccion = txt_direccion.getText().toString();
                String uid = auth.getCurrentUser().getUid();

                if (titulo.isEmpty() || direccion.isEmpty()){
                    Toast.makeText(getActivity(),"faltan datos",Toast.LENGTH_LONG).show();

                }else{

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Denuncias").child(uid);

                    Denunciar denunciar = new Denunciar();
                    denunciar.setTitulo(titulo);
                    denunciar.setDescripcion(direccion);
                    myref.push().setValue(denunciar);
                    Toast.makeText(getActivity(),"creada",Toast.LENGTH_LONG).show();

                }




            }
        });

        return view;
    }
}