package com.madjava.stack;

import java.util.Arrays;

/**
* @author mding
*    2017年11月29日
* Content: 顺序栈
 */
public class SequenceStack<T> {
    private int DEFAULT_SIZE = 10;
    //保存数组的长度
    private int capacity;
    //底层数组长度不够时，每次增加的长度
    private int capacityIncrement;
    //定义一个数组用来保存顺序线性表的元素
    private Object[] elementData;
    //保存顺序表中元素的当前个数;
    private int size = 0;
    //以默认数组长度创建爱顺序栈
    public SequenceStack() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    //以一个元素来创建顺序栈
    public SequenceStack(T element) {
            this();
            elementData[0] = element;
            size++;
    }
    //以指定长度来创建顺序栈
    public SequenceStack(T element,int initSize){
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }
    
    /**以指定长度来创建顺序栈
     * @param element 顺寻栈的第一个元素
     * @param initSize 顺序栈底层数组的长度
     * @param capacityIncrement 指定数组长度不够时，每次增加的长度
     */
    public SequenceStack(T element,int initSize,int capacityIncrement){
        this.capacity = initSize;
        this.capacityIncrement = capacityIncrement;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }
    /**获取栈的大小
     * @return
     */
    public int length(){
        return size;
    }
    /**入栈
     * @param element
     */
    public void push(T element){
        //确认数组容量
        ensureCapacity(size+1);
        elementData[size++] = element;
    }
    /**扩容
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity) {
        //如果数组原有长度小于所需长度，则需要扩充
        if(minCapacity < capacity){
            if(capacityIncrement > 0){//如果递增
                while (capacity < minCapacity) {
                    //不断将增加capacityIncrement长度，直到大于最小容量
                    capacity += capacityIncrement;
                }
            }else {
                while(capacity < minCapacity){
                    capacity <<= 1;
                }
            }
        }
         // 复制指定的数组，截取或用 null 填充（如有必要），以使副本具有指定的长度。
        elementData = Arrays.copyOf(elementData, capacity);
    }
    /**出栈
     * @return 返回出栈的元素
     */
    public T pop(){
        T oldValue = (T) elementData[size-1];
        elementData[size--] = null;
        return oldValue;
    }
    
    /**
     * @return 返回栈顶元素，但不删除
     */
    public T peek(){
        return (T) elementData[size - 1];
    }
    /**
     *清空顺序栈
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