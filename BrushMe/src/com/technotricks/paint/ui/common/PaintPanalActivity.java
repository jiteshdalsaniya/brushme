package com.technotricks.paint.ui.common;

import java.io.File;
import java.io.FileNotFoundException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.constants.IIntentConstants;
import com.technotricks.paint.constants.IResultConstants;
import com.technotricks.paint.customclass.FloodFill;
import com.technotricks.paint.manager.AppPreferenceManager;
import com.technotricks.paint.manager.ColorPickerDialog;
import com.technotricks.paint.manager.Utils;
import com.technotricks.paint.ui.common.ColorPick.OnColorCodeChangedListener;

public class PaintPanalActivity extends BaseActivity implements
		OnClickListener, OnTouchListener, IResultConstants, IIntentConstants {

	private Context context;
	private Intent i;
	private Button btnColorPicker, btnPurple, btnGreen, btnYellow, btnRed;
	private ImageView imgPanel;
	private HorizontalScrollView horizontalScrollColor;

	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	private PointF start = new PointF();
	private Paint mPaint;
	// Bitmap Items..
	private Bitmap _alteredBitmap;
	private Bitmap bitmap;

	int originalImageOffsetX = 0, originalImageOffsetY = 0, color = 0,
			newColor = 0;

	// Save...

	File imageFile, directory;
	String mPath;
	String dt;

	// Type..
	String ACTIVITY_TYPE = "";
	String imagePath = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_paintpanal);

		context = this;

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			ACTIVITY_TYPE = extras.getString(INTENT_IMAGE_TYPE);
			imagePath = extras.getString(RESULT_NEW_STRING);
		}

		System.out.println("ACTIVITY_TYPE =" + ACTIVITY_TYPE);

		System.out.println("imagePath = " + imagePath);

		setupAd();
		intializeUI();
		setListner();

		// initializeCanvas();

	}

	private void intializeUI() {
		btnColorPicker = (Button) findViewById(R.id.btnColorPicker);
		btnPurple = (Button) findViewById(R.id.btnPurple);
		btnGreen = (Button) findViewById(R.id.btnGreen);
		btnYellow = (Button) findViewById(R.id.btnYellow);
		btnRed = (Button) findViewById(R.id.btnRed);

		imgPanel = (ImageView) findViewById(R.id.imgPanel);
		horizontalScrollColor = (HorizontalScrollView) findViewById(R.id.horizontalScrollColor);

		horizontalScrollColor.setVerticalScrollBarEnabled(false);
		horizontalScrollColor.setHorizontalScrollBarEnabled(false);

		newColor = getResources().getColor(R.color.rose);
		mPaint = new Paint();

		if ((ACTIVITY_TYPE.equals(INTENT_IMAGE_SAVE_LIST))
				|| (ACTIVITY_TYPE.equals(INTENT_IMAGE_LIST))) {

			loadImage(imagePath);

		} else {
			loadImage(AppPreferenceManager.getImage(context, 0)
					.getImageName_OR_Path());
		}

	}

	private void initializeCanvas() {

		BitmapDrawable drawable = (BitmapDrawable) imgPanel.getDrawable();
		Bitmap bmp = drawable.getBitmap();
		try {

			_alteredBitmap = Bitmap.createBitmap(bmp.getWidth(),
					bmp.getHeight(), Bitmap.Config.ARGB_8888);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Canvas canvas = new Canvas(_alteredBitmap);
		Paint paint = new Paint();
		Matrix matrix = new Matrix();
		canvas.drawBitmap(bmp, matrix, paint);

		imgPanel.setImageBitmap(_alteredBitmap);
		imgPanel.setOnTouchListener(this);

	}

	private void setListner() {
		btnColorPicker.setOnClickListener(this);
		btnPurple.setOnClickListener(this);
		btnGreen.setOnClickListener(this);
		btnYellow.setOnClickListener(this);
		btnRed.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.paint, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_new:

			i = new Intent(context, ImageListActivity.class);

			i.putExtra(INTENT_IMAGE_TYPE, INTENT_IMAGE_RESULT_BACK);

			startActivityForResult(i, RESULT_NEW_IMAGE);

			break;
		case R.id.action_save:

			hRefresh.sendEmptyMessage(2);

			break;
		case R.id.action_setasWall:

			hRefresh.sendEmptyMessage(1);

			break;

		case R.id.action_share:

			hRefresh.sendEmptyMessage(4);

			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == btnColorPicker) {

			Paint mPaint = new Paint();
			mPaint.setColor(newColor);
			new ColorPick(context, new OnColorCodeChangedListener() {

				@Override
				public void colorChanged(int color) {
					System.out.println("Color Code==" + color);
					newColor = color;

					horizontalScrollColor.setBackgroundColor(newColor);
				}
			}, mPaint.getColor()).show();

		} else if (v == btnPurple) {

			newColor = getResources().getColor(R.color.purple);

		} else if (v == btnGreen) {
			newColor = getResources().getColor(R.color.green);

		} else if (v == btnYellow) {
			newColor = getResources().getColor(R.color.yellow);

		}

		else if (v == btnRed) {
			newColor = getResources().getColor(R.color.red);

		}

		horizontalScrollColor.setBackgroundColor(newColor);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		ImageView view = (ImageView) imgPanel;

		int action = event.getAction() & MotionEvent.ACTION_MASK;

		switch (action) {
		case MotionEvent.ACTION_DOWN:

			System.out.println("ACTION_DOWN");

			BitmapDrawable drawable = (BitmapDrawable) imgPanel.getDrawable();
			bitmap = drawable.getBitmap();
			Rect imageBounds = new Rect();
			imgPanel.getDrawingRect(imageBounds);

			// original height and width of the bitmap
			int intrinsicHeight = drawable.getIntrinsicHeight();
			int intrinsicWidth = drawable.getIntrinsicWidth();

			// height and width of the visible (scaled) image
			int scaledHeight = imageBounds.height();
			int scaledWidth = imageBounds.width();

			// Find the ratio of the original image to the scaled image
			// Should normally be equal unless a disproportionate scaling
			// (e.g. fitXY) is used.
			float heightRatio = (float) intrinsicHeight / scaledHeight;
			float widthRatio = (float) intrinsicWidth / scaledWidth;

			// do whatever magic to get your touch point
			// MotionEvent event;

			// get the distance from the left and top of the image bounds
			float scaledImageOffsetX = event.getX() - imageBounds.left;
			float scaledImageOffsetY = event.getY() - imageBounds.top;

			// scale these distances according to the ratio of your scaling
			// For example, if the original image is 1.5x the size of the scaled
			// image, and your offset is (10, 20), your original image offset
			// values should be (15, 30).
			originalImageOffsetX = Math.round(scaledImageOffsetX * widthRatio);
			originalImageOffsetY = Math.round(scaledImageOffsetY * heightRatio);

			try {
				color = bitmap.getPixel(originalImageOffsetX,
						originalImageOffsetY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			hRefresh.sendEmptyMessage(3);

			break;
		case MotionEvent.ACTION_POINTER_DOWN:

			System.out.println("ACTION_POINTER_DOWN");

			break;

		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:

			break;

		case MotionEvent.ACTION_MOVE:

			break;

		default:

			break;

		}

		return true;
	}

	Handler hRefresh = new Handler() {
		public void handleMessage(Message msg) {
			String path;
			switch (msg.what) {

			case 1:
				try {
					path = Utils.saveImageView(imgPanel, context);
					Utils.setAsWallpaper(context, path);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;

			case 2:
				try {
					Utils.saveImageViewToSdCard(imgPanel, context);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 3:

				FloodFill floodfill = new FloodFill(bitmap, color, newColor);
				floodfill.fill(originalImageOffsetX, originalImageOffsetY);

				imgPanel.invalidate();

				break;

			case 4:
				try {
					path = Utils.saveImageView(imgPanel, context);

					Utils.share(context, path);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	};

	public void loadImage(String path) {

		System.out.println("IMAGE PATH== " + path);
		imageLoader.displayImage(path, imgPanel, new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {

				initializeCanvas();

			}

			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if ((requestCode == RESULT_NEW_IMAGE)
				&& (resultCode == RESULT_NEW_IMAGE)) {

			String imagePath = data.getStringExtra(RESULT_NEW_STRING);

			System.out.println("Path =   " + imagePath);

			loadImage(imagePath);
		}
	}

}
