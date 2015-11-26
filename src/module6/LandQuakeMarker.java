package module6;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;
import processing.core.PImage;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		//this.image;
		// setting field in earthquake marker
		isOnLand = true;
	}
	
	/*public LandImage(Feature image) {
		super(((PointFeature)image).getImage())
	}*/


	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// IMPLEMENT: drawing circle for LandQuake
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		//pg.ellipse(x, y, 2*radius, 2*radius);
		//pg.strokeWeight(4);
		//pg.stroke(67, 211, 227, 100);
		//pg.noFill();
		pg.ellipse(x, y, 2*radius, 2*radius);
		pg.strokeWeight(2);
		//pg.image(lImage, x, y);
		
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}
	
	/*public String getImage() {
		return 
	}*/
		
}