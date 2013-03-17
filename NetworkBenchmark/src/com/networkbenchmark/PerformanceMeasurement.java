package com.networkbenchmark;

import android.content.Context;
import android.telephony.TelephonyManager;


public class PerformanceMeasurement {
	public final String imei;
	public Boolean perfCalculated = false;
	public final String PerfName = "Download Speed Test";
	public int perfResult;
	
	public PerformanceMeasurement(){
		// get IMEI of this
		this.imei = "temporary IMEI";
	}

	// code to calculate a performance measurement
	public void Calculate(){
		this.perfCalculated = true;
		this.perfResult = 35;
	}
	
	// code to save a performance measurement
	public void Save(){
	}
	
	@Override
	public String toString(){
		return this.PerfName + "\n" + this.imei + "\n" + "Performance result is:\n" + this.perfResult;
	}
}
