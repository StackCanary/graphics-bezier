package bezier;

import java.util.ArrayList;
import java.util.List;

import dpoints.AbstractPoint;
import dpoints.Point2d;
import dpoints.Point3d;

/*
 * https://pages.mtu.edu/~shene/COURSES/cs3621/NOTES/spline/Bezier/bezier-der.html
 */

public class Bezier {

	static final double EPSILON = 0.0005;

	List<AbstractPoint> points; public int n; public List<Point2d> bezier = new ArrayList<Point2d>();
	

	public Bezier(List<AbstractPoint> points)
	{
		this.points = points; this.n = points.size() - 1; 
		
		assert(points.size() > 0);
	}
	
	public AbstractPoint bezier(double t)
	{

		AbstractPoint result = null;

		if (Math.abs(t - 0) < EPSILON)
			return points.get(0).clone();


		if (Math.abs(t - 1) < EPSILON)
			return points.get(n).clone();

		for (int i = 0; i <= n; i++)
		{
			AbstractPoint term = points.get(i).mul(MathsUtil.bernstein(n, i, t));

			if (i < 1)
				result = term.clone();
			else
			{
				result = result.add(term);
			}
		}
		
		return result;
	}

	public AbstractPoint bezier1stDerivative(double t)
	{
		AbstractPoint result = null;

		for (int i = 0; i <= n - 1; i++)
		{
			
			AbstractPoint term = points.get(i + 1).sub(points.get(i)).mul(MathsUtil.bernstein(n - 1, i, t) * n);
			
			if (i < 1)
				result = term.clone();
			else
				result = result.add(term);
		}

		return result;
	}


	public AbstractPoint bezier2ndDerivative(double t)
	{
		AbstractPoint result = null;
		
		for (int i = 0; i <= n - 2; i++)
		{
			
			AbstractPoint temp = points.get(i + 2).sub(points.get(i + 1).mul(2)).add(points.get(i));
			
			double bern = MathsUtil.bernstein(n - 2, i, t);
			
			AbstractPoint term = temp.mul(n).mul(n - 1).mul(bern); 
			
			if (i < 1)
				result = term.clone();
			else
				result = result.add(term);
		}

		return result;
	}
	

	
}
