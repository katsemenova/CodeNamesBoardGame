package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

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
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		           e.printStackTrace();
		 }
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
				String codeName = locations[i][j].getLocationCodename();
				Location location = locations[i][j];
				JButton b;
				if(_game.getControl().equals("Spymaster"))
					 b = new JButton("<html> "+location.getPerson().getAgentTypeString().toUpperCase()+"<br/><br/>" + codeName+"</html>");
				else
					 b = new JButton(codeName);
				
				_outputPanel.add(b);
				b.setOpaque(true);
				if(location.isVisible())
					setColor(b, location.getPerson().getAgentTypeString());
				else
					b.setBackground(Color.LIGHT_GRAY);
			    
				b.setPreferredSize(new Dimension(120,120));
				b.addActionListener(new ActionListener(){
				
					@Override
					public void actionPerformed(ActionEvent e) {
						if(_game.getControl().equals("Spymaster")){
						}else{
							if(!location.isVisible()){
								_game.getBoard().selectCodeName(codeName);
								setColorUpdate(b, location.getPerson().getAgentTypeString());
								System.out.println("color changed" + codeName);
							}
	
						}
					}
					
				});

			
			}
		}
	}
	
	private void setColorUpdate(JButton b, String agentType) {
		if(agentType.equals("Red"))
			b.setBackground(Color.red);
		else if(agentType.equals("Blue"))
			b.setBackground(Color.BLUE);
		else if(agentType.equals("Bystander"))
			b.setBackground(Color.yellow);
		else if(agentType.equals("Assassin"))
			b.setBackground(Color.DARK_GRAY);
	
		update();
	}
	
	private void setColor(JButton b, String agentType) {
		if(agentType.equals("Red"))
			b.setBackground(Color.red);
		else if(agentType.equals("Blue"))
			b.setBackground(Color.BLUE);
		else if(agentType.equals("Bystander"))
			b.setBackground(Color.yellow);
		else if(agentType.equals("Assassin"))
			b.setBackground(Color.DARK_GRAY);
		//System.out.println("agentType");
		
	}

	private void updateFeedbackPanel() {
		_feedBackPanel.removeAll();
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
		_playerPanel.removeAll();
		
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
