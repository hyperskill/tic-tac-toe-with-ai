package ticTacToe.tictactoe.model;

public class FieldModel implements Comparable{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FieldModel(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Object o) {
        if(o == null) return -1;
        FieldModel that = (FieldModel)o;
        if(o == null) return -1;
        if(this == that) return 0;
        if( (this.x == that.x)&&(this.y == that.y) ) return 0;
        int tmpThis = this.x + 10*this.y;
        int tmpThat = that.x + 10*that.y;
        if( tmpThis > tmpThat) return 1;
        return -1;
    }
}
