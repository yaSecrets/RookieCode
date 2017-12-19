package com.madjava.thread.demo1;

/**
 * sleep状态下停止一个线程会进入catch语句，并且会清除停止状态值，设为false
 * 
 * @author Secrets
 *
 *         2017年12月9日下午2:34:51
 */
public class MyThread1 extends Thread
{

	@Override
	public void run()
	{
		super.run();

			try
			{
				System.out.println("run begin");
				Thread.sleep(20000);
				System.out.println("run end");
			} catch (InterruptedException e)
			{
				System.out.println("在沉睡中被停止"+this.isInterrupted());
				e.printStackTrace();
			}
			System.out.println("我在外面");
		
	}

	public static void main(String[] args)
	{
		try
		{
			MyThread1 thread = new MyThread1();
			thread.start();
			System.out.println("开始睡觉");
			Thread.sleep(20);
			thread.stop();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
