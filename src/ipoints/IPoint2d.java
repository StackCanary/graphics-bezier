package ipoints;

import dpoints.AbstractPoint;
import dpoints.Point2d;

public class IPoint2d {
	
	public int x;
	public int y;
	
	public boolean dragged = false;
	
	public IPoint2d(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public AbstractPoint toDPoint()
	{
		return new Point2d(x, y);
	}
	
	@Override
	public int hashCode() 
	{
		return Integer.hashCode(Integer.hashCode(x) + Integer.hashCode(y));
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof IPoint2d))
			return false;
		
		IPoint2d p = (IPoint2d) obj;
		
		return x == p.x && y == p.y;
		
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
