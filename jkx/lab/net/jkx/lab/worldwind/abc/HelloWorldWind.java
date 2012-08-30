package net.jkx.lab.worldwind.abc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import javax.swing.JFrame;

public class HelloWorldWind {
	public static void main(String[] args) {
		// ����һ��worldwind����
		WorldWindow wwd = new WorldWindowGLCanvas(); 
		// ΪWorldWindow����һ��Model��
		Model m = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);	
		wwd.setModel(m);
		
		// ����һ��JFrame������ʾwwd
		final JFrame frame = new JFrame();
		frame.setSize(new Dimension(800, 600));		
		frame.add((Component)wwd, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                frame.setVisible(true);
            }
        });
	}
}