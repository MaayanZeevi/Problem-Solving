import java.util.ArrayList;
import java.util.*;

public class Table {
    private char[][] _table;
    private int _size;
//    private Point _start;
    private Point _goal;
    boolean _isClockWise;
    boolean _isWithTime;
    boolean _isWithOpen;
    boolean _isOldFirst;


    public Table(char[][] table, int size, boolean isClockWise,
                 boolean isWithTime, boolean isWithOpen){
        _size=size;
        _table= new char[_size][_size];

        _isClockWise=isClockWise;
        _isWithTime=isWithTime;
        _isWithOpen=isWithOpen;
        for(int i=0; i<_size; i++){
            for(int j=0; j<_size; j++){
                _table[i][j]=table[i][j];
            }
        }
    }

    public Table(char[][] table, int size, boolean isClockWise,
                 boolean isWithTime, boolean isWithOpen, boolean isOldFirst){
        this(table,size, isClockWise, isWithTime, isWithOpen);
        _isOldFirst =isOldFirst;

    }
    public int getSize(){return _size;}
    public boolean isClockWise(){return _isClockWise;}
    public boolean isWithTime(){return _isWithTime;}
    public boolean isWithOpen(){return _isWithOpen;}

    public char getLabel(int x, int y){return _table[x][y];}



