import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class A_Star {
    private static int nodesCounter = 0; //count nodes amount
    private Point goalPoint;
    private int cost = 0; //calculate cost

    public String a_start_algorithm(Node start, Node goal, Table table) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComparator());
        HashMap<String, Node> open_list = new HashMap<>();
        ;
        HashMap<String, Node> closed_list = new HashMap<>(); // the hash for loop avoidance
        goalPoint = goal.getPoint();
        priorityQueue.add(start);
        open_list.put(start.getKey(), start);
        nodesCounter++;
        while (!priorityQueue.isEmpty()) {
            if(table._isWithOpen) table.printList(open_list);
            Node currNode = priorityQueue.poll();
            if (currNode.equals(goal)) {
                cost= currNode.getCost();
                return currNode.getPath().substring(1);
            }
            closed_list.put(currNode.getKey(), currNode);
            ArrayList<Node> neighbors = table.getNeighborsWithHeuristic(currNode, goal.getPoint(), table._isOldFirst);
            for (Node node : neighbors) {
                node.setDad(currNode.getPoint());
                nodesCounter++;

                if (!open_list.containsKey(node.getKey()) && !closed_list.containsKey(node.getKey())) {
                    open_list.put(node.getKey(), node);
                    priorityQueue.add(node);
                } else if (open_list.containsKey(node.getKey()) && open_list.get(node.getKey()).getCost() > node.getCost()) {
                    Node nodeToRemove = open_list.remove(node.getKey());
                    open_list.put(node.getKey(), node);
                    priorityQueue.remove(nodeToRemove);
                    priorityQueue.add(node);
                }
            }

        }

        return "no path";
    }
    public int getNum() { return nodesCounter;}
    public int getCost() { return cost;}
}