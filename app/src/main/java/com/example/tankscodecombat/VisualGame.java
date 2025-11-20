package com.example.tankscodecombat;

import android.os.Bundle;

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

        File file1 = new File("bots/RandomBot1.class");
        File file2 = new File("bots/RandomBot1.class");

        try {
            // run game
            game = new Game(file1, file2);
            game.run(MAX_NUMBER_OF_TURNS);

            // get all the actions that happened in the game
            Map<Integer, Action> doc = game.getDocument();
            System.out.println(doc);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}