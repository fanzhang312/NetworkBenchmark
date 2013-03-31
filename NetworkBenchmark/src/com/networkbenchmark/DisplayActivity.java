package com.networkbenchmark;

import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.parse.ParseObject;

public class DisplayActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Display map
		setContentView(R.layout.map_main);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		// Initial map overlay
		List<Overlay> mapOverlays = mapView.getOverlays();
		// Add marker image
		Drawable drawable = this.getResources().getDrawable(
				R.drawable.ic_launcher);
		// Customized overlay, markers are added on this overlay
		HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(
				drawable, this);
		// Get the message from the intent
		Intent intent = getIntent();
		String imei = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		imei = "A1000013765D35";
		// Get history data for the specified IMEI
		PerformanceResults history = new PerformanceResults(imei);
		List<ParseObject> historyData = history.getHistory();
		// Filter out the null and empty value, set GeoPoint and overlayitem
		historyData.removeAll(Collections.singleton(null));
		for (ParseObject record : historyData) {
			Number r = record.getNumber("latitude");
			if (r != null && r.intValue()!=0) {
				float lat = record.getNumber("latitude").floatValue();
				float lon = record.getNumber("longitude").floatValue();
				String result = ""+record.getNumber("perfResult");
				int latitude = (int)(lat * 1E6);
				int longitude = (int)(lon * 1E6);
				GeoPoint point = new GeoPoint(latitude, longitude);
				OverlayItem overlayitem = new OverlayItem(point,
						"Download Speed (Bytes/Sec)", result);

				itemizedoverlay.addOverlay(overlayitem);
				mapOverlays.add(itemizedoverlay);

			}
		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
