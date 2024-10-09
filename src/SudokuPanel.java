import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SudokuPanel extends JPanel {
    private final char[][][] originalBoards;   // The original Sudoku boards, used to reset the board to its initial state.
    private final char[][] board;   // The current state of the Sudoku board.
    private final JTextField[][] fields;    // A 2D array of text fields, where each field represents a cell in the Sudoku board.
    private int currentBoardIndex; // The index of the current board being displayed

    public SudokuPanel() {
        // Initialize the original Sudoku boards.
        originalBoards = new char[][][] {
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                },
                {
                        {'.', '.', '.', '2', '6', '.', '7', '.', '1'},
                        {'6', '8', '.', '.', '7', '.', '.', '9', '.'},
                        {'1', '9', '.', '.', '.', '4', '5', '.', '.'},
                        {'8', '2', '.', '1', '.', '.', '.', '4', '.'},
                        {'.', '.', '4', '6', '.', '2', '9', '.', '.'},
                        {'.', '5', '.', '.', '.', '3', '.', '2', '8'},
                        {'.', '.', '9', '3', '.', '.', '.', '7', '4'},
                        {'.', '4', '.', '.', '5', '.', '.', '3', '6'},
                        {'7', '.', '3', '.', '1', '8', '.', '.', '.'}
                }
        };

        // Initialize the current state of the Sudoku board by copying the first original board.
        board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(originalBoards[0][i], 0, board[i], 0, 9);
        }
        fields = new JTextField[9][9];
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        // Create a 3x3 grid of sub-grids, where each sub-grid represents a 3x3 region of the Sudoku board.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel subGrid = new JPanel(new GridLayout(3, 3));
                subGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int row = i * 3 + k;
                        int col = j * 3 + l;
                        fields[row][col] = new JTextField(2);
                        fields[row][col].setText(String.valueOf(board[row][col]));
                        fields[row][col].setHorizontalAlignment(JTextField.CENTER);
                        fields[row][col].setEditable(false);
                        subGrid.add(fields[row][col]);
                    }
                }
                boardPanel.add(subGrid);
            }
        }

        JButton button1 = new JButton("1");
        button1.addActionListener(new BoardActionListener(0));

        JButton button2 = new JButton("2");
        button2.addActionListener(new BoardActionListener(1));

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolveActionListener());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetActionListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);

        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        currentBoardIndex = 0;
    }

    private class BoardActionListener implements ActionListener {
        private final int boardIndex;

        public BoardActionListener(int boardIndex) {
            this.boardIndex = boardIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currentBoardIndex = boardIndex;
            for (int i = 0; i < 9; i++) {
                System.arraycopy(originalBoards[boardIndex][i], 0, board[i], 0, 9);
                for (int j = 0; j < 9; j++) {
                    fields[i][j].setText(String.valueOf(board[i][j]));
                }
            }
        }
    }

    private class SolveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SudokuSolver solver = new SudokuSolver();
            solver.solveSudoku(board);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    fields[i][j].setText(String.valueOf(board[i][j]));
                }
            }
        }
    }

    private class ResetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 9; i++) {
                System.arraycopy(originalBoards[currentBoardIndex][i], 0, board[i], 0, 9);
                for (int j = 0; j < 9; j++) {
                    fields[i][j].setText(String.valueOf(board[i][j]));
                }
            }
        }
    }
}