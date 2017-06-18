/**
 * Created by jalajkhandelwal on 01/06/2017.
 */
public class Sudoku {
    private int size = 0;
    private int[] sudoku;
    public Sudoku(int size){
        this.size = size;
        sudoku = new int[this.size*this.size*this.size*this.size];
    }

    public void setElement(int elementNum, int numset){
        sudoku[numset] = elementNum;
    }

    public int getElement(int num){
        return sudoku[num];
    }

    public static Sudoku copy(Sudoku s){
        Sudoku c = new Sudoku(s.size);
        for (int i=0; i < s.getSize()*s.getSize()*s.getSize()*s.getSize();i++){
            c.setElement(s.getElement(i),i);
        }
        return c;
    }

    public int getSize(){
        return size;
    }

    public static void print(Sudoku s){
        for (int i =0;i<(s.size*s.size*s.size*s.size);i++){
            if ((i+1) % (s.size*s.size) == 0){
                System.out.println(s.getElement(i)+ " ");
            } else {
                System.out.print(s.getElement(i)+ " ");
            }
        }
    }
}
