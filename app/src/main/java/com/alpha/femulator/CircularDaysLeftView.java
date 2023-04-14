package com.alpha.femulator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class CircularDaysLeftView extends View {
    private Paint mArcPaint;
    private Paint mTextPaint;
    private int mDaysLeft;
    private float mSweepAngle;
    private int mDaysInMonth;
    private float mStartAngle = -90f;

    public CircularDaysLeftView(Context context) {
        super(context);
        init();
    }

    public CircularDaysLeftView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Initialize paint objects for arc and text
        mArcPaint = new Paint();
        mArcPaint.setStrokeWidth(120);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(50);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        // Get the number of days in the current month
        Calendar calendar = Calendar.getInstance();
        mDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the unfilled arc for the remaining days
        mSweepAngle = (float) (mDaysInMonth - mDaysLeft) / mDaysInMonth * 360f;
        mArcPaint.setColor(Color.BLUE);
        canvas.drawArc(getRectF(), mStartAngle, mSweepAngle, false, mArcPaint);

        // Draw the filled arcs for each day of the month
        float sweepAngle = 360f / mDaysInMonth;
        float startAngle = -90f + (sweepAngle / 2f);
        for (int i = 0; i < mDaysInMonth; i++) {
            if (i < mDaysLeft) {
                mArcPaint.setColor(Color.parseColor("#D33079")); // set the color to green for the part of the arc that represents days left
            } else {
                mArcPaint.setColor(Color.parseColor("#D33079"));
            }
            canvas.drawArc(getRectF(), mStartAngle, mSweepAngle, false, mArcPaint);
            startAngle += sweepAngle;
        }

        // Draw the numbers for each day of the month
        startAngle = -90f + (sweepAngle / 2f);
        float gapAngle = sweepAngle / 4f;
        for (int i = 0; i < mDaysInMonth; i += 2) {
            String text = Integer.toString(i + 1);
            Rect textBounds = new Rect();
            mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
            float angle = startAngle + i * sweepAngle;
            float x = (float) (getWidth() / 2f + (getWidth() / 2f - 50) * Math.cos(Math.toRadians(angle)));
            float y = (float) (getHeight() / 2f + (getHeight() / 2f - 50) * Math.sin(Math.toRadians(angle))) + textBounds.height() / 2f - 10f; // Adjust text position
            canvas.drawText(text, x, y, mTextPaint);
        }

        // Draw the text in the center
        String text = Integer.toString(mDaysLeft);
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
        float x = getWidth() / 2f - textBounds.width() / 2f;
        float y = getHeight() / 2f + textBounds.height() / 2f;
        canvas.drawText(text, x, y, mTextPaint);
    }

    public void setStartAngle(float angle) {
        mStartAngle = angle;
        invalidate(); // Redraw the view
    }

    public void setProgress(int start, int end, int value) {
        // Calculate the number of days left within the given range
        int daysInRange = end - start + 1;
        int daysLeft = value - start + 1; // Calculate daysLeft based on the given range
        mDaysLeft = Math.max(0, Math.min(daysLeft, daysInRange)); // Ensure daysLeft is within range [0, daysInRange]

        invalidate(); // Redraw the view
    }


    public void setDaysLeft(int startDay, int endDay) {
        mDaysLeft = endDay - startDay;
        invalidate(); // Redraw the view
    }


    private RectF getRectF() {
        return new RectF(50, 50, getWidth() - 50, getHeight() - 50);
    }
}


