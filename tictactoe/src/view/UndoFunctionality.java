package view;

import model.RowGameModel;
import controller.RowGameController;

import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * Class to implement undo functionality
 */
public class UndoFunctionality implements View
{
    public JButton undo = new JButton("Undo");

    public UndoFunctionality(JPanel options, RowGameController controller) {
	super();

	options.add(undo);
    
    /**
     * Adding action listener to undo button
    */
    undo.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        controller.undoGame();
        }
    });
    }

    /**
     * Undo should be enabled only when there are moves left to play
     */
    public void update(RowGameModel model) {
        if(!model.isThereMoveToUndo() || model.getFinalResult()!=null) {
            this.undo.setEnabled(false);
            model.lastMovedBlock[0] = -1;
		    model.lastMovedBlock[1] = -1;
        } else {
            this.undo.setEnabled(true);
        }
    }
}
