package com.madjava.linearList;

import java.util.Arrays;

public class SequenceList<T> {
    private int DEFAULT_SIZE = 16;
    //保存数组的长度
    private int capacity;
    //定义一个数组用来保存顺序线性表的元素
    private Object[] elementData;
    //保存顺序表中元素的当前个数;
    private int size = 0;
    //以默认数组长度创建空顺序线性表
    public SequenceList() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    
    //以一个初始元素初始化线性表
    public SequenceList(T element){
        this();
        elementData[0] = element;
        size++;
    }
    
    /**以指定长度数组来创建线性表
     * @param element 指定线性表中的第一个元素
     * @param initSize 指定线性表底层数组的长度
     */
    public SequenceList(T element,int initSize){
        capacity = 1;
        //把capacity设置为大于initSize的最小2的n次方
        while(capacity < initSize){
            capacity <<= 1;
        }
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }
    
    /**获取线性表中的大小
     * @return
     */
    public int length(){
        return size;
    }
    
    /**获取线性表索引为i处的元素
     * @param i
     * @return
     */
    public T get(int i){
        if(i < 0 || i > size - 1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        return (T) elementData[i];
    }
    
    /**查找线性表中指定元素的索引
     * @param element
     * @return
     */
    public int locate(T element){
        for(int i = 0;i < size;i++){
            if(elementData[i].equals(element)){
                return i;
            }
        }
        return -1;
    }
    
    /**向指定位置插入一个元素
     * @param element
     * @param index
     */
    public void insert(T element,int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        ensureCapacity(size+1);
        //这是一个native方法
        System.arraycopy(elementData, index, elementData, index+1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     *  扩充数组的长度
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity) {
        //如果数组的原有长度小于目前所需的长度
        if(minCapacity > capacity){
            while(capacity < minCapacity){
                capacity <<= 1;
            }
            //复制指定数组，并使副本具有指定的长度
            elementData = Arrays.copyOf(elementData, capacity);
        }
    }
    
    /**将指定元素添加到列表的尾部
     * @param element
     */
    public void add(T element){
        insert(element,size);
    }
    
    /**删除线性表中指定索引的元素
     * @param index
     * @return 返回删除的元素
     */
    public T delete(int index){
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        T oldValue = (T) elementData[index];
        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        //清空最后一个元素
        elementData[--size] = null;
        return oldValue;
    }
    
    /**删除最后一个元素
     * @return
     */
    public T remove(){
        return delete(size - 1);
    }
    
    /**判断线性表是否是空表
     * @return
     */
    public boolean empty(){
        return size == 0;
    }
    
    /**
     * 清空线性列表
     */
    public void clear(){
        Arrays.fill(elementData, null);
        size = 0;
    }
    public String toString(){
        if(size == 0){
            return "[]";
        }else{
            StringBuilder sb = new StringBuilder("[");
            for(int i = 0;i < size; i++){
                sb.append(elementData[i].toString()+",");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
