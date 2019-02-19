package com.example.bms.myapplication_226;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView {
    SurfaceHolder sh;
    Context ct;
    Canvas c;
    public MySurfaceView(Context context) {
        super(context);
        ct = context;
        sh = getHolder();
        sh.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                c = sh.lockCanvas();
                draw(c);
                sh.unlockCanvasAndPost(c);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setTextSize(100f);
        canvas.drawText("Hello",100,100,p);

        p.setColor(Color.YELLOW);
        canvas.drawCircle(100,100,100,p);

        p.setColor(Color.CYAN);
        float f[] = {0,0,200,0,200,0,100,100,100,100,0,0};

        p.setColor(Color.BLACK);
        canvas.drawLines(f,p);
    }
}

