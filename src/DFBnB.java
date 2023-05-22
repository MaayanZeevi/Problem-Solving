import java.util.*;

public class DFBnB {

    private int nodesCounter =0 ;
    private int cost =0 ;
    private HashMap<String,Node> loop_avoidance_list;

    public String DFBnB_Algo(Node start , Node goal, Table table) {
        Stack<Node> dfs  = new Stack<>(); ///the stack for the dfs
        loop_avoidance_list = new HashMap<>(); // the hash for loop avoidance
        String result = "no path";
        int threshold = Integer.MAX_VALUE;

        start.setDad(null);
        start.markAsNotOut();
        dfs.add(start); loop_avoidance_list.put(start.getKey(), start); // add to the stack and hash

        while(!dfs.isEmpty()) {
            if(table._isWithOpen) table.printList(loop_avoidance_list);
            Node n = dfs.pop();
            if(n.isOut()) {
//                n.markAsNotOut();
                loop_avoidance_list.remove(n.getKey());
            }
            else {
                n.markAsOut();;
                dfs.add(n); // add with mark out
                ArrayList<Node> neighbors =table.getNeighborsWithHeuristic(n, goal.getPoint(), table._isOldFirst); // move on neighbors
                nodesCounter += neighbors.size();
                neighbors.sort( new NodeComparator()); // sort

                for (int i = 0; i < neighbors.size(); i++) {
                    Node g = neighbors.get(i);
                    g.setDad(n.getPoint()); // set g dad to n
                    if((g.function() > threshold) || (g.function() +g.getCost() >= threshold + cost && result !=null)) { // we don't have what to search , so delete all node from g to end
                        int index = neighbors.indexOf(g);
                        for (int j = index; j < neighbors.size(); j++) {
                            neighbors.remove(j);
                            j--;
                        }
                    }
                    else if(loop_avoidance_list.containsKey(g.getKey())) {
                        Node gTag = loop_avoidance_list.get(g.getKey());
                        if(gTag.isOut()) {neighbors.remove(g); i--;}
                        else if(!gTag.isOut()) {
                            if(gTag.function() <= g.function())  {neighbors.remove(g); i--;}
                            else {dfs.remove(gTag); loop_avoidance_list.remove(gTag.getKey());} // when gTag.getF() > g.getF() ->its mean the we found the same node , only cheaper

                        }
                    }
                    else if(g.equals(goal)) {
                        if(g.function() < threshold ) {
                            threshold = g.function();
                            result = g.getPath().substring(1);
                            cost= g.getCost();
                        }
                        int index = neighbors.indexOf(g); // remove all the next neighbors
                        for (int j = index; j < neighbors.size(); j++) {
                            neighbors.remove(j);
                            j--;
                        }
                    }
                }
                neighbors = revers(neighbors); // Reveres the list of neighbors so when we do pop we out the node with small heuristic value
                for (Node what_stay : neighbors) {
                    dfs.add(what_stay);
                    loop_avoidance_list.put(what_stay.getKey(), what_stay);
                }
            }
        }
//        System.out.println("path:" + result);
//        System.out.println("cost:" + cost);
//        System.out.println("num:" + nodesCounter);
        return result;
    }
    //private function of reveres
    private ArrayList<Node> revers(ArrayList<Node> list) {
        ArrayList<Node> templist = new ArrayList<Node>();
        for (int i = list.size()-1; i >= 0; i--) {
            templist.add(list.get(i));
        }
        return templist;
    }
    public int getNum() { return nodesCounter;}
    public int getCost() { return cost;}
}
