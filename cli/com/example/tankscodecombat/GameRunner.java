package com.example.tankscodecombat;

import java.io.*;

public class GameRunner {
    
    // Smart bot that uses all available state info
    private static final String BOT_SMART = 
        "function run(state) {\n" +
        "    // Aim turret at enemy\n" +
        "    if (state.turret !== state.radar) {\n" +
        "        return { type: 'ROTATE_TURRET', param: state.radar };\n" +
        "    }\n" +
        "    // If very close and have ammo, fire!\n" +
        "    if (state.distance < 20 && state.ammoCount > 0) {\n" +
        "        return { type: 'FIRE', param: 0 };\n" +
        "    }\n" +
        "    // If out of ammo, reload\n" +
        "    if (state.ammoCount === 0) {\n" +
        "        return { type: 'RELOAD', param: 1 }; // BULLET\n" +
        "    }\n" +
        "    // If far, move toward enemy\n" +
        "    if (state.distance > 30) {\n" +
        "        return { type: 'MOVE', param: 2 }; // FAST\n" +
        "    }\n" +
        "    // Strafe perpendicular\n" +
        "    var strafeDir = state.direction + 90;\n" +
        "    return { type: 'MOVE', param: 1 }; // SLOW\n" +
        "}";
    
    // Aggressive bot - always approaches and fires
    private static final String BOT_AGGRO = 
        "function run(state) {\n" +
        "    // Aim and fire if possible\n" +
        "    if (state.turret !== state.radar && state.ammoCount > 0) {\n" +
        "        return { type: 'ROTATE_TURRET', param: state.radar };\n" +
        "    }\n" +
        "    // Face enemy\n" +
        "    if (state.direction !== state.radar) {\n" +
        "        return { type: 'ROTATE', param: state.radar };\n" +
        "    }\n" +
        "    // Fire if aimed and have ammo\n" +
        "    if (state.ammoCount > 0) {\n" +
        "        return { type: 'FIRE', param: 0 };\n" +
        "    }\n" +
        "    // Reload to BULLET (index 1)\n" +
        "    return { type: 'RELOAD', param: 1 };\n" +
        "}";
    
    // Defensive bot - keeps distance, uses missiles
    private static final String BOT_DEFENSE = 
        "function run(state) {\n" +
        "    // If too close, back up\n" +
        "    if (state.distance < 15) {\n" +
        "        return { type: 'MOVE', param: 0 }; // STOP\n" +
        "    }\n" +
        "    // Aim turret at enemy\n" +
        "    if (state.turret !== state.radar) {\n" +
        "        return { type: 'ROTATE_TURRET', param: state.radar };\n" +
        "    }\n" +
        "    // Use missile if far and have it\n" +
        "    if (state.distance > 25 && state.ammoName === 'MISSILE' && state.ammoCount > 0) {\n" +
        "        return { type: 'FIRE', param: 0 };\n" +
        "    }\n" +
        "    // Switch to missile and reload if we have it\n" +
        "    if (state.ammoName !== 'MISSILE') {\n" +
        "        return { type: 'RELOAD', param: 2 }; // MISSILE\n" +
        "    }\n" +
        "    // Keep distance\n" +
        "    if (state.distance > 35) {\n" +
        "        return { type: 'MOVE', param: 2 }; // FAST toward\n" +
        "    }\n" +
        "    return { type: 'MOVE', param: 1 }; // SLOW\n" +
        "}";
    
    // Shotgun bot - close range killer
    private static final String BOT_SHOTGUN = 
        "function run(state) {\n" +
        "    // Get very close\n" +
        "    if (state.distance > 15) {\n" +
        "        if (state.direction !== state.radar) {\n" +
        "            return { type: 'ROTATE', param: state.radar };\n" +
        "        }\n" +
        "        return { type: 'MOVE', param: 2 }; // FAST\n" +
        "    }\n" +
        "    // Aim turret\n" +
        "    if (state.turret !== state.radar) {\n" +
        "        return { type: 'ROTATE_TURRET', param: state.radar };\n" +
        "    }\n" +
        "    // Fire shotgun if we have it\n" +
        "    if (state.ammoName === 'SHOTGUN' && state.ammoCount > 0) {\n" +
        "        return { type: 'FIRE', param: 0 };\n" +
        "    }\n" +
        "    // Switch to shotgun\n" +
        "    return { type: 'RELOAD', param: 1 }; // SHOTGUN\n" +
        "}";

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║         TANK COMBAT - NEW MECHANICS TESTING                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        
        runTests();
    }
    
    private static void runTests() {
        System.out.println("\n┌───────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 1: Smart Bot vs Aggressive Bot (should have winners)       │");
        System.out.println("└───────────────────────────────────────────────────────────────┘");
        runGame(BOT_SMART, BOT_AGGRO, "Smart vs Aggressive");
        
        System.out.println("\n┌───────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 2: Defensive Bot vs Aggressive Bot                         │");
        System.out.println("└───────────────────────────────────────────────────────────────┘");
        runGame(BOT_DEFENSE, BOT_AGGRO, "Defensive vs Aggressive");
        
        System.out.println("\n┌───────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 3: Shotgun Bot vs Aggressive Bot (close range)            │");
        System.out.println("└───────────────────────────────────────────────────────────────┘");
        runGame(BOT_SHOTGUN, BOT_AGGRO, "Shotgun vs Aggressive");
        
        System.out.println("\n┌───────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 4: Two Smart Bots battling                                 │");
        System.out.println("└───────────────────────────────────────────────────────────────┘");
        runGame(BOT_SMART, BOT_SMART, "Smart vs Smart");
        
        System.out.println("\n┌───────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 5: Verify ammo system - shotgun bot vs stationary          │");
        System.out.println("└───────────────────────────────────────────────────────────────┘");
        runGame(BOT_SHOTGUN, 
            "function run(state) { return { type: 'MOVE', param: 0 }; }", 
            "Shotgun vs Stationary");
    }
    
    private static void runGame(String bot1Code, String bot2Code, String description) {
        try {
            System.out.println("  Bot 1: " + description.split(" vs ")[0]);
            System.out.println("  Bot 2: " + description.split(" vs ")[1]);
            System.out.println();
            
            Tank bot1 = new JSBotTank(bot1Code);
            Tank bot2 = new JSBotTank(bot2Code);
            
            GameCLI game = new GameCLI(bot1, bot2);
            int result = game.run();
            Logs[] logs = game.getLog();
            
            VisualGameCLI.printSummary(logs, result);
            
        } catch (Exception e) {
            System.out.println("  ❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
