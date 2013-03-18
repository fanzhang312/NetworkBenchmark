import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class TestDocumentsList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// need to include guava-14.0.1.jar (which is the latest version)
			SpreadsheetService service = new SpreadsheetService("Spreadsheet List Demo");
			
			service.setUserCredentials("fan.zhang.develop@gmail.com",
					args[0]);
			System.out.println("login successful");
			FeedURLFactory factory = FeedURLFactory.getDefault();
			SpreadsheetFeed feed = service.getFeed(factory.getSpreadsheetsFeedUrl(),
					SpreadsheetFeed.class);

			SpreadsheetEntry spreadsheet = feed.getEntries().get(0);
		    System.out.println(spreadsheet.getTitle().getPlainText());

		    WorksheetFeed worksheetFeed = service.getFeed(
		            spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
		        List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
		        WorksheetEntry worksheet = worksheets.get(0);
		        System.out.println(worksheet.getTitle().getPlainText());
		        // Fetch the list feed of the worksheet.
		        URL listFeedUrl = worksheet.getListFeedUrl();
		        ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);

		        // Iterate through each row, printing its cell values.
		        for (ListEntry row : listFeed.getEntries()) {
		          // Print the first column's cell value
		          System.out.print(row.getTitle().getPlainText() + "\n");
		          // Iterate over the remaining columns, and print each cell value
		          for (String tag : row.getCustomElements().getTags()) {
		            System.out.print(row.getCustomElements().getValue(tag) + "\t");
		          }
		          System.out.println();
		        }
		        
		     // Create a local representation of the new row.
		        ListEntry row = new ListEntry();
		        row.getCustomElements().setValueLocal("IMEI", "35-209900-176148-1");
		        row.getCustomElements().setValueLocal("JsonData", "{ping:'36ms',download:'3.60Mbps',upload:'1.33Mbps'}");

		        // Send the new row to the API for insertion.
		        row = service.insert(listFeedUrl, row);
		    
		} catch (AuthenticationException e) {
			System.out.println("1");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("2");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("3");
			e.printStackTrace();
		} catch (ServiceException e) {
			System.out.println("4");
			e.printStackTrace();
		}

	}

}
