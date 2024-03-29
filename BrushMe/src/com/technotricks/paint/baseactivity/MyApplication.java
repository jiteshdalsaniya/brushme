package com.technotricks.paint.baseactivity;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.widget.Toast;

public class MyApplication extends Application{
	
	
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate() {
		/*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
              StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
              StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
      }*/

      super.onCreate();

      initImageLoader(getApplicationContext());
      
      
		
	}
	
	
	
	 public static void initImageLoader(Context context) {
         // This configuration tuning is custom. You can tune every option, you may tune some of them,
         // or you can create default configuration by
         //  ImageLoaderConfiguration.createDefault(this);
         // method.
         ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                         .threadPriority(Thread.NORM_PRIORITY - 2)
                         .denyCacheImageMultipleSizesInMemory()
                         .discCacheFileNameGenerator(new Md5FileNameGenerator())
                         .tasksProcessingOrder(QueueProcessingType.LIFO)
                         .writeDebugLogs() // Remove for release app
                         .build();
         // Initialize ImageLoader with configuration.
         ImageLoader.getInstance().init(config);
 }
	 
	 
	
	 
	 
	 
 

}
