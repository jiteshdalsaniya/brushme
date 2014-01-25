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

import com.technotricks.paint.constants.IAPPConstants;
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

public class Utils implements IAPPConstants {
	
	//private static String assetFolderName="IMAGES"; 
	
	
	
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
            istr = assetManager.open(ASSERT_FOLDER_NAME+"/"+strName);
            
            
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
		   String[] assetsIWant = assetMgr.list(ASSERT_FOLDER_NAME);
		   
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
	
public 	static String saveDrawable(ImageView imageView,Context context) throws FileNotFoundException{
	
	String mPath="";
	String filePath="";
	String appName=getApplicationName(context);
	
	mPath = Environment.getExternalStorageDirectory().toString()+"/"+appName;
	
        Drawable drawable = imageView.getDrawable();
        Rect bounds = drawable.getBounds();
        Bitmap bitmap = Bitmap.createBitmap(bounds.width(),bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        OutputStream fOut = null;
        try {
            new File(mPath).mkdir();
            filePath=mPath+"/"+appName + "_" + Utils.getDate()+ ".jpg";
            fOut = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, fOut);
            
        } finally {
            if ( fOut != null ){
                try {
                    fOut.close();
                } catch (IOException e) {
                    //report error
                }
            }

        }
        
        
        return filePath;
    }
	
	
	
	
	

}
