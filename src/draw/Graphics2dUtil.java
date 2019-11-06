package draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.util.List;

import bezier.Bezier;
import dpoints.Point2d;
import ipoints.IPoint2d;

public class Graphics2dUtil {


	final static int SIZE = 10;
	final static int TEXT = 10;

	public final static int POINT_RADIUS_EPSILON = SIZE/2 + 2;

	public static void drawControlPoints(Bezier function, Graphics g, List<IPoint2d> points)
	{
		for (int i = 0; i < points.size(); i++)
		{
			IPoint2d point = points.get(i);

			if (i == points.size() - 1)
				g.setColor(Color.RED);

			if (i == 0)
				g.setColor(Color.GREEN);

			g.fillOval(point.x - SIZE/2, point.y - SIZE/2, SIZE, SIZE); g.setColor(Color.BLACK); g.drawString("p " + i, point.x, point.y - TEXT);
		}	
	}

	public static void drawCurve(Bezier function, Graphics g)
	{

		double granularity = 0.001;

		Graphics2D g2d = (Graphics2D) g; g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Path2D path = new Path2D.Double(); 

		Point2d startPoint = (Point2d) function.bezier(0); path.moveTo(startPoint.x, startPoint.y);

		for (double t = granularity; t <= 1; t += granularity)
		{
			Point2d p = (Point2d) function.bezier(t); path.lineTo(p.x, p.y);
		}

		g2d.draw(path);
	}

	public static void drawSamplePoint(Bezier function, Graphics g, Point2d p)
	{
		final int oval_size = 4;

		g.drawOval((int) p.x - oval_size/2, (int) p.y - oval_size/2, oval_size, oval_size);
	}

	public static void drawTangent(Bezier function, Graphics g, Point2d p, double t)
	{
		Point2d tangent = (Point2d) function.bezier1stDerivative(t);
		
		tangent.normalise(); tangent.scale(100);

		if (tangent == null)
			return;
		
		
		g.setColor(Color.BLUE);

		g.drawLine((int) p.x, (int) p.y, (int) (p.x + tangent.x * 0.25) , (int) (p.y + tangent.y * 0.25));
		
		g.setColor(Color.BLACK);
	}

	public static void drawNormals(Bezier function, Graphics g, Point2d p, double t)
	{
		Point2d tangent = (Point2d) function.bezier1stDerivative(t);
		
		tangent.normalise(); tangent.scale(100);
		
		if (tangent == null)
			return;

		double normal_x = - tangent.y;
		double normal_y =   tangent.x;

		Point2d _2ndDerivative = (Point2d) function.bezier2ndDerivative(t);

		if (_2ndDerivative == null)
			return;

		double dot = normal_x * _2ndDerivative.x + normal_y * _2ndDerivative.y;
		
		if (dot < 0)
		{
			normal_x = -normal_x;
			normal_y = -normal_y;
		}
		
		g.setColor(Color.PINK);

		g.drawLine((int) p.x, (int) p.y, (int) (p.x + normal_x * 0.25) , (int) (p.y + normal_y * 0.25));

		g.setColor(Color.BLACK);
	}

}
