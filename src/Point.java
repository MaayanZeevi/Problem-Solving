public class Point {
    int _x;
    int _y;
    public Point (int x, int y){
      _x=x;
      _y=y;
    }
    public int getX(){return _x;}
    public int getY(){return _y;}
    public void setX(int x){ _x=x;}
    public void setY(int y){ _y=y;}
    public boolean equals(Point p){
        if(_x==p.getX() && _y==p.getY())return true;
        return false;
    }
    public String toString(){
        return "("+ _x+ "," +_y+ ")";
    }
}
