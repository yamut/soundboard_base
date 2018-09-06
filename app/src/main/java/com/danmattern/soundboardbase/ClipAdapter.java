package com.danmattern.soundboardbase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dan on 2/25/2017.
 */

public class ClipAdapter extends ArrayAdapter {
	private static final String TAG = "ClipAdapter";
	private final int layoutResource;
	private final LayoutInflater layoutInflater;
	private List<ClipEntry> clips;

	public ClipAdapter (Context context, int resource, List<ClipEntry> clips) {
		super(context, resource);
		this.layoutResource = resource;
		this.layoutInflater = LayoutInflater.from(context);
		this.clips = clips;
	}

	public ClipEntry getClipEntry (int position) {
		return clips.get(position);
	}

	@Override
	public int getCount () {
		return clips.size();
	}

	@NonNull
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(layoutResource, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ClipEntry currentClip = clips.get(position);
		viewHolder.textLabel.setText(currentClip.getTextLabel());
		return convertView;
	}

	private class ViewHolder {
		final TextView textLabel;

		ViewHolder (View v) {
			this.textLabel = (TextView) v.findViewById(R.id.textLabel);
		}
	}
}
