package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import code.Game;

/* 
 * @author Kateryna Semenova
 * @author Hollis Pauquette
 * 
 */

public class Driver implements Runnable {
	
	/*
	 * Instance of Game
	 */
	private Game _game;
	
	/*
	 * JFrame variable - Window to render. This window holds the game and all required JPanels.
	 */
	private JFrame _window;
	
	/*
	 * This main JPanel houses the 4 JPanels which make up our UI.
	 */
	private JPanel _mainPanel;
	
	/*
	 * The Constructor assigns private variables, such as the _game instance that is passed through.
	 */
	public Driver(Game g){
		_game = g;
	}
	
	/*
	 * Main method instantiates a new instance of the game. 
	 */
	public static void main(String[] args){
		Game g = new Game();
		SwingUtilities.invokeLater(new Driver(g));
	}
	
	/*
	 * Creates instances of the JFrame, JPanel, instantiates a new instance of the GUI class.
	 */
	@Override
	public void run() {
		set_window(new JFrame("CodeNames"));
		_mainPanel = new JPanel();
		get_window().getContentPane().add(_mainPanel);
		
		new GUI(_game, _mainPanel, this);
		
		get_window().setVisible(true);
		get_window().pack();
		get_window().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * Updates the JFrame, repacks and repaints.
	 */
	public void updateJFrame(){
		get_window().pack();
		get_window().repaint();
	}

	/*
	 * @return JFrame of the current game
	 */
	public JFrame get_window() {
		return _window;
	}

	/*
	 * @param JFrame to set the current window to
	 */
	public void set_window(JFrame _window) {
		this._window = _window;
	}

}
