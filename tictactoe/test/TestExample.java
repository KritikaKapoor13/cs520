import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.RowGameModel;
import model.Player;
import model.RowBlockModel;
import view.RowGameGUI;
import view.BlockIndex;
import controller.RowGameController;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private RowGameController game;

    @Before
    public void setUp() {
	game = new RowGameController();
    }

    @After
    public void tearDown() {
	game = null;
    }

    @Test
    public void testNewGame() {
        assertEquals (Player.PLAYER_1, game.gameModel.getPlayer());
        assertEquals (9, game.gameModel.movesLeft);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
	RowBlockModel block = new RowBlockModel(null);
    }

    @Test
    public void testCase1() {

    game.resetGame();
    //pre conditions
    assertEquals (9, game.gameModel.movesLeft);
    assertEquals(Player.PLAYER_1, game.gameModel.getPlayer());
    assertEquals(false, game.gameModel.isThereMoveToUndo());
    assertNull(game.gameModel.getFinalResult());
	checkInitialCondition();

    //making legal move
    game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(0));
     
    assertEquals(Player.PLAYER_2, game.gameModel.getPlayer());
    assertEquals(8, game.gameModel.movesLeft);
    assertEquals("X", game.gameModel.blocksData[0][0].getContents());
    assertEquals(false, game.gameModel.blocksData[0][0].getIsLegalMove());

    //making illegal move - clicking same block again
    game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(0));
    
    // //post conditions
    // //assertEquals(Player.PLAYER_2, game.gameModel.getPlayer());
    // assertEquals(8, game.gameModel.movesLeft);
    // assertEquals("X", game.gameModel.blocksData[0][0].getContents());
    // assertEquals(false, game.gameModel.blocksData[0][0].getIsLegalMove());
    }

    @Test
    public void testCase2() {
    game.resetGame();
    assertEquals (9, game.gameModel.movesLeft);
    assertEquals(Player.PLAYER_1, game.gameModel.getPlayer());
    assertEquals(false, game.gameModel.isThereMoveToUndo());
    assertNull(game.gameModel.getFinalResult());
	checkInitialCondition();
	
    //method under test
    game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(0));
        
	//post-conditions
	assertEquals(Player.PLAYER_2, game.gameModel.getPlayer());
	assertEquals(8, game.gameModel.movesLeft);
	assertEquals("X", game.gameModel.blocksData[0][0].getContents());
	assertEquals(false, game.gameModel.blocksData[0][0].getIsLegalMove());
    }

    public void checkInitialCondition()
    {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                assertEquals("", game.gameModel.blocksData[row][column].getContents());
                assertEquals(true, game.gameModel.blocksData[row][column].getIsLegalMove());
                //assertTrue("all buttons are enabled initially", game.gameView.gameBoardView().isBlockEnabled(new BlockIndex(row, column)));
            }
        }
    }

    
    @Test
    public void testWinningCondition() {
        //pre-condtion undo should be disabled and getFinalResult must be null 
        assertEquals (9, game.gameModel.movesLeft);
        assertEquals(Player.PLAYER_1, game.gameModel.getPlayer());
        assertEquals(false, game.gameModel.isThereMoveToUndo());
        assertNull(game.gameModel.getFinalResult());

        //Make moves to win
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(0));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(8));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(1));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(7));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(2));

        //post condition - checking whether player 1 won by validating movesLeft, getFinalResult and undo disbaled
        assertEquals(4, game.gameModel.movesLeft);
        assertEquals("Player 1 wins!", game.gameModel.getFinalResult());
        assertEquals(false, game.gameModel.isThereMoveToUndo());
    }

    @Test
    public void testTieCondition(){
        //pre-condtion undo should be disabled and getFinalResult must be null 
        assertEquals (9, game.gameModel.movesLeft);
        assertEquals(Player.PLAYER_1, game.gameModel.getPlayer());
        assertEquals(false, game.gameModel.isThereMoveToUndo());
        assertNull(game.gameModel.getFinalResult());

        //Make moves for tie condition
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(0));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(4));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(3));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(6));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(5));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(1));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(7));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(5));
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(2));

        //post condition - To check for tie condition
        assertEquals(0,game.gameModel.movesLeft);
        assertEquals(RowGameModel.GAME_END_NOWINNER, game.gameModel.getFinalResult());
        assertEquals(false, game.gameModel.isThereMoveToUndo());
    }


    
    @Test
    public void testCase5() {
        //pre-conditions 
        assertEquals (9, game.gameModel.movesLeft);
        assertEquals (Player.PLAYER_1, game.gameModel.getPlayer());
        //assertEquals (false, game.gameModel.isThereMoveToUndo());
        checkInitialCondition();
        assertNull("final result is null at the beginning of the game", game.gameModel.getFinalResult() );

    //     //method under test
    //     game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(0));
    //     game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(1));
    //     game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(2));
    //     game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(3));
        
    //     game.resetGame();

        //post-condition
        assertEquals (9, game.gameModel.movesLeft);
        assertEquals (Player.PLAYER_1, game.gameModel.getPlayer());
        //assertEquals (false, game.gameModel.isThereMoveToUndo());
        checkInitialCondition();
        assertNull("final result is null atfter game is reset", game.gameModel.getFinalResult() );

    }
    

    @Test
    public void testCase6() {

        //pre-conditions 
        assertEquals (9, game.gameModel.movesLeft);

        //post-condition
        assertEquals (false, game.gameModel.isThereMoveToUndo());
    }
    
    @Test
    public void testCase7() {

        //pre-conditions 
        assertEquals (9, game.gameModel.movesLeft);
        assertEquals(Player.PLAYER_1, game.gameModel.getPlayer());
        assertEquals (false, game.gameModel.isThereMoveToUndo());
        game.move((JButton) ((JPanel)((JPanel)game.gameView.gui.getContentPane().getComponent(0)).getComponent(0)).getComponent(0));
        assertEquals (8, game.gameModel.movesLeft);
        assertEquals (false, game.gameModel.blocksData[0][0].getIsLegalMove());
        assertEquals (true, game.gameModel.isThereMoveToUndo());
        
        //method under test
        game.undoGame();

        //post-conditions
        assertEquals (9, game.gameModel.movesLeft);
        assertEquals (true, game.gameModel.blocksData[0][0].getIsLegalMove());
        assertEquals (false, game.gameModel.isThereMoveToUndo());
    }
    
}
