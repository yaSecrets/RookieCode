package com.madjava.linearList;



/**单链表
 * @author Secrets
 *
 * 2017年11月28日下午8:49:11
 */
public class LinkedList<T>
{
	//定义一个Node,Node实例代表链表的节点
	private class Node{
		//保存节点的数据
		private T data;
		//指向下个节点的引用
		private Node next;
		//无参数的构造器
		public Node(){
			
		}
		public Node(T data, LinkedList<T>.Node next)
		{
			super();
			this.data = data;
			this.next = next;
		}
	}
	//保存链表的头节点
	private Node header;
	//保存链表的尾节点
	private Node tail;
	//保存链表已含有的节点数
	private int size;
	//创建空链表
	public LinkedList(LinkedList<T>.Node header, LinkedList<T>.Node tail, int size)
	{
		//空链表，head和tail都是null
		this.header = null;
		this.tail = null;
	}
	//以指定元素来创建链表，该链表只有一个元素
	public LinkedList(T element){
		header = new Node(element,null);
		//只有一个节点，head和tail都指向该节点
		tail = header;
		size++;
	}
	//返回链表的长度
	public int length(){
		return size;
	}
	//获取链式线性表中索引为index处的元素
	public T get(int index){
		return getNodeByIndex(index).data;
	}
	/**根据索引获取指定位置的节点
	 * @param index
	 * @return
	 */
	private Node getNodeByIndex(int index)
	{
		if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
		//从header节点开始
		Node current = header;
		for(int i = 0;i < size && current != null;i++,current = current.next){
			if(i == index){
				return current;
			}
		}
		return null;
	}
	
	/**查找链式线性表中指定元素的索引
	 * @param element
	 * @return
	 */
	public int locate(T element){
		//从节点开始搜索
		Node current = header;
		for(int i = 0;i < size && current != null;i++,current = current.next){
			if(current.data.equals(element)){
				return i;
			}
		}
		return -1;
	}
	
	/**指定位置插入一个元素
	 * @param element
	 * @param index
	 */
	public void insert(T element,int index){
		if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
		//如果还是一个空链表
		if(header == null){
			add(element);
		}else{
			//当index为0时，即在表头插入
			if (index == 0)
			{
				addAtHeader(element);
			}else {
				//获取插入的前一个节点
				Node prev = getNodeByIndex(index - 1);
				//让prev的next指向新节点，让新节点的next指向原来prev的下一个节点
				prev.next = new Node(element,prev.next);
				size++;
			}
		}
		
	}
	/**采用头插法为链表添加新节点
	 * @param element
	 */
	private void addAtHeader(T element)
	{
		header = new Node(element,header);
		//如果插入之前是空链表
		if(tail == null){
			tail = header;
		}
		size++;
	}
	/**采用尾插法为链表添加加节点
	 * @param element
	 */
	private void add(T element)
	{
		//如果该链表还是空链表
		if(header == null){
			header = new Node(element,null);
			//只有一个节点，header和tail都指向该节点
			tail = header;
		}else{
			//创建新节点
			Node newNode = new Node(element,null);
			//让尾节点指向新节点
			tail.next = newNode;
			//以新节点作为新的尾节点
			tail = newNode;
		}
		size++;
	}
	/**删除指定索引处的元素
	 * @param index
	 * @return
	 */
	public T delete(int index){
		if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
		Node del = null;
		//如果被删除的是header节点
		if(index == 0){
			del = header;
			header = header.next;
		}else {
			//获取删除的前一个元素的节点
			Node prev = getNodeByIndex(index - 1);
			//获取将要删除的元素
			del = prev.next;
			prev.next = del.next;
			del.next = null;
		}
		size--;
		return del.data;
	}
	/**删除链式表中的最后一个元素
	 * @return
	 */
	public T remove(){
		return delete(size - 1);
	}
	/**判断链式表是否为空表
	 * @return
	 */
	public boolean empty(){
		return size == 0;
	}
	/**
	 * 清空链式表
	 */
	public void clear(){
		header = null;
		tail = null;
		size = 0;
	}
	public String toString(){
		//链表为空
		if(empty()){
			return "[]";
		}else {
			StringBuilder sb = new StringBuilder();
			for (Node current = header; current != null; current = current.next)
			{
				sb.append(current.data.toString()+",");
			}
			int len = sb.length();
			return sb.delete(len - 2, len).append("]").toString();
		}
	}
}
