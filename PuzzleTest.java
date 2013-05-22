import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PuzzleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PuzzleTest
{
    
    Puzzle p1,psolved;
    int[][] b1,bsolved;
    boolean[][] g1;
    
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
        
            
        g1 = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j< 9; j++) {
                if (b1[i][j] == 0) g1[i][j] = false;
            }
        }
        
        bsolved = new int[][] {
            {2,4,8,3,9,5,7,1,6},
            {5,7,1,6,2,8,3,4,9},
            {9,3,6,7,4,1,5,8,2},
            {6,8,2,5,3,9,1,7,4},
            {3,5,9,1,7,4,6,2,8},
            {7,1,4,8,6,2,9,5,3},
            {8,6,3,4,1,7,2,9,5},
            {1,9,5,2,8,6,4,3,7},
            {4,2,7,9,5,3,8,6,1}
        };
        
        p1 = new Puzzle(b1,g1);
        psolved = new Puzzle(bsolved);
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
    
    /**
     * Test copy constructor
     */
    
    @Test 
    public void copyConstructor() {
         Puzzle puzz = new Puzzle(p1);
         assertEquals(puzz,p1);
    }
    
    @Test 
    public void getBox() {
        int[][] expected = new int[][] {
            {0,3,7,0,0,0,1,5,0},
            {2,0,0,1,0,0,0,3,0},
            {1,0,6,0,3,4,0,0,0},
            {6,0,2,5,8,0,0,1,0},
            {8,0,3,0,0,0,4,0,7},
            {0,4,0,0,7,1,8,0,2},
            {0,0,0,2,9,0,8,0,3},
            {0,4,0,0,0,8,0,0,9},
            {0,8,9,0,0,0,6,1,0}
        };
        for (int i = 0; i < expected.length; i++) {
            int[] result = p1.getBox(i);
            for (int j = 0; j < result.length; j++) {
                assertEquals(expected[i][j],result[j]);
            }
        }
    }
    
    @Test
    public void getColumn() {
        int[][] expected = new int[][] {
            {0,0,1,6,5,0,0,2,8},
            {3,0,5,0,8,1,0,9,0},
            {7,0,0,2,0,0,0,0,3},
            {2,1,0,8,0,4,0,0,0},
            {0,0,3,0,0,0,4,0,0},
            {0,0,0,3,0,7,0,8,9},
            {1,0,0,0,0,8,0,0,6},
            {0,3,0,4,7,0,8,0,1},
            {6,4,0,0,1,2,9,0,0}
        };
        
        for (int i = 0; i < expected.length; i++) {
            int[] result = p1.getColumn(i);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j],result[j]);
            }
        }
    }
    
    @Test
    public void getRow() {
        for (int i = 0; i < b1.length; i++) {
            int[] row = p1.getRow(i);
            for (int j = 0; j < b1[i].length; j++) {
                assertEquals(b1[i][j],row[j]);
            }
        }
    }
    
    @Test
    public void isSolved() {
        System.out.println(psolved);
        assert(psolved.isSolved());
        assertFalse(p1.isSolved());
    }
    
    @Test
    public void noArgConstructor() {
        Puzzle p = new Puzzle();
        int[][] values = p.values();
        boolean[][] givens = p.givens();
        for (int i = 0; i < Puzzle.SIZE; i++) {
            for (int j = 0; j < Puzzle.SIZE; j++) {
                assert(values[i][j] == 0);
                assert(givens[i][j] == false);
            }
        }
    }
    
    @Test
    public void numberOfUnknowns() {
        assertEquals(0,psolved.numberOfUnknowns());
        assertEquals(45,p1.numberOfUnknowns());
    }
    
    @Test
    public void oneParamConstructor() {
        Puzzle p = new Puzzle(bsolved);
        assertEquals(p,psolved);
    }
    
    @Test
    public void reset() {
        Puzzle test1 = new Puzzle(b1,g1);
        Puzzle test2 = new Puzzle(b1,g1);
        test1.set(9,0,1);
        System.out.println("\n\n-- " + test1.get(0,1) + " - " + test2.get(0,1));
        
        assertFalse(test1.equals(test2));

        test1.reset();
        assertEquals(test1,test2);
    }
    
    @Test
    public void saveRetrieveTest() {
        Puzzle.savePuzzle("test.ser",p1);
        assertEquals(p1,Puzzle.retrievePuzzle("test.ser"));
    }
    
    @Test
    public void set() {
        p1.set(3,0,1);
        p1.set(true,0,1);
        assertEquals(p1.get(0,1),3);
        assert(p1.isGiven(0,1));
    }
    
    @Test   
    public void toStringTest() {
        System.out.println(p1);
    }
    
    @Test
    public void twoParamConstructor() {
        assertEquals(p1,new Puzzle(b1,g1));
    }
    
    
    
}
