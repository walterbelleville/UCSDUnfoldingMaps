package module6;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;
import processing.core.PImage;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		//this.image;
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		//IMPLEMENT: drawing centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		//pg.rect(x-radius, y-radius, 2*radius, 2*radius);
		//pg.image(oImage, x, y);
		
		//pg.strokeWeight(4);
		//pg.stroke(67, 211, 227, 100);
		//pg.noFill();
		pg.rect(x-radius, y-radius, 2*radius, 2*radius);
		pg.strokeWeight(2);
		//pg.ellipse(x, y, 2*radius, 2*radius);
	}
	

	

}
