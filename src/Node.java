public class Node<T extends Comparable> {
    public T key;
    Node p,left,right;

    public Node(T key){
        this.key = key;
        left = right = p = null;
    }
}
