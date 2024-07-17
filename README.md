# Java-FX-Wordle
The following project was a final project for my Java course in which we incorporated Java, JavaFX, FXML, XML, and SceneBuilder to build the game Wordle.

The assignment stated to essentially recreate the rules of Wordle one-to-one, though styling and the layout was up to us, I was able to get a 100 for this project and decided to add more pieces to it. Some things that I added that were not required by the project was key inputs from the keyboard instead of just the buttons, some minor tweaks to design and presentation, and viewport modifications.

To run this program requires JavaFX installation, this was made with Version 22 of JavaFX, though other versions may work just fine so long as they are at least 13 and above. If you have issues trying to run it, it more than likely is an environment setup issue. Try this video for setting it up properly on Eclipse: https://www.youtube.com/watch?v=nz8P528uGjk . Try this video for setting it up on VSCode: https://www.youtube.com/watch?v=AubJaosfI-0

If you're still unable to properly run the program after setting up, or you just simply want to see what it looks like, I have provided some images in the folder named: Game Images

You'll also notice that the majority of the code is in one java file. This was due to issues on my end with the Eclipse IDE in the initial development of this project; thus, I settled by putting it all in one folder for the most part as passing some FXML elements across java files created issues.

# How it Works
If you are unfamilar with Wordle, it would be best to look up the rules as they are the same here

There are two screens in this program, one is the Game Board and the other is the Stats Screen

Numerous variables are present, they include FXML elements like buttons, ArrayLists to help aid processing inputted words and for saving previous game data, HashMaps, etc

User can type and enter their inputted letters and backspace to remove them, user's can switch between scenes at any moment, user's can restart or reload a save at any point (save can be loaded even after program termination due to being kept in a simple formatted text file that SHOULD NOT be changed), user's stats are kept across saves and instances of a program run.

# Current Issues/Bugs

The majority of issues with this project have been solved, the only prevailing issue is the scaling of elements whenever the user decides to change the viewport. I decided to simply lock the viewport size to be unchangeable due to many tries at fixing this problem, only to have the elmenents not look as good due to how FXML handles scaling. Thus, I partially put in a solution that won't have this problem noticed, but isn't a true solution.

But Below this section is some issues I had during development, but have solved one way or the other

# Solved Issues/Bugs

Issue 1: Enter key and space bar would activate last clicked button

Solution 1: All keys would be passed through a function that would pass the enter command to the proper function, but would also call the consume function such that the button would not be activated

Issue 2: Board status, and statistcs not being stored properly when switching from scenes within program run, even after making objects static

Solution 2: Put it all in the save and load files, these files were already present for the save/load feature of the project, but now they can be used (alongside ArrayLists) for loading the current status of both screens

Issue 3: GridPane index going out of bounds

Solution 3: The GridPane includes the labels that are used to display the letters the user has inputted, but it also includes the Grid Pane borders to seperarte the cells. The borders were added into the list of children though were not conistent in how they were added and thus were occasionally deleted or helped cause out of bounds errors for the project. Thus, I simply removed them and had no more issues

Issue 4: Winning or Losing the game causing Nullptr error

Solution 4: This was mainly caused due to how I initially changed scenes as I was getting a button source from the board screen. But if the enter key won or lost the game, it couldn't get the source due it not being able to be passed in. Thus, I eventually found a workaround to keep the stage variable in the Main.Java file, and access its reference and change it like that, no more Nullptr errors

Miscellaneous Issues: There were many other issues during the development as learning JavaFX was a challenge for me, but those issues aren't big enough to note or were solved almost immediately.
