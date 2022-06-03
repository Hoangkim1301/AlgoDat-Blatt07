public class SearchTree <T extends Comparable<T> > {
    //Root of the binary tree
    public Node<T> root;
    //Construct
    public SearchTree(){
        this.root = null;
    }

    //Aufgabe 01 a) insert
    public void tree_insert(T z){
        //replicate root
        Node<T> y = root;
        //temp is Node with z the key value
        Node<T> temp = new Node<>(z);
        while (y != null) {
            if(z.compareTo(y.key)==0){
                System.out.println("The value "+ z + " is already exist in the original tree");
                return;
            }
            //update parent from temp
            temp.p = y ;
            if(z.compareTo(y.key)<0){
                y=y.left;
            }else if(z.compareTo(y.key)>0){
                y=y.right;
            }
        }
        //if this tree is an empty BST
        if(root == null){
            root = temp;
        }else{
            //point the parent to the correct left or right tree of the child
            Node<T> parent = temp.p;
            if(temp.key.compareTo(parent.key)<0){
                parent.left = temp;
            }else{
                parent.right = temp;
            }
        }
        return;
    }

    public void delete(T z){
        root = tree_delete(root,z);
    }

    public Node<T> tree_delete(Node<T> root,T z) {
        Node<T> temp = new Node<>(z);
        Node<T> y = root;
        //the BST is empty, no value
        if (y == null) {
            return root;
        }
        //update the parent root to the y pointer by calling recursive
        //find the position of the Node to be deleted
        if (z.compareTo(y.key) < 0) {
            y.left = tree_delete(y.left,z);
        }else if(z.compareTo(y.key) > 0) {
            y.right = tree_delete(y.right,z);
        }else if(z.compareTo(y.key) == 0) {              //if the value to be deleted is the root key
            //Node with 1 child or no child
            if (y.left == null) {
                return y.right;
            } else if (y.right == null) {
                return y.left;
            }
            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            y.key = (T) getMinimum(y.right);
            y.right = tree_delete(y.right,y.key);
        }
        return y;
    }

    //Help-Function for Aufgabe 01 tree_delete
    public T getMinimum(Node<T> y){
        T val = y.key;
        while(y.left != null){
            y = y.left;
            val = y.key;
        }
        return val;
    }

    //Aufgabe 1 b) search
    public void search(T k){
        Node<T> y = root;
        if(y.key.compareTo(k)==0){
            System.out.println("The value: "+ k +" is in the tree");
            return;
        }
        while(y!=null && y.key.compareTo(k)!=0){
            if(k.compareTo(y.key)<0){
                y=y.left;
            }else if(k.compareTo(y.key)>0){
                y=y.right;
            }
        }
        System.out.println("The value: "+ k +" is not in the tree");
        return;
    }

    //Aufgabe 1 c) min and max
    public void min(){
        Node<T> y = root;
        while(y.left!=null){
            y = y.left;
        }
        System.out.println("The min value is " + y.key);
        return;
    }

    public void max(){
        Node<T> y = root;
        while(y.right!=null){
            y = y.right;
        }
        System.out.println("The max value is " + y.key);
        return;
    }

    //Aufgabe 01 d)
    public void arrToBST(T[] a, int left, int right){
        root = sortedArrayToBST(a,left,right);
    }

    public Node<T> sortedArrayToBST(T[] a, int left, int right){
        //if the left index is equal index right
        if(left>right){
            return null;
        }
        //find the middle index element of an array
        int mid =  (int) (right + left)/2;
        Node<T> y = new Node<>(a[mid]);

        y.left = sortedArrayToBST(a,left,mid-1);
        y.right = sortedArrayToBST(a,mid+1,right);

        return y;
    }

    //print the BST
    public void print(){
        inorder_tree_walk(root);
    }

    public void inorder_tree_walk(Node root){
        if(root != null){
            inorder_tree_walk(root.left);
            System.out.print(root.key + " ");
            inorder_tree_walk(root.right);
        }
    }

    public void print2(){
        preorder_tree_walk(root);
    }
    public void preorder_tree_walk(Node root){
        if(root != null){
            System.out.print(root.key + " ");
            preorder_tree_walk(root.left);
            preorder_tree_walk(root.right);
        }
    }

    public static void main(String[] args) {
        SearchTree<Integer> test = new SearchTree<>();
        test.tree_insert(2);
        test.tree_insert(6);
        test.tree_insert(7);
        test.tree_insert(2);
        test.tree_insert(5);
        test.tree_insert(1);
        test.tree_insert(6);
        //test.print();
        test.delete(2);
        test.print();
        System.out.println();
        test.search(3);
        test.min();
        test.max();

        SearchTree<Integer> arr_test = new SearchTree<>();
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        System.out.print("The sorted array in BST is: ");
        arr_test.arrToBST(arr,0,arr.length-1);
        arr_test.print2();
    }
}
