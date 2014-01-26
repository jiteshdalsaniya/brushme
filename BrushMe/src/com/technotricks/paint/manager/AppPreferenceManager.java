/**
 * 
 */
package com.technotricks.paint.manager;

import java.io.IOException;
import java.util.ArrayList;


import com.technotricks.paint.model.ImagesGridModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


/**
 * @author Kishore Raj
 * 
 */
public class AppPreferenceManager {
	
	private static String PREFERANCE_NAME="GuessTheBrand";

	

	public static void saveImages(Context ctx, ArrayList<ImagesGridModel> list) {
		SharedPreferences prefs = ctx.getSharedPreferences(PREFERANCE_NAME,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		Log.e("", "Total Count"+list.size());
		editor.putString("brandlist", ObjectSerializer.serialize(list));
		editor.commit();
	}

	public static ArrayList<ImagesGridModel> getImages(Context ctx) {
		ArrayList<ImagesGridModel> listItems = null;
		SharedPreferences prefs = ctx.getSharedPreferences(PREFERANCE_NAME,
				Context.MODE_PRIVATE);

		try {
			listItems = (ArrayList<ImagesGridModel>) ObjectSerializer
					.deserialize(prefs.getString("brandlist", ObjectSerializer
							.serialize(new ArrayList<ImagesGridModel>())));
		} catch (IOException e) {
			Log.e("", "Error" + e.getMessage());
			e.printStackTrace();
		}
		return listItems;
	}

	public static ImagesGridModel getImage(Context ctx, int position) {
		ArrayList<ImagesGridModel> listItems = null;
		SharedPreferences prefs = ctx.getSharedPreferences(PREFERANCE_NAME,
				Context.MODE_PRIVATE);

		try {
			listItems = (ArrayList<ImagesGridModel>) ObjectSerializer
					.deserialize(prefs.getString("brandlist", ObjectSerializer
							.serialize(new ArrayList<ImagesGridModel>())));
		} catch (IOException e) {
			Log.e("", "Error" + e.getMessage());
			e.printStackTrace();
		}
		return listItems.get(position);
	}

	
	
	public static void saveSoundState(Context ctx, boolean state) {
		SharedPreferences prefs = ctx.getSharedPreferences(PREFERANCE_NAME,
				Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putBoolean("toggle_sound", state);
		editor.commit();
	}

	public static boolean getSoundState(Context ctx) {
		SharedPreferences preferences;
		preferences = ctx.getSharedPreferences(PREFERANCE_NAME,
				Context.MODE_PRIVATE);
		boolean state = preferences.getBoolean("toggle_sound", false);
		return state;

	}

}
