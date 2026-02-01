package com.example.tankscodecombat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LogoutFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Delay by 1 frame to avoid fragment transaction conflicts
        new Handler(Looper.getMainLooper()).post(() -> {
            // OPTIONAL: clear login/session here
            // SharedPreferences prefs = requireContext()
            //        .getSharedPreferences("auth", Context.MODE_PRIVATE);
            // prefs.edit().clear().apply();

            Intent intent = new Intent(requireActivity(), LogIn.class);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );
            startActivity(intent);
        });
    }
}