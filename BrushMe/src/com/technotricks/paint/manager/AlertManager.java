/**
 * 
 */
package com.technotricks.paint.manager;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Jiju Induchoodan
 *
 */
public class AlertManager {
	
	
	public static void longtoastMessage(Context ctx,String message)
	{
		Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
	}
	
	public static void shorttoastMessage(Context ctx,String message)
	{
		Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
	}

}
