/**
 * 
 */
package com.technotricks.paint.manager;

import com.technotricks.paint.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomProgressDialog extends Dialog {

	/**
	 * Show.
	 * 
	 * @param context
	 *            the context
	 * @param cancelable
	 *            the cancelable
	 * @return the custom progress dialog
	 */
	private final static Handler handler = new Handler();

	public static TextView loadingTxt;
	public static ImageView image;

	public static ImageView image1, image2, image3, image4;

	static int type = 1;

	static Runnable runnable;
	public static CustomProgressDialog show(Context context, boolean cancelable) {
		CustomProgressDialog dialog = new CustomProgressDialog(context);
		dialog.setCancelable(cancelable);

		/* The next line will add the ProgressBar to the dialog. */
		// dialog.addContentView(new ProgressBar(context), new
		// LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		View v = LayoutInflater.from(context).inflate(R.layout.progress_layout,
				null);
		loadingTxt = (TextView) v.findViewById(R.id.textView1);
		image = (ImageView) v.findViewById(R.id.img_group_image);
		dialog.addContentView(v, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		return dialog;
	}

	public static CustomProgressDialog showLove(Context context,
			boolean cancelable) {
		CustomProgressDialog dialog = new CustomProgressDialog(context);
		dialog.setCancelable(cancelable);

		/* The next line will add the ProgressBar to the dialog. */
		// dialog.addContentView(new ProgressBar(context), new
		// LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		View v = LayoutInflater.from(context).inflate(
				R.layout.progress_layout_love, null);

		image1 = (ImageView) v.findViewById(R.id.img1);
		image2 = (ImageView) v.findViewById(R.id.img2);
		image3 = (ImageView) v.findViewById(R.id.img3);
		image4 = (ImageView) v.findViewById(R.id.img4);
		dialog.addContentView(v, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		 runnable = new Runnable() {
		    @Override
		    public void run() {
		    	
		    	  handler.postDelayed(runnable, 500);
				
			
System.out.println("TYPE=="+type);
				if (type == 1) {
					
					image1.setVisibility(View.VISIBLE);
					image2.setVisibility(View.INVISIBLE);
					image3.setVisibility(View.INVISIBLE);
					image4.setVisibility(View.INVISIBLE);
					type=2;

				} else if (type == 2) {
					
					image1.setVisibility(View.VISIBLE);
					image2.setVisibility(View.VISIBLE);
					image3.setVisibility(View.INVISIBLE);
					image4.setVisibility(View.INVISIBLE);
					type=3;

				} else if (type == 3) {
					image1.setVisibility(View.VISIBLE);
					image2.setVisibility(View.VISIBLE);
					image3.setVisibility(View.VISIBLE);
					image4.setVisibility(View.INVISIBLE);
					type=4;
					
				} else if (type == 4) {
					image1.setVisibility(View.VISIBLE);
					image2.setVisibility(View.VISIBLE);
					image3.setVisibility(View.VISIBLE);
					image4.setVisibility(View.VISIBLE);
					type=1;
				}
				
				
				
			
				

			}
		};

		
	
		return dialog;
	}

	
	
	public static void showProgress(CustomProgressDialog dialog){
		
		dialog.show();
		type = 1;
		 handler.post(runnable);
		
	}
	
public static void dissmissProgress(CustomProgressDialog dialog){
		
		dialog.dismiss();
		
		handler.removeCallbacks(runnable);
		
	}
	
	
	/**
	 * Instantiates a new custom progress dialog.
	 * 
	 * @param context
	 *            the context
	 */
	public CustomProgressDialog(Context context) {
		super(context, R.style.NewDialog);
	}
}