package gui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Polyline;
/**
 * 
 * @author Kubeka Bs 217010763
 */
public class Edge extends Group {
	private Polyline Line  ;
	private SimpleDoubleProperty loc1X= new SimpleDoubleProperty();
	private SimpleDoubleProperty loc2X= new SimpleDoubleProperty();
	private SimpleDoubleProperty loc1Y= new SimpleDoubleProperty();
	private SimpleDoubleProperty loc2Y= new SimpleDoubleProperty();
        
	public SimpleDoubleProperty getLoc1X() {
		return loc1X;
	}

	public void setLoc1X(SimpleDoubleProperty loc1x) {
		loc1X = loc1x;
	}

	public SimpleDoubleProperty getLoc2X() {
		return loc2X;
	}

	public void setLoc2X(SimpleDoubleProperty loc2x) {
		loc2X = loc2x;
	}

	public SimpleDoubleProperty getLoc1Y() {
		return loc1Y;
	}

	public void setLoc1Y(SimpleDoubleProperty loc1y) {
		loc1Y = loc1y;
	}

	public SimpleDoubleProperty getLoc2Y() {
		return loc2Y;
	}

	public void setLoc2Y(SimpleDoubleProperty loc2y) {
		loc2Y = loc2y;
	}
  /**
   * Default Constructor
   */
        public Edge(){
            
        }
	
	/**
         * constructor to set Up Line
         * @param loc1X
         * @param lo1Y
         * @param loc2X
         * @param loc2Y 
         */
	public Edge(double loc1X, double lo1Y, double loc2X, double loc2Y) {
		Line= new  Polyline();
		this.loc1X.set(loc1X);
		this.loc2X.setValue(loc2X);
		this.loc1Y.setValue(lo1Y);
		this.loc2Y.setValue(loc2Y);
		getChildren().add(Line);
	
	for(SimpleDoubleProperty d : new SimpleDoubleProperty[] {getLoc1X(),getLoc1Y(),getLoc2X(),getLoc2Y()}) {
		d.addListener( (e,ev,ex)->Check());
	}
	Check();
}

	private void Check() {
		Line.getPoints().setAll(loc1X.get(),loc1Y.get(),loc2X.get(),loc2Y.get());
	}
	}
