package com.technotricks.paint.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.technotricks.paint.model.ImagesGridModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class Utils {
	
	private static String assetFolderName="IMAGES"; 
	
	
	
	@SuppressLint("NewApi")
	public static int getDeviceWidth(Context context) {
		int measuredWidth = 0;
		Point size = new Point();
		WindowManager w = ((Activity) context).getWindowManager();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			w.getDefaultDisplay().getSize(size);
			measuredWidth = size.x;
		} else {
			Display d = w.getDefaultDisplay();
			measuredWidth = d.getWidth();
		}
		return measuredWidth;
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
	public static void getList(Context context)
	{
		AssetManager assetMgr = context.getAssets();
		ArrayList<ImagesGridModel> itemList = new ArrayList<ImagesGridModel>();
		  try {
		   String[] assetsIWant = assetMgr.list(assetFolderName);
		   
		   System.out.println("Flags length"+assetsIWant.length);
		   ImagesGridModel itemModel;
		   for (int i = 0; i < assetsIWant.length; i++)
		   {
			
			   itemModel = new ImagesGridModel();
			   itemModel.setId(String.valueOf(i+1));
			   System.out.println("file name"+assetsIWant[i].substring(0, assetsIWant[i].lastIndexOf('.')));
			   itemModel.setImageName(assetsIWant[i]);
			   itemList.add(itemModel);
		   }
		   
		   AppPreferenceManager.saveBrands(context, itemList);
		   System.out.println("flag list size"+itemList.size());
		   System.out.println("preference flag list size"+AppPreferenceManager.getBrands(context).size());
		   
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
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
