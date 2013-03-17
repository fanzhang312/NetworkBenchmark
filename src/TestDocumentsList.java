import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
			
			FeedURLFactory factory = FeedURLFactory.getDefault();
			SpreadsheetFeed feed = service.getFeed(factory.getSpreadsheetsFeedUrl(),
					SpreadsheetFeed.class);
			for (SpreadsheetEntry entry : feed.getEntries()) {
				System.out.println(entry.getTitle().getPlainText());
			}

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
