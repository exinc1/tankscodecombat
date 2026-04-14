package com.example.tankscodecombat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualGame extends AppCompatActivity {
    public static final String LOADED_GAME_KEY = "LOADED_GAME";
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

        // Zoom buttons
        Button zoomIn = findViewById(R.id.zoomIn);
        Button zoomOut = findViewById(R.id.zoomOut);
        if (zoomIn != null) zoomIn.setOnClickListener(v -> gameBoard.zoomIn());
        if (zoomOut != null) zoomOut.setOnClickListener(v -> gameBoard.zoomOut());

        // Buttons
        Button skipStart = findViewById(R.id.skipStart);
        Button skipBack10 = findViewById(R.id.skipBack10);
        Button prevButton = findViewById(R.id.prevButton);
        Button nextButton = findViewById(R.id.nextButton);
        Button skipForward10 = findViewById(R.id.skipForward10);
        Button skipEnd = findViewById(R.id.skipEnd);

        skipStart.setOnClickListener(gameBoard::skipToStart);
        prevButton.setOnClickListener(v -> gameBoard.prevMove());
        nextButton.setOnClickListener(v -> gameBoard.nextMove());
        skipBack10.setOnClickListener(v -> gameBoard.skipBackward10());
        skipForward10.setOnClickListener(v -> gameBoard.skipForward10());
        skipEnd.setOnClickListener(this::skipEnd);


        String jsCode1 = getIntent().getStringExtra(MainActivityFragment.BOT1_CODE_KEY);
        String jsCode2 = getIntent().getStringExtra(MainActivityFragment.BOT2_CODE_KEY);


        boolean isLoadedGame = getIntent().getBooleanExtra(LOADED_GAME_KEY, false);

        if (jsCode1 == null || jsCode2 == null) {
            Toast.makeText(this, "Bot code missing", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isLoadedGame) {
            // Load saved game
            gameResult = getIntent().getIntExtra("GAME_RESULT", -1);
            ArrayList<Map<String, Object>> logsList = (ArrayList<Map<String, Object>>) getIntent().getSerializableExtra("LOGS");
            Logs[] logs = logsListToArray(logsList);
            gameBoard.setLogs(logs);
        } else {
            try {
                // Create JS bots directly from code strings
                Tank bot1 = new JSBotTank(jsCode1);
                Tank bot2 = new JSBotTank(jsCode2);

                // run the Game
                game = new Game(this, bot1, bot2);
                gameResult = game.run();

                // display logs
                Logs[] logs = game.getLog();
                for (Logs l : logs) if (l != null) Log.d("debug", l.toString());

                gameBoard.setLogs(logs);

                // Save game to Firebase Realtime Database
                saveGame(jsCode1, jsCode2, gameResult, logs);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error loading bots: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    private void skipEnd(View v) {
        gameBoard.skipToEnd();
        String message;
        switch (gameResult) {
            case 1: message = "Tank 1 wins!"; break;
            case 2: message = "Tank 2 wins!"; break;
            case 0: message = "Illegal action!"; break;
            default: message = "No winner"; break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void saveGame(String jsCode1, String jsCode2, int gameResult, Logs[] logs) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Log.d("debug", "User not logged in, cannot save game");
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = user.getUid();

        // Create a unique key for the new game
        String gameId = database.getReference("users").child(userId).child("games").push().getKey();

        // Create game object
        Map<String, Object> game = new HashMap<>();
        game.put("bot1Code", jsCode1);
        game.put("bot2Code", jsCode2);
        game.put("result", gameResult);

        List<Map<String, Object>> logsList = new ArrayList<>();
        for (Logs l : logs) {
            if (l != null) {
                logsList.add(logToMap(l));
            }
        }
        game.put("logs", logsList);

        // Save to Firebase Realtime Database
        database.getReference("users").child(userId).child("games").child(gameId)
                .setValue(game)
                .addOnSuccessListener(aVoid -> Log.d("debug", "Game saved with ID: " + gameId))
                .addOnFailureListener(e -> Log.d("debug", "Error saving game", e));
    }

    private Map<String, Object> logToMap(Logs log) {
        Map<String, Object> map = new HashMap<>();
        map.put("tankId", log.get_tankId());
        Map<String, Object> actionMap = new HashMap<>();
        actionMap.put("type", log.get_action().getType().toString());
        actionMap.put("param", log.get_action().getParam());
        map.put("action", actionMap);
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put("x", log.get_location().getX());
        locationMap.put("y", log.get_location().getY());
        map.put("location", locationMap);
        map.put("tankDirection", log.get_tankDirection().getDegrees());
        map.put("turretDirection", log.get_turretDirection().getDegrees());
        map.put("health", log.get_health());
        return map;
    }

    private Logs[] logsListToArray(ArrayList<Map<String, Object>> logsList) {
        Logs[] logsArray = new Logs[logsList.size()];
        for (int i = 0; i < logsList.size(); i++) {
            Map<String, Object> logMap = logsList.get(i);
            Map<String, Object> actionMap = (Map<String, Object>) logMap.get("action");
            Action action = new Action(Action.ActionType.valueOf((String) actionMap.get("type")), ((Number) actionMap.get("param")).intValue());
            Map<String, Object> locationMap = (Map<String, Object>) logMap.get("location");
            Location location = new Location(((Number) locationMap.get("x")).intValue(), ((Number) locationMap.get("y")).intValue());
            Direction tankDirection = new Direction(((Number) logMap.get("tankDirection")).intValue());
            Direction turretDirection = new Direction(((Number) logMap.get("turretDirection")).intValue());
            int health = 100; // default
            if (logMap.containsKey("health")) {
                health = ((Number) logMap.get("health")).intValue();
            }
            Logs log = new Logs(((Number) logMap.get("tankId")).intValue(), action, location, tankDirection, turretDirection, health);
            logsArray[i] = log;
        }
        return logsArray;
    }
}
