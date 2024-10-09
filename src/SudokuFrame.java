import javax.swing.*;

// A JFrame that displays a Sudoku game.
public class SudokuFrame extends JFrame {

    public SudokuFrame() {
        SudokuPanel panel = new SudokuPanel();
        add(panel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SudokuFrame();
    }
}