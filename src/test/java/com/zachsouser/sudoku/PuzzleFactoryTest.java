package com.zachsouser.sudoku;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.zachsouser.sudoku.PuzzleFactory;
import com.zachsouser.sudoku.Puzzle;

/**
 * The test class InformedSolverTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PuzzleFactoryTest
{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {

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
    public void testGetPuzzle() {
        assertTrue(PuzzleFactory.getPuzzle() instanceof Puzzle);
    }
}
