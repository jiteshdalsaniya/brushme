package com.technotricks.paint.baseactivity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.RevMobUserGender;
import com.revmob.ads.banner.RevMobBanner;
import com.revmob.ads.fullscreen.RevMobFullscreen;
import com.revmob.ads.link.RevMobLink;
import com.revmob.ads.popup.RevMobPopup;

import com.revmob.internal.RMLog;
import com.technotricks.paint.manager.SoundManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast; 



public class BaseActivity extends Activity {

	
	//Kishore
	/*
	 * 
	 * 
	 * //Permissions Required 1, <uses-permission
	 * android:name="android.permission.INTERNET"/> <uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE"/>
	 * 
	 * 
	 * 
	 * 
	 * //Rev mob Setting.... inside Application tab
	 * 
	 * <activity android:name="com.revmob.ads.fullscreen.FullscreenActivity"
	 * android:configChanges="keyboardHidden|orientation"
	 * android:theme="@android:style/Theme.Translucent"> </activity>
	 * 
	 * <activity android:name="com.google.ads.AdActivity" android:configChanges=
	 * "keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
	 * />
	 * 
	 * <meta-data android:name="com.revmob.app.id"
	 * android:value="52bac389a5dd42a582000035"/>
	 * 
	 * 
	 * //Chartboost Setting.... inside Application tab <activity
	 * android:name="com.chartboost.sdk.CBImpressionActivity"
	 * android:excludeFromRecents="true"
	 * android:theme="@android:style/Theme.Translucent.NoTitleBar" />
	 */

	private boolean isAd = false;

	private int flag = 5;

	Random random = new Random();

	// RevMob..

	RevMob revmob;
	Activity currentActivity; // for anonymous classes
	RevMobFullscreen fullscreen;
	RevMobBanner banner;
	RevMobPopup popup;
	RevMobLink link;

	// Chartboost..

	private Chartboost cb;
	private static final String TAG = "Chartboost";
	
	
	//leadbolt...
	
	
	
