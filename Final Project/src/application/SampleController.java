package application;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.*;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SampleController {
	@FXML Button Restart;
	@FXML Button A_key;
	@FXML Button B_key;
	@FXML Button C_key;
	@FXML Button D_key;
	@FXML Button E_key;
	@FXML Button F_key;
	@FXML Button G_key;
	@FXML Button H_key;
	@FXML Button I_key;
	@FXML Button J_key;
	@FXML Button K_key;
	@FXML Button L_key;
	@FXML Button M_key;
	@FXML Button N_key;
	@FXML Button O_key;
	@FXML Button P_key;
	@FXML Button Q_key;
	@FXML Button R_key;
	@FXML Button S_key;
	@FXML Button T_key;
	@FXML Button U_key;
	@FXML Button V_key;
	@FXML Button W_key;
	@FXML Button X_key;
	@FXML Button Y_key;
	@FXML Button Z_key;
	@FXML Button enter_key;
	@FXML Button backspace_key;
	@FXML Button go_to_stats_screen_button;
	@FXML GridPane board = new GridPane();
	protected @FXML Label warning;
	protected @FXML Label win_or_lose;
	private Stage curr_stage;
	private Scene curr_scene;
	private static String word_to_guess;
	protected static ArrayList<ArrayList<String>> board_array = new ArrayList<ArrayList<String>>(6);
	protected static ArrayList<ArrayList<String>> color_per_index = new ArrayList<ArrayList<String>>(6);
	protected HashMap<String, Button> buttons_to_disable = new HashMap<String, Button>();
	protected static ArrayList<String> buttons_disabled = new ArrayList<String>();
	private static int board_row = 0;
	private static int board_col = 0;
	private static int color_row = 0;
	private static int color_col = 0;
	private static int GridPane_index = -1;
	private static Boolean win = false;
	protected static int num_games_played = 0;
	protected static int games_won = 0;
	protected static int curr_streak = 0;
	protected static int max_streak = 0;
	protected static int num_guesses = 0;
	protected static int num_guesses_saved = 0;
	@FXML GridPane stats_board = new GridPane();
	@FXML Label played_label;
	@FXML Label win_label;
	@FXML Label curr_streak_label;
	@FXML Label max_streak_label;
	@FXML Button back_to_game;
	@FXML Button reset;
	private static Boolean init = false;
	private static Boolean load_board = false;
	private static GridPane instance;
	private static GridPane stat_instance;
	@FXML BarChart guess_distribution;
	private static HashMap<Integer, Integer> guess_hash_map = new HashMap<>();
	PrintWriter outfile;
	static BufferedWriter outfile2;
	BufferedReader infile2;
	static BufferedWriter outfile_save;
	BufferedReader infile_save;
	private static Boolean only_once = true;
	private static Boolean win_initial = true;
	@FXML Button exit_game;
	@FXML Button save_game;
	@FXML Button load_game;
	protected ArrayList<ArrayList<String>> board_array_save = new ArrayList<ArrayList<String>>(6);
	protected ArrayList<ArrayList<String>> color_per_index_save = new ArrayList<ArrayList<String>>(6);
	private static Boolean win_save = false;
	
	public void exit(ActionEvent e) throws IOException
	{
		// JavaFX documentation says you can end your program with this platform statement
		outfile2.close();
		Platform.exit();
	}
	
	// Method to change the board to what is in the save.txt file
	public void loadSavedBoardState(GridPane board_instance) {
		// Just basically goes element by element of board_array and color_per_index and adds the appropriate label in the correct position
	    for (int row = 0; row < board_array.size(); row++) {
	    	if (board_array.get(row).isEmpty())
	    	{
	    		break;
	    	}
	        for (int col = 0; col < board_array.get(row).size(); col++) {
	            Label label_to_add = new Label(board_array.get(row).get(col));
	            label_to_add.setStyle("-fx-padding: 5px; -fx-font-size: 20px; -fx-text-fill: " + color_per_index.get(row).get(col));
	            board.add(label_to_add, col, row);
	            GridPane.setHalignment(label_to_add, HPos.CENTER);
	        }
	    }
	}

	// Saves relevant game data to the file save.txt
	public void save_board_and_stats_file (ActionEvent e) throws IOException
	{
		// Different i/o usage from notes because I prefer this way
		outfile_save = new BufferedWriter(new FileWriter("save.txt"));
		outfile_save.write(Integer.toString(GridPane_index));
		outfile_save.write("\n");
		outfile_save.write(Integer.toString(board_row));
		outfile_save.write("\n");
		outfile_save.write(Integer.toString(board_col));
		outfile_save.write("\n");
		// Writing board_array
    	for (int row = 0; row < board_array.size(); row++)
    	{
    		if (board_array.get(row).isEmpty())
    		{
    			break;
    		}
    		for (int col = 0; col < board_array.get(row).size(); col++)
    		{
    			outfile_save.write(board_array.get(row).get(col) + " ");
    		}
    		outfile_save.write("\n");
    	}
    	outfile_save.write("-");
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(color_row));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(color_col));
    	outfile_save.write("\n");
    	// Writing color per index's stuff to the save file
    	for (int row = 0; row < color_per_index.size(); row++)
    	{
    		if (color_per_index.get(row).isEmpty())
    		{
    			break;
    		}
    		for (int col = 0; col < color_per_index.get(row).size(); col++)
    		{
    			outfile_save.write(color_per_index.get(row).get(col) + " ");
    		}
    		outfile_save.write("\n");
    	}
    	// Writing the rest of the data to the save file
    	outfile_save.write("-");
    	outfile_save.write("\n");
    	for (int index = 0; index < buttons_disabled.size(); index++)
    	{
    		outfile_save.write(buttons_disabled.get(index));
    		outfile_save.write("\n");
    	}
    	outfile_save.write("-");
    	outfile_save.write("\n");
    	outfile_save.write(word_to_guess);
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(num_games_played));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(games_won));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(curr_streak));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(max_streak));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(num_guesses));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(guess_hash_map.get(1)));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(guess_hash_map.get(2)));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(guess_hash_map.get(3)));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(guess_hash_map.get(4)));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(guess_hash_map.get(5)));
    	outfile_save.write("\n");
    	outfile_save.write(Integer.toString(guess_hash_map.get(6)));
    	outfile_save.close();
    	instance = board;
	}
	
	// Loads the stats and board at the instant the user saved into the text file, works across different program runnings
	public void load_board_and_stats_file() throws IOException
	{
		infile_save = new BufferedReader(new FileReader("save.txt"));
		// Reset everything and set it to what is in the save.txt file essentially
		board_array = new ArrayList<ArrayList<String>>(6);
		color_per_index = new ArrayList<ArrayList<String>>(6);
		buttons_disabled = new ArrayList<String>();
		board_row = 0;
		board_col = 0;
		color_row = 0;
		color_col = 0;
		win = win_save;
		num_guesses = 0;
		String line = infile_save.readLine();
		GridPane_index = Integer.parseInt(line);
		line = infile_save.readLine();
		board_row = Integer.parseInt(line);
		line = infile_save.readLine();
		board_col = Integer.parseInt(line);
		line = infile_save.readLine();
		while (!line.equals("-"))
		{
			// Array List documentation showed that there's a way to split a string into substrings and add those based off of a seperator
			// Instead of making add_row empty, this one line splits line and puts each letter into its own spot in the add_row arraylist
			// I originally did a for loop for this but, this is more concise.
			ArrayList<String> add_row = new ArrayList<>(Arrays.asList(line.split(" ")));
			if (add_row.isEmpty())
			{
				line = infile_save.readLine();
				continue;
			}
			board_array.add(add_row);
			line = infile_save.readLine();
		}
		line = infile_save.readLine();
		color_row = Integer.parseInt(line);
		line = infile_save.readLine();
		color_col = Integer.parseInt(line);
		line = infile_save.readLine();
		// Used to see if the user won or not at the point that they saved
		ArrayList<String> greens = new ArrayList<>();
		for (int index = 0; index < 5; index++)
		{
			greens.add("green");
		}
		while (!(line.equals("-")))
		{
			// Found this useful way of making each element in a string into an arraylist, used to do a for loop but then this was more better
			ArrayList<String> add_row = new ArrayList<>(Arrays.asList(line.split(" ")));
			if (add_row.get(0).equals("green") || add_row.get(0).equals("orange") || add_row.get(0).equals("red"))
			{
				num_guesses++;
			}
			if (add_row.isEmpty())
			{
				line = infile_save.readLine();
				continue;
			}
			color_per_index.add(add_row);
			if (add_row.equals(greens))
			{
				win = true;
			}
			line = infile_save.readLine();
		}
		while (board_array.size() != 6)
		{
			board_array.add(new ArrayList<String>(5));
			color_per_index.add(new ArrayList<String>(5));
		}
		line = infile_save.readLine();
		while (!line.equals("-"))
		{
			buttons_disabled.add(line);
			line = infile_save.readLine();
		}
		line = infile_save.readLine();
		word_to_guess = line;
		line = infile_save.readLine();
		num_games_played = Integer.parseInt(line);
		line = infile_save.readLine();
		games_won = Integer.parseInt(line);
		line = infile_save.readLine();
		curr_streak = Integer.parseInt(line);
		line = infile_save.readLine();
		max_streak = Integer.parseInt(line);
		line = infile_save.readLine();
		num_guesses = Integer.parseInt(line);
		line = infile_save.readLine();
		guess_hash_map.put(1, Integer.parseInt(line));
		line = infile_save.readLine();
		guess_hash_map.put(2, Integer.parseInt(line));
		line = infile_save.readLine();
		guess_hash_map.put(3, Integer.parseInt(line));
		line = infile_save.readLine();
		guess_hash_map.put(4, Integer.parseInt(line));
		line = infile_save.readLine();
		guess_hash_map.put(5, Integer.parseInt(line));
		line = infile_save.readLine();
		guess_hash_map.put(6, Integer.parseInt(line));
		infile_save.close();
		if (num_games_played == 0)
		{
			win_initial = true;
		}
		else
		{
			win_initial = false;
		}
		// Now I need to change the board with this line
		loadSavedBoardState(instance);
	}
	
	// Used for disabling opacity for keys
	public void add_buttons()
	{
		buttons_to_disable.put("A", A_key);
		buttons_to_disable.put("B", B_key);
		buttons_to_disable.put("C", C_key);
		buttons_to_disable.put("D", D_key);
		buttons_to_disable.put("E", E_key);
		buttons_to_disable.put("F", F_key);
		buttons_to_disable.put("G", G_key);
		buttons_to_disable.put("H", H_key);
		buttons_to_disable.put("I", I_key);
		buttons_to_disable.put("J", J_key);
		buttons_to_disable.put("K", K_key);
		buttons_to_disable.put("L", L_key);
		buttons_to_disable.put("M", M_key);
		buttons_to_disable.put("N", N_key);
		buttons_to_disable.put("O", O_key);
		buttons_to_disable.put("P", P_key);
		buttons_to_disable.put("Q", Q_key);
		buttons_to_disable.put("R", R_key);
		buttons_to_disable.put("S", S_key);
		buttons_to_disable.put("T", T_key);
		buttons_to_disable.put("U", U_key);
		buttons_to_disable.put("V", V_key);
		buttons_to_disable.put("W", W_key);
		buttons_to_disable.put("X", X_key);
		buttons_to_disable.put("Y", Y_key);
		buttons_to_disable.put("Z", Z_key);
	}
	// Used for making sure space or enter does not activate any keys
	public void addEnterKeyFilter(Node node) {
        if (node != null) {
            node.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.SPACE) {
                    event.consume();
                }
                else if (event.getCode() == KeyCode.ENTER)
                {
                	try {
						outfile2.write("Key pressed: Enter \n");
					} catch (IOException e) {
						e.printStackTrace();
					}
                    try {
						key_pressed(null, "enter_key");
					} catch (IOException e) {
						e.printStackTrace();
					}
                    event.consume();
                }
            });
        }
    }
	// Applies the filter to all buttons
	public void filter() {
		addEnterKeyFilter(Restart);
        addEnterKeyFilter(A_key);
        addEnterKeyFilter(B_key);
        addEnterKeyFilter(C_key);
        addEnterKeyFilter(D_key);
        addEnterKeyFilter(E_key);
        addEnterKeyFilter(F_key);
        addEnterKeyFilter(G_key);
        addEnterKeyFilter(H_key);
        addEnterKeyFilter(I_key);
        addEnterKeyFilter(J_key);
        addEnterKeyFilter(K_key);
        addEnterKeyFilter(L_key);
        addEnterKeyFilter(M_key);
        addEnterKeyFilter(N_key);
        addEnterKeyFilter(O_key);
        addEnterKeyFilter(P_key);
        addEnterKeyFilter(Q_key);
        addEnterKeyFilter(R_key);
        addEnterKeyFilter(S_key);
        addEnterKeyFilter(T_key);
        addEnterKeyFilter(U_key);
        addEnterKeyFilter(V_key);
        addEnterKeyFilter(W_key);
        addEnterKeyFilter(X_key);
        addEnterKeyFilter(Y_key);
        addEnterKeyFilter(Z_key);
        addEnterKeyFilter(backspace_key);
        addEnterKeyFilter(save_game);
	}
	
	// think of as the Controller's constructor
    public void initialize() throws IOException 
    {
    	filter();
    	if (only_once)
    	{
    		outfile2 = new BufferedWriter(new FileWriter("log.txt"));
    		guess_hash_map.put(1, 0);
    		guess_hash_map.put(2, 0);
    		guess_hash_map.put(3, 0);
    		guess_hash_map.put(4, 0);
    		guess_hash_map.put(5, 0);
    		guess_hash_map.put(6, 0);
    		add_buttons();
    		only_once = false;
    	}
    	if (!init)
    	{
	    	for (int index = 0; index < 6; index++)
	    	{
	    		board_array.add(new ArrayList<String>(5));
	    		color_per_index.add(new ArrayList<String>(5));
	    		board_array_save.add(new ArrayList<String>(5));
	    		color_per_index_save.add(new ArrayList<String>(5));
	    	}
	    	if (word_to_guess != null)
	    	{
	    		return;
	    	}
	    	SelectWord object = new SelectWord();
	    	object.generate_word_list();
	    	word_to_guess = object.generate_random_word();
	    	init = true;
	    }
    }

    // This will be used when loading a save file
    public void load_game(ActionEvent e) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("play_game.fxml"));
        Parent root = loader.load();
        SampleController playController = loader.getController();
        playController.load_board_and_stats_file();
        playController.disable_buttons();
        curr_stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        curr_scene = new Scene(root);
        curr_stage.setScene(curr_scene);
        curr_stage.setResizable(false);
        curr_stage.show();
    }
    // This code was used based off of the provided linked video from the Java FX notes
    public void play_game(ActionEvent e) throws IOException
    {
    	// Need to do it this way, otherwise it will not set the board to what the user put in previously 
    	// This helps allow the program to go back and forth to the stats screen without erasing the board
        FXMLLoader loader = new FXMLLoader(getClass().getResource("play_game.fxml"));
        Parent root = loader.load();
        SampleController playController = loader.getController();
        playController.disable_buttons();
        
        if (playController.load_board) 
        {
        	playController.loadSavedBoardState(stat_instance);
        }
        curr_stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        curr_scene = new Scene(root);
        curr_stage.setScene(curr_scene);
        curr_stage.setResizable(false);
        curr_stage.show();
    }
    
    // Just resets all relevant data, will be changed in the initialize function
    public void restart_game(ActionEvent e) throws IOException
    {
    	word_to_guess = null;
    	win = false;
    	board_row = 0;
    	board_col = 0;
    	color_row = 0;
    	color_col = 0;
    	GridPane_index = -1;
    	init = false;
    	board = new GridPane();
    	load_board = false;
    	board_array = new ArrayList<ArrayList<String>>(6);
    	color_per_index = new ArrayList<ArrayList<String>>(6);
    	num_guesses = 0;
    	win_initial = true;
    	buttons_disabled = new ArrayList<String>();
    	for (int index = 0; index < 6; index++)
    	{
    		board_array.add(new ArrayList<String>(5));
    		color_per_index.add(new ArrayList<String>(5));
    	}
    	play_game(e);
    }
    
    // Loading the stats board
    public void go_to_stats_screen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stats_screen.fxml"));
        Parent root = loader.load();
        SampleController statsController = loader.getController();
        statsController.update_stats_board(num_games_played, games_won, curr_streak, max_streak, num_guesses);
        stat_instance = board;
        load_board = true;
        curr_scene = new Scene(root);
        Main.getPrimaryStage().setScene(curr_scene);
        Main.getPrimaryStage().show();
    }

    // This is the function that figures out how and what stats to display and also how to change the barChart
	void update_stats_board(int games_played, int games_won, int curr_streak, int max_streak, int num_guesses)
	{
		// This is only when the user hasn't entered any words
	    if (guess_hash_map == null) {
	        guess_hash_map.put(1, 0);
	        guess_hash_map.put(2, 0);
	        guess_hash_map.put(3, 0);
	        guess_hash_map.put(4, 0);
	        guess_hash_map.put(5, 0);
	        guess_hash_map.put(6, 0);
	    }
	    // Have to create label variables in order to set their text to white as it will default to black unless I specify
	    Label games_played_label = new Label(Integer.toString(games_played));
	    // Apparently it needs the semicolon in the string
	    games_played_label.setStyle("-fx-text-fill: white;");
		stats_board.add(games_played_label, 0, 0);
		
		if (games_played == 0)
		{
		    Label percentage_label = new Label(Integer.toString(0));
		    percentage_label.setStyle("-fx-text-fill: white;");
			stats_board.add(percentage_label, 1, 0);
		}
		else
		{
			double percentage = (double)games_won / (double)games_played * 100;
		    Label percentage_label = new Label(Double.toString(percentage));
		    percentage_label.setStyle("-fx-text-fill: white;");
			stats_board.add(percentage_label, 1, 0);
		}
		
		Label curr_streak_label = new Label(Integer.toString(curr_streak));
		curr_streak_label.setStyle("-fx-text-fill: white;");
		stats_board.add(curr_streak_label, 2, 0);
		Label max_streak_label = new Label(Integer.toString(max_streak));
		max_streak_label.setStyle("-fx-text-fill: white;");
		stats_board.add(max_streak_label, 3, 0);

		if (num_guesses != 0 && win && win_initial)
		{
			guess_hash_map.put(num_guesses, guess_hash_map.get(num_guesses) + 1);
			win_initial = false;
		}
		
		// Bar Chart documentation says to add series in order to put data onto the chart
		XYChart.Series guesses_series = new XYChart.Series();
		guesses_series.getData().add(new XYChart.Data(guess_hash_map.get(1), "1"));
		guesses_series.getData().add(new XYChart.Data(guess_hash_map.get(2), "2"));
		guesses_series.getData().add(new XYChart.Data(guess_hash_map.get(3), "3"));
		guesses_series.getData().add(new XYChart.Data(guess_hash_map.get(4), "4"));
		guesses_series.getData().add(new XYChart.Data(guess_hash_map.get(5), "5"));
		guesses_series.getData().add(new XYChart.Data(guess_hash_map.get(6), "6"));
		guess_distribution.getData().addAll(guesses_series);
		NumberAxis xAxis = (NumberAxis) guess_distribution.getXAxis();
		List<Integer> guess_wins = Arrays.asList(guess_hash_map.get(1), guess_hash_map.get(2), guess_hash_map.get(3), 
				guess_hash_map.get(4), guess_hash_map.get(5), guess_hash_map.get(6));
		int max = Collections.max(guess_wins);
		xAxis.setTickUnit(1);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(max); 
        xAxis.setAutoRanging(false); 
		if (win)
		{
			win_or_lose.setText("Congratulations, you guessed the word properly!");
		}
		else if (num_guesses == 6)
		{
			win_or_lose.setText("You have run out of guesses, the word was: " + word_to_guess);
		}
	}
	
    // This handles when the keyboard on the computer is pressed and not whenever the buttons are clicked on
	public void keyboard_input(KeyEvent e) throws IOException {
		KeyCode key = e.getCode();
		if (key.isLetterKey()) {
            String keyText = key.getName();
            outfile2.write("Key pressed: " + key + "\n");
            key_pressed(null, keyText);
        } else {
            switch (key) {
                case BACK_SPACE:
                    outfile2.write("Key pressed: " + key + "\n");
                    key_pressed(null, "backspace_key");
                    break;
                case ENTER:
                    outfile2.write("Key pressed: " + key + "\n");
                    key_pressed(null, "enter_key");
                    break;
                default:
                    break;
            }
        }
		
	}
	// This handles all keyboard presses by translating what was actually pressed into another function, this is basically a helper function
	public void key_input(ActionEvent e) throws IOException
    {
		// Thanks to Java FX Documentation for helping me get this right
    	Button key_pressed = (Button)e.getSource();
    	String key = null;
    	// Different if enter or backspace was clicked
    	if (key_pressed.getId().equals("enter_key") || key_pressed.getId().equals("backspace_key"))
    	{
    		key = key_pressed.getId();
    	}
    	// Means that a letter button was pressed
    	else
    	{
    		key = key_pressed.getId().substring(0,1);
    	}
    	outfile2.write("Key pressed: " + key + "\n");
    	key_pressed(e, key);
    }
    
	// This will check if the key press will actually change something, if so then it will update the board, if not then it will display a warning
    void key_pressed(ActionEvent e, String key) throws IOException
	{
    	if (win)
    	{
    		warning.setText("You have already won, restart the game if you want to play again.");
    		return;
    	}
    	if (board_row == 6)
    	{
    		warning.setText("You have already lost this round, click restart if you want to play again.");
    		return;
    	}
		if (key.equals("enter_key"))
		{
			if (board_array.get(board_row).size() != 5)
			{
				this.warning.setText("You need to input a word that is 5 letters long!");
				return;
			}
			update_board(e, key, word_to_guess);
		}
		else if (key.equals("backspace_key"))
		{
			if (board_array.get(board_row).size() == 0)
			{
				return;
			}
			update_board(e, key, word_to_guess);
		}
		else
		{
			if (board_array.get(board_row).size() == 5)
			{
				this.warning.setText("You can not add anymore letters, please click the backspace if you want to replace letters");
				return;
			}
			update_board(e, key, word_to_guess);
		}
	}
    
	void update_board(ActionEvent e, String key, String word_to_guess) throws IOException
	{
		if (key.equals("enter_key"))
		{
			// Update the log accordingly
			outfile2.write("User submitted as answer: " + String.join("", board_array.get(board_row)) + "\n");
			check_word(e, word_to_guess);
		}
		else if (key.equals("backspace_key"))
		{
			board.getChildren().remove(GridPane_index);
			board_array.get(board_row).remove(board_col - 1);
			color_per_index.get(color_row).remove(color_col - 1);
			board_col--;
			color_col--;
			GridPane_index--;
		}
		else
		{
			// Label only takes a string as input, not a character
	    	Label label_to_add = new Label(key);
	    	label_to_add.setStyle("-fx-padding: 5px; -fx-font-size: 20px; -fx-text-fill: white");
	    	// Documentation said I could center the letter in a given box with this line of code
	    	board.add(label_to_add, board_col, board_row);
	    	// This was the only method that worked to center the label whenever I made it
	    	// I tried setting alignment on the label, but it didn't affect how it appeared in the GridPane
	    	GridPane.setHalignment(label_to_add, HPos.CENTER);
	    	board_array.get(board_row).add(key);
	    	color_per_index.get(color_row).add("white");
	    	GridPane_index++;
	    	board_col++;
	    	color_col++;
		}
	}
	
	void disable_buttons()
	{
		add_buttons();
		for (String button_id : buttons_disabled)
		{
			Button button_to_disable = buttons_to_disable.get(button_id.substring(0,1));
			button_to_disable.setOpacity(0.5);
		}
	}
	
	void check_word(ActionEvent e, String word_to_guess) throws IOException
	{
    	// Had to go through a lot of documentation to find how to make the color and change the size
    	// Letter in the right spot
		// Need to first check that the word is valid from the list of words provided
    	SelectWord object = new SelectWord();
    	// Need to actually make the list for this new object otherwise it will just be an empty array list
    	object.generate_word_list();
    	String whole_word = String.join("", board_array.get(board_row)).toLowerCase();
    	if (!object.valid_word(whole_word))
    	{
    		warning.setText("Invalid word, please input a valid word");
    		return;
    	}
    	num_guesses++;
    	// This is the logic for determining what color the letters in the row should be
		int num_letters_green = 0;
		// Turns out that this line already puts false as each value in the index
		boolean[] used = new boolean[5];
		for (int index = 0; index < board_array.get(board_row).size(); index++)
		{
			// Holds the current letter
			String letter = board_array.get(board_row).get(index).toLowerCase();
			// We will have to relook over the whole word again to ensure orange letters are set properly, so for now, just changing
			// To either green or red
			if (letter.equals(word_to_guess.substring(index, index + 1)))
			{
				num_letters_green++;
    			color_per_index.get(color_row).set(index, "green");
    			used[index] = true;
			}
			else
			{
				color_per_index.get(color_row).set(index, "red");
			}
		}
		
		for (int index = 0; index < board_array.get(board_row).size(); index++)
		{
			// If it's green it doesn't need to be considered for being orange, only if a letter is red
			if (color_per_index.get(color_row).get(index).equals("green"))
			{
				continue;
			}
			
			// Need to loop over the whole word in order to determine if the letter should stay red or orange
			for (int index2 = 0; index2 < board_array.get(board_row).size(); index2++)
			{
				// First checks that the letter we are looking at is red, and that it also equals the letter that the outer loop is looking at
				// If it does, then that means the letter is in the word but in the incorrect position, and thus should be orange
				// It won't need to be considered again, and we just need the outer loop to go to the next letter
				if (!used[index2] && board_array.get(board_row).get(index).toLowerCase().equals(word_to_guess.substring(index2, index2 + 1)))
				{
					color_per_index.get(color_row).set(index, "orange");
					used[index2] = true;
					break;
				}
			}
		}
		int index = 0;
		// Actually adding the letters so that they can be displayed on the board
		for (Node node_in_board : board.getChildren())
		{
			// Sometimes, the GridPane doesn't have all of its children as Label, even though it should, so I added this check
			if (node_in_board instanceof Label && GridPane.getRowIndex(node_in_board) == board_row)
			{
	    		Label label_to_change = (Label) node_in_board;
	    		label_to_change.setStyle("-fx-padding: 5px; -fx-font-size: 20px; -fx-text-fill: " + color_per_index.get(color_row).get(index));
	    		if (color_per_index.get(color_row).get(index).equals("red") && !word_to_guess.contains(board_array.get(board_row).get(index).toLowerCase()))
	    		{
	    			Button disable_button = buttons_to_disable.get(board_array.get(board_row).get(index));
	    			disable_button.setOpacity(0.5);
	    			if (!buttons_disabled.contains(disable_button.getId()))
	    			{
	    				buttons_disabled.add(disable_button.getId());
	    			}
	    		}
	    		index++;
			}
		}
    	board_row++;
    	color_row++;
    	board_col = 0;
    	color_col = 0;
    	// Player won the round
    	if (num_letters_green == 5)
    	{
    		win = true;
    		num_games_played++;
    		games_won++;
    		curr_streak++;
    		if (curr_streak > max_streak)
    		{
    			max_streak = curr_streak;
    		}
    		go_to_stats_screen();
    		return;
    	}
    	// Means the player lost but completed the game
    	if (board_row == 6)
    	{
    		num_games_played++;
    		curr_streak = 0;
    		go_to_stats_screen();
    	}
	}
}
