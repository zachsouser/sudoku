import java.io.*;
import java.util.Arrays;
/**
 * Puzzle data structure
 * 
 * @author Zach Souser
 * @version Spring 2013
 */
public class Puzzle implements Serializable
{
    
    /** Size of the puzzle **/
    
    public static int SIZE = 9;
    
    /** Unknown value **/
    
    public static int UNKNOWN = 0;
    
    /** Serializing ID **/
    
    static final long serialVersionUID = 74132;
    
    /** Board and initial board **/
    
    private int[][] board, initialBoard;
    
    /** Givens **/
    
    private boolean[][] givens;
    
    /**
     * Constructor for objects of class Puzzle
     */
    public Puzzle() {
        this.board = new int[SIZE][SIZE];
        this.initialBoard = new int[SIZE][SIZE];
        this.givens = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
             for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = UNKNOWN;
                this.initialBoard[i][j] = UNKNOWN;
                this.givens[i][j] = false;
            }
        }
    }
    
    /**
     * One-arg constructor. Automatically populates givens
     * 
     * @param the initial array
     */
    
    public Puzzle(int[][] init) {
        board = init;
        initialBoard = init;
        givens = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = init[i][j];
                initialBoard[i][j] = init[i][j];
                if (board[i][j] != UNKNOWN) givens[i][j] = true;
            }
        }
    }
    
    /**
     * Two-arg constructor
     * @param the initial board
     * @param the initial givens
     */
    public Puzzle(int[][] vals, boolean[][] givens) {
        this.initialBoard = new int[SIZE][SIZE];
        this.board = new int[SIZE][SIZE];
        this.givens = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.initialBoard[i][j] = vals[i][j];
                this.board[i][j] = vals[i][j];
                this.givens[i][j] = givens[i][j];
            }
        }
    }
    
    /**
     * One-param copy constructor
     * 
     * @param p the source puzzle;
     */
    
    public Puzzle(Puzzle p) {
        this.initialBoard = new int[SIZE][SIZE];
        this.board = new int[SIZE][SIZE];
        this.givens = new boolean[SIZE][SIZE];
        int[][] vals = p.values();
        boolean[][] givs = p.givens();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.initialBoard[i][j] = vals[i][j];
                this.board[i][j] = vals[i][j];
                this.givens[i][j] = givs[i][j];
            }
        }
    }
    
    /**
     * Equals implementation
     * @param the object
     * @return if equal
     */
    
    public boolean equals(Object obj) {
        boolean equal = true;
        int[][] values = ((Puzzle)obj).values();
        boolean[][] theirGivens = ((Puzzle)obj).givens();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                equal &= (values[i][j] == board[i][j]);
                equal &= (theirGivens[i][j] == givens[i][j]);
            }
        }
        return equal;
    }
    
    /** 
     * Get the value of a cell
     * @param row
     * @param column
     * @return the cell value
     */
    public int get(int row, int column) {
        return board[row][column];
    }
    
    /**
     * Get the box at a given index
     * @param row
     * @param column
     * @return the box
     */
    
    public int[] getBox(int box) {
        int[] retVal = new int[SIZE];
        int k = 0;
        int row = box % 3;
        int col = (box - row) / 3;
        
        for (int i = col * 3; i < col * 3 + 3; i++) {
            for (int j = row * 3; j < row * 3 + 3; j++) {
                retVal[k++] = board[i][j];
            }
        }
        return retVal;
    }
    
    /**
     * Get the box at a given index
     * @param row
     * @param column
     * @return the box
     */
    
    public int[] getBox(int row, int column) {
        return getBox(getBoxIndex(row,column));
    }
    
    /**
     * Get the box index of a given row, column
     * 
     * @param row
     * @param columm
     * @return box index
     */
    public int getBoxIndex(int row, int column) {
        if (column < 3 && row < 3) return 0;
        if (column < 6 && row < 3) return 1;
        if (column < 9 && row < 3) return 2;
        if (column < 3 && row < 6) return 3;
        if (column < 6 && row < 6) return 4;
        if (column < 9 && row < 6) return 5;
        if (column < 3 && row < 9) return 6;
        if (column < 6 && row < 9) return 7;
        return 8;
    }
    
    /**
     * Get the entire row from the puzzle
     * 
     * @param the column contents
     * @return the column contents
     */
    
    public int[] getColumn(int column) {
        int[] retVal = new int[SIZE];
        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            retVal[k++] = board[i][column];
        }
        return retVal;
    }
    
    /**
     * Get the entire row from the puzzle
     * 
     * @param the row number
     * @return the row contents
     */
    public int[] getRow(int row) {
        return board[row];
    }
    
    /**
     * Get the two-dimensional array of givens
     * @return the givens array
     */
    
    public boolean[][] givens() {
        return this.givens;
    }
    
    /**
     * Hashcode implementation
     */
    
    public int hashCode() {
        return 20;
    }
    
    /**
     * Is the square given?
     * @param row
     * @param column
     * @return true/false
     */
    public boolean isGiven(int row, int column) {
        return givens[row][column];
    }
    
    /**
     * Is the puzzle solved?
     * Determines whether the puzzle constitutes a valid solution
     * 
     * @return if the puzzle is solved or not
     */
    
    public boolean isSolved() {
        if (numberOfUnknowns() > 0) return false;
        boolean solved = true;
        for (int i = 0; i < SIZE; i++) {
            int[] row = getRow(i);
            int[] col = getColumn(i);
            int[] box = getBox(i);
            
            
            for (int j = 0; j < SIZE; j++) {
                boolean foundRow = false;
                boolean foundCol = false;
                boolean foundBox = false;
                for (int k = 0; k < SIZE; k++) {
                    foundRow |= row[k] == j + 1;
                    foundCol |= col[k] == j + 1;
                    foundBox |= box[k] == j + 1;
                }
                solved &= foundRow && foundCol && foundBox;
            }
            
        }
        return solved;
    }
    
    /**
     * Get the number of unknown cells
     * @return the count
     */
    
    public int numberOfUnknowns() {
        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == UNKNOWN) k++;
            }
        }
        
        return k;
        
    }
    
    /**
     * Set the given status of a cell
     * 
     * @param newGiven the true/false value of given
     * @param row
     * @param column
     * @return the new given
     */
    
    public boolean set(boolean newGiven, int row, int column) {
        this.givens[row][column] = newGiven;
        return newGiven;
    }
    
    /**
     * Set the board's value at row, column to newValue
     * 
     * @param newValue the new value
     * @param row the row
     * @param column the column
     * @return the new value
     */
    
    public int set(int newValue, int row, int column) {
        this.board[row][column] = newValue;
        return newValue;
    }
    
    /**
     * Return the size of the puzzle
     * @return the size
     */
    
    public int size() {
        return SIZE;
    }
    
    /**
     * toString implementation
     */
    
    public String toString() {
        String s = "\n+===+===+===+===+===+===+===+===+===+\n";
        for (int i = 0; i < SIZE; i++) {
            s += ": ";
            for (int j = 0; j < SIZE; j++) {
                s += board[i][j];
                if (j % 3 == 2) s += " : ";
                else s += " | ";
            }
            if (i % 3 == 2) s += "\n+===+===+===+===+===+===+===+===+===+\n";
            else s += "\n+---+---+---+---+---+---+---+---+---+\n";
        }
        
        return s;
    }
    
    /**
     * Get the values for the board
     * @reutrn the two-dimensional board array
     */
    
    public int[][] values() {
        return board;
    }
    
    /**
     * Reset the board to its initial state
     */
    
    public void reset() { 
        board = initialBoard;
    }
    
    /**
     * Unserialize a file
     * @param filename
     * @return the puzzle
     */
    public static Puzzle retrievePuzzle(String filename) {
        if (filename == null) filename = "puzzle.ser";
        Puzzle restored = null;
        try {
            InputStream file = new FileInputStream(filename);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer);
            try {
                @SuppressWarnings("unchecked") // Accommodate type erasure.
                Puzzle retrieved = (Puzzle)input.readObject();
                restored = retrieved;
            }
            finally {
                input.close();
            }
        }
        catch (ClassNotFoundException ex) {
            System.err.println(
                "Unsuccessful deserialization: Class not found. " + ex);
        }
        catch (IOException ex) {
            System.err.println("Unsuccessful deserialization: " + ex);
        }
        if (restored == null) 
            System.err.println("Unsuccessful deserialization: restored == null");
        return restored;
    }
    
    /**
     * Serialize the puzzle
     * @param filename
     * @param puzzle
     */
    
    public static void savePuzzle(String filename, Puzzle puzzle) {
        if (filename == null) filename = "puzzle.ser";
        // Serialize the graph.
        try {
            OutputStream file = new FileOutputStream(filename);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try { output.writeObject(puzzle); }
            finally { output.close(); }
        }
        catch (IOException ex) {
            System.err.println("Unsuccessful save. " + ex);
        }

        // Attempt to deserialize the graph as verification.
        try {
            InputStream file = new FileInputStream(filename);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer);
            try {

                @SuppressWarnings("unchecked") // Accommodate type erasure.
                Puzzle restored = null;
                restored = (Puzzle)input.readObject();
                // Simple check that deserialized data matches original.
                if (!puzzle.toString().equals(restored.toString()))
                    System.err.println("[1] State restore did not match save!");
                if (!puzzle.equals(restored))
                    System.err.println("[2] State restore did not match save!");
            }
            finally { input.close(); }
        }
        catch (ClassNotFoundException ex) {
            System.err.println(
                "Unsuccessful deserialization: Class not found. " + ex);
        }
        catch (IOException ex) {
            System.err.println("Unsuccessful deserialization: " + ex);
        }
    }
}   
