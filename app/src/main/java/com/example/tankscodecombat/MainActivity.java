package com.example.tankscodecombat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private File bot1File = null;
    private File bot2File = null;
    private TextView tvBot1Status, tvBot2Status;

    // Launcher for picking bot1 from Drive
    private final ActivityResultLauncher<String> selectBot1Launcher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    File temp = copyUriToCache(uri, "bot1_temp");
                    bot1File = copyUriToDex(temp, "bot1");
                    tvBot1Status.setText(bot1File != null ? "Bot 1 Ready!" : "Error loading");
                }
            }
    );

    // Launcher for picking bot2 from Drive
    private final ActivityResultLauncher<String> selectBot2Launcher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    File temp = copyUriToCache(uri, "bot2_temp");
                    bot2File = copyUriToDex(temp, "bot2");
                    tvBot2Status.setText(bot2File != null ? "Bot 2 Ready!" : "Error loading");
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvBot1Status = findViewById(R.id.tvBot1Status);
        tvBot2Status = findViewById(R.id.tvBot2Status);

        // Button listeners
        findViewById(R.id.btnSelectBot1).setOnClickListener(v -> selectBot1Launcher.launch("*/*"));
        findViewById(R.id.btnSelectBot2).setOnClickListener(v -> selectBot2Launcher.launch("*/*"));
    }

    // Start VisualGame activity and pass paths
    public void startGame(View view) {
        if (bot1File == null || bot2File == null) {
            Toast.makeText(this, "Please select both bots first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, VisualGame.class);
        intent.putExtra("BOT1_PATH", bot1File.getAbsolutePath());
        intent.putExtra("BOT2_PATH", bot2File.getAbsolutePath());
        Log.d("debug", "Starting VisualGame with bots: " +
                bot1File.getAbsolutePath() + " , " + bot2File.getAbsolutePath());
        startActivity(intent);
    }

    // Copy a Uri (from Drive or Storage) to app cache
    private File copyUriToCache(Uri uri, String fileName) {
        try {
            File file = new File(getCacheDir(), fileName);
            try (InputStream inputStream = getContentResolver().openInputStream(uri);
                 OutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[4096];
                int read;
                while ((read = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, read);
                }
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Copy a file to code_cache, rename to .dex, and make read-only
    private File copyUriToDex(File srcFile, String dexName) {
        if (srcFile == null) return null;
        try {
            File dexFile = new File(getCodeCacheDir(), dexName + ".dex");
            if (dexFile.exists()) dexFile.delete();

            try (InputStream in = new FileInputStream(srcFile);
                 OutputStream out = new FileOutputStream(dexFile)) {
                byte[] buffer = new byte[4096];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
            }

            // DO NOT set read-only — DexClassLoader needs write access
            return dexFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
