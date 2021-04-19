
package model;

/**
 *
 * @author Kubeka 217010763
 */
public class Route {
    private String pName ="";
	private double cost=0.0;
	private int x1;
        private int y1;
    private int x2;
        private int y2;


    public Route(String n, double c, int x1, int y1, int x2,int y2) {
     this.pName=n;
     this.x1= x1;
     this.x2=x2;
     this.y1=y1;
     this.y2=y2;
    }
/**
 * 
 * @return name value 
 */
	public String getpName() {
		return pName;
	}
        
        public Double getcost() {
		return cost;
	}
        public int getX1() {
		return x1;
        }
                 public int getY1() {
		return y1;
	}
          
                 public int getX2() {
		return x2;
        }
                 public int getY2() {
		return y2;
	}
                  public void setCost(int aC) {
		this.cost=aC;
        } 
          public void setX2(int x) {
		this.x2=x;
        } 
          
           public void setY2(int y) {
		this.y2=y;
        } 
           
           public void setX(int x) {
		this.x2=x;
        } 
          
           public void setY(int y) {
		this.y2 =y;
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

}
