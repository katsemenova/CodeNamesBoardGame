package GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
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
public class GUI implements Observer {

	/*
	 * Panel that holds the game board that consists of JButtons
	 */
	private JPanel _outputPanel;

	/*
	 * Panel which holds _feedBackPanel and _playerPanel
	 */
	private JPanel _inputPanel;

	/*
	 * Panel which display's which team's turn it is and if it is the spymaster's or player's turn
	 */
	private JPanel _feedBackPanel;

	/*
	 * Panel in which spymasters may enter inputs or player may view the clue and
	 * count.
	 */
	private JPanel _playerPanel;

	/*
	 * The game instance for the current game 
	 */
	private Game _game;

	/*
	 * Driver variable  that holds the instance of the window that is passed through with the
	 * instantiation of the game.
	 */
	private Driver _windowHolder;

	/*
	 * String variable holding the currentClue given by the Spymaster
	 */
	private String currentClue;

	/*
	 * Int variable holding the current guess count provided by the spymaster
	 */
	private int countForTurn;

	/*
	 * GUI constructor initializes all JPanels to the _windowHolder instance and
	 * adds them to the subsequent JFrame, calls the update() method to display everything
	 * in the Jframe 
	 */
	public GUI(Game g, JPanel mp, Driver driver) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		_windowHolder = driver;
		_game = g;

