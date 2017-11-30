package com.madjava.linearList;

public class SequenceListTest
{
	public static void main(String[] args)
	{
		SequenceList<String> list = new SequenceList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		//在索引1出插入一个新元素
		list.insert("ddd", 1);
		System.out.println(list);
		//删除索引为2处的元素
		list.delete(2);
		System.out.println(list);
		System.out.println("ccc在线性表中的位置：" + list.locate("ccc"));
	}
}
