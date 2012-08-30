package net.jkx.test.worldwind.abc;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Extent;
import gov.nasa.worldwind.geom.Intersection;
import gov.nasa.worldwind.geom.Line;
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

		Globe globe = m.getGlobe();
		// Extent
		{
			Extent extent = globe.getExtent(); // Earth.getExtent() return itself.
			System.out.println("(1)Extent class: " + extent.getClass()); 
			System.out.println("    center: " + extent.getCenter()); // center: (0.0, 0.0, 0.0, 1.0)
			System.out.println("    diameter: " + extent.getDiameter());// diameter: 1.2756274E7
			System.out.println("    radius: " + extent.getRadius()); // radius: 6378137.0
		}
		
		System.out.println("(2)globe's equatorial(���) Radius: " + globe.getEquatorialRadius());
		System.out.println("(3)globe's polar(����) radius: " + globe.getPolarRadius());
		System.out.println("(4)globe's radius at (110��E, 35��N): " + globe.getRadiusAt(Angle.fromDegrees(35), Angle.fromDegrees(110)));
		System.out.println("(5)globe's radius at (110��E, 0��): " + globe.getRadiusAt(Angle.fromDegrees(0), Angle.fromDegrees(110)));
		System.out.println("(6)elevation of Everest(��庣�Σ����ݲ�׼ȷ): " + globe.getElevation(Angle.fromDMS(27, 59, 14), Angle.fromDMS(86, 55, 26)) + " m");
		System.out.println("(7)ƫ�ĵ�ƽ������֪����ʲô������: " + globe.getEccentricitySquared());
		
		// intersect
		{
			View view = wwd.getView();
			Line ray = view.computeRayFromScreenPoint(80, 100);
//			double eyeElevation = view.getEyePosition().getElevation();
			Intersection[] intersections = globe.intersect(ray);
			System.out.println(intersections.length);
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
