package com.example.tankscodecombat;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivityFragment extends Fragment {
    public static String BOT1_CODE_KEY = "BOT1_CODE";
    public static String BOT2_CODE_KEY = "BOT2_CODE";

    private String bot1Code, bot2Code;
    private TextView tvBot1Status, tvBot2Status;
    private int botSlotToLoad;

    private final ActivityResultLauncher<String> selectBot1Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    bot1Code = readJsFile(uri);
                    if (bot1Code != null) {
                        tvBot1Status.setText("Bot 1 Ready!");
                        showSaveDialog(bot1Code);
                    } else {
                        tvBot1Status.setText("Error loading");
                    }
                }
            });

    private final ActivityResultLauncher<String> selectBot2Launcher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    bot2Code = readJsFile(uri);
                    if (bot2Code != null) {
                        tvBot2Status.setText("Bot 2 Ready!");
                        showSaveDialog(bot2Code);
                    } else {
                        tvBot2Status.setText("Error loading");
                    }
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

        view.findViewById(R.id.btnSelectBot1).setOnClickListener(this::loadBot1);

        view.findViewById(R.id.btnSelectBot2).setOnClickListener(this::loadBot2);

        view.findViewById(R.id.startGame).setOnClickListener(this::startGame);

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

    private void loadBot1(View v) {
        String[] items = {"select from db", "select from device"};

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            botSlotToLoad = 1;
                            loadBotFromDB();
                            break;
                        case 1:
                            selectBot1Launcher.launch("*/*");
                            break;
                        case 2:
                            break;
                    }
                })
                .create();

        alertDialog.show();
    }

    private void loadBot2(View v) {
        String[] items = {"select from db", "select from device"};

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            botSlotToLoad = 2;
                            loadBotFromDB();
                            break;
                        case 1:
                            selectBot2Launcher.launch("*/*");
                            break;
                        case 2:
                            break;
                    }
                })
                .create();

        alertDialog.show();
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

    private void loadBotFromDB() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(requireContext(), "Please log in first", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tankscodecombat-default-rtdb.firebaseio.com");
        DatabaseReference userBotsReference = database.getReference("users").child(user.getUid()).child("bots");

        userBotsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> bots = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        bots.add(snapshot.getKey());
                    }
                }

                if (bots.isEmpty()) {
                    Toast.makeText(requireContext(), "No bots found in database", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] botsArray = bots.toArray(new String[0]);
                new AlertDialog.Builder(requireContext())
                        .setTitle("Select a Bot")
                        .setItems(botsArray, (dialog, which) -> {
                            String selectedBot = bots.get(which);
                            getBotCodeFromDB(selectedBot);
                        }).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to load bots", error.toException());
                Toast.makeText(requireContext(), "Failed to load bots: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBotCodeFromDB(String botName) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tankscodecombat-default-rtdb.firebaseio.com");
        DatabaseReference botCodeReference = database.getReference("users").child(user.getUid()).child("bots").child(botName);

        botCodeReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot != null && snapshot.exists()) {
                    String code = snapshot.child("code").getValue(String.class);

                    if (code != null) {
                        if (botSlotToLoad == 1) {
                            bot1Code = code;
                            tvBot1Status.setText("Bot 1 Ready!");
                        } else if (botSlotToLoad == 2) {
                            bot2Code = code;
                            tvBot2Status.setText("Bot 2 Ready!");
                        }
                        Toast.makeText(requireContext(), "Bot loaded successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Bot code is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Bot not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Exception e = task.getException();
                Log.e("Firebase", "Error getting bot code", e);
                String errorMessage = (e != null) ? e.getMessage() : "Unknown error";
                Toast.makeText(requireContext(), "Error taking bot from db: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSaveDialog(String code) {
        EditText input = new EditText(requireContext());
        input.setHint("Enter bot name");

        new AlertDialog.Builder(requireContext())
                .setTitle("Save Bot to Database?")
                .setMessage("Do you want to save this bot for future use?")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String botName = input.getText().toString().trim();
                    if (!botName.isEmpty()) {
                        saveBotToDB(botName, code);
                    } else {
                        Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void saveBotToDB(String botName, String code) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return;

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tankscodecombat-default-rtdb.firebaseio.com");
        DatabaseReference botRef = database.getReference("users")
                .child(user.getUid())
                .child("bots")
                .child(botName);

        Map<String, Object> botData = new HashMap<>();
        botData.put("code", code);

        botRef.setValue(botData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Bot saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("Firebase", "Failed to save bot", task.getException());
                Toast.makeText(requireContext(), "Failed to save bot to DB", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
