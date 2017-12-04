package com.madjava.tree;

import java.util.ArrayList;
import java.util.List;


/**
* @author mding
*    2017年11月29日
* Content:子节点链表示法
 */
public class TreeChild<E> {
    private static class SonNode{
        //记录当前节点的位置
        private int pos;
        private SonNode next;
        public SonNode(int pos, SonNode next) {
            this.pos = pos;
            this.next = next;
        }
    }
    
    public static class Node<T>{
        T data;
        //记录第一个子节点
        SonNode first;
        public Node(T data) {
            super();
            this.data = data;
            this.first = null;
        }
        public String toString(){
            if(first == null){
                return "TreeChild$Node[data="+data+",first="+first.pos+"]";
            }else {
                return "TreeChild$Node[data="+data+",first=-1]";
            }
        }
    }
    
    private final   int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    //使用一个node数组来记录数组里的所有节点
    private Node<E>[] nodes;
    //记录节点树
    private int nodeNums;
    //以指定根节点创建树
    public TreeChild(E data){
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data);
        nodeNums++;
    }
    //以指定根节点指定treesize创建树
    public TreeChild(E data,int treeSize){
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data);
        nodeNums++;
    }
    //为指定节点添加子节点
    public void addNode(E data,Node parent){
        for (int i = 0; i < treeSize; i++) {
            //找到数组中第一个为null的元素，该元素保存新节点
            if(nodes[i] == null){
                //创建新节点并用指定数组来保存它
                nodes[i] = new Node<E>(data);
                if(parent.first == null){
                    parent.first = new SonNode(i, null);
                }else {
                    SonNode next = parent.first;
                    while(next.next != null){
                        next = next.next;
                    }
                    next.next = new SonNode(i, null);
                }
                nodeNums++;
                return;
            }
        }
        throw new RuntimeException("");
    }
    //判断树是否为空
    public boolean empty(){
        //根节点是否为null
        return nodes[0] == null;
    }
    //返回根节点
    public Node<E> root(){
        return nodes[0];
    }
    //返回指定节点(非叶子节点)的所有子节点
    public List<Node<E>> children(Node parent){
        List<Node<E>> list = new ArrayList<Node<E>>();
        //获取parent节点的第一个子节点
        SonNode next = parent.first;
        //沿着孩子链不断搜索下一个孩子节点
        while(next != null){
            //添加孩子链中的节点 
            Node<E> node = nodes[next.pos];
            list.add(node);
            next = next.next;
        }
        return list;
    }
    
    /**获取指定节点的第index个子节点
     * @param parent
     * @param index
     * @return
     */
    public Node<E> child(Node parent,int index){
        //获取parent节点的第一个子节点
        SonNode next = parent.first;
        //沿着孩子链不断搜索下一个孩子节点
        for (int i = 0; next != null; i++) {
            if(index == i){
                return nodes[next.pos];
            }
            next = next.next;
        }
        return null;
    }
    /**
     * @return 返回树的深度
     */
    public int deep(){
        return deep(root());
    }
    /**这是一个递归方法，每棵子树的深度为其所有字数的最大深度+1
     * @param root
     * @return
     */
    private int deep(Node<E> node) {
        if(node.first == null){
            return 1;
        }else {
            //记录其所有子树的最大深度
            int max = 0;
            SonNode next = node.first;
            //沿着孩子链不断搜索下一个孩子节点
            while(next != null){
                int tmp = deep(nodes[next.pos]);
                if(tmp > max){
                    max = tmp;
                }
                next = next.next;
            }
            //最后返回所有子树的最大深度+1
            return max +1;
        }
    }
}