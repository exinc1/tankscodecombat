package com.example.tankscodecombat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseUser;
import android.content.Intent;

public class LogIn extends AppCompatActivity {
    private EditText ETpassword;
    private EditText ETemail;
    private FirebaseAuth ref;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ETpassword = findViewById(R.id.password);
        ETemail = findViewById(R.id.email);
        ref = FirebaseAuth.getInstance();

        // Initialize SharedPreferences
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        // Check if a user is already saved in SharedPreferences
        String savedUid = prefs.getString("uid", null);
        if (savedUid != null) {
            // go to MainActivity automatically
            Intent intent = new Intent(LogIn.this, HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    public void Login(View view) {
        String email = ETemail.getText().toString().trim();
        String password = ETpassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LogIn.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Connecting...");
            pd.setMessage("Signing in...");
            pd.show();

            // sign in with Firebase Authentication
            ref.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    pd.dismiss();
                    if (task.isSuccessful()) {
                        Log.i("LogIn", "signInWithEmailAndPassword:success");
                        FirebaseUser user = ref.getCurrentUser();
                        Toast.makeText(LogIn.this, "Login successful\nUid: " + user.getUid(), Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("uid", user.getUid());
                        editor.apply();

                        Log.d("debug", "main has started");

                        // go to MainActivity after successful login
                        Intent intent = new Intent(LogIn.this, HomePage.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Exception exp = task.getException();
                        if (exp instanceof FirebaseAuthInvalidUserException) {
                            Toast.makeText(LogIn.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(LogIn.this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                        }
                        else if (exp instanceof FirebaseNetworkException) {
                            Toast.makeText(LogIn.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LogIn.this, "Authentication failed: " + exp.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    public void goToSignIn(View view) {
        // go to sign in
        Intent intent = new Intent(LogIn.this, SignIn.class);
        startActivity(intent);
        finish();
    }
}