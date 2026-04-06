package com.example.tankscodecombat;

import java.util.Arrays;

public class VisualGameCLI {
    private Logs[] logs;
    private int currentIndex = 0;
    private static final int GRID_SIZE = 100;
    private static final int DISPLAY_WIDTH = 80;
    private static final int DISPLAY_HEIGHT = 40;
    
    public VisualGameCLI(Logs[] logs) {
        this.logs = logs != null ? logs : new Logs[0];
    }
    
    public void setCurrentIndex(int index) {
        this.currentIndex = Math.max(0, Math.min(index, logs.length - 1));
    }
    
    public int getCurrentIndex() {
        return currentIndex;
    }
    
    public int getTotalMoves() {
        return logs.length;
    }
    
    public void nextMove() {
        if (currentIndex < logs.length - 1) currentIndex++;
    }
    
    public void prevMove() {
        if (currentIndex > 0) currentIndex--;
    }
    
    public void skipForward10() {
        currentIndex = Math.min(currentIndex + 10, logs.length - 1);
    }
    
    public void skipBackward10() {
        currentIndex = Math.max(currentIndex - 10, 0);
    }
    
    public void skipToStart() {
        currentIndex = 0;
    }
    
    public void skipToEnd() {
        currentIndex = logs.length - 1;
    }
    
    public void render() {
        clearScreen();
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           TANK COMBAT - CLI VISUALIZER                              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════╣");
        
        if (logs.length == 0) {
            System.out.println("║                           NO GAME DATA AVAILABLE                                       ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════╝");
            return;
        }
        
        int turn = currentIndex / 2 + 1;
        int tank = (currentIndex % 2) + 1;
        System.out.printf("║  Turn: %d | Current: Tank %d | Move %d/%d                                        ║%n",
                turn, tank, currentIndex + 1, logs.length);
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════╣");
        
        char[][] grid = new char[DISPLAY_HEIGHT][DISPLAY_WIDTH];
        for (char[] row : grid) Arrays.fill(row, ' ');
        
        float[] bounds = computeBounds();
        float minX = bounds[0], minY = bounds[1], maxX = bounds[2], maxY = bounds[3];
        float scaleX = (DISPLAY_WIDTH - 4) / (maxX - minX + 1);
        float scaleY = (DISPLAY_HEIGHT - 2) / (maxY - minY + 1);
        float scale = Math.min(scaleX, scaleY);
        
        drawGrid(grid, minX, maxX, minY, maxY, scale);
        
        drawTanks(grid, minX, minY, scale);
        
        printGrid(grid);
        
        printTankInfo();
        
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  [←/→] Prev/Next  [H/L] Skip -10/+10  [B]egin  [E]nd  [Q]uit  [1-9] Jump to turn    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════╝");
    }
    
    private float[] computeBounds() {
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;
        
        for (int i = 0; i <= currentIndex && i < logs.length; i++) {
            Logs log = logs[i];
            if (log == null) continue;
            Location loc = log.get_location();
            if (loc == null) continue;
            minX = Math.min(minX, loc.getX());
            minY = Math.min(minY, loc.getY());
            maxX = Math.max(maxX, loc.getX());
            maxY = Math.max(maxY, loc.getY());
        }
        
        if (minX == Float.MAX_VALUE) {
            minX = 0; minY = 0; maxX = 100; maxY = 100;
        }
        
        float padding = 20;
        return new float[]{minX - padding, minY - padding, maxX + padding, maxY + padding};
    }
    
    private void drawGrid(char[][] grid, float minX, float maxX, float minY, float maxY, float scale) {
        for (int gx = (int)(minX / 10) * 10; gx <= maxX; gx += 10) {
            int screenX = (int)((gx - minX) * scale) + 2;
            if (screenX >= 0 && screenX < DISPLAY_WIDTH) {
                for (int y = 0; y < DISPLAY_HEIGHT - 2; y++) {
                    grid[y][screenX] = '·';
                }
            }
        }
        for (int gy = (int)(minY / 10) * 10; gy <= maxY; gy += 10) {
            int screenY = DISPLAY_HEIGHT - 3 - (int)((gy - minY) * scale);
            if (screenY >= 0 && screenY < DISPLAY_HEIGHT - 2) {
                Arrays.fill(grid[screenY], 2, DISPLAY_WIDTH - 2, '·');
            }
        }
    }
    
