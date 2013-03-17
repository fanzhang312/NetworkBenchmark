package com.networkbenchmark;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends Activity {

    public final static PerformanceMeasurement PerfMeasure = new PerformanceMeasurement();
    public final static String EXTRA_MESSAGE = "com.NetworkBenchmark.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
    
    public void viewPerfMeasure(View view) {
    	Intent intent = new Intent(this, ViewPerfMeasureActivity.class);
    	// this should take in the whole PerfMeasure object to process
    	intent.putExtra(EXTRA_MESSAGE, PerfMeasure.toString());
    	startActivity(intent);
    }
    
    /** Called when the user clicks the Save Performance Measurement button */
    public void savePerfMeasure(View view) {
    	PerfMeasure.Save();
    }
    
    
    
}
