import java.util.*;
import java.util.ArrayList;

public class BFS {
    private static int nodesCounter = 0; //count nodes amount
    private int cost = 0; //calculate cost
    private Queue<Node> queue = new LinkedList<>(); // queue

    public String bfs_algorithm(Point start, Point goal, Table table){
        HashMap<String, Node> open_list= new HashMap<>(); /// queue map for the BFS
        HashMap<String, Node> closed_list = new HashMap<>(); //closed list
//        start.setDad(null);
        Node startNode= new Node(start.getX(),start.getY(),table.getLabel(start.getX(),start.getY()),true, goal, table._isOldFirst);
        queue.add(startNode);
        open_list.put(startNode.getKey(), startNode);//adding start node to open list
        nodesCounter++;
        while(!queue.isEmpty()) {
            if (table._isWithOpen) {
                table.printList(open_list);
            }
            Node currNode= queue.poll();
            open_list.remove(currNode.getKey());
            closed_list.put(currNode.getKey(), currNode);
            ArrayList<Node> neighbors = table.getNeighbors(currNode);
            for(Node node: neighbors) {
                node.setDad(currNode.getPoint());
                nodesCounter++;

                if (!closed_list.containsKey(node.getKey()) && !open_list.containsKey(node.getKey())) {
                    if (node.getPoint().equals(goal)) {
                        cost= node.getCost();
                        return node.getPath().substring(1);
                    }
                    queue.add(node);
                    open_list.put(node.getKey(), node);
                }
            }
        }
        return "no path";
    }
    public int getNum() { return nodesCounter;}
    public int getCost() { return cost;}

}


