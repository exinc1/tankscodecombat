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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivityFragment extends Fragment {

    private File bot1File, bot2File;
    private TextView tvBot1Status, tvBot2Status;

    private final ActivityResultLauncher<String> selectBot1Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    File temp = copyUriToCache(uri, "bot1_temp");
                    bot1File = copyUriToDex(temp, "bot1");
                    tvBot1Status.setText(bot1File != null ? "Bot 1 Ready!" : "Error loading");
                }
            });

    private final ActivityResultLauncher<String> selectBot2Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    File temp = copyUriToCache(uri, "bot2_temp");
                    bot2File = copyUriToDex(temp, "bot2");
                    tvBot2Status.setText(bot2File != null ? "Bot 2 Ready!" : "Error loading");
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
                .setOnClickListener(v -> selectBot1Launcher.launch("*/*"));

        view.findViewById(R.id.btnSelectBot2)
                .setOnClickListener(v -> selectBot2Launcher.launch("*/*"));

        view.findViewById(R.id.goScoreBoard)
                .setOnClickListener(this::startGame);

        return view;
    }

    private void startGame(View v) {
        if (bot1File == null || bot2File == null) {
            Toast.makeText(requireContext(),
                    "Please select both bots first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(requireActivity(), VisualGame.class);
        intent.putExtra("BOT1_PATH", bot1File.getAbsolutePath());
        intent.putExtra("BOT2_PATH", bot2File.getAbsolutePath());

        Log.d("debug", "Starting VisualGame");
        startActivity(intent);
    }

    private File copyUriToCache(Uri uri, String name) {
        try {
            File file = new File(requireContext().getCacheDir(), name);
            try (InputStream in = requireContext().getContentResolver().openInputStream(uri);
                 OutputStream out = new FileOutputStream(file)) {

                byte[] buf = new byte[4096];
                int r;
                while ((r = in.read(buf)) != -1) out.write(buf, 0, r);
            }
            return file;
        } catch (Exception e) {
            return null;
        }
    }

    private File copyUriToDex(File src, String name) {
        if (src == null) return null;
        try {
            File dex = new File(requireContext().getCodeCacheDir(), name + ".dex");
            if (dex.exists()) dex.delete();

            try (InputStream in = new FileInputStream(src);
                 OutputStream out = new FileOutputStream(dex)) {

                byte[] buf = new byte[4096];
                int r;
                while ((r = in.read(buf)) != -1) out.write(buf, 0, r);
            }
            return dex;
        } catch (Exception e) {
            return null;
        }
    }
}