package model;

import javax.tools.DocumentationTool;
import javax.xml.stream.Location;

/**
 * 
 * @author Kubeka BS 217010763
 * @param <T>
 *
 */

public class PowerPlant implements Comparable<PowerPlant> {
	private String pName ="";
	private double aCoal=0.0;
	private int x;
        private int y;
    
    public PowerPlant(String string, double coal, int x, int y) {

this.pName=string;
this.aCoal= coal;
this.x= x;
this.y= y;
	}

    public PowerPlant() {
     
    }
/**
 * 
 * @return name value 
 */
	public String getpName() {
		return pName;
	}
        
        public Double getaCoal() {
		return aCoal;
	}
        public int getX() {
		return x;
        }
                 public int getY() {
		return y;
	}
                  public void setaCoal(int aC) {
		this.aCoal=aC;
        } 
          public void setX(int x) {
		this.x=x;
        } 
          
           public void setY(int y) {
		this.y=y;
        } 
/**
 * 
 * @param define pName value
 */
	public void setpName(String pName) {
		this.pName = pName;
	}
	

/**
 * string value
 */
	public String toString() {
		return pName;
		
	}
        
        /**
	 *function for hashCode
	 */
	@Override
	public int hashCode() {
            int res = 1;
		final int p = 31;
		
		res = p * res + ((pName == null) ? 0 : pName.hashCode());
		
		res = p * res + x;
		res = p * res + y;
		return res;
	}

    @Override
    public int compareTo(PowerPlant o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
