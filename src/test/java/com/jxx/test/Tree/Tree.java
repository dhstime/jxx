package com.jxx.test.Tree;
import java.util.*;
import	java.awt.SystemTray;

import java.util.Iterator;

public class Tree {
    //前序遍历
    public void preOrder(TreeNode node) {
        if(node==null){
            return;
        }else{//遍历顺序 根-左-右
            System.out.println(node.getData());
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }
    //中序遍历
    public void midOrder(TreeNode node){
        if(node==null){
            return;
        }else{//遍历顺序 左-根-右
            midOrder(node.getLeftChild());
            System.out.println(node.getData());
            midOrder(node.getRightChild());
        }
    }
    //后序遍历
    public void postOrder(TreeNode node){
        if(node == null){
            return;
        }else{//遍历顺序 左-右-根
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            System.out.println(node.getData());
        }

    }
    /**
     * 前序遍历非递归算法
     * output:A-B-D-E-C-F
     * 每访问一个结点后，在向左子树遍历下去之前，利用栈来记录该结点的右子女（如果有的话），
     * 以便在左子树退回时可以直接从栈顶取得右子树的根结点，继续其右子树的遍历
     */
    public void preOrder2(TreeNode node){
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(null);
        while (node != null) {
            // 访问根结点
            System.out.println("preOrderNonRecursive data:" + node.getData());
            // 当前结点右子树不为空则放入栈中
            if (node.getRightChild() != null)
                nodeStack.push(node.getRightChild());
            // 访问左子树
            if (node.getLeftChild() != null)
                node = node.getLeftChild();
            else node = nodeStack.pop();
        }
    }
    /**
     * 中序遍历非递归算法
     * output:D-B-E-A-F-C
     *从根结点开始沿着leftChild到最下角的结点，将指针依次压入栈中，
     * 直到该结点的leftChild指针为NULL。访问它的数据后，再遍历该结点的右子树。此时该结点为栈中推出的指针
     */
    public void midOrder2(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        do {
            while(node!=null){
                //将所有左节点压入栈中
                stack.push(node);
                node = node.getLeftChild();
            }
            if(!stack.isEmpty()){
                TreeNode pop = stack.pop();
                System.out.println(pop.getData());
                node = pop.getRightChild();
            }
        }while(node!=null ||!stack.isEmpty());
    }

    /**
     * 后序遍历非递归算法
     * output:D-E-B-F-C-A
     *从根结点开始沿着leftChild到最下角的结点，将指针依次压入栈中，直到该结点的leftChild指针为NULL。
     * 判断当前结点有无右子树，若有，则优先访问右子树
     * 无右子树货已经访问过右子树则访问当前结点
     */
    public void postOrder2(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode> ();
        //记录上一个节点
        TreeNode pre = node;
        do {
            while(node!=null){
                //将所有左节点压入栈
                stack.push(node);
                node = node.getLeftChild();
            }
            if(!stack.isEmpty()){
                //获取右子树但不弹出
                TreeNode temp = stack.peek().getRightChild();
                //如果不存在右子树则访问左子树
                if(temp==null ||temp==pre){
                    node = stack.pop();
                    System.out.println(node.getData());
                    //记录已经访问过的节点
                    pre=node;
                    //把节点置为空
                    node=null;
                }else{
                    //存在右子树则访问
                    node=temp;
                }
            }
        }while (node != null || !stack.isEmpty());
    }
    /**
     * 前序遍历创建二叉树
     * ABD##E##CF###
     *
     * @param data
     * @return
     */
    public TreeNode createBinaryTree(List<String> data){
        if(data==null ||data.size() ==0)
            return null;

        return createTreeManger(data.size(),data);

    }
    //前序遍历创建二叉树
    private TreeNode createTreeManger(int size, List<String> data) {
        TreeNode node = new TreeNode();
        TreeNode root = new TreeNode();
        while(data!=null&&data.get(0)!=null) {
            String s = data.get(0);
            int index = size - data.size();
                // 根结点
                if(s.equals("#")){
                    data.remove(0);
                }
                if (index == 0 ) {
                    node = new TreeNode(index, s);
                }
                data.remove(0);
                if(index+1<=data.size()) {
                    if (data.get(index) != "#") {
                        node.setLeftChild(new TreeNode(index, data.get(index)));
                        data.remove(index);
                    } else if (data.get(index + 1) != "#") {
                        node.setRightChild(new TreeNode(index + 1, data.get(index + 1)));
                        data.remove(index + 1);
                    }
                }
        }
        return node;
    }

}
