package Tree;

class Node {

    int values;
    Node left;
    Node right;

    public Node(int value){
        this.values = value;
        this.left = null;
        this.right=null;

    }

}

class TreeBinary{

    Node root;

    public TreeBinary(){
        root = null;
    }

    public void add(int value){
        root = addNode(root,value);
    }

    private Node addNode(Node current,int value){
        if(current == null){
            return new Node(value);
        }

        if(value < current.values){
            current.left = addNode(current.left,value);
        }

        if(value > current.values){
            current.right = addNode(current.right,value);
        }

        return current;
    }

    public void InOrderTraversal(Node current){
        if(current != null){
            InOrderTraversal(current.left);
            System.out.println("Node :"+current.values+ " ");
            InOrderTraversal(current.right);
        }
    }

    public void PreOrderTraversal(Node current){
        if(current != null){
            System.out.println("Node :"+current.values+ " ");
            PreOrderTraversal(current.left);
            PreOrderTraversal(current.right);
        }
    }

    public void PostOrderTraversal(Node Current){
        if(Current != null){
            PostOrderTraversal(Current.left);
            PostOrderTraversal(Current.right);
            System.out.println(Current.values+" ");
        }
    }

    public  int CalculateDepthOfTree(Node node){
        if(node == null){
            //Depth Of Empty Tree is 0 so returning 0
            return 0;
        } else {
            //Calculating Left Depth
            int depthLeft = CalculateDepthOfTree(node.left);
            //Calculate Right Depth
            int depthRight = CalculateDepthOfTree(node.right);

            return  Math.max(depthLeft,depthRight) +1;
        }


    }

    public int FindMax(Node node){
        if(node == null){
            return 0;
        }

        int leftMax = FindMax(node.left);
        int rightMax = FindMax(node.right);

        return Math.max(node.values,Math.max(leftMax,rightMax));


    }
}
