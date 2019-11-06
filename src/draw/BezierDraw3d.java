package draw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import bezier.Bezier;
import bezier.BezierSurface;
import bezier.BezierUtil;
import draw._3d.Bezier3dPanel;
import draw._3d.Bezier3dPanel.Strategy;

// TODO The viewport stuff

public class BezierDraw3d extends JFrame {
	private static final long serialVersionUID = 1L;
	
	Bezier3dPanel panel = new Bezier3dPanel();

	JToolBar toolBar = new JToolBar("Toolbar");
	
	JLabel label1 = new JLabel("n:"); 
	JTextField n = new JTextField(3);
	JLabel label2 = new JLabel("n:");
	JTextField N = new JTextField(3);
	JLabel label3 = new JLabel("m:");
	JTextField M = new JTextField(3);
	JButton curve = new JButton("Curve"); JButton surface = new JButton("Surface");
	
	public BezierDraw3d() 
	{
		setPreferredSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		toolBar.setRollover(true);
		toolBar.setLayout(new FlowLayout());
	
		toolBar.add(label1);
		toolBar.add(n);
		toolBar.add(curve);
		toolBar.add(label2);
		toolBar.add(N);
		toolBar.add(label3);
		toolBar.add(M);
		toolBar.add(surface);
		
		n.setText("2");
		N.setText("3");
		M.setText("3");
		
		curve.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panel.strategy = Strategy.Curve;
				
				panel.points = BezierUtil.getCurve(new BigDecimal(n.getText()).intValue()); 
				
				panel.mouse.p = -1;
				
				panel.function = new Bezier(panel.points);
				
				panel.recaclulateSamples();

			}
		});
		
		surface.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panel.strategy = Strategy.Surface;
				
				int surface_n = new BigDecimal(N.getText()).intValue();
				int surface_m = new BigDecimal(M.getText()).intValue();
				
				panel.points = BezierUtil.getSurface(surface_n, surface_m); 
				
				panel.mouse.p = -1;
				
				panel.surface = new BezierSurface(panel.points, surface_n, surface_m);
				
				panel.recaclulateSamples();

			}
		});
		
		add(toolBar, BorderLayout.PAGE_START);
		add(panel, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}
	
	public static void invoke()
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				new BezierDraw3d();
			}
		});
	}
	
	public static void main(String[] args)
	{
		invoke();
	}

}
