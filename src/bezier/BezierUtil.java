package bezier;

import java.util.ArrayList;
import java.util.List;

import dpoints.AbstractPoint;
import dpoints.Point3d;

public class BezierUtil {
	
	
	// Control points for Bezier Curve
	public static List<AbstractPoint> getCurve(int n)
	{
		List<AbstractPoint> list = new ArrayList<AbstractPoint>();
		

		final double h_size = 0.5; final double v_size = 2; 
		
		for (int i = 0; i <= n; i++)
		{
			int direction = 0;
			
			switch(i % 2)
			{
			case 0:
				direction = 0; break;
			case 1:
				direction = 1; break;
			}
			
			list.add(new Point3d(h_size * i, direction * v_size, - h_size * i));
		}
		
		return list;
	}
	
	// Control points for Bezier Surface
		
	public static List<AbstractPoint> getSurface(int n, int m)
	{
		List<AbstractPoint> points = new ArrayList<AbstractPoint>();
		
		final double size = 4; final int elevation = 1;
		
		for (int i = 0; i <= n; i++)
		{
			for (int j = 0; j <= m; j++)
			{
				points.add(new Point3d(j * size, elevation, - i * size)); 
			}
		}
		
		
		return points;
	}

}
