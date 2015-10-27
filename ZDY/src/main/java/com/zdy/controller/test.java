package com.zdy.controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class test {
	public static void main(String[] args) {
		//System.out.println((int)Math.ceil((float)11/(float)10));
		//求从10到100中能被3或5整除的数的和
		int count = 0;
		for (int i = 10; i <=100; i++) {
			if(i%3==0||i%5==0){
				count+=i;
			}
		}
		System.out.println(count);
		//将一个字符串逆序，不要使用反转函数
		String message = "he saw a racecar";
		String result = "";
		for (int i = message.length()-1; i >=0; i--) {
			result+=message.charAt(i);
		}
		System.out.println(result);
		//反转一个栈
		/*
		 * 栈（后进先出）
		 * java中stack的使用方法，堆栈是一种"后进先出"（LIFO） 的数据结构， 只能在一端进行插入（称为"压栈"） 或删除 （称为"出栈"）
		 * 方法：  1. public push  （item ）  把项 压入栈顶。其作用与 addElement (item ) 相同。

			参数 item 压入栈顶的项 。 返回： item 参数 ；
			
			2. public pop () 移除栈顶对象，并作为函数的值 返回该对象。
			
			返回：栈顶对象（Vector 对象的中的最后一项）。
			
			抛出异常 ： EmptyStackException 如果堆栈式空的 。。。
			
			3. public peek() 查看栈顶对象而不移除它。。
			
			返回：栈顶对象（Vector 对象的中的最后一项）。
			
			抛出异常 ： EmptyStackException 如果堆栈式空的 。。。
			
			4. public boolean empty （测试堆栈是否为空。）  当且仅当堆栈中不含任何项时 返回 true，否则 返回 false.
			
			5. public int search  (object o)  返回对象在堆栈中位置， 以 1 为基数， 如果对象 ｏ是栈中的一项，该方法返回距离　栈顶最近的出现位置到栈顶的距离；　栈中最上端项的距离为　１　。　使用equals 方法比较 o 与 堆栈中的项。。。   
		 */
		Stack stack = new Stack();//he is at the bottom of the stack
		stack.push("he");      
		stack.push("saw");
		stack.push("a");
		stack.push("racecar");
		reverseStack(stack);
		while(stack.size()>0) System.out.println(stack.pop());
	}
	/*
	 * 队列（先进先出）
	 * Queue接口与List、Set同一级别，都是继承了Collection接口。LinkedList实现了Queue接 口
	 * 
	 * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
		remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
		element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
		offer       添加一个元素并返回true       如果队列已满，则返回false
		poll         移除并返问队列头部的元素    如果队列为空，则返回null
		peek       返回队列头部的元素             如果队列为空，则返回null
		put         添加一个元素                      如果队列满，则阻塞
		take        移除并返回队列头部的元素     如果队列为空，则阻塞
	 */
	public static void reverseStack(Stack stack){
		Queue rev = new LinkedList();
        while(stack.size()>0){
        	rev.offer(stack.pop());
        }
        while(rev.size()>0){
        	stack.push(rev.poll());
        }
	}
}
