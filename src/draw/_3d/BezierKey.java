package draw._3d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dpoints.Point3d;

public class BezierKey implements KeyListener {
	
	Bezier3dPanel panel;
	
	public BezierKey(Bezier3dPanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		final double sensitvity = 0.1;


		if (panel.mouse.p > -1)
		{
			Point3d p = (Point3d) panel.points.get(panel.mouse.p);

			switch( e.getKeyCode()) 
			{

			case KeyEvent.VK_D: p.x += sensitvity; panel.recaclulateSamples(); return;
			case KeyEvent.VK_A: p.x -= sensitvity; panel.recaclulateSamples(); return;
			case KeyEvent.VK_W: p.z -= sensitvity; panel.recaclulateSamples(); return;
			case KeyEvent.VK_S: p.z += sensitvity; panel.recaclulateSamples(); return;
			case KeyEvent.VK_F: p.y -= sensitvity; panel.recaclulateSamples(); return;
			case KeyEvent.VK_R: p.y += sensitvity; panel.recaclulateSamples(); return;
			}
		}
		
		
		switch( e.getKeyCode())
		{
		case KeyEvent.VK_W:     panel.camera.f();     panel.resetCamera(); return;
		case KeyEvent.VK_A:     panel.camera.l();     panel.resetCamera(); return;
		case KeyEvent.VK_S:     panel.camera.d();     panel.resetCamera(); return;
		case KeyEvent.VK_D:     panel.camera.r();     panel.resetCamera(); return;
		case KeyEvent.VK_UP:    panel.camera.up();    panel.resetCamera(); return;
		case KeyEvent.VK_DOWN:  panel.camera.down();  panel.resetCamera(); return;
		case KeyEvent.VK_LEFT:  panel.camera.left();  panel.resetCamera(); return;
		case KeyEvent.VK_RIGHT: panel.camera.right(); panel.resetCamera(); return;
		}
		
		
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

	}



}
