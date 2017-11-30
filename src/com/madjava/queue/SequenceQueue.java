package com.madjava.queue;

import java.util.Arrays;


/**
* @author mding
*    2017年11月29日
* Content: 顺序队列
*
 */
public class SequenceQueue<T> {
    private int DEFAULT_SIZE = 10;
    //保存数组的长度
    private int capacity;
    //定义一个数组用来保存顺序线性表的元素
    private Object[] elementData;
    private int front = 0;
    private int rear = 0;
    /**
     *默认长度创建队列 
     */
    public SequenceQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    /**以一个元素初始化队列
     * @param element
     */
    public SequenceQueue(T element){
        this();
        elementData[0] = element;
        rear++;
    }
    
    /**指定长度创建队列
     * @param element
     * @param initSize
     */
    public SequenceQueue(T element,int initSize){
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }
    /**
     * @return 获取对列的大小
     */
    public int length(){
        return rear - front;
    }
    /**入列
     * @param element
     */
    public void add(T element){
        if(rear > capacity - 1){
            throw new IndexOutOfBoundsException("队列已满异常");
        }
        elementData[rear++] = element;
    }
    /**出列
     * @return
     */
    public T remove(){
        if(empty()){
            throw new IndexOutOfBoundsException("空队列异常");
        }
        //保留首端的值
        T oldValue = (T) elementData[front];
        elementData[front++] = null;
        return oldValue;
    }
    /**
     * @return 判断是否空队列
     */
    private boolean empty() {
        return rear == front;
    }
    /**
     * @return 返回队列front位置元素
     */
    public T element(){
        if(empty()){
            throw new IndexOutOfBoundsException("空队列异常");
        }
        return (T) elementData[front];
    }
    /**
     * 清空顺序队列
     */
    public void clear(){
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;
    }
    public String toString(){
        if(empty()){
            return "[]";
        }else {
            StringBuilder sb = new StringBuilder("[");
            for(int i = front;i < rear; i++){
                sb.append(elementData[i].toString()+",");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
