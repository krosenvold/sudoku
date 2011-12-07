package models;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author Kristian Rosenvold
 */
public class SudokuServiceTest {

    private int[][] puzzle;
    private int[][] expected;

    @Before
    public void setUp() throws Exception {
        puzzle = new int[][]{
                {0,0,0, 5,8,0, 0,0,1},
                {4,0,0, 0,0,2, 0,0,0},
                {3,8,0, 0,9,0, 2,0,0},
                {0,0,7, 0,0,0, 0,6,2},
                {0,2,0, 0,4,0, 0,8,0},
                {9,5,0, 0,0,0, 3,0,0},
                {0,0,6, 0,5,0, 0,1,3},
                {0,0,0, 6,0,0, 0,0,4},
                {8,0,0, 0,2,7, 0,0,0},
        };
        expected = new int[][]{
                {7,6,2, 5,8,3, 4,9,1},
                {4,9,5, 1,7,2, 6,3,8},
                {3,8,1, 4,9,6, 2,7,5},
                {1,4,7, 8,3,9, 5,6,2},
                {6,2,3, 7,4,5, 1,8,9},
                {9,5,8, 2,6,1, 3,4,7},
                {2,7,6, 9,5,4, 8,1,3},
                {5,3,9, 6,1,8, 7,2,4},
                {8,1,4, 3,2,7, 9,5,6},
        };
    }

    @Test
    public void testSolver(){

        SudokuService sudokuService = new SudokuService(puzzle);
        final boolean solve = sudokuService.solve();
        assertTrue(solve);
        final int[][] actual = sudokuService.getmBoard();
        for (int x = 0; x < puzzle.length; x++ ){
            for (int y = 0; y < puzzle.length; y++ ){
                assertEquals( "Mismatch at [" + x +"][" + y +"]",  expected[x][y], actual[x][y]);
            }
        }

    }
}
