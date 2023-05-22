import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 *
 * @author Maayan Sulimani,
 *
 * IDA* algorithm
 * Each iteration is a depth-first search that
 * keeps track of the cost evaluation f = g + h
 * of each node generated.
 * If a node is generated whose cost exceeds the threshold for that iteration, its path is cutoff.
 * The cost threshold is initialized to the
 * heuristic of the initial state.
 * The cost threshold increases in each iteration to the total cost of the lowest-cost node that was
 * pruned during the previous iteration.
 * The algorithm terminates when a goal state is
 * generated whose total cost does not exceed the current threshold.
 *
 *
 * */

public class IDA_Star {

    private int num =0 ;
    private int cost =0 ;
    private static HashMap<String,Node> loop_avoidance_list;



    public String IDAStar_Al(Node start , Node goal, Table table) {
        Stack<Node> dfs  = new Stack<>(); ///the stack for the dfs
        loop_avoidance_list = new HashMap<>(); // the hash for loop avoidance

        int threshold = start.heuristicFunc(goal.getPoint()); //the heuristic count
        start.setDad(null);
        while(threshold != Integer.MAX_VALUE) {
            int minF =Integer.MAX_VALUE; // init minf to inf
            dfs  = new Stack<>(); loop_avoidance_list = new HashMap<>(); // init the stack and hash
            start.markAsNotOut();
            dfs.add(start);
            loop_avoidance_list.put(start.getKey(), start); // add start node to stack and hash

            while(!dfs.isEmpty()) {
                if(table._isWithOpen) table.printList(loop_avoidance_list);
                Node n = dfs.pop();//out the lest thing that came in to stack
                if(n.isOut()) {
                    n.markAsNotOut();
                    loop_avoidance_list.remove(n.getKey());
                }

                else {
                    n.markAsOut();
                    dfs.add(n);
                    ArrayList<Node> neighbors = table.getNeighbors(n); // move on neighbors
                    for (Node g : neighbors) {
                        num++;//+1 to number of node we created
                        g.setDad(n.getPoint()); // set g dad to the node we out from the queue
                        if(g.function() > threshold) { // if the heuristic value is big then the threshold
                            minF = Math.min(minF, g.function()); // check the big and smallest
                            continue;
                            //continue with the next operator
                        }
                        if(loop_avoidance_list.containsKey(g.getKey())) {
                            Node gTag = loop_avoidance_list.get(g.getKey());
                            if(gTag.isOut()) continue;
                            if(!gTag.isOut()) {
                                if(gTag.function() > g.function()) {
                                    loop_avoidance_list.remove(gTag.getKey());
                                    dfs.remove(gTag); // remove the node in the heuristic value bigger then the current node
                                }
                            }
                            else continue;
                        }
                        if(g.equals(goal)) {
                            cost=g.getCost();
                            return g.getPath().substring(1);
                        }
                        dfs.add(g);
                        loop_avoidance_list.put(g.getKey(), g);
                    }
                }
            }
            threshold = minF; //Update the threshold
        }
        return "no path";
    }
    public int getNum() { return num;}
    public int getCost() { return cost;}

}


