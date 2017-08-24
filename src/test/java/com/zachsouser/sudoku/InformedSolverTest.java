package com.zachsouser.sudoku;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.zachsouser.sudoku.Puzzle;

/**
 * The test class InformedSolverTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class InformedSolverTest
{
    Puzzle p1, pEasy,pMedium,pHard,pEvil, psolved;
    int[][] b1,b2,bsolved;
    boolean[][] g1;
    InformedSolver n;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {

        b1 = new int[][] {
            {0,3,7,2,0,0,1,0,6},
            {0,0,0,1,0,0,0,3,4},
            {1,5,0,0,3,0,0,0,0},
            {6,0,2,8,0,3,0,4,0},
            {5,8,0,0,0,0,0,7,1},
            {0,1,0,4,0,7,8,0,2},
            {0,0,0,0,4,0,0,8,9},
            {2,9,0,0,0,8,0,0,0},
            {8,0,3,0,0,9,6,1,0}
        };

        bsolved = new int[][] {
            {4,3,7,2,8,5,1,9,6},
            {9,2,8,1,7,6,5,3,4},
            {1,5,6,9,3,4,7,2,8},
            {6,7,2,8,1,3,9,4,5},
            {5,8,4,6,9,2,3,7,1},
            {3,1,9,4,5,7,8,6,2},
            {7,6,5,3,4,1,2,8,9},
            {2,9,1,7,6,8,4,5,3},
            {8,4,3,5,2,9,6,1,7}
        };

        g1 = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j< 9; j++) {
                if (b1[i][j] == 0) g1[i][j] = false;
            }
        }

        p1 = new Puzzle(b1,g1);
        psolved = new Puzzle(bsolved,g1);



        n = new InformedSolver();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void solve() {
        assertEquals(psolved,n.solve(p1));
    }

    @Test
    public void candidates() {
        int[] candidates = n.candidates(p1,0,0);
        assertEquals(candidates.length,2);
        assertEquals(candidates[0],4);
        assertEquals(candidates[1],9);
    }

    @Test
    public void backtrackCounter() {
        n.solve(p1);
        // No backtracking done sometimes!
        // assert(n.getBacktrackCounter() > 0);
        n.resetBacktrackCounter();
        assertEquals(n.getBacktrackCounter(), 0);
    }
}
