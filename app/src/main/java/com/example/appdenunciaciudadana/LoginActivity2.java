package com.example.appdenunciaciudadana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity2 extends AppCompatActivity {

    EditText txt_email, txt_password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        txt_email = findViewById(R.id.login_email);
        txt_password = findViewById(R.id.login_clave);
        auth = FirebaseAuth.getInstance();
    }

    public void launch_register(View view) {
        Intent intent = new Intent(this, RegisterActivity2.class);
        startActivity(intent);
        finish();
    }

    public void singInt(View view) {
        String email = txt_email.getText().toString();
        String password = txt_email.getText().toString();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity2.this,"Complete la informacion",Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity2.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(LoginActivity2.this msg, Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }
                    });
        }
    }
}