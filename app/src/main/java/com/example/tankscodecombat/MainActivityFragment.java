package com.example.tankscodecombat;

import android.app.AlertDialog;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivityFragment extends Fragment {
    public static String BOT1_CODE_KEY = "BOT1_CODE";
    public static String BOT2_CODE_KEY = "BOT2_CODE";

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
        intent.putExtra(BOT1_CODE_KEY, bot1Code);
        intent.putExtra(BOT2_CODE_KEY, bot2Code);

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

    private void loadSavedGames() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(requireContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("users").child(user.getUid()).child("games")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Map<String, Object>> games = new ArrayList<>();
                        for (DataSnapshot gameSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> game = (Map<String, Object>) gameSnapshot.getValue();
                            if (game != null) {
                                games.add(game);
                            }
                        }
                        showGamesDialog(games);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(requireContext(), "Error loading games", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showGamesDialog(List<Map<String, Object>> games) {
        if (games.isEmpty()) {
            Toast.makeText(requireContext(), "No saved games", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] gameNames = new String[games.size()];
        for (int i = 0; i < games.size(); i++) {
            int result = ((Long) games.get(i).get("result")).intValue();
            String resultStr = result == 1 ? "Tank 1 Wins" : result == 2 ? "Tank 2 Wins" : "No Winner";
            gameNames[i] = "Game " + (i + 1) + ": " + resultStr;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select a Saved Game")
                .setItems(gameNames, (dialog, which) -> {
                    Map<String, Object> selectedGame = games.get(which);
                    startLoadedGame(selectedGame);
                });
        builder.show();
    }

    private void startLoadedGame(Map<String, Object> game) {
        String bot1Code = (String) game.get("bot1Code");
        String bot2Code = (String) game.get("bot2Code");
        int gameResult = ((Long) game.get("result")).intValue();
        List<Map<String, Object>> logsList = (List<Map<String, Object>>) game.get("logs");

        Intent intent = new Intent(requireActivity(), VisualGame.class);
        intent.putExtra("BOT1_CODE", bot1Code);
        intent.putExtra("BOT2_CODE", bot2Code);
        intent.putExtra("LOADED_GAME", true);
        intent.putExtra("GAME_RESULT", gameResult);
        intent.putExtra("LOGS", new ArrayList<>(logsList));

        startActivity(intent);
    }
}
