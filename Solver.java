
/**
 * Abstract sudoku solver
 * @author Zach Souser
 * @version Spring 2013
 */
public abstract class Solver
{
    // instance variables - replace the example below with your own
    protected int NUM_BACKTRACKS;
    

    
    public abstract int[] candidates(Puzzle puzzle, int row, int col);
    public abstract int getBacktrackCounter();
    public abstract int[] nextCell(Puzzle puzzle);
    public abstract int resetBacktrackCounter();
    public abstract Puzzle solve(Puzzle puzzle);
    
    /**
     * Time the solve function
     * @param the puzzle
     * @return the time elapsed
     */
    public long time(Puzzle puzzle) {
        long start = System.nanoTime();
        solve(puzzle);
        return System.nanoTime() - start;
    }
}
