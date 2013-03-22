package com.networkbenchmark;

import java.util.Collections;
import java.util.List;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * PerformanceResults class aims for obtain the results data from Parse
 * getHistory() return all the data for a specify IMEI
 * getLatest() return the latest data for a specify IMEI
 * 
 * @author fan
 *
 */
public class PerformanceResults {
	public String imei;
	public List<ParseObject> history;
	public ParseObject latest;
	
	public PerformanceResults(String imei){
		this.imei = imei;
		history = null;
		latest = null;
	}
	
	public List<ParseObject> getHistory(){
		ParseQuery query = new ParseQuery("PerfMeasure");
		query.whereEqualTo("imei", this.imei);
		query.orderByDescending("createdAt");
		try {
			history = query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.d("record", "Error: " + e.getMessage());
		}
		return history;
	}
	
	public ParseObject getLatest(){
		ParseQuery query = new ParseQuery("PerfMeasure");
		query.whereEqualTo("imei", this.imei);
		try {
			latest = query.getFirst();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.d("record", "Error: " + e.getMessage());
		}
		return latest;
	}
	
	public String print(ParseObject record){
		return  "Device ID: " + record.getString("imei")
				+ "\nPerformance result is (Bytes/Sec):" + record.getNumber("perfResult")
				+ "\nPerformance time is (msecs):" + record.getNumber("perfMeasureTime")
				+ "\nnum bytes received is:" + record.getNumber("receivedBytes")
				+ "\nlocation: " + record.getNumber("latitude")+" "+record.getNumber("longitude")
				+ "\ntime: " + record.getUpdatedAt().toLocaleString()+"\n\n";
	}
	
	public String printList(List<ParseObject> recordList){
		String print = "";
		for(ParseObject item : recordList){
			print += this.print(item);
		}
		return print;
	}
}
