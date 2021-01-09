package com.example.demo.tree;

import lombok.Data;

/**
 * @author LIJIALONG1
 */
@Data
public class TreeNode {
    public int val;
    public TreeNode leftTree;
    public TreeNode rightTree;

    public TreeNode(int val) {
        this.val = val;
    }

}
