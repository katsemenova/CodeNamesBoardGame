package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import code.Game;
import code.Location;

/* 
 * @author Kateryna Semenova
 * @author Sidney Bloch
 * @author Aaron Kong
 */
public class GUI implements Observer{

	private JPanel _outputPanel;
	private JPanel _inputPanel;
	private JPanel _feedBackPanel;
	private JPanel _playerPanel;
	private Game _game;
	private Driver _windowHolder;
	private String currentClue;
	private int countForTurn;
	
	
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
								boolean result = _game.getBoard().selectCodeName(codeName);
								decrementCount(result);
								setColorUpdate(b, location.getPerson().getAgentTypeString());
								checkWinningState();
								}
						}
					}


					
				});
				
				_outputPanel.add(b);

			
			}
		}
	}

	private boolean checkWinningState() {
		if(_game.getBoard().checkWinningState()){
	
			System.out.println("Winner is");
			if(_game.getBoard().checkForAssassin()){
				System.out.println("due to assassin");
				/*
				 * 
				 * @Sidney @Hollis
				 * put in the pop textbox here stating ___ team won cause the other team revealed assassin
				 * 
				 * you can get the winner by calling a method in game class
				 * 
				 */
			}else{
				
				/*
				 * 
				 * @Sydney @Hollis
				 * put in the pop textbox here stating ___ team won cause they revealed all agents
				 * 
				 * 
				 * 
				 */
				
			}
			return true;
		}
		return false;
	}
	private void decrementCount(boolean result) {
		countForTurn--;
		if(!_game.getBoard().legalCount(countForTurn) && result == true){
			_game.changeControl();
			_game.switchTeamTurn();
			update();
		}
		if(!_game.getBoard().legalCount(countForTurn) && result == false){
			
			update();
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
		if(checkWinningState()){
			_feedBackPanel.removeAll();

		}
	}
	
	private void updatePlayerActionPanel() {
		if(checkWinningState()){
			_playerPanel.removeAll();
		
			
		}else if(_game.getControl().equals("Spymaster")){
			_playerPanel.removeAll();
			
			JLabel clueLabel  = new JLabel("Clue:");
			clueLabel.setFont(new Font("Serif", Font.BOLD, 13));
			_playerPanel.add(clueLabel);
		
			JTextField clueInput = new JTextField("input clue");
			clueInput.setSize( new Dimension(250,clueInput.getHeight()));
			_playerPanel.add(clueInput);
		
			JLabel countLabel  = new JLabel("Count:");
			countLabel.setFont(new Font("Serif", Font.BOLD, 13));
			_playerPanel.add(countLabel);
		
			JTextField countInput = new JTextField("input count");
			countInput.setSize( new Dimension(25,countInput.getHeight()));
			_playerPanel.add(countInput);
			
			JButton enterButton = new JButton("Enter");
			enterButton.setBackground(Color.LIGHT_GRAY);
			enterButton.setPreferredSize(new Dimension(50,30));
			enterButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String count = countInput.getText();
					currentClue = clueInput.getText();
					
					if(count.matches("[0-9]") && _game.getBoard().legalCount(Integer.parseInt(count))){
						countForTurn = Integer.parseInt(count);
						if(_game.getBoard().legalClue(currentClue)){
							_game.changeControl();
							update();
						
						}else{
							System.out.println("Illegal Clue ");
						
							/*
							 * 
							 * Hollis and Sidney (Put in pop up message stating illegal clue)
							 * 
						 	*/
						}	
					}else{
						System.out.println("Count illegal");
						/*
						 * 
						 * pop up message stating the count is illegal
						 * 
						 */
					}

				}
				
			});
			_playerPanel.add(enterButton);
		}else if(_game.getControl().equals("Players")){
			_playerPanel.removeAll();
			String textOne = "Clue: " + currentClue;
			JLabel clueLabel = new JLabel(textOne);
			clueLabel.setFont(new Font("Serif", Font.BOLD, 20));
			clueLabel.setBorder(new EmptyBorder(5, 20, 5, 20));
			_playerPanel.add(clueLabel);
			
			String textTwo = "Current Count: " + countForTurn;
			JLabel countLabel = new JLabel(textTwo);
			countLabel.setFont(new Font("Serif", Font.BOLD, 20));
			countLabel.setBorder(new EmptyBorder(5, 20, 5, 20));
			_playerPanel.add(countLabel);
		}
		
		
	}

	private void updateJFrameIfNotHeadless() {
		if(_windowHolder != null){
			_windowHolder.updateJFrame();
		}
	

	}}
