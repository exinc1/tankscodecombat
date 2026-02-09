package com.example.tankscodecombat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameBoardView extends View {

    private final int GRID_SIZE = 100; // Grid resolution
    private final Paint gridPaint = new Paint();
    private final Paint tank1Paint = new Paint();
    private final Paint tank2Paint = new Paint();

    private Logs[] logs = new Logs[0];
    private int currentIndex = 0;

    // Constructors
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

    // Initialize paints
    private void init() {
        gridPaint.setColor(0xFFAAAAAA);
        gridPaint.setStrokeWidth(2);

        tank1Paint.setColor(0xFFFF0000); // Red for tank 1
        tank2Paint.setColor(0xFF00FF00); // Green for tank 2
    }

    // Set logs with null check
    public void setLogs(Logs[] logs) {
        if (logs != null) {
            this.logs = logs;
        } else {
            this.logs = new Logs[0];
        }
        this.currentIndex = 0;
        invalidate();
    }

    // Navigation methods
    public void nextMove() {
        if (currentIndex < logs.length - 1) {
            currentIndex++;
            invalidate();
        }
    }

    public void prevMove() {
        if (currentIndex > 0) {
            currentIndex--;
            invalidate();
        }
    }

    public void skipForward10() {
        currentIndex = Math.min(currentIndex + 10, logs.length - 1);
        invalidate();
    }

    public void skipBackward10() {
        currentIndex = Math.max(currentIndex - 10, 0);
        invalidate();
    }

    public void skipToEnd() {
        currentIndex = logs.length - 1;
        invalidate();
    }

    public void skipToStart() {
        currentIndex = 0;
        invalidate();
    }

    // Draw the grid and tanks
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Clear background
        canvas.drawColor(0xFF000000);

        float cellW = getWidth() / (float) GRID_SIZE;
        float cellH = getHeight() / (float) GRID_SIZE;

        // Draw grid lines every 10 cells
        for (int i = 0; i <= GRID_SIZE; i += 10) {
            canvas.drawLine(i * cellW, 0, i * cellW, getHeight(), gridPaint);
            canvas.drawLine(0, i * cellH, getWidth(), i * cellH, gridPaint);
        }

        // Draw all logs up to currentIndex
        for (int i = 0; i <= currentIndex; i++) {
            Logs log = logs[i];
            if (log == null) continue;

            Location loc = log.get_location();
            if (loc == null) continue;

            float x = loc.getX() * cellW;
            float y = loc.getY() * cellH;

            Paint paint;
            if (log.get_tankId() == 1) {
                paint = tank1Paint;
            } else {
                paint = tank2Paint;
            }

            canvas.drawRect(x, y, x + cellW, y + cellH, paint);
        }
    }
}
