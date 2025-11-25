package com.example.tankscodecombat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private File bot1File = null;
    private File bot2File = null;
    private TextView tvBot1Status, tvBot2Status;

    // Launchers (to use drive)
    private final ActivityResultLauncher<String> selectBot1Launcher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    // copy file to cache so we can use it
                    bot1File = copyUriToCache(uri, "bot1.jar");
                    tvBot1Status.setText(bot1File != null ? "Bot 1 Ready!" : "Error loading");
                }
            }
    );

    private final ActivityResultLauncher<String> selectBot2Launcher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    bot2File = copyUriToCache(uri, "bot2_temp.jar");
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

        // Setup Button Listeners
        findViewById(R.id.btnSelectBot1).setOnClickListener(v -> selectBot1Launcher.launch("*/*"));
        findViewById(R.id.btnSelectBot2).setOnClickListener(v -> selectBot2Launcher.launch("*/*"));
    }

    public void startGame(View view) {
        if (bot1File == null || bot2File == null) {
            Toast.makeText(this, "Please select both bots first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, VisualGame.class);
        // Pass the file paths to the next activity
        intent.putExtra("BOT1_PATH", bot1File.getAbsolutePath());
        intent.putExtra("BOT2_PATH", bot2File.getAbsolutePath());
        startActivity(intent);
    }

    // Helper to copy file from Gallery/Storage to App Cache
    private File copyUriToCache(Uri uri, String fileName) {
        try {
            File file = new File(getCacheDir(), fileName);
            try (InputStream inputStream = getContentResolver().openInputStream(uri);
                 OutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            }
            return file;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}