	//mem catche
	 protected ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SoundManager.instance().load(this);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		chartBoostSetup();

	}

	@Override
	protected void onStart() {
		super.onStart();
		this.cb.onStart(this);
		// spiceManager.start(this);
	}

	@Override
	protected void onStop() {
		// spiceManager.shouldStop();
		super.onStop();
		this.cb.onStop(this);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.cb.onDestroy(this);
		
		
	}

	
	
	public void setupAd(/*ViewGroup top,ViewGroup bottom*/) {

		currentActivity = this;
		flag = random.nextInt(7);

		System.out.println("Show Ad Flag =" + flag);

		if (isAd) {

			if (flag == 0) {

				revMobSetup();

				// showAbsoluteBannerOnTop();
				// 
				showFullscreen();
				
				
				

			} else if (flag == 1) {

				showMoreButtonClick();

			}

			else if (flag == 2) {

				 showInterstitial();

			}

			else if (flag == 3) {
				revMobSetup();
				showPopup();
			}

			else if (flag == 4) {
				revMobSetup();
				showAdLink();

			}

			else if (flag == 5) {
			
				//revMobSetup();

				//showAbsoluteBannerOnBottom();
				
				revMobSetup();
				showFullscreen();
			
			}

			else if (flag == 6) {
				
				
				revMobSetup();
				showFullscreen();
				//showadMob();
			}
			
			
			
			showadMob();
			
			revMobSetup();
		//	showBannerCustomSize(top);
			//showBannerCustomSize(bottom);
			showAbsoluteBannerOnTop();
			//showAbsoluteBannerOnBottom();
			
			
		}
	}

	public void chartBoostSetup() {

		this.cb = Chartboost.sharedChartboost();
		String appId = "52ba85f5f8975c300e6ee910";
		String appSignature = "58d0d48a306f7319dc80b0475d87bd83bb9d6b87";
		this.cb.onCreate(this, appId, appSignature, this.chartBoostDelegate);

		this.cb.startSession();

		// Pro Tip: Use code below to print Android ID in log:
		String android_id = Secure.getString(getBaseContext()
				.getContentResolver(), Secure.ANDROID_ID);
		Log.e(TAG, android_id);
	}

	public void revMobSetup() {
		RMLog.d("onCreate");

		revmob = RevMob.start(currentActivity);
		fillUserInfo();

	}

	private ChartboostDelegate chartBoostDelegate = new ChartboostDelegate() {

		/*
		 * Chartboost delegate methods
		 * 
		 * Implement the delegate methods below to finely control Chartboost's
		 * behavior in your app
		 * 
		 * Minimum recommended: shouldDisplayInterstitial()
		 */

		/*
		 * shouldDisplayInterstitial(String location)
		 * 
		 * This is used to control when an interstitial should or should not be
		 * displayed If you should not display an interstitial, return false
		 * 
		 * For example: during gameplay, return false.
		 * 
		 * Is fired on: - showInterstitial() - Interstitial is loaded & ready to
		 * display
		 */
		@Override
		public boolean shouldDisplayInterstitial(String location) {
			Log.i(TAG, "SHOULD DISPLAY INTERSTITIAL '" + location + "'?");
			return true;
		}

		/*
		 * shouldRequestInterstitial(String location)
		 * 
		 * This is used to control when an interstitial should or should not be
		 * requested If you should not request an interstitial from the server,
		 * return false
		 * 
		 * For example: user should not see interstitials for some reason,
		 * return false.
		 * 
		 * Is fired on: - cacheInterstitial() - showInterstitial() if no
		 * interstitial is cached
		 * 
		 * Notes: - We do not recommend excluding purchasers with this delegate
		 * method - Instead, use an exclusion list on your campaign so you can
		 * control it on the fly
		 */
		@Override
		public boolean shouldRequestInterstitial(String location) {
			Log.i(TAG, "SHOULD REQUEST INSTERSTITIAL '" + location + "'?");
			return true;
		}

		/*
		 * didCacheInterstitial(String location)
		 * 
		 * Passes in the location name that has successfully been cached
		 * 
		 * Is fired on: - cacheInterstitial() success - All assets are loaded
		 * 
		 * Notes: - Similar to this is: cb.hasCachedInterstitial(String
		 * location) Which will return true if a cached interstitial exists for
		 * that location
		 */
		@Override
		public void didCacheInterstitial(String location) {
			Log.i(TAG, "INTERSTITIAL '" + location + "' CACHED");
		}

		/*
		 * didFailToLoadInterstitial(String location)
		 * 
		 * This is called when an interstitial has failed to load for any reason
		 * 
		 * Is fired on: - cacheInterstitial() failure - showInterstitial()
		 * failure if no interstitial was cached
		 * 
		 * Possible reasons: - No network connection - No publishing campaign
		 * matches for this user (go make a new one in the dashboard)
		 */
		@Override
		public void didFailToLoadInterstitial(String location) {
			// Show a house ad or do something else when a chartboost
			// interstitial fails to load

			Log.i(TAG, "INTERSTITIAL '" + location + "' REQUEST FAILED");
			Toast.makeText(currentActivity,
					"Interstitial '" + location + "' Load Failed",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didDismissInterstitial(String location)
		 * 
		 * This is called when an interstitial is dismissed
		 * 
		 * Is fired on: - Interstitial click - Interstitial close
		 * 
		 * #Pro Tip: Use the delegate method below to immediately re-cache
		 * interstitials
		 */
		@Override
		public void didDismissInterstitial(String location) {

			// Immediately re-caches an interstitial
			cb.cacheInterstitial(location);

			Log.i(TAG, "INTERSTITIAL '" + location + "' DISMISSED");
			Toast.makeText(currentActivity,
					"Dismissed Interstitial '" + location + "'",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didCloseInterstitial(String location)
		 * 
		 * This is called when an interstitial is closed
		 * 
		 * Is fired on: - Interstitial close
		 */
		@Override
		public void didCloseInterstitial(String location) {
			Log.i(TAG, "INSTERSTITIAL '" + location + "' CLOSED");
			Toast.makeText(currentActivity,
					"Closed Interstitial '" + location + "'",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didClickInterstitial(String location)
		 * 
		 * This is called when an interstitial is clicked
		 * 
		 * Is fired on: - Interstitial click
		 */
		@Override
		public void didClickInterstitial(String location) {
			Log.i(TAG, "DID CLICK INTERSTITIAL '" + location + "'");
			Toast.makeText(currentActivity,
					"Clicked Interstitial '" + location + "'",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didShowInterstitial(String location)
		 * 
		 * This is called when an interstitial has been successfully shown
		 * 
		 * Is fired on: - showInterstitial() success
		 */
		@Override
		public void didShowInterstitial(String location) {
			Log.i(TAG, "INTERSTITIAL '" + location + "' SHOWN");
		}

		/*
		 * didFailToLoadURL(String location)
		 * 
		 * This is called when a url after a click has failed to load for any
		 * reason
		 * 
		 * Is fired on: - Interstitial click - More-Apps click
		 * 
		 * Possible reasons: - No network connection - no valid activity to
		 * launch - unable to parse url
		 */
		@Override
		public void didFailToLoadUrl(String url) {
			// Show a house ad or do something else when a chartboost
			// interstitial fails to load

			Log.i(TAG, "URL '" + url + "' REQUEST FAILED");
			Toast.makeText(currentActivity, "URL '" + url + "' Load Failed",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * More Apps delegate methods
		 */

		/*
		 * shouldDisplayLoadingViewForMoreApps()
		 * 
		 * Return false to prevent the pretty More-Apps loading screen
		 * 
		 * Is fired on: - showMoreApps()
		 */
		@Override
		public boolean shouldDisplayLoadingViewForMoreApps() {
			return true;
		}

		/*
		 * shouldRequestMoreApps()
		 * 
		 * Return false to prevent a More-Apps page request
		 * 
		 * Is fired on: - cacheMoreApps() - showMoreApps() if no More-Apps page
		 * is cached
		 */
		@Override
		public boolean shouldRequestMoreApps() {

			return true;
		}

		/*
		 * shouldDisplayMoreApps()
		 * 
		 * Return false to prevent the More-Apps page from displaying
		 * 
		 * Is fired on: - showMoreApps() - More-Apps page is loaded & ready to
		 * display
		 */
		@Override
		public boolean shouldDisplayMoreApps() {
			Log.i(TAG, "SHOULD DISPLAY MORE APPS?");
			return true;
		}

		/*
		 * didFailToLoadMoreApps()
		 * 
		 * This is called when the More-Apps page has failed to load for any
		 * reason
		 * 
		 * Is fired on: - cacheMoreApps() failure - showMoreApps() failure if no
		 * More-Apps page was cached
		 * 
		 * Possible reasons: - No network connection - No publishing campaign
		 * matches for this user (go make a new one in the dashboard)
		 */
		@Override
		public void didFailToLoadMoreApps() {
			Log.i(TAG, "MORE APPS REQUEST FAILED");
			Toast.makeText(currentActivity, "More Apps Load Failed",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didCacheMoreApps()
		 * 
		 * Is fired on: - cacheMoreApps() success - All assets are loaded
		 */
		@Override
		public void didCacheMoreApps() {
			Log.i(TAG, "MORE APPS CACHED");
		}

		/*
		 * didDismissMoreApps()
		 * 
		 * This is called when the More-Apps page is dismissed
		 * 
		 * Is fired on: - More-Apps click - More-Apps close
		 */
		@Override
		public void didDismissMoreApps() {
			Log.i(TAG, "MORE APPS DISMISSED");
			Toast.makeText(currentActivity, "Dismissed More Apps",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didCloseMoreApps()
		 * 
		 * This is called when the More-Apps page is closed
		 * 
		 * Is fired on: - More-Apps close
		 */
		@Override
		public void didCloseMoreApps() {
			Log.i(TAG, "MORE APPS CLOSED");
			Toast.makeText(currentActivity, "Closed More Apps",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didClickMoreApps()
		 * 
		 * This is called when the More-Apps page is clicked
		 * 
		 * Is fired on: - More-Apps click
		 */
		@Override
		public void didClickMoreApps() {
			Log.i(TAG, "MORE APPS CLICKED");
			Toast.makeText(currentActivity, "Clicked More Apps",
					Toast.LENGTH_SHORT).show();
		}

		/*
		 * didShowMoreApps()
		 * 
		 * This is called when the More-Apps page has been successfully shown
		 * 
		 * Is fired on: - showMoreApps() success
		 */
		@Override
		public void didShowMoreApps() {
			Log.i(TAG, "MORE APPS SHOWED");
		}

		/*
		 * shouldRequestInterstitialsInFirstSession()
		 * 
		 * Return false if the user should not request interstitials until the
		 * 2nd startSession()
		 */
		@Override
		public boolean shouldRequestInterstitialsInFirstSession() {
			return true;
		}
	};

	RevMobAdsListener revmobListener = new RevMobAdsListener() {
		@Override
		public void onRevMobAdReceived() {
			toastOnUiThread("RevMob ad received.");
		}

		@Override
		public void onRevMobAdNotReceived(String message) {
			toastOnUiThread("RevMob ad not received.");
		}

		@Override
		public void onRevMobAdDismiss() {
			toastOnUiThread("Ad dismissed.");
		}

		@Override
		public void onRevMobAdClicked() {
			toastOnUiThread("Ad clicked.");
		}

		@Override
		public void onRevMobAdDisplayed() {
			toastOnUiThread("Ad displayed.");
		}
	};

	void toastOnUiThread(final String message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(currentActivity, message, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	void fillUserInfo() {
		revmob.setUserGender(RevMobUserGender.FEMALE);

		revmob.setUserAgeRangeMin(18);
		revmob.setUserAgeRangeMax(25);
		revmob.setUserBirthday(new GregorianCalendar(1990, 11, 12));
		revmob.setUserPage("twitter.com/revmob");
		ArrayList<String> interests = new ArrayList<String>();
		interests.add("mobile");
		interests.add("Android");
		interests.add("apps");
		revmob.setUserInterests(interests);

		try {
			LocationManager locationManager = (LocationManager) getApplicationContext()
					.getSystemService(Context.LOCATION_SERVICE);
			Location gpsLocation = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			Location netLocation = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

			if (gpsLocation != null) {
				revmob.setUserLocation(gpsLocation.getLatitude(),
						gpsLocation.getLongitude(), gpsLocation.getAccuracy());
			} else if (netLocation != null) {
				revmob.setUserLocation(netLocation.getLatitude(),
						netLocation.getLongitude(), netLocation.getAccuracy());
			} else {
				RMLog.d("No location data available");
			}
		} catch (Exception e) {
			RMLog.d("Unable to get the location data");
		}
	}
	
	
	//adMob
	@SuppressLint("NewApi")
	public void showadMob() {
		AdView adView = new AdView(this, AdSize.BANNER,
				"a152b96e8b244ee");
		AdRequest request = new AdRequest();
		request.addTestDevice(AdRequest.TEST_EMULATOR);
		adView.loadAd(request);
		adView.setGravity(Gravity.TOP);
		adView.setY(100);

		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		relativeParams.addRule(RelativeLayout.ALIGN_BOTTOM);

		// this.setContentView(adView);
		this.addContentView(adView, relativeParams);
	}

	// Revmob...
	
	
	public void showBannerCustomSize(ViewGroup v) {
		RevMobBanner banner = revmob.createBanner(currentActivity);
		
		v.removeAllViews();
		v.addView(banner);
	}
	
	
	public void showAbsoluteBannerOnTop() {
		revmob.showBanner(currentActivity, Gravity.TOP, null, revmobListener);
	}

	public void showAbsoluteBannerOnBottom() {
		revmob.showBanner(currentActivity, Gravity.BOTTOM, null, revmobListener);
		
		
	}

	public void showFullscreen() {

		revmob.showFullscreen(currentActivity);

	}

	public void showPopup() {

		revmob.showPopup(currentActivity);
	}

	public void showAdLink() {

		revmob.openAdLink(currentActivity, revmobListener);
	}

	// chart boost..

	public void showInterstitial() {
		/*
		 * showInterstitial()
		 * 
		 * Shows an interstitial on the screen
		 * 
		 * Notes: - Shows a cached interstitial if one exists - Otherwise
		 * requests an interstitial and shows it
		 */
		this.cb.showInterstitial();

		Log.i(TAG, "showInterstitial");
		String toastStr = "Loading Interstitial";
		if (cb.hasCachedInterstitial())
			toastStr = "Loading Interstitial From Cache";
		Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
	}

	public void showMoreButtonClick() {

		this.cb.cacheMoreApps();
		this.cb.showMoreApps();

		Log.i(TAG, "showMoreApps");
		String toastStr = "Showing More-Apps";
		if (cb.hasCachedMoreApps())
			toastStr = "Showing More-Apps From Cache";
		Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();

	}

}
