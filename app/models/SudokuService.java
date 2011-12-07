package models;

public class SudokuService {

    private final int mBoard[][];
    private final int mBoardSize;
    private final int mBoxSize;
    private final boolean mRowSubset[][];
    private final boolean mColSubset[][];
    private final boolean mBoxSubset[][];

    public SudokuService(int board[][]) {
        mBoard = board;
        mBoardSize = mBoard.length;
        mBoxSize = (int) Math.sqrt(mBoardSize);
        mRowSubset = new boolean[mBoardSize][mBoardSize];
        mColSubset = new boolean[mBoardSize][mBoardSize];
        mBoxSubset = new boolean[mBoardSize][mBoardSize];
        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard.length; j++) {
                int value = mBoard[i][j];
                if (value != 0) {
                    setSubsetValue(i, j, value, true);
                }
            }

        }
    }

    private void setSubsetValue(int i, int j, int value, boolean present) {
        mRowSubset[i][value - 1] = present;
        mColSubset[j][value - 1] = present;
        mBoxSubset[computeBoxNo(i, j)][value - 1] = present;
    }

    public boolean solve() {
        return solve(0, 0);
    }

    public boolean solve(int i, int j) {
        if (i == mBoardSize) {
            i = 0;
            if (++j == mBoardSize) {
                return true;
            }
        }
        if (mBoard[i][j] != 0) {
            return solve(i + 1, j);
        }
        for (int value = 1; value <= mBoardSize; value++) {
            if (isValid(i, j, value)) {
                mBoard[i][j] = value;
                setSubsetValue(i, j, value, true);
                if (solve(i + 1, j)) {
                    return true;
                }
                setSubsetValue(i, j, value, false);
            }
        }

        mBoard[i][j] = 0;
        return false;
    }

    private boolean isValid(int i, int j, int val) {
        val--;
        boolean
                isPresent =
                mRowSubset[i][val] || mColSubset[j][val] || mBoxSubset[computeBoxNo(i, j)][val];
        return !isPresent;
    }

    private int computeBoxNo(int i, int j) {
        int boxRow = i / mBoxSize;
        int boxCol = j / mBoxSize;
        return boxRow * mBoxSize + boxCol;
    }

    public int[][] getBoard() {
        return mBoard;
    }

}
