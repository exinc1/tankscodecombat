package com.example.tankscodecombat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.google.firebase.database.DatabaseReference;
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
    private int botSlotToLoad;

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
                            selectBot1Launcher.launch("application/javascript");
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
                            selectBot2Launcher.launch("application/javascript");
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userBotsReference = database.getReference("users").child(user.getUid()).child("bots");

        ArrayList<String> bots = new ArrayList<>();
        ValueEventListener botListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (!bots.contains(snapshot.getKey())) {
                            bots.add(snapshot.getKey());
                        }
                    }
                }

                String[] botsArray = bots.toArray(new String[0]);
                AlertDialog alertDialog = new AlertDialog.Builder(requireContext()).setItems(botsArray, (dialog, which) -> {
                    String selectedBot = bots.get(which);
                    getBotCodeFromDB(selectedBot);
                }).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(requireContext(), "Failed to load bots: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        userBotsReference.addValueEventListener(botListener);
    }

    private void getBotCodeFromDB(String botName) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference botCodeReference = database.getReference("users").child(user.getUid()).child("bots").child(botName);

        botCodeReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                String code = task.getResult().child("code").getValue(String.class);

                if (code != null) {
                    if (botSlotToLoad == 1) {
                        bot1Code = code;
                        tvBot1Status.setText("Bot 1 Ready!");
                    }
                    else if (botSlotToLoad == 2) {
                        bot2Code = code;
                        tvBot2Status.setText("Bot 2 Ready!");
                    }
                }
            }
        });
    }
}
