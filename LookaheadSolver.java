import java.util.Random;
/**
 * Solve a sudoku puzzle by backtracking when any square is out of moves
 * @author Zach Souser 
 * @version Spring 2013
 */

public class LookaheadSolver extends Solver
{
    /**
     * Generate a list of candidates for a given cell
     * 
     * @param puzzle the puzzle
     * @param row the row
     * @param col the column
     */
    
    public int[] candidates(Puzzle puzzle, int row, int col) {
        int[] tempCandidates = new int[Puzzle.SIZE];
        int ncandidates = 0;
        int[] rowData = puzzle.getRow(row);
        int[] colData = puzzle.getColumn(col);
        int[] boxData = puzzle.getBox(row,col);
        for (int i = 1; i < Puzzle.SIZE+1; i++) {
            boolean found = false;
            for (int j = 0; j < Puzzle.SIZE; j++) {
                found |= rowData[j] == i || colData[j] == i || boxData[j] == i;    
            }
            if (!found) tempCandidates[ncandidates++] = i;
        }
        int[] candidates = new int[ncandidates];
        System.arraycopy(tempCandidates,0,candidates,0,ncandidates);
        return candidates;
    }
    
    /** 
     * Get the backtrack counter
     * @return the backtrack counter
     */
    
    public int getBacktrackCounter() {
        return NUM_BACKTRACKS;
    }
    
    /**
     * Reset the backtrack counter
     */
    
    public int resetBacktrackCounter() {
        NUM_BACKTRACKS = 0;
        return 0;
    }
    
    /**
     * Determine the next cell to modify
     * 
     * @param the puzzle
     * @return the cell as {row, col}
     */
    
    public int[] nextCell(Puzzle puzzle) {
        int i = 0;
        int j = 0;
        Random r = new Random();
        int[][] values = puzzle.values();
        while (values[i][j] != Puzzle.UNKNOWN) {
            i = r.nextInt(9);
            j = r.nextInt(9);
        }
        return new int[] {i,j};
    }
    
    /**
     * Solve the sudoku puzzle
     * @param puzzle the puzzle to solve
     * @return the solved puzzle
     */
    
    public Puzzle solve(Puzzle puzzle) {
        if (puzzle.isSolved()) return puzzle;
        int[][][] allCandidates = new int[Puzzle.SIZE][Puzzle.SIZE][];
        int[] square = nextCell(puzzle);
        for (int i = 0; i < Puzzle.SIZE; i++) {
            for (int j = 0; j < Puzzle.SIZE; j++) {
                allCandidates[i][j] = candidates(puzzle,i,j);
                if (allCandidates[i][j].length == 0 && puzzle.get(i,j) == 0) {
                    NUM_BACKTRACKS++;
                    return puzzle;
                }
            }
        }
        int[] candidates = allCandidates[square[0]][square[1]];
        if (candidates.length == 0) {
            NUM_BACKTRACKS++;
            return puzzle;
        }
        for (int i = 0; i < candidates.length; i++) {
            puzzle.set(candidates[i],square[0],square[1]);
            puzzle = solve(puzzle);
            System.out.println(puzzle);
            if (puzzle.isSolved()) return puzzle;
            puzzle.set(Puzzle.UNKNOWN,square[0],square[1]);
        }
        return puzzle;
    }
}