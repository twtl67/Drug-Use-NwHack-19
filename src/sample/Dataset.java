package sample;

/**
 *
 * @author terry
 */
public class Dataset {
    private String name;
    private int xcord;
    private int ycord;
    public Dataset(String n, int x, int y){
        name = n;
        xcord = x;
        ycord = y;
    }
    public int getX (){
        return xcord;
    }
    public int getY (){
        return ycord;
    }
    public String getName (){
        return name;
    }
}