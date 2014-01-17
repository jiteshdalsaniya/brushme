/**
 * 
 */
package com.technotricks.paint.manager;




import com.technotricks.paint.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomProgressDialog extends Dialog {

    /**
     * Show.
     *
     * @param context the context
     * @param cancelable the cancelable
     * @return the custom progress dialog
     */

	public static TextView loadingTxt;
	public static ImageView image;
    public static CustomProgressDialog show(Context context,boolean cancelable) {
    	CustomProgressDialog dialog = new CustomProgressDialog(context);        
        dialog.setCancelable(cancelable);
       
        /* The next line will add the ProgressBar to the dialog. */
        //dialog.addContentView(new ProgressBar(context), new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
        View v=LayoutInflater.from(context).inflate(R.layout.progress_layout, null);
        loadingTxt = (TextView) v.findViewById(R.id.textView1);
        image = (ImageView) v.findViewById(R.id.img_group_image);
        dialog.addContentView(v, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));    
        return dialog;
    }

    /**
     * Instantiates a new custom progress dialog.
     *
     * @param context the context
     */
    public CustomProgressDialog(Context context) {
        super(context, R.style.NewDialog);
    }
}