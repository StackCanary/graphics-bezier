package dpoints;

import matrix.Vector;

public abstract class AbstractPoint implements Cloneable {
	
	public boolean pressed = false;
	
	public abstract AbstractPoint add(AbstractPoint point);
	public abstract AbstractPoint mul(double scalar);
	public abstract AbstractPoint sub(AbstractPoint point);
	
	public abstract AbstractPoint clone();
	
	public abstract Vector toVector();
	
	public void press()
	{
		pressed = !pressed;
	}
	
	public abstract void scale(double scale);
	public abstract void normalise();
	
	
}
