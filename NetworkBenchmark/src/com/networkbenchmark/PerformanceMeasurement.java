package com.networkbenchmark;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


public class PerformanceMeasurement {
	public final String imei;
	public Boolean perfCalculated = false;
	public final String PerfName = "Download Speed Test";
	public float perfResult;
	public long perfTime;
	public long perfBytes;
	
	public String temporary;
	public String temporary2;

	// constructor
	public PerformanceMeasurement(){
		// get IMEI of this device
		this.imei = "temporary IMEI";
	}

	// code to calculate a performance measurement
	public void Calculate(){
		this.perfCalculated = true;
		this.perfResult = 35;
		
		// start a thread to calculate download speed in background
		Thread t = new Thread(new downloader(this));
		t.start();
	}
	
	// code to calculate download speed goes here
	private static class downloader
		extends PerformanceMeasurement
		implements Runnable   {
		
		private PerformanceMeasurement PerfMeasure;
		public downloader(PerformanceMeasurement perf){
			this.PerfMeasure = perf;
		}
		
		public void run(){
			try {
		
				PerfMeasure.perfBytes = 0;
				PerfMeasure.perfResult = (float)0.0;
				PerfMeasure.perfTime = 0;
				
			    String line = "";
			   
			    // get time
			    long l1 = System.currentTimeMillis();
			    
			    URL url = new URL("http://www-users.cs.umn.edu/~jtucker/");
			    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		        InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());	        
		        BufferedReader reader = new BufferedReader(isr);
		        while((line = reader.readLine()) != null){
		        	PerfMeasure.perfBytes += line.getBytes().length;
		        }

		        // get time
			    long l2 = System.currentTimeMillis();
		        
			    PerfMeasure.perfTime = l2-l1;
			    if (PerfMeasure.perfTime != 0){
				    PerfMeasure.perfResult = (float) (PerfMeasure.perfBytes/(((float)PerfMeasure.perfTime)/1000.0));
			    }

		        urlConnection.disconnect();		        

	        } catch (Exception e){
			    PerfMeasure.temporary2 = e.toString();
			    PerfMeasure.temporary = "FAILED TO CALCULATE SPEED!!";
	        }
		}
	}
	
	// code to save a performance measurement
	// likely this is going to have to be threaded
	public void Save(){
	}
	
	@Override
	public String toString(){
		return  "Performance result is (Bytes/Sec):\n" + this.perfResult
				+ "\nPerformance time is (msecs):" + this.perfTime
				+ "\nnum bytes received is:" + this.perfBytes;
		
		//this.PerfName + "\n" + this.imei + "\n"
			//	+ "\n" + this.temporary + "\n" + this.temporary2;
		//return this.temporary2;
	}
}
