package model.gameObjects;

import general.Stage;
import resources.ImgResources;

import java.awt.*;

/***
 * Null object, stops null pointer exceptions.
 *
 * @author claytohann
 *
 */
public class NullGameObject extends GameObject {
    private String objectName = "Type: Null Object";
	private Point p;

    public NullGameObject(Point p){
        this.p = p;
    }

    @Override
    public void collide(Car c) {

    }

    @Override
    public String getID() {
        return "0";
    }

    @Override
    public Point getOrigin() {
        return null;
    }

    @Override
    public Rectangle getBoundingBox() {
    	Rectangle bounds = new Rectangle(p.x*ImgResources.imgDimension, p.y*ImgResources.imgDimension,ImgResources.imgDimension,ImgResources.imgDimension);
    return bounds;
    }

    @Override
    public ImgResources getImageEnum(Stage s) {
        return null;
    }

    @Override
    public boolean equals(Object o ){
        return o instanceof GameObject;
    }

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean isDestroyable() {
		return false;
	}

	@Override
	public String toString() {
		return objectName;
	}
}
