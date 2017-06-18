import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jalajkhandelwal on 01/06/2017.
 */
public class Solver {
    private static int squared = 0;
    private static int num = 0;
    private static int no_of_solutions_found = 0;
    private static boolean solvable = false;
    private static Sudoku solved;
    private static Sudoku initial;
    private static int numZeros = 0;
    private static int column;
    private static int row;
    private static int box_corner;
    private static int firstCounter =0;

    public static void main(String[] args) {

        initial = setupSudoku();
        solve_Sudoku(initial, numZeros);

        if (solvable) {
            Sudoku.print(solved);
        } else {
            System.out.println("UNSOLVABLE\n");
        }
    }

    public enum retVal_type {
        INCOMPLETE, COMPLETE, INVALID
    }

    public static Sudoku setupSudoku() {
        Sudoku initial = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = br.readLine();
            num = Integer.parseInt(s);
            initial = new Sudoku(num);
            squared = num * num;
            for (int i = 0; i < squared; i++) {
                String line = br.readLine();
                String[] strs = line.trim().split("\\s+");
                for (int j = 0; j < squared; j++) {
                    initial.setElement(Integer.parseInt(strs[j]), i * squared + j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid input, Error, aborting");
        }
        return initial;
    }


    public static retVal_type check_list(int[] list, int length) {
        retVal_type t;
        boolean incomplete = false;
        int[] temp = new int[length + 1];

        int var;

        for (int i = 0; i < length; i++) {
            if (list[i] > length || list[i] < 0) {
                t = retVal_type.INVALID;
                return t;
            }
            var = list[i];
            if (var != 0) {
                if (temp[var] == 1) {
                    t = retVal_type.INVALID;
                    return t;
                }
            }
            temp[var] += 1;
            if (temp[0] > 0) {
                incomplete = true;
            }

        }

        if (incomplete) {
            t = retVal_type.INCOMPLETE;
        } else {
            t = retVal_type.COMPLETE;
        }
        return t;
    }

    public static retVal_type check_sudoku(Sudoku c) {
        retVal_type t;
        boolean incomplete = false;
        int[] temp = new int[c.getSize() * c.getSize()];
        //check rows
        for (int i = 0; i < ((c.getSize() * c.getSize())); i++) {
            for (int j = 0; j < (c.getSize() * c.getSize()); j++) {
                temp[j] = c.getElement(j + i * (c.getSize() * c.getSize()));
            }
            t = check_list(temp, c.getSize() * c.getSize());
            if (t == retVal_type.INVALID) {
                t = retVal_type.INVALID;
                return t;
            } else {
                if (!incomplete && t == retVal_type.INCOMPLETE) {
                    incomplete = true;
                }
            }
        }
        //check columns
        for (int i = 0; i < (c.getSize() * c.getSize()); i++) {
            for (int j = 0; j < (c.getSize() * c.getSize()); j++) {
                temp[j] = c.getElement(i + j * (c.getSize() * c.getSize()));
            }
            t = check_list(temp, c.getSize() * c.getSize());
            if (t == retVal_type.INVALID) {
                t = retVal_type.INVALID;
                return t;
            } else {
                if (!incomplete && t == retVal_type.INCOMPLETE) {
                    incomplete = true;
                }
            }
        }
        //check boxes
        int var = 0;
        for (int i = 0; i < (c.getSize() * c.getSize()); i++) {
            int counter = 0;
            for (int j = 0; j < (c.getSize()); j++) {
                for (int k = var; k < (var + c.getSize()); k++) {
                    temp[counter++] = c.getElement(j * c.getSize() * c.getSize() + k);
                }
            }

            if ((i + 1) % c.getSize() != 0) {
                var += c.getSize();
            } else {
                var += c.getSize() * c.getSize() * c.getSize() - (c.getSize() - 1) * c.getSize();
            }

            t = check_list(temp, c.getSize() * c.getSize());
            if (t == retVal_type.INVALID) {
                t = retVal_type.INVALID;
                return t;
            } else {
                if (!incomplete && t == retVal_type.INCOMPLETE) {
                    incomplete = true;
                }
            }
        }

        if (!incomplete) {
            t = retVal_type.COMPLETE;
        } else {
            t = retVal_type.INCOMPLETE;
        }
        return t;
    }

    public static void solve_Sudoku(Sudoku c, int numZeros) {
        if (firstCounter ==0){
            retVal_type t = check_sudoku(c);
            if (t == retVal_type.INVALID) {
                return;
            } else {
                if (t == retVal_type.COMPLETE) {
                    solvable = true;
                    solved = new Sudoku(c.getSize());
                    solved = Sudoku.copy(c);
                    return;
                }
            }
            firstCounter++;
        }


        if (numZeros == 0) {
            //if (t == retVal_type.COMPLETE) {
            if (no_of_solutions_found == 0) {
                solvable = true;
                solved = new Sudoku(c.getSize());
                solvable = true;
                no_of_solutions_found++;
                return;
            } else {
                System.out.println("retVal_type.MULTIPLE\n");
                System.exit(0);
            }

            //   }
        }


        for (int i = 0; i < ((c.getSize()*c.getSize()*c.getSize()*c.getSize())); i++) {
            if (c.getElement(i) == 0) {
                for (int j = 1; j < (c.getSize() * c.getSize() + 1); j++) {
                    int numTempZeros = numZeros--;
                    column = i % (c.getSize() * c.getSize());
                    row = i / (c.getSize() * c.getSize());
                    int box_row = (row / c.getSize()) % c.getSize();
                    int box_clm = (column / c.getSize()) % c.getSize();
                    box_corner = box_row * c.getSize() * c.getSize() * c.getSize() + box_clm * c.getSize();
                    int temp = c.getElement(i);
                    c.setElement(i,j);
                    if (is_Addition_Valid(c)){
                        solve_Sudoku(c, numZeros);
                    }
                    c.setElement(i,temp);
                    numZeros = numTempZeros;
                }
                break;
            }
        }


    }


    public static boolean is_Addition_Valid(Sudoku c) {
    retVal_type t;
    int[] temp = new int[c.getSize() * c.getSize()];

    //check row
        for (int j = 0; j < (c.getSize() * c.getSize()); j++) {
            temp[j] = c.getElement(j + row * (c.getSize() * c.getSize()));
        }
    t = check_list(temp, c.getSize() * c.getSize());
    if (t == retVal_type.INVALID) {
        return false;
    }

    //check column
    for (int j = 0; j < (c.getSize() * c.getSize()); j++) {
        temp[j] = c.getElement(column + j * (c.getSize() * c.getSize()));
    }
    t = check_list(temp, c.getSize() * c.getSize());
    if (t == retVal_type.INVALID) {
        return false;
    }

    //check box
    int counter = 0;
    for (int j = 0; j < (c.getSize()); j++) {
        for (int k = box_corner; k < (box_corner + c.getSize()); k++) {
            temp[counter++] = c.getElement(j * c.getSize() * c.getSize() + k);
        }
    }
    t = check_list(temp, c.getSize() * c.getSize());
    if (t == retVal_type.INVALID) {
        return false;
    }
    return true;

}
}
