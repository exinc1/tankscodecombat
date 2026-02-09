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

        if (logs.length == 0) return;

        // --- Compute bounds around tanks ---
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;

        for (int i = 0; i <= currentIndex; i++) {
            Logs log = logs[i];
            if (log == null) continue;
            Location loc = log.get_location();
            if (loc == null) continue;

            minX = Math.min(minX, loc.getX());
            minY = Math.min(minY, loc.getY());
            maxX = Math.max(maxX, loc.getX());
            maxY = Math.max(maxY, loc.getY());
        }

        // Add padding
        float padding = 10f;
        minX -= padding; minY -= padding;
        maxX += padding; maxY += padding;

        float width = maxX - minX;
        float height = maxY - minY;

        // Apply zoom factor to make view less zoomed-in
        float zoomFactor = 0.75f;
        float scaleX = getWidth() / width * zoomFactor;
        float scaleY = getHeight() / height * zoomFactor;
        float scale = Math.min(scaleX, scaleY);

        // Centering
        float offsetX = getWidth()/2f - (minX + width/2f) * scale;
        float offsetY = getHeight()/2f - (minY + height/2f) * scale;

        // Draw grid lines every 10 units
        for (int gx = (int)Math.floor(minX / 10) * 10; gx <= maxX; gx += 10) {
            float screenX = gx * scale + offsetX;
            canvas.drawLine(screenX, 0, screenX, getHeight(), gridPaint);
        }
        for (int gy = (int)Math.floor(minY / 10) * 10; gy <= maxY; gy += 10) {
            float screenY = gy * scale + offsetY;
            canvas.drawLine(0, screenY, getWidth(), screenY, gridPaint);
        }

        // Draw tanks
        for (int i = 0; i <= currentIndex; i++) {
            Logs log = logs[i];
            if (log == null) continue;
            Location loc = log.get_location();
            if (loc == null) continue;

            float x = loc.getX() * scale + offsetX;
            float y = loc.getY() * scale + offsetY;

            Paint paint = log.get_tankId() == 1 ? tank1Paint : tank2Paint;

            float size = scale * 3; // tank square size
            canvas.drawRect(x, y, x + size, y + size, paint);

            float centerX = x + size / 2;
            float centerY = y + size / 2;

            // Tank direction
            Direction tankDir = log.get_tankDirection();
            if (tankDir != null) {
                double rad = Math.toRadians(tankDir.getDegrees());
                float lineLen = scale * 5;
                canvas.drawLine(centerX, centerY,
                        (float) (centerX + lineLen * Math.cos(rad)),
                        (float) (centerY + lineLen * Math.sin(rad)),
                        tankDirPaint);
            }

            // Turret direction
            Direction turretDir = log.get_turretDirection();
            if (turretDir != null) {
                double rad = Math.toRadians(turretDir.getDegrees());
                float lineLen = scale * 4;
                canvas.drawLine(centerX, centerY,
                        (float) (centerX + lineLen * Math.cos(rad)),
                        (float) (centerY + lineLen * Math.sin(rad)),
                        turretDirPaint);
            }
        }
    }
}
