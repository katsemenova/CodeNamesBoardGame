package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import code.Game;

/* 
 * @author Kateryna Semenova
 * @author Hollis Pauquette
 * 
 */

public class Driver implements Runnable {

	private Game _game;
	private JFrame _window;
	private JPanel _mainPanel;
	
	
	public Driver(Game g){
		_game = g;
	}
	
	public static void main(String[] args){
		Game g = new Game();
		SwingUtilities.invokeLater(new Driver(g));
	}
	@Override
	public void run() {
		set_window(new JFrame("CodeNames"));
		_mainPanel = new JPanel();
		get_window().getContentPane().add(_mainPanel);
		
		new GUI(_game, _mainPanel, this);
		
		get_window().setVisible(true);
		get_window().pack();
		get_window().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JOptionPane.showMessageDialog(null, "Red Team's Turn", "Starting Turn", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void updateJFrame(){
		get_window().pack();
		get_window().repaint();
	}

	public JFrame get_window() {
		return _window;
	}

	public void set_window(JFrame _window) {
		this._window = _window;
	}

}
