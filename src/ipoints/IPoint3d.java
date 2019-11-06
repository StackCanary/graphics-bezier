package ipoints;

import dpoints.AbstractPoint;
import dpoints.Point3d;

public class IPoint3d {
	
	public int x;
	public int y;
	public int z;
	
	public IPoint3d(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public AbstractPoint toDPoint()
	{
		return new Point3d(x, y, z);
	}
	
	@Override
	public int hashCode() 
	{
		return Integer.hashCode(Integer.hashCode(x) + Integer.hashCode(y) + Integer.hashCode(z));
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof IPoint3d))
			return false;
		
		IPoint3d p = (IPoint3d) obj;
		
		return x == p.x && y == p.y && z == p.z;
		
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	

}
