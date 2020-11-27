package com.example.appdenunciaciudadana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdenunciaciudadana.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity2 extends AppCompatActivity {

    EditText txt_email, txt_name, txt_phone, txt_passoword;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        txt_email = findViewById(R.id.register_email);
        txt_name = findViewById(R.id.register_name);
        txt_phone = findViewById(R.id.register_phone);
        txt_passoword = findViewById(R.id.register_clave);

        auth = FirebaseAuth.getInstance();
    }

    public void create_account(View view) {
        final String email, name, phone, password;
        email = txt_email.getText().toString();
        name = txt_name.getText().toString();
        phone = txt_phone.getText().toString();
        password = txt_phone.getText().toString();

        if (email.isEmpty() || name.isEmpty() || phone.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"debe completar todos los campos",Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("usuarios");
                                Usuario user = new Usuario();
                                user.setEmail(email);
                                user.setName(name);
                                user.setPhone(phone);
                                user.setUid(task.getResult().getUser().getUid());

                                myRef.push().setValue(user);

                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(RegisterActivity2.this,msg,Toast.LENGTH_LONG).show();
                            }

                        }
                    });

        }
    }

    public void launch_login(View view) {
        Intent intent = new Intent(this, LoginActivity2.class);
        startActivity(intent);
        finish();
    }
}