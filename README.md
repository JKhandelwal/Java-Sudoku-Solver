# Java-Sudoku-Solver
A sudoku solver written in Java, it takes in a sudoku box size, and then the sudoku, with 0's representing empty cells in the sudoku, and if it has one unique solution, prints out the solution. If there are multiple solutions, the word 'MULTIPLE' is printed out, and if the sudoku is invalid, the word 'UNSOLVABLE' is printed out.

The format to enter as follows, e.g. for a sudoku with box size of 2:

2 <Hit-Enter>
0  2  3  4 <Hit-Enter> 
3  4  1  2 <Hit-Enter>
4  0  2  1 <Hit-Enter>
2  0  4  0 <Hit-Enter>

Which will output the following result:
1  2  3  4
3  4  1  2
4  3  2  1
2  1  4  3


The approach used is a variation on the brute force method, which tries entering a number, from 1 through the Box Size * Box Size, in each empty cell, denoted by the '0' in the sudoku, and checks to see if the number entered is valid, if so it tries entering the numbers from 1 through the Box Size * Box Size in the next empty cell, and so on. If it finds that no number will satisfy the sudoku, it backtracks to the last cell filled, and tries the next number along from 1 to Box Size * Box Size, until each combination has been tried, and no solution has been found, multiple solutions exist, or a singular solution has been found. 

With regards to efficiency, due to the fact that it is brute force, the algorithm will work well on nearly completed sodokus, or sudokus where a string of cells can be filled in from a small number of options. 

The solver can in theory work on any size of sudokus, however after a point it may start to become inefficient.
