package nhatz.me.Java.Swing.Flappin_birb;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

public class LabelVie extends JComponent {

	private static final Dimension DIMENSION = new Dimension (20, 18);
	private final int   MAX_VIE;
	private       Image image;
	private int vies;

	public LabelVie (int maxVie) {
		MAX_VIE = maxVie;
		vies = MAX_VIE;
		image = Toolkit.getDefaultToolkit ().getImage ("assets/coeur.gif");
		setSize ((DIMENSION.width + 1) * MAX_VIE, DIMENSION.height);
	}

	public int getVies () {
		return vies;
	}

	public void init() {
		vies = MAX_VIE;
		invalidate ();
		repaint ();
	}

	public void decrement () {
		if (vies > 0) {
			vies -= 1;
			invalidate ();
			repaint ();
		}
	}

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);

		setSize ((DIMENSION.width + 1) * vies + DIMENSION.width,
		         DIMENSION.height);
		for (int i = 0; i < vies; ++ i) {
			g.drawImage (image, i * (DIMENSION.width + 1), 0, this);
		}
	}
}
