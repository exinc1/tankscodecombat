package com.example.tankscodecombat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class GameBoardView extends View {

    private final int GRID_SIZE = 100;
    private final Paint gridPaint = new Paint();
    private final Paint tank1Paint = new Paint();
    private final Paint tank2Paint = new Paint();
    private final Paint tankDirPaint = new Paint();
    private final Paint turretDirPaint = new Paint();
    private final Paint bulletPaint = new Paint();
    private final Paint healthBarPaint = new Paint();
    private final Paint smokePaint = new Paint();
    private final Paint turnIndicatorPaint = new Paint();

    private Bitmap backgroundBitmap;
    private Bitmap explosionBitmap;
    private Rect viewRect = new Rect(0, 0, getWidth(), getHeight());

    private Logs[] logs = new Logs[0];
    private int currentIndex = 0;

    private float cachedMinX, cachedMinY, cachedMaxX, cachedMaxY;
    private float cachedScale, cachedOffsetX, cachedOffsetY;
    private int cachedCurrentIndex = -1;

    private float zoomFactor = 1.0f;
    private ScaleGestureDetector scaleDetector;

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

        bulletPaint.setColor(Color.WHITE);
        bulletPaint.setStrokeWidth(3);

        healthBarPaint.setStyle(Paint.Style.FILL);

        smokePaint.setColor(Color.GRAY);
        smokePaint.setAlpha(100);

        turnIndicatorPaint.setColor(Color.WHITE);
        turnIndicatorPaint.setTextSize(40);
        turnIndicatorPaint.setTextAlign(Paint.Align.CENTER);

        // Load bitmaps
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tank_background);
        explosionBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.explosion);

        scaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
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

    public void skipToStart(View v) {
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

        // Draw background
        if (backgroundBitmap != null) {
            canvas.drawBitmap(backgroundBitmap, null, viewRect, null);
        } else {
            canvas.drawColor(Color.BLACK);
        }

        if (logs.length == 0) return;

        if (currentIndex != cachedCurrentIndex) {
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
            float scaleX = getWidth() / width * zoomFactor;
            float scaleY = getHeight() / height * zoomFactor;
            float scale = Math.min(scaleX, scaleY);

            // Centering
            float offsetX = getWidth()/2f - (minX + width/2f) * scale;
            float offsetY = getHeight()/2f - (minY + height/2f) * scale;

            cachedMinX = minX; cachedMinY = minY; cachedMaxX = maxX; cachedMaxY = maxY;
            cachedScale = scale; cachedOffsetX = offsetX; cachedOffsetY = offsetY;
            cachedCurrentIndex = currentIndex;
        }

        // Draw grid lines every 10 units
        for (int gx = (int)Math.floor(cachedMinX / 10) * 10; gx <= cachedMaxX; gx += 10) {
            float screenX = gx * cachedScale + cachedOffsetX;
            canvas.drawLine(screenX, 0, screenX, getHeight(), gridPaint);
        }
        for (int gy = (int)Math.floor(cachedMinY / 10) * 10; gy <= cachedMaxY; gy += 10) {
            float screenY = gy * cachedScale + cachedOffsetY;
            canvas.drawLine(0, screenY, getWidth(), screenY, gridPaint);
        }

        // Draw tanks
        for (int i = 0; i <= currentIndex; i++) {
            Logs log = logs[i];
            if (log == null) continue;
            Location loc = log.get_location();
            if (loc == null) continue;

            float x = loc.getX() * cachedScale + cachedOffsetX;
            float y = loc.getY() * cachedScale + cachedOffsetY;

            Paint paint = log.get_tankId() == 1 ? tank1Paint : tank2Paint;

            float size = cachedScale * 3; // tank square size
            canvas.drawRect(x, y, x + size, y + size, paint);

            float centerX = x + size / 2;
            float centerY = y + size / 2;

            // Tank direction
            Direction tankDir = log.get_tankDirection();
            if (tankDir != null) {
                double rad = Math.toRadians(tankDir.getDegrees());
                float lineLen = cachedScale * 5;
                canvas.drawLine(centerX, centerY,
                        (float) (centerX + lineLen * Math.cos(rad)),
                        (float) (centerY + lineLen * Math.sin(rad)),
                        tankDirPaint);
            }

            // Turret direction
            Direction turretDir = log.get_turretDirection();
            if (turretDir != null) {
                double rad = Math.toRadians(turretDir.getDegrees());
                float lineLen = cachedScale * 4;
                canvas.drawLine(centerX, centerY,
                        (float) (centerX + lineLen * Math.cos(rad)),
                        (float) (centerY + lineLen * Math.sin(rad)),
                        turretDirPaint);
            }

            // Draw bullet if firing
            if (log.get_action().getType() == Action.ActionType.FIRE) {
                if (turretDir != null) {
                    double rad = Math.toRadians(turretDir.getDegrees());
                    float bulletLen = cachedScale * 20;
                    canvas.drawLine(centerX, centerY,
                            (float) (centerX + bulletLen * Math.cos(rad)),
                            (float) (centerY + bulletLen * Math.sin(rad)),
                            bulletPaint);
                }
            }

            // Draw health bar
            float health = log.get_health();
            float healthBarWidth = size;
            float healthBarHeight = cachedScale / 4;
            float healthX = x;
            float healthY = y - healthBarHeight - 2;

            // Background red
            healthBarPaint.setColor(Color.RED);
            canvas.drawRect(healthX, healthY, healthX + healthBarWidth, healthY + healthBarHeight, healthBarPaint);

            // Foreground green/yellow/red
            if (health > 66) healthBarPaint.setColor(Color.GREEN);
            else if (health > 33) healthBarPaint.setColor(Color.YELLOW);
            else healthBarPaint.setColor(Color.RED);
            canvas.drawRect(healthX, healthY, healthX + healthBarWidth * (health / 100f), healthY + healthBarHeight, healthBarPaint);

            // Draw smoke if low health
            if (health < 50) {
                canvas.drawCircle(centerX - cachedScale, centerY - cachedScale, cachedScale, smokePaint);
                canvas.drawCircle(centerX + cachedScale, centerY - cachedScale, cachedScale, smokePaint);
            }

            // Draw explosion if health <=0
            if (health <= 0 && explosionBitmap != null) {
                canvas.drawBitmap(explosionBitmap, x - size/2, y - size/2, null);
            }
        }

        // Draw turn indicator
        String turnText = "Turn: Tank " + ((currentIndex % 2) + 1);
        canvas.drawText(turnText, getWidth() / 2, 50, turnIndicatorPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        return true;
    }

    public void zoomIn() {
        zoomFactor *= 1.2f;
        zoomFactor = Math.min(zoomFactor, 5.0f);
        refresh();
    }

    private void refresh() {
        ((VisualGame) getContext()).runOnUiThread(this::invalidate);
    }

    public void zoomOut() {
        zoomFactor /= 1.2f;
        zoomFactor = Math.max(zoomFactor, 0.1f);
        refresh();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            zoomFactor *= detector.getScaleFactor();
            zoomFactor = Math.max(0.1f, Math.min(zoomFactor, 5.0f));
            refresh();
            return true;
        }
    }
}
