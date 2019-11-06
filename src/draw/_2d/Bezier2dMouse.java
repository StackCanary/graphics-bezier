package draw._2d;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import draw.Graphics2dUtil;
import ipoints.IPoint2d;

public class Bezier2dMouse extends MouseAdapter {

	Bezier2dPanel panel;
	
	final int ADD_POINT = MouseEvent.BUTTON1;
	final int MOV_POINT = MouseEvent.BUTTON2;
	final int REM_POINT = MouseEvent.BUTTON3;

	public Bezier2dMouse(Bezier2dPanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{

		if (e.getButton() == ADD_POINT)
		{
			panel.points.add(new IPoint2d(e.getX(), e.getY())); panel.reset(); panel.revalidate(); panel.repaint();
		}
		
		if (e.getButton() == REM_POINT)
		{
			IPoint2d p = panel.search(e.getX(), e.getY(), Graphics2dUtil.POINT_RADIUS_EPSILON);
			
			if (p != null)
			{
				panel.points.remove(p);
				
				
				if (panel.points.isEmpty())
					panel.function = null;
				else
					panel.reset();
				
				panel.revalidate(); panel.repaint();
				
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MOV_POINT)
		{
			panel.dragged = panel.search(e.getX(), e.getY(), Graphics2dUtil.POINT_RADIUS_EPSILON);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (e.getButton() == MOV_POINT)
		{
			panel.dragged = null;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {


		if (panel.dragged != null)
		{
			panel.dragged.x = e.getX();
			panel.dragged.y = e.getY();
			panel.reset(); 
			panel.revalidate(); 
			panel.repaint(); 
		}


	}


}
