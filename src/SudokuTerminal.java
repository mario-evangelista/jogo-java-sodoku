public class SudokuTerminal {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();

        for (String arg : args) {
            String[] parts = arg.split(";");
            String[] pos = parts[0].split(",");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            int value = Integer.parseInt(parts[1]);
            boolean fixed = Boolean.parseBoolean(parts[2]);
            board.getBoard()[y][x].setValue(value);
            board.getBoard()[y][x].setFixed(fixed);
        }

        if (board.solveRecursive(0, 0)) {
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    System.out.print(board.getBoard()[y][x].getValue() + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Não foi possível resolver.");
        }
    }
}