package net.jkx.test.worldwind.abc;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Extent;
import gov.nasa.worldwind.globes.Globe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GlobeAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ����һ��worldwind����
		WorldWindow wwd = new WorldWindowGLCanvas();
		// ΪWorldWindow����һ��Model��
		Model m = (Model) WorldWind
				.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
		wwd.setModel(m);

		// Extent
		{
			Globe globe = m.getGlobe();
			Extent extent = globe.getExtent();
			System.out.println("Extent class: " + extent.getClass()); 
			System.out.println("    center: " + extent.getCenter()); // center: (0.0, 0.0, 0.0, 1.0)
			System.out.println("    diameter: " + extent.getDiameter());// diameter: 1.2756274E7
			System.out.println("    radius: " + extent.getRadius()); // radius: 6378137.0
		}

		// ����һ��JFrame������ʾwwd
		final JFrame frame = new JFrame();
		frame.setSize(new Dimension(800, 600));
		frame.add((Component) wwd, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});

	}

}
