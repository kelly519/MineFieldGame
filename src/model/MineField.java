package model;

public class MineField {
    private Button[][] board;
    private int openButton;

    public MineField() {
        board = new Button[10][10];
        openButton = 0;
        initializeBoard();
        generateMines();
        updateCount();
    }

    private void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Button(row, col);
            }
        }
    }

    private void generateMines() {
        int i = 0;
        while (i < 10) {
            int randRow = (int) (Math.random() * board.length);
            int randCol = (int) (Math.random() * board[0].length);

            while (board[randRow][randCol].isMine()) {
                randRow = (int) (Math.random() * board.length);
                randCol = (int) (Math.random() * board[0].length);
            }
            board[randRow][randCol].setMine(true);
            i++;
        }
    }

    private void updateCount() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    counting(row, col);
                }
            }
        }
    }

    private void counting(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int k = col - 1; k <= col + 1; k++) {
                try {
                    int value = board[i][k].getCount();
                    board[i][k].setCount(++value);
                } catch (Exception e) {
                    //
                }
            }
        }
    }

    public Button[][] getBoard() {
        return board;
    }

    public int getOpenButtonCount() {
        return openButton;
    }

    public void incrementOpenButtonCount() {
        openButton++;
    }

    public boolean isWin() {
        return openButton == (board.length * board[0].length) - 10;
    }
}
