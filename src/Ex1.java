import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ex1 {

    public static void main(String[] args) throws FileNotFoundException {
        //getting all the values from the input
        Scanner scan = new Scanner(new File("input.txt"));
        String chosenAlgorithm= scan.nextLine();
        String nextLine=scan.nextLine();
        boolean is_clockwise;
        boolean is_old_first=false;
        if(chosenAlgorithm.equals("A*") || chosenAlgorithm.equals("DFBnB")){
            String[] splitLineArgs= nextLine.split(" ");
            is_clockwise=splitLineArgs[0].equals("clockwise") ? true : false;
            is_old_first=splitLineArgs[1].equals("old-first") ? true : false;
        }
        else{
            is_clockwise= (nextLine.equals("clockwise" ) ? true : false);
        }
        boolean is_with_time = (scan.nextLine().equals("with time" ) ? true : false);
        boolean is_with_open = (scan.nextLine().equals("with open") ? true : false);

        int table_size= Integer.parseInt(scan.nextLine());
//        System.out.println("n= " + table_size);

        String pointsInputLine= scan.nextLine();
        String[] splitPoints= pointsInputLine.split(",");

        Point[] points= new Point[2];
        points= convertStartAndGoalPoints(splitPoints);

        char [][] table= new char[table_size][table_size];
        //loop to get the table into char[][]
        for(int i=0; i<table_size; i++){
            String line= scan.nextLine();
            char[] chars= new char[table_size];
            chars= line.toCharArray();
            for(int j=0; j<table_size; j++){
                table[i][j]= chars[j];
            }
        }
        //finished to get all inputs

        Node startNode= new Node(points[0].getX(),points[0].getY(), table[points[0].getX()][points[0].getY()], true, points[1]);
        Node goalNode= new Node(points[1].getX(), points[1].getY(), table[points[1].getX()][points[1].getY()], false, new Point(-1,-1));

        double startRunTime =0 ;//= System.currentTimeMillis();
        double endRunTime = 0;
        String path = "";
        int num =0 , cost =0;
        if(chosenAlgorithm.equals("BFS")) {
            Table _table= new Table(table, table_size, is_clockwise, is_with_time, is_with_open, is_old_first);
            startRunTime = System.currentTimeMillis();
            BFS bfs= new BFS();
            path= bfs.bfs_algorithm(points[0], points[1],_table);
            endRunTime =(System.currentTimeMillis() -  startRunTime)/1000;
            num = bfs.getNum();
            cost = bfs.getCost();
        }
        else if(chosenAlgorithm.equals("DFID")) {
            Table _table= new Table(table, table_size, is_clockwise, is_with_time, is_with_open, is_old_first);
            DFID dfid= new DFID();
            startRunTime = System.currentTimeMillis();
            path= dfid.dfid_algorithm(startNode,points[1],_table);
            endRunTime =(System.currentTimeMillis() -  startRunTime)/1000;
            num = dfid.getNum();
            cost = dfid.getCost();
        }
        else if(chosenAlgorithm.equals("A*")) {
            Table _table= new Table(table, table_size, is_clockwise, is_with_time, is_with_open, is_old_first);
            A_Star a = new A_Star();
            startRunTime = System.currentTimeMillis();
            path = a.a_start_algorithm(startNode, goalNode ,_table);
            endRunTime =(System.currentTimeMillis() -  startRunTime)/1000;
            num = a.getNum();
            cost = a.getCost();
        }
        else if(chosenAlgorithm.equals("IDA*")) {
            Table _table= new Table(table, table_size, is_clockwise, is_with_time, is_with_open);
            IDA_Star idaStar = new IDA_Star();
            startRunTime = System.currentTimeMillis();
            path = idaStar.IDAStar_Al(startNode, goalNode, _table);
            endRunTime =(System.currentTimeMillis() -  startRunTime)/1000;
//
            num = idaStar.getNum();
            cost = idaStar.getCost();
        }
        else if(chosenAlgorithm.equals("DFBnB")) {
            Table _table= new Table(table, table_size, is_clockwise, is_with_time, is_with_open, is_old_first);
            DFBnB dfbnb = new DFBnB();
            startRunTime = System.currentTimeMillis();
            path = dfbnb.DFBnB_Algo(startNode, goalNode, _table);
            endRunTime =(System.currentTimeMillis() -  startRunTime)/1000;
            num = dfbnb.getNum();
            cost = dfbnb.getCost();
        }
        PrintWriter wiretoFile  = new PrintWriter("output.txt");
        wiretoFile.println(path);
        wiretoFile.println("Num: "+num);
        if(path.equals("no path")) wiretoFile.println("Cost: inf");
        else wiretoFile.println("Cost: "+cost);
        if(is_with_time) wiretoFile.println(endRunTime +" seconds");
        wiretoFile.close();

    }
    //this method getting points line splitted by "," and converts the input to Point array
    public static Point[] convertStartAndGoalPoints(String[] pointsAsString){
        Point[] ans= new Point [2];
        int xStart= Integer.parseInt(pointsAsString[0].substring(1));
        int yStart= Integer.parseInt(pointsAsString[1].substring(0, pointsAsString[1].length()-1));
        int xGoal= Integer.parseInt(pointsAsString[2].substring(1));
        int yGoal= Integer.parseInt(pointsAsString[3].substring(0,pointsAsString[3].length()-1 ));
        ans[0]= new Point(xStart-1, yStart-1);
        ans[1]= new Point(xGoal-1, yGoal-1);
        return ans;
    }
}
