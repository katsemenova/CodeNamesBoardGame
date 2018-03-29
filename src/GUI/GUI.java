package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import code.Game;
import code.Location;

/* 
 * @author Kateryna Semenova
 * @author Sidney Bloch
 */
public class GUI implements Observer{

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
		createCards();
		updateFeedbackPanel();
		updatePlayerActionPanel();
		

		
		updateJFrameIfNotHeadless();
	}	

	private void createCards(){
		_outputPanel.removeAll();
		
		Location[][] locations = _game.getBoard().getLocations();
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				JButton b;
				//the Jbutton can also have an additional paramater of an icon aka a pic
				if(_game.getControl().equals("Spymaster"))
					 b = new JButton("<html> "+locations[i][j].getPerson().getAgentTypeString()+"<br/><br/>" + locations[i][j].getLocationCodename()+"</html>");
				else
					 b = new JButton(locations[i][j].getLocationCodename());
				b.setBackground(Color.LIGHT_GRAY);
				b.setPreferredSize(new Dimension(120,120));
				
				/*
				 * create action for button on click, remember action should differ based on if it is 
				 * the turn of the spymaster or not - use _game.getControl() to find out if
				 * "Spymaster" is returned or if "Players" is returned
				 */
			
				_outputPanel.add(b);
			}
		}
	}
	
	private void updateFeedbackPanel() {
		Color col;
		if(_game.getTurnString().equals("Blue"))
			col = Color.blue;
		else
			col = Color.red;
		
		JLabel labelOfTeamPlaying  = new JLabel(_game.getTurnString()+" Team's Turn");
		labelOfTeamPlaying.setForeground(col);
		labelOfTeamPlaying.setFont(new Font("Serif", Font.BOLD, 20));
		labelOfTeamPlaying.setBorder(new EmptyBorder(5, 20, 5, 20));
		_feedBackPanel.add(labelOfTeamPlaying);
		
		
		JLabel labelSpymasterOrNot  = new JLabel(_game.getControl()+"'s Turn");
		labelSpymasterOrNot.setFont(new Font("Serif", Font.BOLD, 20));
		labelSpymasterOrNot.setForeground(col);
		labelSpymasterOrNot.setBorder(new EmptyBorder(5, 20, 5, 20));
		_feedBackPanel.add(labelSpymasterOrNot);
	}
	
	private void updatePlayerActionPanel() {

		
		JLabel clueLabel  = new JLabel("Clue:");
		clueLabel.setFont(new Font("Serif", Font.BOLD, 13));
		_playerPanel.add(clueLabel);
		
		JTextField clueInput = new JTextField("input clue");
		//makesureonly letters can be put in
		clueInput.setSize( new Dimension(250,clueInput.getHeight()));
		_playerPanel.add(clueInput);
		
		JLabel countLabel  = new JLabel("Count:");
		countLabel.setFont(new Font("Serif", Font.BOLD, 13));
		_playerPanel.add(countLabel);
		
		JTextField countInput = new JTextField("input count");
		//makesureonly numbers can be put in
		countInput.setSize( new Dimension(25,countInput.getHeight()));
		_playerPanel.add(countInput);
		
		JButton enterButton = new JButton("Enter");
		enterButton.setBackground(Color.LIGHT_GRAY);
		enterButton.setPreferredSize(new Dimension(50,30));
		_playerPanel.add(enterButton);
		
	}

	private void updateJFrameIfNotHeadless() {
		if(_windowHolder != null){
			_windowHolder.updateJFrame();
		}
	

	}}