		JPanel _mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));

		_outputPanel = new JPanel();
		_outputPanel.setLayout(new GridLayout(5, 5));
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
	 * Reinitializes and updates all JPanels with their components and then
	 * updates the JFrame itself
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
	 * Removes the 25 JButtons from the _outputPanel, and re-renders the cards
	 * in the panel in accordance with their new states. Set's the genral button
	 * properties. 
	 * Redraws them based on their updated properties of visible or not and on if it is the
	 * spymasters or the player's turn. 
	 * 
	 * 
	 * actionPerformed(ActionEvent e)
	 * Sets the JButtons action when clicked
	 * depending on current game state. If it is the Spymaster's turn
	 * the buttons have no action. Otherwise if it is the player's turn and the button is not visible they have 
	 * an action when clicked to be revealed, count is decremented, color is updated, and checked if the game is 
	 * in a winning state. 
	 * 
	 */
	private void createCards() {
		_outputPanel.removeAll();

		Location[][] locations = _game.getBoard().getLocations();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				String codeName = locations[i][j].getLocationCodename();
				Location location = locations[i][j];
				JButton b;
				if (_game.getControl().equals("Spymaster"))
					b = new JButton("<html> " + location.getPerson().getAgentTypeString().toUpperCase() + "<br/><br/>"
							+ codeName + "</html>");
				else
					b = new JButton(codeName);

				if (location.isVisible()) {
					b.setText(location.getPerson().getAgentTypeString());
					setColor(b, location.getPerson().getAgentTypeString());
					b.setForeground(Color.WHITE);
				} else
					b.setBackground(new Color(234, 227, 234));

				b.setPreferredSize(new Dimension(120, 100));
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (_game.getControl().equals("Spymaster")) {
						} else {
							if (!location.isVisible()) {
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
	 * Checks the state of the game at every guess. If a team has won the game then
	 * a pop up displays the winner and the game is over, as well as the reason
	 * for the win: assassin reveal or all agents revealed
	 * 
	 * If the game is not over then nothing happens.
	 * 
	 * @return {@code true} if the game is in a winning state and {@code false}
	 * otherwise
	 */
	private boolean checkWinningState() {
		if (_game.getBoard().checkWinningState()) {
			if (_game.getBoard().checkForAssassin())
				JOptionPane.showMessageDialog(null, _game.getTurnString() + " Team Wins!", "Assassin Revealed",
						JOptionPane.INFORMATION_MESSAGE);
			else {
				if (!_game.getWinner().equals(_game.getTurnString()))
					Game.switchTeamTurn();
				JOptionPane.showMessageDialog(null, _game.getTurnString() + " Team Wins!", "All Agents Revealed",
						JOptionPane.INFORMATION_MESSAGE);
			}
			return true;
		}
		return false;
	}

	/*
	 * This is triggered every time a JButton is pressed. countForTurn is
	 * decreased regardless of wheather the guess was successful or not.
	 * If the cound is not legal after it is decremented then the turn switches
	 * from the players of one team to the spymaster of the other team.
	 * 
	 * @param boolean is the result of a button being a successful or not successful guess
	 */
	private void decrementCount(boolean result) {
		countForTurn--;
		if (!_game.getBoard().legalCount(countForTurn) && result == true) {
			Game.changeControl();
			Game.switchTeamTurn();
			update();
		}
		if (!_game.getBoard().legalCount(countForTurn) && result == false)
			update();
	}

	/*
	 * Updates Background color of the JButton passed based on the agentType
	 * and then updates the jframe to display the color
	 * change
	 * @param JButton who's color is being updated
	 * @param String of the agent type to determine what color
	 * the button will get set to
	 */
	private void setColorUpdate(JButton b, String agentType) {
		setColor(b, agentType);
		updateJFrameForColor();
	}

	/*
	 * Sets the Background color of JButton according to the type of person at that
	 * button, but does not update the Jframe. This method is used when JButtons are 
	 * reinstentiated when the JPanel is updated. 
	 * 
	 * @param JButton who's color is being updated
	 * @param String of the agent type to determine what color
	 * the button will get set to
	 */
	private void setColor(JButton b, String agentType) {
		if (agentType.equals("Red"))
			b.setBackground(new Color(236, 87, 107));
		else if (agentType.equals("Blue"))
			b.setBackground(new Color(78, 197, 193));
		else if (agentType.equals("Bystander"))
			b.setBackground(new Color(229, 227, 56));
		else if (agentType.equals("Assassin"))
			b.setBackground(new Color(0, 0, 0));

	}

	/*
	 * Updates _feedbackPanel at the beginning of each turn.
	 * This panel displays which team's turn it is, as well as if it is the players
	 * or the spymaster's turn.
	 * 
	 * If the game is in a winning state the _feedBackPanel is removed from the
	 * JFrame.
	 */
	private void updateFeedbackPanel() {
		_feedBackPanel.removeAll();
		Color col;

		if (_game.getTurnString().equals("Blue"))
			col = new Color(78, 197, 193);
		else
			col = new Color(236, 87, 107);
		JLabel labelOfTeamPlaying = new JLabel(_game.getTurnString() + " Team's Turn");
		labelOfTeamPlaying.setForeground(col);
		labelOfTeamPlaying.setFont(new Font("Serif", Font.BOLD, 20));
		labelOfTeamPlaying.setBorder(new EmptyBorder(5, 20, 5, 20));
		_feedBackPanel.add(labelOfTeamPlaying);

		JLabel labelSpymasterOrNot = new JLabel(_game.getControl() + "'s Turn");
		labelSpymasterOrNot.setFont(new Font("Serif", Font.BOLD, 20));
		labelSpymasterOrNot.setForeground(col);
		labelSpymasterOrNot.setBorder(new EmptyBorder(5, 20, 5, 20));
		_feedBackPanel.add(labelSpymasterOrNot);
		
		if (_game.getBoard().checkWinningState())
			_feedBackPanel.removeAll();
	}

	/*
	 * Displays updates to the _playerPanel. First it removes everything from _playerPanel 
	 * This panel varies on the options it contains based on whether it is the spymaster's or the player's turn
	 * If the game is in a winning state the _inputPanel is removed from the JFrame.
	 */
	private void updatePlayerActionPanel() {
		if (_game.getBoard().checkWinningState())
			_playerPanel.removeAll();
		else if (_game.getControl().equals("Spymaster"))
			setPlayerActionPanelSpymasterView();
		else if (_game.getControl().equals("Players")) 
			setPlayerActionPanelPlayerView();


	}
	/*
	 * Sets the _playerPanel to contain all contents for the Player view
	 * Displays the current clue, the current count, and a button to voluntarily end the turn
	 * 
	 */
	private void setPlayerActionPanelPlayerView() {
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
		endTurn.setPreferredSize(new Dimension(50, 30));
		endTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.changeControl();
				Game.switchTeamTurn();
				update();
			}
		});

		_playerPanel.add(endTurn);

	}
	/*
	 * Sets the _playerPanel to contain contents for the Spymaster's view
	 * Has a JTextField for clue input and a JComboBox for the count input.
	 * On the click of the enter button the validity of the clue and count are checked 
	 * If one is not valid a Message Dialog is shown displaying the issue and allows the spymaster
	 * to enter a legal value 
	 * 
	 * Has easter egg for clue of "hertz"
	 */
	private void setPlayerActionPanelSpymasterView() {
		_playerPanel.removeAll();

		JLabel clueLabel = new JLabel("Clue:");
		clueLabel.setFont(new Font("Serif", Font.BOLD, 13));
		_playerPanel.add(clueLabel);

		JTextField clueInput = new JTextField("input clue");
		clueInput.setSize(new Dimension(250, clueInput.getHeight()));
		_playerPanel.add(clueInput);

		JLabel countLabel = new JLabel("Count:");
		countLabel.setFont(new Font("Serif", Font.BOLD, 13));
		_playerPanel.add(countLabel);

		Integer[] countBox = new Integer[_game.getBoard().getCount()];
		Integer input = 1;
		for (int i = 0; i < _game.getBoard().getCount(); i++) {
			countBox[i] = input;
			input++;
		}

		JComboBox<Integer> spyCount = new JComboBox<Integer>(countBox);
		spyCount.setSelectedIndex(0);
		_playerPanel.add(spyCount);

		JButton enterButton = new JButton("Enter");
		enterButton.setBackground(Color.LIGHT_GRAY);
		enterButton.setPreferredSize(new Dimension(50, 30));
		enterButton.addActionListener(new ActionListener() {

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
					JOptionPane.showMessageDialog(null,
							"\"You fail 100% of the JUnit tests you dont write.\"" + System.lineSeparator()
									+ "- Matthew Hertz",
							easter.toUpperCase() + "LOCKER", JOptionPane.NO_OPTION, image);
				}

				if (count.matches("[0-9]") && _game.getBoard().legalCount(Integer.parseInt(count))) {
					countForTurn = Integer.parseInt(count);

					if (_game.getBoard().legalClue(currentClue.toLowerCase())) {
						Game.changeControl();
						update();
					} else
						JOptionPane.showMessageDialog(null, "Illegal Clue", "Uh Oh", JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Illegal Count", "Uh Oh", JOptionPane.ERROR_MESSAGE);
			}

		});
		_playerPanel.add(enterButton);
	}

	/*
	 * Updates the JFrame to display the changes to the panel.
	 * Also check's if the _game.getControl() was updated to spymaster. In which case
	 * a messageDialog shows up stating who's team's turn it is since it has just been changed. 
	 */
	private void updateJFrameIfNotHeadless() {
		if (_windowHolder != null) {
			_windowHolder.updateJFrame();
		}
		if (_game.getControl().equals("Spymaster")) {
			JOptionPane.showMessageDialog(null, _game.getTurnString() + " Team's Turn", "Turn",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/*
	 * This is a helper method for updating the JFrame to display color changes without 
	 * the messageDialog popping up cause turn is not changed. 
	 */
	private void updateJFrameForColor() {
		if (_windowHolder != null) {
			_windowHolder.updateJFrame();
		}

	}

	/*
	 * JMenu creation method.
	 * Creates a JMenuBar and adds a fileMenu to it which contains an option to start a new
	 * game, exit the game, and see the rules (aka the easter egg).
	 * When the new game option is pressed the current window is closed and a new instance of Game is created
	 * creating all new selections. When quit game is pressed the system exits.  
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
