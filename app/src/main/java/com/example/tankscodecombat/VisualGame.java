package com.example.tankscodecombat;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.Map;

public class VisualGame extends AppCompatActivity {
    public final int MAX_NUMBER_OF_TURNS = 100;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visual_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // get file paths from Intent
        String path1 = getIntent().getStringExtra("BOT1_PATH");
        String path2 = getIntent().getStringExtra("BOT2_PATH");

        if (path1 == null || path2 == null) {
            Toast.makeText(this, "Files missing", Toast.LENGTH_SHORT).show();
            return;
        }

        File file1 = new File(path1);
        File file2 = new File(path2);

        // run the Game Logic
        try {
            game = new Game(file1, file2);

            int result = game.run(MAX_NUMBER_OF_TURNS);

            // display Results
            Map<Integer, Action> doc = game.getDocument();

            StringBuilder logBuilder = new StringBuilder();


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}