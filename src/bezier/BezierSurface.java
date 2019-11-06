package bezier;

import java.util.List;

import dpoints.AbstractPoint;

public class BezierSurface {
	
	List<AbstractPoint> points; int n; int m;
	
	public BezierSurface(List<AbstractPoint> points, int n, int m)
	{
		this.points = points; this.n = n; this.m = m;
	}
	
	public AbstractPoint bezier(double u, double v)
	{
		
		AbstractPoint result = null;

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				
				AbstractPoint term = points.get(i * (n + 1) + j).mul(MathsUtil.bernstein(n, i, u) * MathsUtil.bernstein(m, j, v));

				if (i < 1)
					result = term.clone();
				else
				{
					result = result.add(term);
				}
			}
		}

		return result;
	}

}
