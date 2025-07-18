import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SudokuGUI extends JFrame {
    private final JTextField[][] cells = new JTextField[9][9];
    private final SudokuBoard board = new SudokuBoard();
    private final JComboBox<String> difficultySelector = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});

    public SudokuGUI() {
        setTitle("Sudoku Game");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                JTextField cell = new JTextField();
                cells[y][x] = cell;
                cell.setHorizontalAlignment(JTextField.CENTER);
                gridPanel.add(cell);
            }
        }

        JPanel controlPanel = new JPanel();
        JButton generateButton = new JButton("Gerar Jogo");
        JButton solveButton = new JButton("Resolver");

        generateButton.addActionListener(this::generatePuzzle);
        solveButton.addActionListener(this::solvePuzzle);

        controlPanel.add(difficultySelector);
        controlPanel.add(generateButton);
        controlPanel.add(solveButton);

        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void generatePuzzle(ActionEvent e) {
        String level = (String) difficultySelector.getSelectedItem();
        board.generateRandomPuzzle(level);
        refreshUI();
    }

    private void solvePuzzle(ActionEvent e) {
        readBoardFromUI();
        if (board.solveRecursive(0, 0)) {
            refreshUI();
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível resolver.");
        }
    }

    private void readBoardFromUI() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                String text = cells[y][x].getText();
                int val = 0;
                try {
                    val = Integer.parseInt(text);
                } catch (Exception ignored) {}
                board.getBoard()[y][x].setValue(val);
                board.getBoard()[y][x].setFixed(val != 0);
            }
        }
    }

    private void refreshUI() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int val = board.getBoard()[y][x].getValue();
                JTextField field = cells[y][x];
                field.setText(val == 0 ? "" : String.valueOf(val));
                field.setEditable(!board.getBoard()[y][x].isFixed());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGUI().setVisible(true));
    }
}