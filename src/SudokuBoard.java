import java.util.Random;

public class SudokuBoard {
    private final Cell[][] board = new Cell[9][9];

    public SudokuBoard() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                board[y][x] = new Cell();
            }
        }
    }

    public void clearBoard() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                board[y][x].setValue(0);
                board[y][x].setFixed(false);
            }
        }
    }

    public boolean isValidMove(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i].getValue() == value || board[i][col].getValue() == value) return false;
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j].getValue() == value) return false;
            }
        }
        return true;
    }

    public boolean solveRecursive(int row, int col) {
        if (row == 9) return true;
        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;

        if (board[row][col].getValue() != 0) {
            return solveRecursive(nextRow, nextCol);
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidMove(row, col, num)) {
                board[row][col].setValue(num);
                if (solveRecursive(nextRow, nextCol)) return true;
            }
            board[row][col].setValue(0);
        }
        return false;
    }

    public SudokuBoard clone() {
        SudokuBoard clone = new SudokuBoard();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                clone.board[y][x].setValue(this.board[y][x].getValue());
                clone.board[y][x].setFixed(this.board[y][x].isFixed());
            }
        }
        return clone;
    }

    public boolean hasUniqueSolution() {
        return countSolutions(0, 0, 0) == 1;
    }

    private int countSolutions(int row, int col, int count) {
        if (row == 9) return count + 1;
        if (count > 1) return count;

        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;

        if (board[row][col].getValue() != 0) {
            return countSolutions(nextRow, nextCol, count);
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidMove(row, col, num)) {
                board[row][col].setValue(num);
                count = countSolutions(nextRow, nextCol, count);
            }
            board[row][col].setValue(0);
            if (count > 1) return count;
        }
        return count;
    }

    public void generateRandomPuzzle(String difficulty) {
        clearBoard();
        solveRecursive(0, 0);

        int clues = switch (difficulty) {
            case "Fácil" -> 40;
            case "Médio" -> 30;
            case "Difícil" -> 20;
            default -> 30;
        };

        int cellsToRemove = 81 - clues;
        Random rand = new Random();

        while (cellsToRemove > 0) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if (board[y][x].getValue() != 0) {
                int backup = board[y][x].getValue();
                board[y][x].setValue(0);
                board[y][x].setFixed(false);

                SudokuBoard copy = this.clone();
                if (!copy.hasUniqueSolution()) {
                    board[y][x].setValue(backup);
                    board[y][x].setFixed(true);
                } else {
                    cellsToRemove--;
                }
            }
        }

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x].getValue() != 0) {
                    board[y][x].setFixed(true);
                }
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }
}