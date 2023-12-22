package chess.gui;

import javax.swing.*;
import java.awt.*;

public class SwingGUI extends JFrame {

    private JPanel chessBoardPanel;

    public SwingGUI() {
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chessBoardPanel = new JPanel(new GridLayout(8, 8));
        InitChessBoard();

        add(chessBoardPanel);
        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private void InitChessBoard() {
        // Create and add buttons to represent chessboard squares
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                JButton square = new JButton();
                square.setPreferredSize(new Dimension(60, 60));
                chessBoardPanel.add(square);
            }
        }
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwingGUI().setVisible(true);
        });
    }
}
