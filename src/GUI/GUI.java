package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import code.Game;
import code.Location;

/* 
 * @author Kateryna Semenova
 * @author Sidney Bloch
 * @author Aaron Kong
 * @author Hollis Pauquette
 */
public class GUI implements Observer{

	
	/*
	 * Panel that holds the game board for viewing pleasure
	 */
	private JPanel _outputPanel;
	
	/*
	 * Panel which holds panels that allow user input
	 */
	private JPanel _inputPanel;
	
	/*
	 * Panel which allows the game instance to communicate with the player.
	 */
	private JPanel _feedBackPanel;
	
	/*
	 * 	Panel in which players may enter inputs or view the move count.
	 */
	private JPanel _playerPanel;
		
	/*
	 * Private variable holding the game instance.
	 */
	private Game _game;
	
	/*
	 * Holds the instance of the window that is passed through with the instantiation of the game.
	 */
	private Driver _windowHolder;
	
	/*
	 * Variable holding the current clue.
	 */
	private String currentClue;
	
	/*
	 * Variable holding the current guess count.
	 */
	private int countForTurn;
	
	/*
	 * GUI constructor initializes all JPanels to the _windowHolder instance and to the subsequent JFrame.
	 */
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
	
	/*
	 * update() reinitializes and updates all JFrames. 
	 */
	@Override
	public void update() {
		createCards();
		createJMenu();
		updateFeedbackPanel();
		updatePlayerActionPanel();
		updateJFrameIfNotHeadless();
	}	

	/*
	 * createCards() removes the 25 JButtons from the _outputPanel, and re-renders the cards in the panel.
	 */
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
				
				
				
				
				if(location.isVisible()){
					b.setText(location.getPerson().getAgentTypeString());
					setColor(b, location.getPerson().getAgentTypeString());
					b.setForeground(Color.WHITE);
				}
				else
					b.setBackground(new Color(234, 227, 234));
			    
				b.setPreferredSize(new Dimension(120,100));
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

	/*
	 * checkWinningState() checks the state of the game every turn. If a team has won
	 * the game then a pop up displays the winner and the game is over. 
	 * If the game is not over then nothing happens.
	 */
	private boolean checkWinningState() {
		if(_game.getBoard().checkWinningState()){
			if(_game.getBoard().checkForAssassin()){
				
				JOptionPane.showMessageDialog(null, _game.getTurnString() + " Team Wins!", "Assassin Revealed", JOptionPane.INFORMATION_MESSAGE);
				
				}else{
					if(!_game.getWinner().equals(_game.getTurnString()))
						_game.switchTeamTurn();
				JOptionPane.showMessageDialog(null, _game.getTurnString() + " Team Wins!", "All Agents Revealed", JOptionPane.INFORMATION_MESSAGE);				
				}
			return true;
		}
		return false;
	}

	/*
	 * This is triggered every time a JBUtton is pressed. countForTurn is decreased. 
	 * If countForTurn reaches 0 then the turn is switched to the other team.
	 */
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
	

	/*
	 * Updates color of the JButtons.
	 */
	private void setColorUpdate(JButton b, String agentType) {
		setColor(b,agentType);
		updateJFrameForColor();
	}

	/*
	 * Sets the color of JButtons according to the type of person at that location.
	 */
	private void setColor(JButton b, String agentType) {
		if(agentType.equals("Red"))
			b.setBackground(new Color(236, 87, 107));
		else if(agentType.equals("Blue"))
			b.setBackground(new Color(78, 197, 193));
		else if(agentType.equals("Bystander"))
			b.setBackground(new Color(229, 227, 56));
		else if(agentType.equals("Assassin"))
			b.setBackground(new Color(0, 0, 0));
	
	}

	/*
	 * Updates _feedbackPanel at the beginning of each turn.
	 */
	private void updateFeedbackPanel() {
		_feedBackPanel.removeAll();
		Color col;
		if(_game.getTurnString().equals("Blue"))
			col = new Color(78, 197, 193);
		else
			col = new Color(236, 87, 107);
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
		if(_game.getBoard().checkWinningState()){
			_feedBackPanel.removeAll();
		}
	}

	/*
	 * Displays updates to the _playerPanel. _playerPanel resets at the end of every turn. 
	 * At the end of the game the _inputPanel is removed from the JFrame.
	 */
	private void updatePlayerActionPanel() {
		if(_game.getBoard().checkWinningState()){
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

			Integer[] countBox = new Integer[_game.getBoard().getCount()];
			Integer input = 1;
			for (int i = 0; i< _game.getBoard().getCount();i++) {
				countBox[i] = input;
				input++;
			}

			JComboBox<Integer> spyCount = new JComboBox<Integer>(countBox);
			spyCount.setSelectedIndex(0);
			_playerPanel.add(spyCount);

			JButton enterButton = new JButton("Enter");
			enterButton.setBackground(Color.LIGHT_GRAY);
			enterButton.setPreferredSize(new Dimension(50,30));
			enterButton.addActionListener(new ActionListener(){

				/*
				 * ActionListener definition for each button.
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					String count = Integer.toString((int) spyCount.getSelectedItem());
					currentClue = clueInput.getText();
					String easter = "hertz";
					
					if (currentClue.toLowerCase().equals(easter)) {
						Icon image = new ImageIcon("./images/hertzlocker.png");
						JOptionPane.showMessageDialog(null, "\"You fail 100% of the JUnit tests you dont write.\"" + System.lineSeparator() + "— Matthew Hertz", easter.toUpperCase() + "LOCKER", JOptionPane.NO_OPTION, image);
					}
					
					if(count.matches("[0-9]") && _game.getBoard().legalCount(Integer.parseInt(count))){
						countForTurn = Integer.parseInt(count);
						
						if(_game.getBoard().legalClue(currentClue.toLowerCase())){
							_game.changeControl();
							update();
						}
						else
							JOptionPane.showMessageDialog(null, "Illegal Clue", "Uh Oh", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "Illegal Count", "Uh Oh", JOptionPane.ERROR_MESSAGE);
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
			
			JButton endTurn = new JButton("End Turn");
			endTurn.setBackground(Color.LIGHT_GRAY);
			endTurn.setPreferredSize(new Dimension(50,30));
			endTurn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					_game.changeControl();
					_game.switchTeamTurn();
					
					update();
				}});
				
			_playerPanel.add(endTurn);
			
		}
		
		
	}

	/*
	 * 
	 */
	private void updateJFrameIfNotHeadless() {
		if(_windowHolder != null){
			_windowHolder.updateJFrame();
		}
		if (_game.getControl().equals("Spymaster")) {
			JOptionPane.showMessageDialog(null, _game.getTurnString() + " Team's Turn", "Turn", JOptionPane.INFORMATION_MESSAGE);
		}

		}

	/*
	 * 
	 */
	private void updateJFrameForColor() {
		if(_windowHolder != null){
			_windowHolder.updateJFrame();
		}

		}

	/*
	 * JMenu creation method.
	 */
	public void createJMenu() {
		JMenuBar fileBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileBar.add(fileMenu);
		JMenuItem newGame = new JMenuItem("Start New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_windowHolder.get_window().setVisible(false);
				_windowHolder.get_window().dispose();
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
		
		JMenuItem easter = new JMenuItem("Rules");
		easter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
			        Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=dQw4w9WgXcQ").toURI());
			    } catch (Exception e1) {
			        e1.printStackTrace();
			    }
			}
		});
		fileMenu.add(easter);
		_windowHolder.get_window().setJMenuBar(fileBar);
	}
	}
