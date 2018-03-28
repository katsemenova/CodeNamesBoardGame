package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import code.Game;

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
		_window = new JFrame("CodeNames");
		_mainPanel = new JPanel();
		_window.getContentPane().add(_mainPanel);
		
		new GUI(_game, _mainPanel, this);
		
		_window.setVisible(true);
		_window.pack();
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void updateJFrame(){
		_window.pack();
		_window.repaint();
	}

}
