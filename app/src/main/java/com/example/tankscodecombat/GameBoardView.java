// chat gpt
package com.example.tankscodecombat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameBoardView extends View {

    private final int GRID_SIZE = 1000; // 1000x1000 grid
    private Paint gridPaint = new Paint();
    private Paint tankPaint = new Paint();

    public GameBoardView(Context context) { super(context); init(); }
    public GameBoardView(Context context, AttributeSet attrs) { super(context, attrs); init(); }
    public GameBoardView(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); init(); }

    private void init() {
        gridPaint.setColor(0xFF444444);     // dark gray grid lines
        tankPaint.setColor(0xFFFF0000);     // red tank
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cellW = getWidth() / (float) GRID_SIZE;
        float cellH = getHeight() / (float) GRID_SIZE;

        // Draw grid
        for (int i = 0; i <= GRID_SIZE; i++) {
            canvas.drawLine(i * cellW, 0, i * cellW, getHeight(), gridPaint);
            canvas.drawLine(0, i * cellH, getWidth(), i * cellH, gridPaint);
        }

        // Example tank at (30, 40)
        float tankX = 30 * cellW;
        float tankY = 40 * cellH;
        canvas.drawRect(tankX, tankY, tankX + cellW, tankY + cellH, tankPaint);
    }
}