    public ArrayList<Node> getNeighbors(Node node){
        ArrayList<Node> ans= new ArrayList<>();
        Point p= node.getPoint();
        int x=p.getX();
        int y=p.getY();
/* clockwise: R RD D LD L LU U RU
   counter clockwise:
              R RU U LU L LD D RD
              then first insert all possible directions nodes by clockwise type then check the type and do reverse on values if is not clockwise
              and finally insert right at first index
*/        //right down
        if(x<_size-1 && y<_size-1 && _table[x+1][y+1]!='X'){
            if(node.isStart()) {
                Node rightDown= new Node(x+1, y+1, _table[x+1][y+1],false, node.getGoal());
                rightDown.setPath(node.getPath()+ "-" + "RD");
                rightDown.setCost(node.getCost(), "RD");
                ans.add(rightDown);
            }
            else{
                if(!node.getDad().equals(new Point(x+1,y+1))){
                    Node rightDown= new Node(x+1, y+1, _table[x+1][y+1],false, node.getGoal());
                    rightDown.setPath(node.getPath()+ "-" + "RD");
                    rightDown.setCost(node.getCost(), "RD");
                    ans.add(rightDown);
                }

            }
        }
        //down
        if(x<_size-1 && _table[x+1][y]!='X'){
            if(node.isStart()) {
                Node down= new Node(x+1, y,_table[x+1][y],false, node.getGoal());
                down.setPath(node.getPath()+ "-" + "D");
                down.setCost(node.getCost(), "D");
                ans.add(down);
            }
            else{
                if(!node.getDad().equals(new Point(x+1,y))){
                    Node down= new Node(x+1, y,_table[x+1][y],false, node.getGoal());
                    down.setPath(node.getPath()+ "-" + "D");
                    down.setCost(node.getCost(), "D");
                    ans.add(down);
                }
            }
        }
            //left down
        if(x<_size-1 && y>0 && _table[x+1][y-1]!='X'){
                if(node.isStart()){
                    Node leftDown= new Node(x+1, y-1,_table[x+1][y-1],false, node.getGoal());
                    leftDown.setPath(node.getPath()+ "-" + "LD");
                    leftDown.setCost(node.getCost(), "LD");
                    ans.add(leftDown);
                }
                else{
                    if(!node.getDad().equals(new Point(x+1,y-1))){
                        Node leftDown= new Node(x+1, y-1,_table[x+1][y-1],false, node.getGoal());
                        leftDown.setPath(node.getPath()+ "-" + "LD");
                        leftDown.setCost(node.getCost(), "LD");
                        ans.add(leftDown);
                    }
                }
            }
            //left
            if(y>0 && _table[x][y-1]!='X'){
                if(node.isStart()){
                    Node left= new Node(x, y-1,_table[x][y-1],false, node.getGoal());
                    left.setPath(node.getPath()+ "-" + "L");
                    left.setCost(node.getCost(), "L");
                    ans.add(left);
                }
                else{
                    if(!node.getDad().equals(new Point(x,y-1))){
                        Node left= new Node(x, y-1,_table[x][y-1],false, node.getGoal());
                        left.setPath(node.getPath()+ "-" + "L");
                        left.setCost(node.getCost(), "L");
                        ans.add(left);
                    }
                }
            }
            //left up
            if(x>0 && y>0 && _table[x-1][y-1]!='X'){
                if(node.isStart()){
                    Node leftUp= new Node(x-1, y-1,_table[x-1][y-1],false, node.getGoal());
                    leftUp.setPath(node.getPath()+ "-" + "LU");
                    leftUp.setCost(node.getCost(), "LU");
                    ans.add(leftUp);
                }
                else{
                    if(!node.getDad().equals(new Point(x-1,y-1))){
                        Node leftUp= new Node(x-1, y-1,_table[x-1][y-1],false, node.getGoal());
                        leftUp.setPath(node.getPath()+ "-" + "LU");
                        leftUp.setCost(node.getCost(), "LU");
                        ans.add(leftUp);
                    }
                }
            }
            //up
            if(x>0 && _table[x-1][y]!='X'){
                if(node.isStart()){
                    Node up= new Node(x-1, y,_table[x-1][y],false, node.getGoal());
                    up.setPath(node.getPath()+ "-" + "U");
                    up.setCost(node.getCost(), "U");
                    ans.add(up);
                }
                else{
                    if(!node.getDad().equals(new Point(x-1,y))){
                        Node up= new Node(x-1, y,_table[x-1][y],false,node.getGoal());
                        up.setPath(node.getPath()+ "-" + "U");
                        up.setCost(node.getCost(), "U");
                        ans.add(up);
                    }
                }
            }
            //right up
            if(x>0 && y<_size-1 && _table[x-1][y+1]!='X'){
                if(node.isStart()){
                    Node rightUp= new Node(x-1, y+1,_table[x-1][y+1],false,node.getGoal());
                    rightUp.setPath(node.getPath()+ "-" + "RU");
                    rightUp.setCost(node.getCost(), "RU");
                    ans.add(rightUp);
                }
                else{
                    if(!node.getDad().equals(new Point(x-1,y+1))){
                        Node rightUp= new Node(x-1, y+1,_table[x-1][y+1],false,node.getGoal());
                        rightUp.setPath(node.getPath()+ "-" + "RU");
                        rightUp.setCost(node.getCost(), "RU");
                        ans.add(rightUp);
                    }
                }
            }
            if(!_isClockWise) Collections.reverse(ans);

            // no matter clockwise/counter clockwise - right node always insert first
            //right
            if(y<_size-1 && _table[x][y+1]!='X' ){
                if(node.isStart()) {
                    Node right = new Node(x, y + 1, _table[x][y + 1], false,node.getGoal());
                    right.setPath(node.getPath() + "-" + 'R');
                    right.setCost(node.getCost(), "R");
                    ans.add(0,right);
                }
                else{
                    if(!node.getDad().equals(new Point(x,y+1))){
                        Node right= new Node(x,y+1, _table[x][y+1],false,node.getGoal());
                        right.setPath(node.getPath()+ "-" + 'R');
                        right.setCost(node.getCost(), "R");
                        ans.add(0,right);
                    }
                }
            }
        return ans;
    }
    public ArrayList<Node> getNeighborsWithHeuristic(Node node, Point goal, Boolean isOldFirst){
        ArrayList<Node> ans= new ArrayList<>();
        Point p= node.getPoint();
        int x=p.getX();
        int y=p.getY();
/* clockwise: R RD D LD L LU U RU
   counter clockwise:
              R RU U LU L LD D RD
              then first insert all possible directions nodes by clockwise type then check the type and do reverse on values if is not clockwise
              and finally insert right at first index
*/        //right down
        if(x<_size-1 && y<_size-1 && _table[x+1][y+1]!='X'){
            if(node.isStart()) {
                Node rightDown= new Node(x+1, y+1, _table[x+1][y+1],false, goal, isOldFirst);
                rightDown.setPath(node.getPath()+ "-" + "RD");
                rightDown.setCost(node.getCost(), "RD");
                ans.add(rightDown);
            }
            else{
                if(!node.getDad().equals(new Point(x+1,y+1))){
                    Node rightDown= new Node(x+1, y+1, _table[x+1][y+1],false, goal, isOldFirst);
                    rightDown.setPath(node.getPath()+ "-" + "RD");
                    rightDown.setCost(node.getCost(), "RD");
                    ans.add(rightDown);
                }

            }
        }
        //down
        if(x<_size-1 && _table[x+1][y]!='X'){
            if(node.isStart()) {
                Node down= new Node(x+1, y,_table[x+1][y],false, goal, isOldFirst);
                down.setPath(node.getPath()+ "-" + "D");
                down.setCost(node.getCost(), "D");
                ans.add(down);
            }
            else{
                if(!node.getDad().equals(new Point(x+1,y))){
                    Node down= new Node(x+1, y,_table[x+1][y],false, goal, isOldFirst);
                    down.setPath(node.getPath()+ "-" + "D");
                    down.setCost(node.getCost(), "D");
                    ans.add(down);
                }
            }
        }
        //left down
        if(x<_size-1 && y>0 && _table[x+1][y-1]!='X'){
            if(node.isStart()){
                Node leftDown= new Node(x+1, y-1,_table[x+1][y-1],false, goal, isOldFirst);
                leftDown.setPath(node.getPath()+ "-" + "LD");
                leftDown.setCost(node.getCost(), "LD");
                ans.add(leftDown);
            }
            else{
                if(!node.getDad().equals(new Point(x+1,y-1))){
                    Node leftDown= new Node(x+1, y-1,_table[x+1][y-1],false, goal, isOldFirst);
                    leftDown.setPath(node.getPath()+ "-" + "LD");
                    leftDown.setCost(node.getCost(), "LD");
                    ans.add(leftDown);
                }
            }
        }
        //left
        if(y>0 && _table[x][y-1]!='X'){
            if(node.isStart()){
                Node left= new Node(x, y-1,_table[x][y-1],false, goal, isOldFirst);
                left.setPath(node.getPath()+ "-" + "L");
                left.setCost(node.getCost(), "L");
                ans.add(left);
            }
            else{
                if(!node.getDad().equals(new Point(x,y-1))){
                    Node left= new Node(x, y-1,_table[x][y-1],false, goal, isOldFirst);
                    left.setPath(node.getPath()+ "-" + "L");
                    left.setCost(node.getCost(), "L");
                    ans.add(left);
                }
            }
        }
        //left up
        if(x>0 && y>0 && _table[x-1][y-1]!='X'){
            if(node.isStart()){
                Node leftUp= new Node(x-1, y-1,_table[x-1][y-1],false, goal, isOldFirst);
                leftUp.setPath(node.getPath()+ "-" + "LU");
                leftUp.setCost(node.getCost(), "LU");
                ans.add(leftUp);
            }
            else{
                if(!node.getDad().equals(new Point(x-1,y-1))){
                    Node leftUp= new Node(x-1, y-1,_table[x-1][y-1],false, goal, isOldFirst);
                    leftUp.setPath(node.getPath()+ "-" + "LU");
                    leftUp.setCost(node.getCost(), "LU");
                    ans.add(leftUp);
                }
            }
        }
        //up
        if(x>0 && _table[x-1][y]!='X'){
            if(node.isStart()){
                Node up= new Node(x-1, y,_table[x-1][y],false, goal, isOldFirst);
                up.setPath(node.getPath()+ "-" + "U");
                up.setCost(node.getCost(), "U");
                ans.add(up);
            }
            else{
                if(!node.getDad().equals(new Point(x-1,y))){
                    Node up= new Node(x-1, y,_table[x-1][y],false, goal, isOldFirst);
                    up.setPath(node.getPath()+ "-" + "U");
                    up.setCost(node.getCost(), "U");
                    ans.add(up);
                }
            }
        }
        //right up
        if(x>0 && y<_size-1 && _table[x-1][y+1]!='X'){
            if(node.isStart()){
                Node rightUp= new Node(x-1, y+1,_table[x-1][y+1],false, goal, isOldFirst);
                rightUp.setPath(node.getPath()+ "-" + "RU");
                rightUp.setCost(node.getCost(), "RU");
                ans.add(rightUp);
            }
            else{
                if(!node.getDad().equals(new Point(x-1,y+1))){
                    Node rightUp= new Node(x-1, y+1,_table[x-1][y+1],false, goal, isOldFirst);
                    rightUp.setPath(node.getPath()+ "-" + "RU");
                    rightUp.setCost(node.getCost(), "RU");
                    ans.add(rightUp);
                }
            }
        }

        if(!_isClockWise) Collections.reverse(ans);

        // no matter clockwise/counter clockwise - right node always insert first
        //right
        if(y<_size-1 && _table[x][y+1]!='X' ){
            if(node.isStart()) {
                Node right = new Node(x, y + 1, _table[x][y + 1], false, goal, isOldFirst);
                right.setPath(node.getPath() + "-" + 'R');
                right.setCost(node.getCost(), "R");
                ans.add(0,right);
            }
            else{
                if(!node.getDad().equals(new Point(x,y+1))){
                    Node right= new Node(x,y+1, _table[x][y+1],false, goal, isOldFirst);
                    right.setPath(node.getPath()+ "-" + 'R');
                    right.setCost(node.getCost(), "R");
                    ans.add(0,right);
                }
            }
        }
        return ans;
    }

    public boolean _isWithOpen() {
        return _isWithOpen;
    }

    public void printList(HashMap<String, Node> list) {
        System.out.println("open list:"+ list.keySet());
//        System.out.println(list.keySet());
        for (Node node : list.values()) {
            if (!node.isOut()) {
                node.print();
            }
        }
        System.out.println("_____________________________");
    }

}
