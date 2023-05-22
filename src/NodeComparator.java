import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        int compareResult=Integer.compare(n1.function(), n2.function());
        /* if the nodes have the same value of f() and it also new-first, then the order will be decrease
              on the node's counter which indicator to know which node generate first
              (as the indicator is greater the node is newer
         */
        if(compareResult==0){
            if(n1.isOldFirst()){
                return Integer.compare(n1.getNodeAge(), n2.getNodeAge());
            }
            else{
                return Integer.compare(n2.getNodeAge(), n1.getNodeAge());
            }
        }

        return compareResult;
    }
}
