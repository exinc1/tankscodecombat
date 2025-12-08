package com.example.tankscodecombat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameBoardView extends View {

    private final int GRID_SIZE = 100;
    private Paint gridPaint = new Paint();
    private Paint tankPaint = new Paint();

    private Logs[] logs = new Logs[0];
    private int currentIndex = 0;

    public GameBoardView(Context context) { super(context); init(); }
    public GameBoardView(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public GameBoardView(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        gridPaint.setColor(0xFFAAAAAA);
        tankPaint.setColor(0xFFFF0000);
    }

    public void setLogs(Logs[] logs) {
        this.logs = logs;
        this.currentIndex = 0;
        invalidate();
    }

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(0xFF000000);

        float cellW = getWidth() / (float) GRID_SIZE;
        float cellH = getHeight() / (float) GRID_SIZE;

        for (int i = 0; i <= GRID_SIZE; i += 10) {
            canvas.drawLine(i * cellW, 0, i * cellW, getHeight(), gridPaint);
            canvas.drawLine(0, i * cellH, getWidth(), i * cellH, gridPaint);
        }

        if (logs.length == 0) return;

        // Draw ONLY the current log
        Logs log = logs[currentIndex];
        Location loc = log.get_location();

        float x = loc.getX() * cellW;
        float y = loc.getY() * cellH;

        canvas.drawRect(x, y, x + cellW, y + cellH, tankPaint);
    }
}
