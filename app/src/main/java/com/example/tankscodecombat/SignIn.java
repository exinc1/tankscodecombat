package com.example.tankscodecombat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private EditText ETpassword;
    private EditText ETemail;
    private FirebaseAuth ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ETpassword = findViewById(R.id.password);
        ETemail = findViewById(R.id.email);
        ref = FirebaseAuth.getInstance();
    }

    public void createUser(View view) {
        String email = ETemail.getText().toString();
        String password = ETpassword.getText().toString();
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignIn.this, "please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Connecting...");
            pd.setMessage("connecting user...");
            pd.show();
            ref.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    pd.dismiss();
                    if (task.isSuccessful()) {
                        Log.i("MainActivity", "createUserWithEmailAndPassword:success");
                        FirebaseUser user = ref.getCurrentUser();
                        Toast.makeText(SignIn.this, "User created successfully\nUid: " + user.getUid(), Toast.LENGTH_SHORT).show();

                        // go to MainActivity after successful sign in
                        Intent intent = new Intent(SignIn.this, HomePage.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Exception exp = task.getException();
                        if (exp instanceof FirebaseAuthInvalidUserException) {
                            Toast.makeText(SignIn.this, "invalid email address.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthWeakPasswordException) {
                            Toast.makeText(SignIn.this, "Password too weak.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignIn.this, "User already exists.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(SignIn.this, "General authentication failure.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseNetworkException) {
                            Toast.makeText(SignIn.this, "Network error. please check your connection.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignIn.this, "An error occured. please try again later.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }
    }

    public void goToLogin(View view) {
        // go to login
        Intent intent = new Intent(SignIn.this, LogIn.class);
        startActivity(intent);
        finish();
    }
}