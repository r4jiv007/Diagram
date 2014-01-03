package com.prowizards.diagram.customview;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvas extends View {

	private Paint mPaintObj;

	ArrayList<CustomPoint> mAllPoints = new ArrayList<CustomPoint>();
	ArrayList<ArrayList<CustomPoint>> mAllPaths = new ArrayList<ArrayList<CustomPoint>>();

	public MyCanvas(Context context) {
		super(context);
		mPaintObj = new Paint();

		mPaintObj.setStyle(Paint.Style.STROKE);
		mPaintObj.setAntiAlias(true);
		mPaintObj.setColor(Color.BLUE);
		mPaintObj.setStrokeWidth(10);
		mPaintObj.setStrokeCap(Cap.ROUND);
		// TODO Auto-generated constructor stub
	}

	public MyCanvas(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mPaintObj = new Paint();
		mPaintObj.setStyle(Paint.Style.STROKE);
		mPaintObj.setAntiAlias(true);
		mPaintObj.setColor(Color.BLUE);
		mPaintObj.setStrokeWidth(10);
		mPaintObj.setStrokeCap(Cap.ROUND);

	}

	public MyCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);

		mPaintObj = new Paint();
		mPaintObj.setStyle(Paint.Style.STROKE);
		mPaintObj.setAntiAlias(true);
		mPaintObj.setColor(Color.BLUE);
		mPaintObj.setStrokeWidth(10);
		mPaintObj.setStrokeCap(Cap.ROUND);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			float x = event.getX();
			float y = event.getY();
			mAllPoints.add(new CustomPoint(x, y));
			invalidate();
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			mAllPaths.add(mAllPoints);
			mAllPoints = new ArrayList<MyCanvas.CustomPoint>();
		}

		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		super.onDraw(canvas);

		for (Object obj : mAllPaths) {
			drawLine(canvas, (ArrayList<MyCanvas.CustomPoint>) obj);
		}
		drawLine(canvas, mAllPoints);
	}

	public void drawLine(Canvas canvas, ArrayList<CustomPoint> allPoints) {
		Path path = new Path();
		boolean first = true;
		for (CustomPoint point : allPoints) {
			if (first) {
				first = false;
				path.moveTo(point.X, point.Y);
			} else {
				path.lineTo(point.X, point.Y);
			}
		}
		canvas.drawPath(path, mPaintObj);
	}

	class CustomPoint implements Serializable {
		public float X, Y;

		public CustomPoint(float x, float y) {
			this.X = x;
			this.Y = y;
		}

		public float getX() {
			return X;
		}

		public void setX(float x) {
			this.X = x;
		}

		public float getY() {
			return Y;
		}

		public void setY(float y) {
			this.Y = y;
		}

	}
}
