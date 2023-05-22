import java.util.ArrayList;
import java.util.HashMap;

public class DFID {
    private int nodesCounter = 0;
    private int cost = 0;
    private HashMap<String, Node> h;// = new HashMap<>();
    private Table _table;

    public String dfid_algorithm(Node start, Point goal, Table table) {
        _table = table;
        HashMap<String, Node> h = new HashMap<>();
        nodesCounter++; //count +1 for the start node
        for (int depth = 1; depth < Integer.MAX_VALUE; depth++) {
            String result = limited_dfid(start, goal, depth, h);
            h.clear();
            if (!result.equals("cutoff")) return result;
        }
        return "no path";
    }

    public String limited_dfid(Node node, Point goal, int limit, HashMap<String, Node> h) {
        if (node.getPoint().equals(goal)) {
            cost=node.getCost();
            return node.getPath().substring(1);
        } else if (limit == 0) return "cutoff"; // the sign we move on all the graph in the given depth
        else {
            h.put(node.getKey(), node); // add this node
            boolean isCutOff = false;
            ArrayList<Node> neighbors = _table.getNeighbors(node);
            for (Node n : neighbors) {
                n.setDad(node.getPoint());
                nodesCounter++;
                if (!h.containsKey(n.getKey())) {
                    String result = limited_dfid(n, goal, limit - 1, h);
                    if (result.equals("cutoff")) isCutOff = true;
                    else if (!result.equals("fail")) return result;
                }
            }
            if(_table._isWithOpen) _table.printList(h);
            h.remove(node.getKey());
            if (isCutOff == true) return "cutoff";
            else return "fail";

        }
    }
    public int getNum() { return nodesCounter;}
    public int getCost() { return cost;}
}

