package draw._3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import bezier.Bezier;
import bezier.BezierSurface;
import bezier.BezierUtil;
import dpoints.AbstractPoint;
import matrix.Matrix;
import matrix.MatrixUtil;
import matrix.Vector;

// TODO Move Camera using Mouse
// TODO Fix Coordinates

public class Bezier3dPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public enum Strategy
	{
		Curve, 
		Surface
	}
	
	public Strategy strategy = Strategy.Curve;

	public Bezier function = null; List<Vector> samples = new ArrayList<Vector>(); List<Vector> tformed = new ArrayList<Vector>();
	
	public BezierSurface surface = null;
	
	public List<AbstractPoint> points = new ArrayList<AbstractPoint>(); List<Vector> tPoints = new ArrayList<Vector>();
 
	Matrix m; 

	Vector vo;
	Vector vx;
	Vector vy;
	Vector vz;
	
	public Bezier3dMouse mouse;

	List<Vector> axis = new ArrayList<Vector>();

	public Camera camera = new Camera();

	double tx = 0;
	double ty = 0;
	double tz = 0;
	double sf = 100;

	   int w = 400; 
	   int h = 400;
	double a = Math.PI / 3; // Fov
	double n = 0.1d;
	double f = 1000.0d;
	

	public Bezier3dPanel()
	{
		setBackground(Color.GRAY);

		points = BezierUtil.getCurve(2);

		function = new Bezier(points);

		recaclulateSamples();

		addKeyListener(new BezierKey(this));

		mouse = new Bezier3dMouse(this); addMouseListener(mouse); addMouseMotionListener(mouse);

		setFocusable(true);
		
		addComponentListener(new ComponentAdapter() 
		{
			public void componentResized(ComponentEvent componentEvent) 
			{
				Rectangle r = getBounds(); w = r.width; h = r.height; resetCamera();
			}
		});

	}
	

	public void resetCamera()
	{
		m = MatrixUtil.projP((double) h / (double) w, n, f, a).mul(MatrixUtil.view(camera.getPos(), camera.getTar(), camera.getUp()).mul(MatrixUtil.model(tx, ty, tz, sf)));

		tformed.clear(); tPoints.clear();

		for (Vector v : samples)
			tformed.add(m.mul(v));

		for (Vector t : tformed)
			t.perspectiveDivide();
		
		for (AbstractPoint p : points)
			tPoints.add(m.mul(p.toVector()));
		
		for (Vector p : tPoints)
			p.perspectiveDivide();

		repaint(); revalidate();

	}

	public void recaclulateSamples()
	{
		double curve_granularity = 0.001; double surface_granularity = 0.02;

		samples.clear();
		
		if (strategy == Strategy.Curve)
			for (double t = 0; t <= 1; t += curve_granularity)
			{
				samples.add(function.bezier(t).toVector()); 
			}
		
		if (strategy == Strategy.Surface)
			for (double t = 0; t <= 1; t += surface_granularity)
			{
				for (double u = 0; u <= 1; u += surface_granularity)
					samples.add(surface.bezier(t, u).toVector()); 
			}
		
		resetCamera();

		repaint(); revalidate();
	}
	
	public int search(int x, int y, double epsilon)
	{
		
		for (int i = 0; i < tPoints.size(); i++)
		{
			int px = tPoints.get(i).panelX();
			int py = tPoints.get(i).panelY();
				
			if ( Math.abs(px - x) < epsilon && Math.abs(py - y) < epsilon)
			{
				return i;
			}
		}
	
		return -1;
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		
		// draw reference grid
		
		for (int z = 0; z < 10; z++)
		{
			for (int x = 0; x < 10; x++)
			{
				Vector o = new Vector(x, 0, -z, 1);
				Vector r = new Vector(x + 1, 0, -z, 1);
				Vector u = new Vector(x, 0, -z - 1, 1);
				Vector d = new Vector(x + 1, 0, -z - 1, 1);
				
				o = m.mul(o); o.perspectiveDivide();
				r = m.mul(r); r.perspectiveDivide();
				u = m.mul(u); u.perspectiveDivide();
				d = m.mul(d); d.perspectiveDivide();
				
				
				if (o.safeForViewPort(w, h) && r.safeForViewPort(w, h))
					g.drawLine(o.panelX(), o.panelY(), r.panelX(), r.panelY());
				
				if (o.safeForViewPort(w, h) && u.safeForViewPort(w, h))
					g.drawLine(o.panelX(), o.panelY(), u.panelX(), u.panelY());
				
				if (u.safeForViewPort(w, h) && d.safeForViewPort(w, h))
					g.drawLine(u.panelX(), u.panelY(), d.panelX(), d.panelY());
				
				if (x == 9)
					if (r.safeForViewPort(w, h) && u.safeForViewPort(w, h))
						g.drawLine(r.panelX(), r.panelY(), d.panelX(), d.panelY());
				
			}
		}

		// draw transformed samples
		for (int i = 1; i < tformed.size(); i++)
		{
			int x = tformed.get(i).panelX();
			int y = tformed.get(i).panelY();

			if (tformed.get(i).safeForViewPort(w, h))
				g.drawOval(x, y, 1, 1);
		}
		
		// draw mini axis
		if (mouse.p > -1) 
		{
			AbstractPoint origin = points.get(mouse.p);

			vo = origin.toVector();

			final double size = 1;

			vx = vo.clone(); vx.set(0, vx.get(0) + size); 
			vy = vo.clone(); vy.set(1, vy.get(1) + size);
			vz = vo.clone(); vz.set(2, vz.get(2) + size);

			vo = m.mul(vo); vo.perspectiveDivide();
			vx = m.mul(vx); vx.perspectiveDivide();
			vy = m.mul(vy); vy.perspectiveDivide();
			vz = m.mul(vz); vz.perspectiveDivide();

			g.setColor(Color.RED);
			g.drawLine(vo.panelX(), vo.panelY(), vx.panelX(), vx.panelY());
			g.setColor(Color.BLUE);
			g.drawLine(vo.panelX(), vo.panelY(), vy.panelX(), vy.panelY());
			g.setColor(Color.GREEN);
			g.drawLine(vo.panelX(), vo.panelY(), vz.panelX(), vz.panelY());
		}

		// draw pressed control points
		
		g.setColor(Color.GREEN);
		
		final int size = 8; final int hsize = size + 4;
		
		if (mouse.p > -1)
			g.fillOval(tPoints.get(mouse.p).panelX() - hsize/2, tPoints.get(mouse.p).panelY() - hsize/2, hsize, hsize);
		
		
		// draw control points

		g.setColor(Color.RED);
		
		for (Vector p : tPoints)
			g.fillOval(p.panelX() - size/2, p.panelY() - size/2, size, size);
		

	}




}
