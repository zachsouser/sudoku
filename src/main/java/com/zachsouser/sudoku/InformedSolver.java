package com.zachsouser.sudoku;

/**
 * Solver that backtracks when the most constrained square has no options.
 * Otherwise, chooses the most constrained square
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InformedSolver extends Solver
{
		public InformedSolver() {
			super();
		}

		public InformedSolver(Display display) {
				super(display);
		}

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
        int[][][] allCandidates = new int[Puzzle.SIZE][Puzzle.SIZE][];
        int minSize = 10;
        int mini = 0, minj = 0;
        for (int i = 0; i < Puzzle.SIZE; i++) {
            for (int j = 0; j < Puzzle.SIZE; j++) {
                allCandidates[i][j] = candidates(puzzle,i,j);
                if (allCandidates[i][j].length == 0 && puzzle.get(i,j) == Puzzle.UNKNOWN) {
                    return new int[] {-1,-1};
                }
                if (puzzle.get(i,j) == 0 && allCandidates[i][j].length < minSize) {
                    mini = i;
                    minj = j;
                    minSize = allCandidates[i][j].length;
                }
            }
        }
        return new int[] {mini,minj};
    }

    /**
     * Solve the sudoku puzzle
     * @param puzzle the puzzle to solve
     * @return the solved puzzle
     */

    public Puzzle solve(Puzzle puzzle) {
				this.display(puzzle);
        if (puzzle.isSolved()) return puzzle;
        int[] square = nextCell(puzzle);
        if (square[0] == -1 || square[1] == -1) {
            NUM_BACKTRACKS++;
            return puzzle;
        }
        int[] candidates = candidates(puzzle,square[0],square[1]);
        for (int i = 0; i < candidates.length; i++) {
            puzzle.set(candidates[i],square[0],square[1]);
            puzzle = solve(puzzle);
            if (puzzle.isSolved()) return puzzle;
            puzzle.set(Puzzle.UNKNOWN,square[0],square[1]);
        }
        return puzzle;
    }
}
