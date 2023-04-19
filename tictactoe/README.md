# TicTacToe

### How to build and test (from Terminal):

1. Make sure that you have Apache Ant installed. Run each ant command in the tictactoe folder, which contains the `build.xml` build file.

2. Run `ant document` to generate the javadoc (a hypertext description) for all of the java classes. Generated hypertext description will be in the `jdoc` folder. Open the `index.html` file. 

3. Run `ant compile` to compile all of the java classes. Compiled classes will be in the `bin` folder.

4. Run `ant test` to run all unit tests.

### How to run (from Terminal):

1. After building the project (i.e., running `ant`), run the following command in the tictactoe folder:
   `java -cp bin RowGameApp`

### How to clean up (from Terminal):

1. Run `ant clean` to clean the project (i.e., delete all generated files).

### Undo functionality 

1. Added UndoFunctionality class in the view. The update function in this class enables/disables undo button on the view based on the conditions mentioned below -
	
	a. Undo button will be enabled when a legal move is made.
	b. Undo button will be disabled when a new game is started, when the game is reset , when undo is used for the current move or if there is a winning or tie condition.

2. undoGame function is added in the controller, which will fetch the last moved block, reset the view and makes that block as legal move. And finally switches back the player and updates the view.

3. isThereMoveToUndo() method is implemented in the model which will return boolean based on whether there was a previous legal move made which can be undone.