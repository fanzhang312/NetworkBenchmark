package com.networkbenchmark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		Intent intent = getIntent();
	    double[] message = intent.getDoubleArrayExtra(MainActivity.EXTRA_MESSAGE);
		
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		//webView.loadUrl("https://google-developers.appspot.com/maps/documentation/javascript/examples/map-simple");
	
		String customHtml=
		"<head>"+
	    "<meta name='viewport' content='initial-scale=1.0, user-scalable=no' />"+
	    "<style type='text/css'>"+
	     " html { height: 100% }"+
	     " body { height: 100%; margin: 0; padding: 0 }"+
	     " #map-canvas { height: 100% }"+
	   " </style>"+
	   " <script type='text/javascript'"+
	   "   src='https://maps.googleapis.com/maps/api/js?key=AIzaSyAU1h9XBM08rMQ9x6Kz4ixAwhwR5K2kZ88&sensor=false'>"+
	  "  </script>"+
	   " <script type='text/javascript'>"+
	   "   function initialize() {"+
	   "     var mapOptions = {center: new google.maps.LatLng("+message[0]+","+message[1]+"), zoom: 8, mapTypeId: google.maps.MapTypeId.ROADMAP};"+
	   "     var map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);"+
	   "	var marker=new google.maps.Marker({position: new google.maps.LatLng("+message[0]+","+message[1]+")  });"+
	   "	marker.setMap(map);"+
	   "   }"+
	   "   google.maps.event.addDomListener(window, 'load', initialize);"+
	  "  </script>"+
	"  </head>"+
	"  <body>"+
	"    <div id='map-canvas'/>"+
	"  </body>";
		webView.loadData(customHtml, "text/html", "UTF-8");

	
		
	}

}