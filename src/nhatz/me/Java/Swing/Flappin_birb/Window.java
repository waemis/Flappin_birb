package nhatz.me.Java.Swing.Flappin_birb;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Window extends JFrame {

	private World world;

	public Window (Dimension d) {
		setSize (d);
		setListeners ();
		setTitle ("Flappin' Birb");
		setResizable (false);
		setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
		setVisible (true);

		world = new World (d);
		add (world);
	}

	private void setListeners () {
		addKeyListener (new KeyAdapter () {
			@Override
			public void keyPressed (KeyEvent e) {
				world.keyPressed (e);
			}
		});
	}

	public void play () {
		world.play ();
	}
}