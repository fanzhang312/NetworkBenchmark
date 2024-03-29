package com.networkbenchmark;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import com.parse.Parse;


public class MainActivity extends Activity {
	
    public PerformanceMeasurement PerfMeasure;
    public final static String EXTRA_MESSAGE = "com.NetworkBenchmark.MESSAGE";
    
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.PerfMeasure = new PerformanceMeasurement(this.getBaseContext());
        Parse.initialize(this, "qT4wyDLVqILutqN3RRTbUxLwqiXFIHPXqnsVX7B5", "FGWV2oti13RNZrVendUhCW2TsmV8TbkOB3AjbWfO"); 
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the Calculate Performance Measurement button */
    public void calcPerfMeasure(View view) {
    	PerfMeasure.Calculate();
    	alertbox("HTTP Download Performance",PerfMeasure.toString());
    }
    
    public void viewPerfMeasure(View view) {
    	Intent intent = new Intent(this, ViewPerfMeasureActivity.class);
    	// this should take in the whole PerfMeasure object to process
    	intent.putExtra(EXTRA_MESSAGE, PerfMeasure.toString());
    	//startActivity(intent);
    	alertbox("Review Your Download Performance", PerfMeasure.toString());
    }
    
    /** Called when the user clicks the Save Performance Measurement button */
    public void savePerfMeasure(View view) {
    	double[] gps = getGPS();
    	PerfMeasure.Save(gps);
    	alertbox("Save Results","The performance results are saved successfully.");
    }
    
    /**
     * Display the history data for a specified IMEI
     * @param view
     */
    public void viewPerfHistory(View view) {
    	PerformanceResults history = new PerformanceResults(PerfMeasure.imei);
    	alertbox("Performance History", history.printList(history.getHistory()));
    }
    
    public void pingFunction(View view) {
    	Intent intent = new Intent(this, PingActivity.class);
    	// this should take in the whole PerfMeasure object to process
    	intent.putExtra(EXTRA_MESSAGE, "Ping");
    	startActivity(intent);
    }
    

    public void viewmap(View view){
    	Intent intent = new Intent(this, DisplayActivity.class);
    	intent.putExtra(EXTRA_MESSAGE, PerfMeasure.imei);
    	startActivity(intent);
    }
    
    public void helpFunction(View view) {
    	alertbox("How to use", 
    			"Speed Test: Check your internet speed\n" +
    			"View Result: Review your speed test\n" +
    			"Save the data: Save your speed test\n" +
    			"View History: Check all speed tests\n" +
    			"Ping Test: Test ping\n" +
    			"Show map: Show your performance history based on location\n\n\n" +
    			
    			"Update\n" +
    			"Version 1.7: Added My location\n"+
    			"Version 1.6: Added Map function\n"+
				"Version 1.5: Added GUI\n" +
				"Version 1.4: Added Ping Test\n" +
				"Version 1.3: Added View History\n" +
				"Version 1.2: Added Save The Data\n" +
				"Version 1.1: Added View Result\n" +
    			"Version 1.0: Added Speed Test\n\n" +
    			
    			"Developer:\n" +
    			"     Jeremy, Fan, Zhiqi, Yongtak\n");
    	 
    }
    
    public void myLocation(View arg0){
    	Intent intent2 = new Intent(this, WebViewActivity.class);
    	double[] gps = getGPS();
    	intent2.putExtra(EXTRA_MESSAGE, gps);
		startActivity(intent2);
    }
	/**
	 * Define an alert window
	 * 
	 * Add scrollview to the alertbox
	 */
	protected void alertbox(String title, String mymessage) {
		// add scrollview to alertbox
		new AlertDialog.Builder(this)
				.setMessage(mymessage)
				.setTitle(title)
				.setView(LayoutInflater.from(this).inflate(R.layout.scroll_result,null))
				.setCancelable(true)
				.setNeutralButton("Done",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
	}   
    
    /**
     * Get the GPS location
     * @return double[] gps
     */
    private double[] getGPS() {
		LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = locationManager.getProviders(true);
		Location location = null;
		for (String provider : providers) {
			location = locationManager.getLastKnownLocation(provider);
			if (location != null)
				break;
		}

		double[] gps = new double[2];
		if (location != null) {
			gps[0] = location.getLatitude();
			gps[1] = location.getLongitude();
		}
		return gps;
	}

}
