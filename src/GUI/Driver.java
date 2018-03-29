package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		
		JMenuBar fileBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileBar.add(fileMenu);
		JMenuItem newGame = new JMenuItem("Start New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_window.setVisible(false);
				_window.dispose();
				Game g = new Game();
				SwingUtilities.invokeLater(new Driver(g));
			}
		});
		fileMenu.add(newGame);
		JMenuItem quitGame = new JMenuItem("Quit Game");
		quitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(quitGame);		
		_window.setJMenuBar(fileBar);
	}
	
	public void updateJFrame(){
		_window.pack();
		_window.repaint();
	}

}
