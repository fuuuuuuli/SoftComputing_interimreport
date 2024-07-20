public class City {
    private int number;
    private int x;
    private int y;
    City(int data[]){
        number=data[0];
        x=data[1];
        y=data[2];
    }
    
    public int getNumber(){
        return number;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
