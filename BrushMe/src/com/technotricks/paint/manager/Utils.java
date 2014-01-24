package com.technotricks.paint.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class Utils {
	
	private static String assetFolderName="IMAGES"; 
	
	public static Bitmap getBitmapFromAsset(String strName,Context context)
    {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(assetFolderName+"/"+strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
	
	
	public static String getDate() {
		// TODO Auto-generated method stub  
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		
		return  dateFormat.format(new Date());
	}
	
	public static String getApplicationName(Context context) {
	    int stringId = context.getApplicationInfo().labelRes;
	    return context.getString(stringId);
	}
	
	
	public static String save(ImageView imgPanel,Context context) {

		File directory,imageFile = null;
		String mPath="";
		String appName=getApplicationName(context);
		Bitmap bitmap;
		ImageView i = imgPanel;
		i.setScaleType(ScaleType.FIT_XY);
	
		mPath = Environment.getExternalStorageDirectory().toString();
		
		View v1 = i.getRootView();
		
		
		v1.setDrawingCacheEnabled(true);
		bitmap = Bitmap.createBitmap(v1.getDrawingCache());
		
	
		v1.setDrawingCacheEnabled(false);

		OutputStream fout = null;
		directory = new File(mPath, appName);
		Log.d("path", mPath);
		Log.d("dir", directory.toString());
		
		directory.mkdirs();
		Calendar c = Calendar.getInstance();                            

		try {

			imageFile = new File(directory, appName + "_" + Utils.getDate()+ ".jpg");
			
			
			
			Log.d("nee", imageFile.toString());
			fout = new FileOutputStream(imageFile);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout);
			fout.flush();
			fout.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Toast.makeText(context.getApplicationContext(), "'Image saved to Gallery'",
				Toast.LENGTH_SHORT).show();
		context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
				Uri.parse("file://" + Environment.getExternalStorageDirectory())));  
		
		return imageFile.getAbsolutePath();
	}
	
	
	

}
