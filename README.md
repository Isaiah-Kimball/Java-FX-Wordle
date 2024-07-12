# Java-FX-Wordle
The following project was a final project for my Java course in which we incorporated Java, JavaFX, FXML, XML, and SceneBuilder to build the game Wordle.

The assignment stated to essentially recreate the rules of Wordle one-to-one, though styling and the layout was up to us, I was able to get a 100 for this project and decided to add more pieces to it. Some things that I added that were not required by the project was key inputs from the keyboard instead of just the buttons, some minor tweaks to design and presentation, and viewport modifications.

To run this program requires JavaFX installation, this was made with Version 22 of JavaFX, though other versions may work just fine so long as they are at least 13 and above. If you have issues trying to run it, it more than likely is an environment setup issue. Try this video for setting it up properly on Eclipse: https://www.youtube.com/watch?v=nz8P528uGjk . Try this video for setting it up on VSCode: https://www.youtube.com/watch?v=AubJaosfI-0

If you're still unable to properly run the program after setting up, or you just simply want to see what it looks like, I have provided some images in the folder named: Game Images

# How it Works
If you are unfamilar with Wordle, it would be best to look up the rules as they are the same here

There are two screens in this program, one is the Game Board and the other is the Stats Screen

Numerous variables are present, they include FXML elements like buttons, ArrayLists to help aid processing inputted words and for saving previous game data, HashMaps, etc

User can type and enter their inputted letters and backspace to remove them, user's can switch between scenes at any moment, user's can restart or reload a save at any point (save can be loaded even after program termination due to being kept in a simple formatted text file that SHOULD NOT be changed), user's stats are kept across saves and instances of a program run.

# Issues

The majority of issues with this project have been solved, the only prevailing issue is the scaling of elements whenever the user decides to change the viewport. I decided to simply lock the viewport size to be unchangeable due to many tries at fixing this problem, only to have the elmenents not look as good due to how FXML handles scaling. Thus, I partially put in a solution that won't have this problem noticed, but isn't a true solution.
