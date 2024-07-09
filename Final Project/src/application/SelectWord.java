package application;

import java.io.*;
import java.util.*;
import java.util.Random;

public class SelectWord {
	private ArrayList<String> words = new ArrayList<String>();
	
	// Eclipse told me to put the throws statement
	void generate_word_list() throws FileNotFoundException
	{
		// Basically following what the i/o notes say about taking file input
		Scanner infile = new Scanner(new FileReader("words.txt"));
		while (infile.hasNextLine())
		{
			String line = infile.nextLine();
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");
			// Looking through the tokenizer documentation, just using the previous print statement did not go through every single word
			// And only went through the first word, the following while loop should print each one
			// It did, so each word was added successfully in the words arraylist
			while (tokenizer.hasMoreTokens())
			{
				words.add(tokenizer.nextToken());
			}
		}
		infile.close();
	}
	// This returns a randomly chosen word
	String generate_random_word()
	{
		Random random = new Random();
		return words.get(random.nextInt(2310));
	}
	
	// Used to check if the user inputed a valid word that is in the list of 2310 words
	Boolean valid_word(String word_to_check)
	{
		return words.contains(word_to_check);
	}
}
