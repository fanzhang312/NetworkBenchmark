import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;


public class Upload {

	/**
	 * @param args
	 */
	public static final String SPREADSHEET_NAME = "Network Benchmark";
	public SpreadsheetService service;
	public SpreadsheetFeed feed;
	public FeedURLFactory factory;
	public SpreadsheetEntry spreadsheet;
	public WorksheetEntry worksheet;
	
	public Upload(){
		
	}
	public Upload(String IMEI, String JsonData){
		GetSpreadsheetService();
		LoadSheet(SPREADSHEET_NAME);
		GetWorksheet();
		saveData(IMEI, JsonData);
	}
	public void LoadSheet(String spreadsheetName){
		factory = FeedURLFactory.getDefault();
		try {
			feed = service.getFeed(factory.getSpreadsheetsFeedUrl(),
					SpreadsheetFeed.class);
			for(SpreadsheetEntry spreadsheet:feed.getEntries()){
				if(spreadsheet.getTitle().getPlainText().equals(spreadsheetName)){
					this.spreadsheet = spreadsheet;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void GetSpreadsheetService(){
		try {
			service = new SpreadsheetService("Save data to Spreadsheet");
			service.setUserCredentials("fan.zhang.develop@gmail.com","python@umn");
			System.out.println("login successful");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}
	public void GetWorksheet(){
		WorksheetFeed worksheetFeed;
		try {
			worksheetFeed = service.getFeed(
			        spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
			List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
	        worksheet = worksheets.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
	public boolean saveData(String IMEI, String JsonData){
		URL listFeedUrl = worksheet.getListFeedUrl();
		ListEntry row = new ListEntry();
        row.getCustomElements().setValueLocal("IMEI", IMEI);
        row.getCustomElements().setValueLocal("JsonData", JsonData);

        // Send the new row to the API for insertion.
        try {
			row = service.insert(listFeedUrl, row);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        System.out.println("Save data successfully");
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Upload u = new Upload("35-209900-176148-2", "{ping:'36ms',download:'3.60Mbps',upload:'1.33Mbps'}");
//		Upload u = new Upload();
//		u.GetSpreadsheetService();
	}

}
