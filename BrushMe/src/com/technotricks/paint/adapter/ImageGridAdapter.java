package com.technotricks.paint.adapter;

import java.util.ArrayList;

import com.technotricks.paint.R;
import com.technotricks.paint.manager.Utils;
import com.technotricks.paint.model.ImagesGridModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.RelativeLayout;

public class ImageGridAdapter extends ArrayAdapter<ImagesGridModel> {

	Context context;

	private RelativeLayout.LayoutParams layoutSize;

	public ImageGridAdapter(Context context, int resource,
			ArrayList<ImagesGridModel> objects, int numColumns) {
		super(context, resource, objects);

		this.context = context;

		int width = (Utils.getDeviceWidth(context) / numColumns);
		layoutSize = new RelativeLayout.LayoutParams(width, width);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final ImagesGridModel rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_image, null);
			holder = new ViewHolder();

			holder.layout = (RelativeLayout) convertView
					.findViewById(R.id.relItem);
			
			holder.imageIcon = (ImageView) convertView.findViewById(R.id.imageIcon);
			holder.btnMore = (ImageView) convertView.findViewById(R.id.btnMore);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		
		holder.layout.setLayoutParams(layoutSize);

		
		holder.imageIcon.setImageBitmap(Utils.getBitmapFromAsset(rowItem.getImageName(), context));
		holder.btnMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				showPopupMenu(v);

			}
		});
	

		return convertView;
	}

	@SuppressLint("NewApi")
	private void showPopupMenu(View v) {
		PopupMenu popupMenu = new PopupMenu(context, v);
		popupMenu.getMenuInflater().inflate(R.menu.splash,
				popupMenu.getMenu());

		popupMenu
				.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, item.toString(),
								Toast.LENGTH_LONG).show();
						return true;
					}
				});

		popupMenu.show();
	}

	/* private view holder class */
	private class ViewHolder {

		RelativeLayout layout;
		ImageView imageIcon;

		ImageView btnMore;
	}

}
