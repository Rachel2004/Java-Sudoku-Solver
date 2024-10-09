class SudokuSolver {

    //Checks if it is safe to place a number in a given position on the board.
    public boolean isSafe(char[][] board, int row, int col, int number) {
        // Check if the number already exists in the row or column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0')) {
                return false; // Number already exists in column
            }
            if (board[row][i] == (char) (number + '0')) {
                return false; // Number already exists in row
            }
        }

        // Check if the number already exists in the 3x3 grid
        int startingRow = (row / 3) * 3;
        int startingCol = (col / 3) * 3;
        for (int i = startingRow; i < startingRow + 3; i++) {
            for (int j = startingCol; j < startingCol + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false; // Number already exists in grid
                }
            }
        }

        return true; // Number is safe to place
    }

    public boolean helper(char[][] board, int row, int col) {
        // Base case: if we've reached the end of the board, return true
        if (row == board.length) {
            return true;
        }

        // Calculate the next row and column indices
        int newRow;
        int newCol;
        if (col != board[0].length - 1) {
            newRow = row;
            newCol = col + 1;
        } else {
            newRow = row + 1;
            newCol = 0;
        }

        // If the current cell is not empty, move to the next cell
        if (board[row][col] != '.') {
            return helper(board, newRow, newCol);
        } else {
            // Try numbers 1-9 in the current cell
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0');

                    // Recursively try to solve the rest of the puzzle
                    if (helper(board, newRow, newCol)) {
                        return true; // Puzzle is solved
                    } else {
                        // If the puzzle can't be solved with this number, reset the cell
                        board[row][col] = '.';
                    }
                }
            }
        }

        return false; // Puzzle can't be solved
    }

    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }
}