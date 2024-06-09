package view;

import model.Button;

import javax.swing.*;
import java.awt.*;

public class MineFieldView {
    private JFrame frame;
    private JPanel panel;
    private model.Button[][] board;

    public MineFieldView(Button[][] board) {
        this.board = board;
        frame = new JFrame("model.MineField");
        panel = new JPanel(new GridLayout(10, 10));
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        initializeBoard();
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                panel.add(board[row][col]);
            }
        }
    }

    public void updateBoard() {
        panel.revalidate();
        panel.repaint();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
