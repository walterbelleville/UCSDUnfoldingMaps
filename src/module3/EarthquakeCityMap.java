package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */


public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int yellow = color(255, 255, 0);
	    int red = color(255, 0, 0);
	    int blue = color(0, 0, 255);
	    int orange = color(255, 153, 0);
	    int green = color(0, 153, 51);
	    int purple =  color(153, 0, 153);
	    int darkgreen = color(153, 153, 102);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    //if (earthquakes.size() > 0) {
	    for (int i = 0; i < earthquakes.size(); i++)
	    {
	    	PointFeature f = earthquakes.get(i);
	    	System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	SimplePointMarker s = createMarker(earthquakes.get(i));
	    	//float earthquakeMarkers = MapUtils.createSimpleMarkers(s);
	    	// PointFeatures also have a getLocation method
	    	if (mag < 3)
	    	{
	    		s.setStrokeWeight(0);
	    		s.setColor(darkgreen);
	    		s.setRadius(4);
	    	}
	    	else if (mag >= 3 && mag <= 3.9)
	    	{
	    		s.setStrokeWeight(0);
	    		s.setColor(blue);
	    		s.setRadius(4);
	    	}	
	    	
	    	else if (mag >= 4.0 && mag <= 4.9) 
	    	{
	    		s.setStrokeWeight(0);
	    		s.setColor(yellow);
	    		s.setRadius(8);
	    	}
	    	else if (mag >= 5 && mag <= 5.9) 
	    	{
	    		s.setStrokeWeight(0);
	    		s.setColor(red);
	    		s.setRadius(12);
	    	}
	    	else if (mag >= 6 && mag <= 6.9) 
	    	{
	    		s.setStrokeWeight(0);
	    		s.setColor(orange);
	    		s.setRadius(16);
	    	}
	    	else if (mag >= 7 && mag <= 7.9) 
	    	{
	    		s.setStrokeWeight(0);
	    		s.setColor(green);
	    		s.setRadius(20);
	    	}
	    	else if (mag >= 8) 
	    	{
	    		s.setStrokeWeight(0);
	    		s.setColor(purple);
	    		s.setRadius(24);
	    	}
	    	map.addMarkers(s);
	    	
	    }
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		
		// finish implementing and use this method, if it helps.
		return new SimplePointMarker(feature.getLocation());
	}
	
	public void draw() {
	    //background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	private void addKey() 
	{	
		//box
		fill(255, 255, 255);
		rect(10, 30, 170, 400);
		
		//Title
		fill(0, 0, 0);
		textSize(12);
		text("Earthquake Key", 50, 70);
		
		//purple earthquake key
		fill(153, 0, 153);
		ellipse(30, 120, 24, 24);
		
		fill(0, 0, 0);
		textSize(12);
		text("8.0+ Magnitude", 50, 125);
		
		//green earthquake key
		fill(0, 153, 51);
		ellipse(30, 160, 20, 20);
		
		fill(0, 0, 0);
		textSize(12);
		text("7.0 - 7.9 Magnitude", 50, 165);
		
		//orange earthquake key
		fill(255, 153, 0);
		ellipse(30, 200, 16, 16);
		
		fill(0, 0, 0);
		textSize(12);
		text("6.0 - 6.9 Magnitude", 50, 205);
		
		//Red earthquake key
		fill(255, 0, 0);
		ellipse(30, 240, 12, 12);
		
		fill(0, 0, 0);
		textSize(12);
		text("5.0 - 5.9 Magnitude", 50, 245);
		
		// Yellow earthquake key
		fill(255, 290, 0);
		ellipse(30, 270, 8, 8);
		
		fill(0, 0, 0);
		textSize(12);
		text("4.0 - 4.9 Magnitude", 50, 275);
		
		// blue earthquake key
		fill(0, 0, 255);
		ellipse(30, 300, 4, 4);
		
		fill(0, 0, 0);
		textSize(12);
		text("3.0 - 3.9 Magnitude", 50, 305);
		
		// dark green earthquake key
		fill(153, 153, 102);
		ellipse(30, 340, 4, 4);
		
		fill(0, 0, 0);
		textSize(12);
		text("2.9 or less Magnitude", 50, 345);
				
	}
}
