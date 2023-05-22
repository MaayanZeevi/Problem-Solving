public class Node{
    private Point _dad;
    private char _value;
    private String _key; //the key is the (x,y) values
    private Point _point;
    private Point _goal;
    private int cost;
    private String path;
    private boolean _isStart;
    private int heuristicValue;
    private static int _nodeCounter=0;
    private static int counter=0; // the counter is for to know which node is older, as the counter is small then the node is older
    private int nodeAge;
    boolean _old_first;
    private boolean isOut;
//bfs , dfid
    public Node(Point p, char value, boolean isStart){
        _value=value;
        _key =p.toString();
        _point =p;
        cost=0;
        path="";
        _isStart=isStart;
        isOut=false;
        _nodeCounter++;
        nodeAge=counter;
        counter++;
    }
    //ida*
    public Node(int x, int y, char value, boolean isStart,Point goal){
        this(new Point(x,y),value,isStart);
        _goal=goal;
        heuristicValue= heuristicFunc(goal);
    }
    //constructor for h() -A* and DFBnB
    public Node(int x, int y, char value, boolean isStart, Point goal, Boolean old_first){
        this(new Point(x,y),value,isStart);
        _goal=goal;
        heuristicValue= heuristicFunc(goal);
        _old_first=old_first;
    }
    public String getKey(){return _key;}
    public Point getPoint(){return _point;}
    public String getPath(){

        return path;
    }
    public static int get_nodeCounter(){
        return _nodeCounter;
    }
    public Point getGoal(){return _goal;}
    public int getCost(){
        return cost;
    }
    public Point getDad(){ return _dad;}
    public int getNodeAge(){return nodeAge;}
    public void setPath(String updatePath){
        path=updatePath;
    }
    public void setDad(Point d){
        _dad=d;
    }
    public void setCost(int dadCost,String direction){
        if(_value=='D') cost=dadCost+1;
        else if(_value=='R') cost=dadCost+3;
        else if(_value=='G') cost=dadCost+5;
        else if(_value=='H'){
            if(direction=="RD" || direction=="LD" || direction=="UR" || direction=="UL") cost=dadCost+10;
            else cost=dadCost+5;
        }

    }
    public void markAsNotOut() {
        isOut = false;
    }

    public void markAsOut() {
        isOut = true;
    }

    public boolean isOut() {
        return isOut;
    }
    public int getNodesCounter(){return _nodeCounter;}
    public boolean isStart(){return _isStart;}
//    public void setPathUntillNow(Node dad){
//        String direction= findDirection(dad.getPoint());
//        path=dad.getPath() + "-" + direction;
//    }
    public void setCostUntillNow(Node dad){

    }
    public boolean equals(Node n){
        if(this._point.equals(n.getPoint()))return true;
        return false;
    }

    public String toString(){
        int f= function();
        return  "Point="+ _point + ", nodeAge:="+nodeAge + " f(n)= "+ f;
    }

    //heuristicFunc- estimate distance between the current node to the goal node base on Chebyshev distance
    public int heuristicFunc(Point goal){
        int x_dist= Math.abs(_point.getX()-goal.getX());
        int y_dist= Math.abs(_point.getY()-goal.getY());
        int currVal=0;
        if(_value=='D') currVal=1;
        else if(_value=='R') currVal=3;
        else if(_value=='H' || _value=='G') currVal=5;

        return Math.max(x_dist,y_dist); // + or - 5 or currVal
    }
    /*
    f(state)= h(state) +g(state) =>
    using in A* to calculate f(state): which combines the heuristic method with the real cost from start node
    until the current node
    */
    public int function(){
        return heuristicValue + cost;
    }
    public void setCost(int n){this.cost=n;}
    public boolean isOldFirst(){return _old_first;}
    public void print() {
        String temp_path = path;
        if (path.length() > 0) {
            temp_path = temp_path.substring(1);
        }

        System.out.print("point " + _key +":");
        System.out.print(" path: " + temp_path +", ");
        System.out.print("cost: " + cost+ ",");
        System.out.print("nodeAge: " + nodeAge+ ",");
        System.out.print("counter=  " + counter+ ",");
        System.out.println("heuristic price: " + heuristicValue);

    }

}