package com.example.tankscodecombat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import android.util.Log;

public class VisualGame extends AppCompatActivity {
    private Game game;
    private GameBoardView gameBoard;
    private int gameResult;

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

        gameBoard = findViewById(R.id.gameBoard);
        Button skipEnd = findViewById(R.id.skipEnd);

        // get dex file paths from Intent
        String path1 = getIntent().getStringExtra("BOT1_PATH");
        String path2 = getIntent().getStringExtra("BOT2_PATH");

        if (path1 == null || path2 == null) {
            Toast.makeText(this, "Files missing", Toast.LENGTH_SHORT).show();
            return;
        }

        File file1 = new File(path1);
        File file2 = new File(path2);

        // create writable optimized directory
        File optimizedDir = new File(getCodeCacheDir(), "opt_dex");
        if (!optimizedDir.exists()) optimizedDir.mkdirs();

        try {
            // Load bots from dex files
            Tank bot1 = BotLoader.loadBot(this, file1, optimizedDir);
            Tank bot2 = BotLoader.loadBot(this, file2, optimizedDir);

            // run the Game
            game = new Game(this, bot1, bot2);
            gameResult = game.run();

            // display logs
            Logs[] logs = game.getLog();
            for (Logs l : logs) if (l != null) Log.d("debug", l.toString());

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading bots: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // skip button
        skipEnd.setOnClickListener(v -> {
            gameBoard.skipToEnd();
            String message;
            switch (gameResult) {
                case 1: message = "Tank 1 wins!"; break;
                case 2: message = "Tank 2 wins!"; break;
                case 0: message = "Illegal action!"; break;
                default: message = "No winner"; break;
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}
