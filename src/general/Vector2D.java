package general;

import java.awt.Point;

/***
 * Vector 2D wrapper class, wrapping an x and a y value
 * into a single class, along with helper and arithmetic
 * methods
 *
 * @author huonfraser
 *
 */
public class Vector2D {
    private final double x;
    private final double y;

    /***
     * Return a new Vector2D with the given x and y values
     * @param x
     * @param y
     */
    public Vector2D(double x, double y){
        this.x=x;
        this.y=y;
    }

    /***
     * @return the x value
     */
    public double getX(){
        return x;
    }

    /***
     * @return the y value
     */
    public double getY(){
        return y;
    }

    /***
     * Rotates the x and v values by a standard rotation matrix
     * and returns a new Vector
     * @param degrees
     * @return
     */
    public Vector2D rotate(double degrees){
        double newX = Math.cos(degrees)*x-Math.sin(degrees)*y;
        double newY = Math.sin(degrees)*x + Math.cos(degrees)*y;
        return new Vector2D(newX,newY);
    }

    /***
     * Two Vector2D objects are equal if they x and y values are
     *  within 0.001 of each other
     */
    @Override
    public boolean equals(Object o) throws ClassCastException{
    	Vector2D other = (Vector2D)o;
        return ( Math.abs(x-other.getX())<0.001 && (Math.abs(y-other.getY())) <0.001 );
    }

    /***
     * Return the length of the vector
     * @return
     */
    public double getMagnitude() {
    	return Math.sqrt(x*x+y*y);
    }

    /***
     * Returns a Vector in the same direction of length 1
     * @return
     */
    public Vector2D getUnitVector() {
    	return new Vector2D(x/getMagnitude(),y/getMagnitude());
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }

    /***
     * Return a new Vector with x and y values multiplied by l
     * @param l
     * @return
     */
    public Vector2D scalarMultiply(double l) {
    	return new Vector2D(x*l,y*l);
    }

    /***
     * Convert the Vector into a Point object
     * @see java.awt.point
     * @return
     */
    public Point toPoint() {
    	return new Point((int)x,(int)y);
    }

    /***
     * Return the vector given by the difference of this-other
     * @param other
     * @return
     */
    public Vector2D minus(Vector2D other){
        return new Vector2D(this.x-other.getX(),this.y-other.getY());
    }

    /***
     * Return the vector given by adding other to this
     * @param other
     * @return
     */
    public Vector2D add(Vector2D other){
        return new Vector2D(this.x+other.getX(),this.y+other.getY());
    }


    /***
     * Return the direction associated with this vector,
     * based of 0=NORTH,90=EAST,180=SOUTH,270=WEST
     *
     * Values are returned as integers between 0 and 360
     * @return
     */
    public int getDirection() {
    	int d = -(int)Math.round(Math.toDegrees(Math.atan((x/y))));
    	d+=360;	//make d always positive
    	if(x>=0 && y>=0) { //bottom right
    		d+=180;
    	//	System.out.println("bottom right");
    	}else if(x>=0 && y<0) {// top right
    		//d+=180;
    		//System.out.println("top right");
    	}else if(x <0 && y>=0) {//bottom left
    		d=-90;
    		//System.out.println("bottom left");
    	}else if(x<0 && y <0) {//bottom right
    		//System.out.println("bottom right");
    	}

    	return d%360;

    }
}
