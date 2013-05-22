

/**
 * Write a description of class PlotSolverTests here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlotSolverTests
{
    public static void main(String[] args) {
        NaiveSolver n = new NaiveSolver();
        LookaheadSolver l = new LookaheadSolver();
        InformedSolver in = new InformedSolver();
        
        Puzzle easyPuzzle = new Puzzle(new int[][] {
            {2,0,5,0,4,7,0,3,1},
            {0,0,3,0,0,6,0,0,0},
            {0,0,0,0,0,1,8,0,0},
            {5,9,0,7,0,8,3,0,0},
            {0,1,0,0,2,0,0,8,0},
            {0,0,6,4,0,5,0,2,9},
            {0,0,9,5,0,0,0,0,0},
            {0,0,0,1,0,0,5,0,0},
            {7,5,0,8,6,0,4,0,3}
        });
        
        Puzzle mediumPuzzle = new Puzzle(new int[][] {
            {6,9,0,1,5,0,0,0,7},
            {0,0,0,0,9,0,0,0,1},
            {0,0,5,6,0,0,4,2,0},
            {0,8,0,0,0,0,1,0,2},
            {0,4,0,0,0,0,0,5,0},
            {3,0,2,0,0,0,0,4,0},
            {0,5,8,0,0,6,7,0,0},
            {4,0,0,0,8,0,0,0,0},
            {2,0,0,0,7,4,0,9,5}
        });
        Puzzle hardPuzzle = new Puzzle(new int[][] {
            {0,0,0,7,1,0,0,8,0},
            {0,0,0,0,0,8,0,7,9},
            {0,0,7,0,0,9,0,5,0},
            {3,2,0,0,0,0,7,0,0},
            {0,1,8,0,0,0,3,4,0},
            {0,0,5,0,0,0,0,2,1},
            {0,6,0,3,0,0,9,0,0},
            {2,7,0,6,0,0,0,0,0},
            {0,8,0,0,4,7,0,0,0}
        });
        
        Puzzle evilPuzzle = new Puzzle(new int[][] {
            {9,0,0,7,0,0,4,0,0},
            {0,0,0,0,0,0,0,0,9},
            {0,3,0,9,0,4,0,2,1},
            {0,0,5,2,0,0,0,0,0},
            {7,0,0,0,9,0,0,0,8},
            {0,0,0,0,0,8,5,0,0},
            {1,9,0,4,0,6,0,7,0},
            {4,0,0,0,0,0,0,0,0},
            {0,0,6,0,0,5,0,0,3}
        });
        
        test(new Puzzle(easyPuzzle),n,"naive","easy");
        test(new Puzzle(easyPuzzle),l,"lookahead","easy");
        test(new Puzzle(easyPuzzle),in,"informed","easy");
        test(new Puzzle(mediumPuzzle),n,"naive","medium");
        test(new Puzzle(mediumPuzzle),l,"lookahead","medium");
        test(new Puzzle(mediumPuzzle),in,"informed","medium");
        //test(hardPuzzle,n,"naive","hard");
        test(new Puzzle(hardPuzzle),l,"lookahead","hard");
        test(new Puzzle(hardPuzzle),in,"informed","hard");
        test(new Puzzle(evilPuzzle),l,"lookahead","evil");
        test(new Puzzle(evilPuzzle),in,"informed","evil");
        
    }
    
    private static void test(Puzzle p, Solver s, String puzzType, String difficulty) {
        p.reset();
        s.solve(p);
        int backtracks = s.getBacktrackCounter();
        p.reset();
        long time = s.time(p);
        s.resetBacktrackCounter();
        System.out.println(difficulty + " -- " + puzzType + " - " + (time / 1000L) + "ms - " + backtracks);
    }
}
