package draw._3d;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dpoints.AbstractPoint;

public class Bezier3dMouse extends MouseAdapter {
	
	Bezier3dPanel panel;
	
	int x; int y; public int p = -1;
	
	public Bezier3dMouse(Bezier3dPanel panel) 
	{
		this.panel = panel;
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		panel.requestFocus();
		
		p = panel.search(e.getX(), e.getY(), 5);

		panel.repaint(); panel.revalidate();
	}
	
	
}
