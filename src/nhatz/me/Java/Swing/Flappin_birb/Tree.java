package nhatz.me.Java.Swing.Flappin_birb;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Tree extends JComponent {

	public static final  int TREE_WIDTH  = 100;
	public static final  int TREE_HEIGHT = 200;
	private static final int TRUNC_WIDTH = 30;
	private boolean valid;

	public Tree () {
		valid = true;
		setSize (TREE_WIDTH, TREE_HEIGHT);
	}

	public void init () {
		valid = true;
	}

	public void reset () {
		valid = false;
	}

	public boolean getValidity () {
		return valid;
	}

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);


		// trunc
		g.setColor (new Color (102, 51, 0));
		g.fillRect ((getWidth () - TRUNC_WIDTH) / 2, getHeight () / 5,
		            TRUNC_WIDTH, getHeight ());

		// leaves
		g.setColor (new Color (58, 95, 11));
		g.fillOval (0, 0, getWidth (), getHeight () / 10 * 6);
	}
}
