package draw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import draw._2d.Bezier2dPanel;

public class BezierDraw2d extends JFrame {
	private static final long serialVersionUID = 1L;

	JToolBar toolBar = new JToolBar("Toolbar");

	JTextField noSamples = new JTextField(10);
	JButton samples = new JButton("Samples");
	JToggleButton tangent = new JToggleButton("Tangents");
	JToggleButton normals = new JToggleButton("Normals");
	
	Bezier2dPanel bezier2dPanel = new Bezier2dPanel();
	
	public BezierDraw2d() 
	{
		setPreferredSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		noSamples.setText("5");
		toolBar.setRollover(true);
		toolBar.setLayout(new FlowLayout());
		
		samples.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int sample = new BigDecimal(noSamples.getText()).intValue();
				
				bezier2dPanel.setNSample(sample - 1);
			}
		});
		
		
		tangent.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				bezier2dPanel.setTangent(); 
			}
		});
		
		normals.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				bezier2dPanel.setNormals();
			}
		});
		
		toolBar.add(noSamples);
		toolBar.add(samples);
		toolBar.add(tangent);
		toolBar.add(normals);
		
		add(toolBar, BorderLayout.PAGE_START);
		add(bezier2dPanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	public static void invoke()
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				new BezierDraw2d();
			}
		});
	}
	
	public static void main(String[] args)
	{
		invoke();
	}

}
