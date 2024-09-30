package Tree;

public class MainClass {
    public static void main(String[] args) {
        TreeBinary tree = new TreeBinary();

        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(10);
        tree.add(11);

        int maxValue = tree.FindMax(tree.root);

        System.out.println("Maximum Value is :"+maxValue);

        int depthOfTree = tree.CalculateDepthOfTree(tree.root);

        System.out.println("Depth of the Tree is :"+depthOfTree);
    }
}
