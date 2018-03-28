package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.Game;
import code.Location;

/* 
 * @author Kateryna Semenova
 * @author Sidney Bloch
 */
public class GUI implements Observer {

	private JPanel _outputPanel;
	private JPanel _inputPanel;
	private JPanel _feedBackPanel;
	private JPanel _playerPanel;
	private Game _game;
	private Driver _windowHolder;
	
	public GUI(Game g, JPanel mp, Driver driver) {
		_windowHolder = driver;
		_game = g;
		
		JPanel _mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		_outputPanel = new JPanel();
		_outputPanel.setLayout(new GridLayout(5,5));
		_mainPanel.add(_outputPanel);
		
		_inputPanel = new JPanel();
		_inputPanel.setLayout(new BoxLayout(_inputPanel, BoxLayout.X_AXIS));
		_mainPanel.add(_inputPanel);
		
		_feedBackPanel = new JPanel();
		_feedBackPanel.setLayout(new BoxLayout(_feedBackPanel, BoxLayout.Y_AXIS));
		_inputPanel.add(_feedBackPanel);
		
		_playerPanel = new JPanel();
		_playerPanel.setLayout(new BoxLayout(_playerPanel, BoxLayout.Y_AXIS));
		_inputPanel.add(_playerPanel);
		
		update();
	}

	@Override
	public void update() {
		
		_outputPanel.removeAll();
		
		Location[][] locations = _game.getBoard().getLocations();
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				//the Jbutton can also have an additional paramater of an icon aka a pic
				JButton b = new JButton(locations[i][j].getLocationCodename());
				b.setBackground(Color.LIGHT_GRAY);
				b.setPreferredSize(new Dimension(100,100));
				/*
				 * create action for button on click, remember action should differ based on if it is 
				 * the turn of the spymaster or not!
				 */
			
				_outputPanel.add(b);
				System.out.println("stuff works");
			}
		}
		
		
		JLabel labelOfTeamPlaying  = new JLabel(_game.getTurnString()+" Team's Turn");
		//labelOfTeamPlaying.setPreferredSize(new Dimension(50,100));
		labelOfTeamPlaying.setFont(new Font("Serif", Font.BOLD, 20));
		_feedBackPanel.add(labelOfTeamPlaying);
		
		JLabel labelSpymasterOrNot  = new JLabel(_game.spymasterPlaying()+"'s Turn");
		//labelOfTeamPlaying.setPreferredSize(new Dimension(50,100));
		labelSpymasterOrNot.setFont(new Font("Serif", Font.BOLD, 20));
		_feedBackPanel.add(labelSpymasterOrNot);
		
		JLabel clueLabel  = new JLabel("Clue:");
		clueLabel.setFont(new Font("Serif", Font.BOLD, 11));
		_playerPanel.add(clueLabel);
		
		JTextField clueInput = new JTextField("input clue");
		//makesureonly letters can be put in
		clueInput.setPreferredSize(new Dimension(10,50));
		_playerPanel.add(clueInput);
		
		JLabel countLabel  = new JLabel("Count:");
		countLabel.setFont(new Font("Serif", Font.BOLD, 11));
		_playerPanel.add(countLabel);
		
		JTextField countInput = new JTextField("input count");
		//makesureonly numbers can be put in
		countInput.setPreferredSize(new Dimension(10,50));
		_playerPanel.add(countInput);
		
		
		
		updateJFrameIfNotHeadless();
	}

	private void updateJFrameIfNotHeadless() {
		if(_windowHolder != null){
			_windowHolder.updateJFrame();
		}
	}

}
