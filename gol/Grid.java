package gol;

/**
 * Created by Quinn.Tucker18 on 6/18/2017.
 * 
 * Represents the grid for the cellular automaton; handles stepping the simulation.
 */
public class Grid {
    
    public static final int SIZE = 64;
    
    public static final boolean[][][] rules = new boolean[][][] {
        //     0      1      2      3      4      5      6      7      8
        { // #1: John Conway's original game of life
            {false, false, false, true,  false, false, false, false, false}, // begin rule
            {false, false, true,  true,  false, false, false, false, false}, // stay rule
        },
        { // #2: an automaton that generates nice "caves"
            {false, false, false, false, false, true,  true,  true,  true }, // begin rule
            {false, false, false, false, true,  true,  true,  true,  true }, // stay rule
        },
        { // #3: Seeds
            {false, true,  false, false, false, false, false, false, false}, // begin rule
            {false, false, false, false, false, false, false, false, false}, // stay rule
        },
        { // #4: Diamoeba
            {false, false, false, true,  false, true,  true,  true,  true }, // begin rule
            {false, false, false, false, false, true,  true,  true,  true }, // stay rule
        },
        { // #5: Anneal
            {false, false, false, false, true,  false, true,  true,  true }, // begin rule
            {false, false, false, true,  false, true,  true,  true,  true }, // stay rule
        }
        // try out other rules, too!
        // TODO: wikipedia link
    };
    
    public static int ruleIndex = 0;
    
    private boolean[][] grid = new boolean[SIZE][SIZE];
    
    public Grid(boolean random) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y] = random && Math.random() < 0.5;
            }
        }
    }
    
    private int wrapCoord(int c) {
        c %= SIZE;
        if (c < 0) c += SIZE;
        return c;
    }
    
    public boolean getCell(int x, int y) {
        return grid[wrapCoord(x)][wrapCoord(y)];
    }
    
    public void setCell(int x, int y, boolean alive) {
        grid[wrapCoord(x)][wrapCoord(y)] = alive;
    }
    
    public void step() {
        boolean[][] newGrid = new boolean[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                int count = 0;
                if (getCell(x-1, y-1)) count++;
                if (getCell(x-1, y+0)) count++;
                if (getCell(x-1, y+1)) count++;
                if (getCell(x+0, y-1)) count++;
                if (getCell(x+0, y+1)) count++;
                if (getCell(x+1, y-1)) count++;
                if (getCell(x+1, y+0)) count++;
                if (getCell(x+1, y+1)) count++;
                newGrid[x][y] = rules[ruleIndex][getCell(x, y)? 1 : 0][count];
            }
        }
        grid = newGrid;
    }
    
    public static void nextRule() {
        ruleIndex++;
        if (ruleIndex == rules.length)
            ruleIndex = 0;
        System.out.println("Using rule #"+(ruleIndex+1));
    }
    
}
