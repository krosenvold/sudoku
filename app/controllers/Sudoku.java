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
      solver.solve();
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
               System.out.println("s = " + s);
               puzzle[ x][y] = Integer.valueOf( s);
           }
       }
       return puzzle;
   }
}
