package draw._2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import bezier.Bezier;
import dpoints.Point2d;
import draw.Graphics2dUtil;
import ipoints.IPoint2d;

public class Bezier2dPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	List<IPoint2d> points = new ArrayList<IPoint2d>(); Bezier function = null; IPoint2d dragged = null;
	
	boolean drawTangent; boolean drawNormals; public int nSample;

	public Bezier2dPanel() 
	{
		setBackground(Color.gray);
		Bezier2dMouse mouse = new Bezier2dMouse(this); addMouseListener(mouse); addMouseMotionListener(mouse);
	}
	
	public IPoint2d search(int x, int y, int epsilon)
	{
		for(IPoint2d point : points)
		{
			if ( Math.abs(point.x - x) < epsilon && Math.abs(point.y - y) < epsilon)
			{
				return point;
			}
		}
		return null;
	}
	
	public void reset()
	{
		function = new Bezier(points.stream().map(IPoint2d::toDPoint).collect(Collectors.toList()));
	}
		
	public void setNSample(int sample)
	{
		this.nSample = sample; 
		repaint(); revalidate();
	}
	
	public void setTangent()
	{
		drawTangent = !drawTangent; 
		repaint(); revalidate();
	}
	
	public void setNormals()
	{
		drawNormals = !drawNormals; 
		repaint(); revalidate();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		Graphics2dUtil.drawControlPoints(function, g, points);
		
		g.setColor(Color.BLACK);
		
		if (function != null && function.n > 0)
		{
			Graphics2dUtil.drawCurve(function, g);

			if (nSample > 0)
				for (double t = 0; t <= 1; t += (double) 1 / nSample)
				{
					Point2d p = (Point2d) function.bezier(t); Graphics2dUtil.drawSamplePoint(function, g, p);

					if (drawTangent)
						Graphics2dUtil.drawTangent(function, g, p, t);
					
					if (drawNormals)
						Graphics2dUtil.drawNormals(function, g, p, t);
				}
		}
		
			
	}

	
}
