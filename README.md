# Game of Life

by Quinn Tucker '18

A Game of Life simulation in Java. It has no dependencies except
for the standard library; the main class is `gol.Main` (surprise).

### Controls

 * Space – steps the simulation
 * P – toggles autoplay (runs the simulation as fast as the window repaints)
 * Del/Backspace – clears the grid
 * R – fills the grid with 50% random noise
 * C – cycles through the different rules (enumerated below)
 * mouse/touch – draws/erases cells

### Rules

There are several alternative cellular automaton rules
defined towards the top of `Grid.java`. These change the
types of patterns/behavior you get. Pressing C will
cycle through rules; the newly selected rule number is
printed to the console.

 1. John Conway's original Game of Life (default)  
    `B3/S23`
 2. An automaton that generates stable blobs/"caves"  
    `B5678/S45678`
 3. "Seeds" (lots of chaotic growth)  
    `B2/S`
 4. "Diamoeba" (diamond-shaped blobs with shifting edges)  
    `B35678/S5678`
 5. "Anneal" (blobs that tend to reduce their edges' curvature)  
    `B4678/S35678`

Feel free to add/change the rules in `Grid.java`. Some
rules are listed and described at http://psoup.math.wisc.edu/mcell/rullex_life.html