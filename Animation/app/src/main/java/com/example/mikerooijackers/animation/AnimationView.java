package com.example.mikerooijackers.animation;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

/**
 * Created by mikerooijackers on 10-09-15.
 */
public class AnimationView extends View {
    private int xMin = 0;
    private int xMax;
    private int yMin = 0;
    private int yMax;
    private float ballRadius = 40;
    private float ballX = ballRadius + 20;
    private float ballY = ballRadius + 40;
    private float ballSpeedX = 5;
    private float ballSpeedY = 10;
    private RectF ball;
    private Paint paint;
    private Random rnd;

    public AnimationView(Context context) {
        super(context);
        ball = new RectF();
        paint = new Paint();
        rnd = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ball.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
        //paint.setColor(Color.RED);
        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        canvas.drawOval(ball, paint);

        update();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
        }
        invalidate();
    }

    private void update() {
        // Get new (x,y) position
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        // Detect collision and react
        if (ballX + ballRadius > xMax) {
            ballSpeedX = -ballSpeedX;
            ballX = xMax-ballRadius;
        } else if (ballX - ballRadius < xMin) {
            ballSpeedX = -ballSpeedX;
            ballX = xMin+ballRadius;
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = -ballSpeedY;
            ballY = yMax - ballRadius;
        } else if (ballY - ballRadius < yMin) {
            ballSpeedY = -ballSpeedY;
            ballY = yMin + ballRadius;
        }
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // change position
        xMax = w-1;
        yMax = h-1;
    }
}
