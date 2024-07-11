# Java-FX-Wordle
The following project was a final project for my Java course in which we incorporated Java, JavaFX, FXML, XML, and SceneBuilder to build the game Wordle.

The assignment stated to essentially recreate the rules of Wordle one-to-one, though styling and the layout was up to us, I was able to get a 100 for this project and decided to add more pieces to it. Some things that I added that were not required by the project was key inputs from the keyboard instead of just the buttons, some minor tweaks to design, and viewport modifications.

To run this program requires JavaFX installation, this was made with Version 22 of JavaFX, though other versions may work just fine so long as they are at least 13 and above. If you have issues trying to run it, it more than likely is an environment setup issue. Try this video for setting it up properly on Eclipse: https://www.youtube.com/watch?v=nz8P528uGjk . Try this video for setting it up on VSCode: https://www.youtube.com/watch?v=AubJaosfI-0

If you're still unable to properly run the program after setting up, or you just simply want to see what it looks like, I have provided some images in the folder named: Game Images

# How it Works
If you are unfamilar with Wordle, it would be best to look up the rules as they are the same here

There are two screens in this program, one is the Game Board and the other is the Stats Screen

Numerous variables are present, they include FXML elements like buttons, ArrayLists to help aid processing inputted words and for saving previous game data, HashMaps, etc

User can type their inputted letters and backspace to remove them (enter key not useable as explained in the issues section below, user's can switch between scenes at any moment, user's can restart or reload a save at any point (save can be loaded even after program termination due to being kept in a simple formatted text file that SHOULD NOT be changed), user's stats are kept across saves and instances of a program run.

# Issues

There are two main issues that I have been unable to solve.

The first is that you can type your words, but pressing the enter key will not actually enter the word and move on. The issue comes from that JavaFX by default has the enter and space bar as sort of like button clicks, so if you clicked one of the key buttons and pressed enter or space, it would basically press that same button again. 

I was able to block the enter key from causing this and even made the program enter the typed word properly, the true issue came from whenever the user ran out of guesses or guessed the word correctly in time. Switching scenes requires an ActionEvent to be present, pressing enter gives a KeyEvent and they can not be casted to each other. My program started with only considering clicking the buttons and taking key inputs was added much later.

It is possible to make the program work to not have this issue, though it would require a lot of revamp and taking a lot of steps back. Which I unfortunately can't do due to other projects and work

The second issue was the viewport scaling, JavaFX has some CSS elements and can affect the viewport width and height. But despite my efforts, the scaling of elements became inconsistent and unsatisfactory whenever they varied from the original size. Thus, I simply locked the viewport size with no way to change it such that it would not be an issue.
