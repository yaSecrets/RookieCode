package com.madjava.tree;

import java.util.ArrayList;
import java.util.List;


/**
* @author mding
*    2017年11月29日
* Content:父节点表示法
 * @param <E>
 */
public class TreeParent<E> {
    public static class Node<T>{
        T data;
        //记录父节点的位置
        int parent;
        public Node() {
        }
        public Node(T data) {
            this.data = data;
        }
        public Node(T data, int parent) {
            this.data = data;
            this.parent = parent;
        }
         public String toString(){
             return "TreeParent$Node[data="+data+",parent="+parent+"]";
         }
    }
    
    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    //使用一个node数组来记录树里的所有节点
    private Node<E>[] nodes;
    //记录节点数
    private int nodeNums;
    //以指定根节点创建树
    public TreeParent(E data){
         treeSize = DEFAULT_TREE_SIZE;
         nodes = new Node[treeSize];
         nodes[0] = new Node(data,-1);
         nodeNums++;
    }
    //以指定根节点指定大小创建树
    public TreeParent(E data,int treeSize){
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNums++;
    }
    //为指定节点添加子节点
    public void addNode(E data,Node parent){
        for(int i = 0;i < treeSize;i++){
            // 找到数组中第一个null的元素，该元素保存新节点
            if (nodes[i] == null) {
                nodes[i] = new Node(data,pos(parent));
                nodeNums++;
                return;
            }
        }
        throw new RuntimeException("该树已满无法添加节点");
    }
    
    //判断树是否为空
    public boolean empty(){
        //根节点是否为null
        return nodes[0] == null;
    }
    
    /**
     * @return 返回根节点
     */
    public Node<E> root(){
        return nodes[0];
    }
    
    //返回指定节点(非根节点)的父节点
    public Node<E> parent(Node node){
        //每个节点都记录了父节点的位置
        return nodes[node.parent];
    }
    /**
     * @param parent
     * @return 返回指定节点的位置
     */
    private int pos(Node parent) {
        for (int i = 0; i < treeSize; i++) {
            //找到指定节点
            if(nodes[i] == parent){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * @param parent
     * @return 返回指定节点(非叶子节点)的所有子节点
     */
    public List<Node<E>> children(Node parent){
        List<Node<E>> list = new ArrayList<Node<E>>();
        for (int i = 0; i < treeSize; i++) {
            if(nodes[i] != null && nodes[i].parent == pos(parent)){
                list.add(nodes[i]);
            }
        }
        return list;
    }
    
    /**
     * @return 返回树的深度
     */
    public int deep(){
        //用于记录节点的最大深度
        int max = 0;
        for (int i = 0; i < treeSize && nodes[i] != null; i++) {
            //初始化本节点的深度
            int def = 1;
            int m = nodes[i].parent;//m指当前元素父节点的位置
            //如果父节点存在
            while(nodes[m].parent != -1){
                //向上继续搜索父节点
                m = nodes[m].parent;
                def++;
            }
            if(max < def){
                max = def;
            }
        }
        return max;
    }
 }      