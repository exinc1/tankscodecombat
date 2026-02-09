package com.example.tankscodecombat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameBoardView extends View {

    private final int GRID_SIZE = 100;
    private final Paint gridPaint = new Paint();
    private final Paint tank1Paint = new Paint();
    private final Paint tank2Paint = new Paint();
    private final Paint tankDirPaint = new Paint();
    private final Paint turretDirPaint = new Paint();

    private Logs[] logs = new Logs[0];
    private int currentIndex = 0;

    public GameBoardView(Context context) {
        super(context);
        init();
    }

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gridPaint.setColor(Color.DKGRAY);
        gridPaint.setStrokeWidth(2);

        tank1Paint.setColor(Color.RED);
        tank2Paint.setColor(Color.GREEN);

        tankDirPaint.setColor(Color.YELLOW); // tank facing
        tankDirPaint.setStrokeWidth(3);

        turretDirPaint.setColor(Color.CYAN); // turret facing
        turretDirPaint.setStrokeWidth(2);
    }

    public void setLogs(Logs[] logs) {
        this.logs = logs != null ? logs : new Logs[0];
        currentIndex = 0;
        invalidate();
    }

    public void nextMove() {
        if (currentIndex < logs.length - 1) currentIndex++;
        invalidate();
    }

    public void prevMove() {
        if (currentIndex > 0) currentIndex--;
        invalidate();
    }

    public void skipForward10() {
        currentIndex = Math.min(currentIndex + 10, logs.length - 1);
        invalidate();
    }

    public void skipBackward10() {
        currentIndex = Math.max(currentIndex - 10, 0);
        invalidate();
    }

    public void skipToStart() {
        currentIndex = 0;
        invalidate();
    }

    public void skipToEnd() {
        currentIndex = logs.length - 1;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        float cellW = getWidth() / (float) GRID_SIZE;
        float cellH = getHeight() / (float) GRID_SIZE;

        // Draw grid
        for (int i = 0; i <= GRID_SIZE; i += 10) {
            canvas.drawLine(i * cellW, 0, i * cellW, getHeight(), gridPaint);
            canvas.drawLine(0, i * cellH, getWidth(), i * cellH, gridPaint);
        }

        // Draw tanks and directions
        for (int i = 0; i <= currentIndex; i++) {
            Logs log = logs[i];
            if (log == null) continue;

            Location loc = log.get_location();
            if (loc == null) continue;

            float x = loc.getX() * cellW;
            float y = loc.getY() * cellH;

            Paint paint = log.get_tankId() == 1 ? tank1Paint : tank2Paint;

            float sizeW = cellW * 3;
            float sizeH = cellH * 3;

            // Draw tank body
            canvas.drawRect(x, y, x + sizeW, y + sizeH, paint);

            float centerX = x + sizeW / 2;
            float centerY = y + sizeH / 2;

            // Tank facing direction (yellow)
            Direction tankDir = log.get_tankDirection();
            if (tankDir != null) {
                double rad = Math.toRadians(tankDir.getDegrees());
                float lineLen = cellW * 5;
                float endX = (float) (centerX + lineLen * Math.cos(rad));
                float endY = (float) (centerY + lineLen * Math.sin(rad));
                canvas.drawLine(centerX, centerY, endX, endY, tankDirPaint);
            }

            // Turret facing direction (cyan)
            Direction turretDir = log.get_turretDirection();
            if (turretDir != null) {
                double rad = Math.toRadians(turretDir.getDegrees());
                float lineLen = cellW * 4; // slightly shorter
                float endX = (float) (centerX + lineLen * Math.cos(rad));
                float endY = (float) (centerY + lineLen * Math.sin(rad));
                canvas.drawLine(centerX, centerY, endX, endY, turretDirPaint);
            }
        }
    }
}