    private void drawTanks(char[][] grid, float minX, float minY, float scale) {
        for (int i = 0; i <= currentIndex && i < logs.length; i++) {
            Logs log = logs[i];
            if (log == null) continue;
            Location loc = log.get_location();
            if (loc == null) continue;
            
            int screenX = (int)((loc.getX() - minX) * scale) + 2;
            int screenY = DISPLAY_HEIGHT - 3 - (int)((loc.getY() - minY) * scale);
            
            if (screenX >= 1 && screenX < DISPLAY_WIDTH - 1 && screenY >= 1 && screenY < DISPLAY_HEIGHT - 2) {
                char tankChar = log.get_tankId() == 0 ? '1' : '2';
                char turretChar = '▄';
                char healthChar = log.get_health() > 50 ? '♥' : (log.get_health() > 0 ? '♡' : '✕');
                
                if (log.get_health() <= 0) {
                    tankChar = 'X';
                    turretChar = ' ';
                }
                
                if (i == currentIndex) {
                    grid[screenY][screenX] = '[';
                    if (screenX + 1 < DISPLAY_WIDTH - 1) grid[screenY][screenX + 1] = tankChar;
                    if (screenX + 2 < DISPLAY_WIDTH - 1) grid[screenY][screenX + 2] = ']';
                    
                    Direction turret = log.get_turretDirection();
                    if (turret != null && log.get_health() > 0) {
                        int rad = turret.getDegrees();
                        int tx = screenX + 1 + (int)(3 * Math.cos(Math.toRadians(rad)));
                        int ty = screenY - (int)(3 * Math.sin(Math.toRadians(rad)));
                        if (ty >= 0 && ty < DISPLAY_HEIGHT - 2 && tx >= 0 && tx < DISPLAY_WIDTH - 1) {
                            grid[ty][tx] = turretChar;
                        }
                    }
                } else {
                    grid[screenY][screenX] = tankChar;
                }
                
                if (screenY > 0) {
                    grid[screenY - 1][screenX] = healthChar;
                }
            }
        }
    }
    
    private void printGrid(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append("║");
            sb.append(new String(row));
            sb.append(" ║\n");
        }
        System.out.print(sb.toString());
    }
    
    private void printTankInfo() {
        Logs tank1Log = null, tank2Log = null;
        
        for (int i = currentIndex; i >= 0; i--) {
            if (logs[i] != null && logs[i].get_tankId() == 0) {
                tank1Log = logs[i];
                break;
            }
        }
        for (int i = currentIndex; i >= 0; i--) {
            if (logs[i] != null && logs[i].get_tankId() == 1) {
                tank2Log = logs[i];
                break;
            }
        }
        
        if (tank1Log != null) {
            Location loc = tank1Log.get_location();
            System.out.printf("║  [TANK 1] HP: %s%d%% | Pos: (%d,%d) | Dir: %d° | Turret: %d° | Action: %s     ║%n",
                    tank1Log.get_health() > 50 ? "▓" : "░",
                    tank1Log.get_health(),
                    loc.getX(), loc.getY(),
                    tank1Log.get_tankDirection().getDegrees(),
                    tank1Log.get_turretDirection().getDegrees(),
                    tank1Log.get_action().getType());
        }
        
        if (tank2Log != null) {
            Location loc = tank2Log.get_location();
            System.out.printf("║  [TANK 2] HP: %s%d%% | Pos: (%d,%d) | Dir: %d° | Turret: %d° | Action: %s     ║%n",
                    tank2Log.get_health() > 50 ? "▓" : "░",
                    tank2Log.get_health(),
                    loc.getX(), loc.getY(),
                    tank2Log.get_tankDirection().getDegrees(),
                    tank2Log.get_turretDirection().getDegrees(),
                    tank2Log.get_action().getType());
        }
    }
    
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void printSummary(Logs[] logs, int result) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                 GAME SUMMARY                                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════╣");
        
        if (logs == null || logs.length == 0) {
            System.out.println("║                                    NO LOGS                                             ║");
        } else {
            for (int i = 0; i < logs.length; i++) {
                Logs log = logs[i];
                if (log == null) break;
                int turn = i / 2 + 1;
                String tank = "T" + (log.get_tankId() + 1);
                Location loc = log.get_location();
                System.out.printf("║  Turn %2d [%s]: %-12s at (%4d,%4d) | HP:%3d | Dir:%3d° Tur:%3d°                ║%n",
                        turn, tank, log.get_action().getType(),
                        loc.getX(), loc.getY(),
                        log.get_health(),
                        log.get_tankDirection().getDegrees(),
                        log.get_turretDirection().getDegrees());
            }
        }
        
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════╣");
        String resultStr;
        switch (result) {
            case 1: resultStr = "🏆 TANK 1 WINS!"; break;
            case 2: resultStr = "🏆 TANK 2 WINS!"; break;
            case 0: resultStr = "⚠ ILLEGAL ACTION!"; break;
            case -1: resultStr = "🤝 DRAW - MAX TURNS"; break;
            default: resultStr = "? UNKNOWN RESULT"; break;
        }
        System.out.printf("║                              %s                                          ║%n", resultStr);
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════╝");
    }
}
