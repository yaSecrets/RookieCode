package com.madjava.thread.demo1;

/**
 * 使用interrupt方法中断线程，但不会真的停止线程，而是在当前线程打了一个停止的标记。
 * 
 * @author Secrets
 *
 *         2017年12月9日下午2:34:51
 */
public class MyThread extends Thread
{

	@Override
	public void run()
	{
		super.run();
		for (int i = 0; i < 500000; i++)
		{
			try
			{
				if (this.interrupted())
				{
					System.out.println("已经是停止状态，我要退出了");
					throw new InterruptedException("end");
				}
			} catch (InterruptedException e)
			{
				System.out.println(i);
				e.printStackTrace();
			}
		}
		System.out.println("我在for循环下");
	}

	public static void main(String[] args)
	{
		try
		{
			MyThread thread = new MyThread();
			thread.start();
			System.out.println("开始睡觉");
			Thread.sleep(20);
			thread.interrupt();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
