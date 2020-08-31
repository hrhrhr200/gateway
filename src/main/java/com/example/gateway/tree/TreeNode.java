package com.example.gateway.tree;

import lombok.Data;

/**
 * Created by hrhrh on 2020/8/20 15:41
 */
@Data
public class TreeNode {

    int data;
    TreeNode leftChild;
    TreeNode rightChild;

    public TreeNode(int data) {
        this.data = data;
    }
}
