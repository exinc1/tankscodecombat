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
        Log.d("debug", "vgame has started");
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
            game = new Game(this, file1, file2);
            gameResult = game.run(); // store the result

            // display Results in Logcat
            Logs[] logs = game.getLog();
            for (Logs l: logs) {
                if (l != null) Log.d("debug", l.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // set listener INSIDE onCreate
        skipEnd.setOnClickListener(v -> {
            // Move the board to the end
            gameBoard.skipToEnd();

            // Show the winner
            String message;
            switch (gameResult) {
                case 1:
                    message = "Tank 1 wins!";
                    break;
                case 2:
                    message = "Tank 2 wins!";
                    break;
                case 0:
                    message = "Illegal action!";
                    break;
                default:
                    message = "No winner";
                    break;
            }

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}
