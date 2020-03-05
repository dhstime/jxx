package com.jxx.test.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String [] args){
        String [] a = new String []{"A","B","D","#","#","E","#","#","C","F","#","#"};
        List<String> list2 = Arrays.asList(a);
        List<String>list = new ArrayList<>();
        for (String s : list2) {
            list.add(s);
        }
        Tree tree = new Tree();
//        TreeNode root = tree.createBinaryTree(list);
//        tree.postOrder(root);
        TreeNode root = new TreeNode(0,"A");
        TreeNode left = new TreeNode(1,"B");
        TreeNode right = new TreeNode(2,"C");
        root.setLeftChild(left);
        root.setRightChild(right);
        System.out.println("前序遍历:");
//        tree.preOrder(root);
//        tree.preOrder2(root);
        System.out.println("中序遍历:");
//        tree.midOrder(root);
        tree.midOrder2(root);
        System.out.println("后序遍历");
//        tree.postOrder(root);
//        tree.postOrder2(root);
    }
}
