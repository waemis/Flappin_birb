package nhatz.me.Java.Swing.Flappin_birb;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JComponent;

public class Birb extends JComponent {

	private static final int DEF_FALL_SPEED = 2;
	private static final int DEF_FLY_SPEED  = 15;
	private final int GRASS_Y;
	private Image     image;
	private Dimension spriteDimension;
	private       int fall_speed;
	private       int fly_speed;

	public Birb (Dimension d, int grassY) {
		fall_speed = DEF_FALL_SPEED;
		fly_speed = DEF_FLY_SPEED;
		spriteDimension = d;
		GRASS_Y = grassY;
		image = Toolkit.getDefaultToolkit ().getImage ("assets/bird.gif");
		image = image.getScaledInstance (d.width, d.height, Image.SCALE_FAST);
		setSize (spriteDimension);
	}


	public void init() {
		fly_speed = DEF_FLY_SPEED;
		fall_speed = DEF_FALL_SPEED;
		setVisible (true);
	}

	public void reset () {
		setVisible (false);
		fly_speed = 0;
		fall_speed = 0;
	}

	public boolean fall (Tree[] trees) {
		setLocation (getX (), getY () + fall_speed);
		if (getY () + getHeight () > GRASS_Y) {
			setLocation (getX (), getY () - fall_speed);
		}
		return hittingTree (trees);
	}

	private boolean hittingTree (Tree[] trees) {
		Rectangle b = getBounds ();
		for (Tree t : trees) {
			if (t.getBounds ().intersects (b) && t.getValidity ()) {
				t.reset ();
				return true;
			}
		}
		return false;
	}

	public boolean fly (Tree[] trees) {
		setLocation (getX (), getY () - fly_speed);
		if (getY () < 0) {
			setLocation (getX (), getY () + fly_speed);
		}
		return hittingTree (trees);
	}

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);

		g.drawImage (image, 0, 0, this);
	}
}
