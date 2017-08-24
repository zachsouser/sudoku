package com.zachsouser.sudoku;

/**
 * Abstract sudoku solver
 * @author Zach Souser
 * @version Spring 2013
 */
public abstract class Solver
{
  protected int NUM_BACKTRACKS;

		protected Display displayer;

		public Solver(Display display) {
			displayer = display;
		}

		public Solver() {
			displayer = null;
		}

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

		/**
		 * If there is a display, use it
		 */
		public void display(Puzzle puzzle) {
			if (displayer != null) {
				displayer.displayPuzzle(puzzle);
			} else {
				System.out.println(puzzle);
			}
		}
}
