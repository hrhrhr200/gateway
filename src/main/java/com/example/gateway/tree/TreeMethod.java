package com.example.gateway.tree;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by hrhrh on 2020/8/20 15:43
 */
public class TreeMethod {

    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if(CollectionUtils.isEmpty(inputList)) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if(data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    public static void preOrderTraveralWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode treeNode = root;
        stack.push(treeNode);
        while(!stack.isEmpty()) {
            treeNode = stack.pop();
            stack2.push(treeNode);
            if(treeNode.leftChild != null) {
                stack.push(treeNode.leftChild);
            }
            if(treeNode.rightChild != null) {
                stack.push(treeNode.rightChild);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().data);
        }
    }

    public static void a2(TreeNode root) {
        if(null == root) {
            return;
        }
        System.out.println(root.data);
        if(root.leftChild != null) {
            System.out.println(root.leftChild.data);
        }
        if(root.rightChild != null) {
            System.out.println(root.rightChild.data);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(3,2,9,null,null,10,null,null,8,null,4));
        TreeNode root = createBinaryTree(inputList);
        a2(root);
    }
}
