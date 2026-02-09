package com.example.tankscodecombat;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivityFragment extends Fragment {

    private String bot1Code, bot2Code;
    private TextView tvBot1Status, tvBot2Status;

    private final ActivityResultLauncher<String> selectBot1Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    bot1Code = readJsFile(uri);
                    tvBot1Status.setText(bot1Code != null ? "Bot 1 Ready!" : "Error loading");
                }
            });

    private final ActivityResultLauncher<String> selectBot2Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    bot2Code = readJsFile(uri);
                    tvBot2Status.setText(bot2Code != null ? "Bot 2 Ready!" : "Error loading");
                }
            });

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable android.os.Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        tvBot1Status = view.findViewById(R.id.tvBot1Status);
        tvBot2Status = view.findViewById(R.id.tvBot2Status);

        view.findViewById(R.id.btnSelectBot1)
                .setOnClickListener(v -> selectBot1Launcher.launch("application/javascript"));

        view.findViewById(R.id.btnSelectBot2)
                .setOnClickListener(v -> selectBot2Launcher.launch("application/javascript"));

        view.findViewById(R.id.goScoreBoard)
                .setOnClickListener(this::startGame);

        return view;
    }

    private void startGame(View v) {
        if (bot1Code == null || bot2Code == null) {
            Toast.makeText(requireContext(),
                    "Please select both bots first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(requireActivity(), VisualGame.class);
        intent.putExtra("BOT1_CODE", bot1Code);
        intent.putExtra("BOT2_CODE", bot2Code);

        Log.d("debug", "Starting VisualGame with JS bots");
        startActivity(intent);
    }

    private String readJsFile(Uri uri) {
        try (InputStream in = requireContext().getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
