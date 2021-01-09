package com.example.demo.listnode;

import lombok.Data;

/**
 * @author : coder
 * @create 2020/10/28 15:59
 */
@Data
public class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

}
