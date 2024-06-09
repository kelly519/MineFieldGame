package controller;

import model.Button;
import model.MineField;
import view.MineFieldView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineFieldController extends MouseAdapter {
    private MineField model;
    private MineFieldView view;

    public MineFieldController(MineField model, MineFieldView view) {
        this.model = model;
        this.view = view;
        Button[][] board = model.getBoard();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col].addMouseListener(this);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Button b = (Button) e.getComponent();
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (b.isMine()) {
                revealMines();
                disableAllButtons();
                view.showMessage("GAME OVER!");
                System.exit(0);
            } else {
                open(b.getRow(), b.getCol());
                if (model.isWin()) {
                    revealMines();
                    disableAllButtons();
                    view.showMessage("Congratulations, you won the game!");
                    System.exit(0);
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            toggleFlag(b);
        }
        view.updateBoard();
    }

    private void open(int r, int c) {
        Button[][] board = model.getBoard();
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || !board[r][c].isEnabled()) {
            return;
        } else if (board[r][c].getCount() != 0) {
            board[r][c].setText(board[r][c].getCount() + "");
            board[r][c].setEnabled(false);
            model.incrementOpenButtonCount();
        } else {
            model.incrementOpenButtonCount();
            board[r][c].setEnabled(false);
            open(r - 1, c);
            open(r + 1, c);
            open(r, c - 1);
            open(r, c + 1);
        }
    }

    private void revealMines() {
        Button[][] board = model.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    board[row][col].setIcon(new ImageIcon("mine.png"));
                }
            }
        }
    }

    private void disableAllButtons() {
        Button[][] board = model.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col].setEnabled(false);
            }
        }
    }

    private void toggleFlag(Button b) {
        if (!b.isFlag()) {
            b.setIcon(new ImageIcon("flag.png"));
            b.setFlag(true);
        } else {
            b.setIcon(null);
            b.setFlag(false);
        }
    }
}
