package controllers;

import models.SudokuService;
import play.mvc.Controller;
import play.mvc.Scope;

/**
 * @author Kristian Rosenvold
 */
public class Sudoku extends Controller  {

    public static void index() {
      int dim = 9;
      final int[][] puzzleFromParams = createPuzzleFromParams(dim, params);
      SudokuService solver = new SudokuService(puzzleFromParams);
      if (!isAllNull(puzzleFromParams)) solver.solve();
      final int[][] board = solver.getBoard();
      render(board);
  }
    
   private static int[][] createPuzzleFromParams(int dim, Scope.Params params1){
       int[][] puzzle = new int[dim][dim];
       for (int x= 0; x < dim; x++){
           for (int y= 0; y < dim; y++){
               
               String paramName = x + "x" + y;
               String s = params1.get(paramName);
               if (s == null || s.trim().length() == 0)  s = "0";
               puzzle[ x][y] = Integer.valueOf( s);
           }
       }
       return puzzle;
   }

    private static boolean isAllNull(int[][] puzzle){
        for (int x= 0; x < puzzle.length; x++){
            for (int y= 0; y < puzzle.length; y++){
                if (puzzle[x][y] != 0) return false;
            }
        }
        return true;
    }
}
