package nhatz.me.Java.Swing.Flappin_birb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class World extends JPanel {

	private final static int TREE_SPACING = 250;
	private final static int TREE_SPEED   = 5;
	private final int GRASS_HEIGHT;
	private final int GRASS_Y;

	private Tree[]   trees;
	private Birb     birb;
	private LabelVie labelVie;

	private Thread gameThread;

	private boolean playing;

	public World (Dimension d) {
		GRASS_Y = d.height / 6 * 5;
		GRASS_HEIGHT = d.height - GRASS_Y;
		setSize (d);
		setLayout (null);

		birb = new Birb (new Dimension (100, 100), GRASS_Y);
		trees = new Tree[d.width / (Tree.TREE_WIDTH + TREE_SPACING) + 1];
		birb.setLocation ((getWidth () - birb.getWidth ()) / 2, 0);
		add (birb);

		labelVie = new LabelVie (10);
		labelVie.setLocation (0, 0);
		add (labelVie);

		initTrees ();
		gameThread = new Thread (() -> {
			while (true) {
				while (playing) {
					for (Tree t : trees) {
						moveTree (t);
					}

					try {
						Thread.sleep (20);
					} catch (InterruptedException ie) {
						System.out.println ("Welp");
					}

					if (birb.fall (trees)) {
						labelVie.decrement ();
					}

					if (labelVie.getVies () <= 0) {
						playing = false;
						reset ();
						askNewGame ();
					}

					Toolkit.getDefaultToolkit ().sync ();
				}
			}
		});
	}

	public void askNewGame () {
		int answer = JOptionPane
			  .showConfirmDialog (this, "Start a new game?", "Game " + "Over",
			                      JOptionPane.YES_NO_OPTION,
			                      JOptionPane.QUESTION_MESSAGE);
		switch (answer) {
			case JOptionPane.YES_OPTION:
				restart ();
				break;
			case JOptionPane.NO_OPTION:
				System.exit (0);
		}
	}

	public void reset () {
		birb.reset ();
		for (Tree t : trees) {
			t.reset ();
		}
	}

	private void moveTree (Tree t) {
		t.setLocation (t.getX () - TREE_SPEED, t.getY ());

		if (t.getX () < - t.getWidth ()) {
			t.setLocation (getWidth () + TREE_SPACING, t.getY ());
			t.init ();
		}
	}

	private void initTrees () {
		for (int i = 0; i < trees.length; ++ i) {
			trees[i] = new Tree ();
			trees[i].setLocation ((Tree.TREE_WIDTH + TREE_SPACING) * i,
			                      getHeight () - trees[i].getHeight ());
			add (trees[i]);
		}
	}

	public void restart () {
		birb.init();
		birb.setLocation ((getWidth () - birb.getWidth ()) / 2, 0);
		labelVie.init ();
		playing = true;
	}

	public void play () {
		playing = true;
		gameThread.run ();
	}

	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode () == KeyEvent.VK_SPACE) {
			if (birb.fly (trees)) {
				labelVie.decrement ();
			}
		}
	}

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);

		// grass background
		g.setColor (new Color (96, 128, 56));
		g.fillRect (0, GRASS_Y, getWidth (), GRASS_HEIGHT);

		// sky background
		g.setColor (new Color (39, 142, 255));
		g.fillRect (0, 0, getWidth (), GRASS_Y);
	}
}
