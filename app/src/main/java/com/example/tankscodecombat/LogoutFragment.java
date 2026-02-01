package com.example.tankscodecombat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Delay by 1 frame to avoid fragment transaction conflicts
        new Handler(Looper.getMainLooper()).post(() -> {
            // Log out from Firebase
            FirebaseAuth.getInstance().signOut();

            // Clear saved UID from SharedPreferences
            SharedPreferences prefs = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            prefs.edit().remove("uid").apply(); // remove only the UID

            // Redirect to LogIn and clear back stack
            Intent intent = new Intent(requireActivity(), LogIn.class);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );
            startActivity(intent);
        });
    }
}