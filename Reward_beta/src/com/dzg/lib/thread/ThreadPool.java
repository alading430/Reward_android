package com.dzg.lib.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.dzg.lib.app.Settings;

import android.os.Process;
import android.util.Log;


/**
 * 类ThreadPool.java的实现描述：<BR>
 * 使用异步线程需要统一管理<BR>
 * 整个App维护两个线程池，一个主线程池，一个高优先级线程池<BR>
 * 主线程池用于执行后台任务，高优先级会立即启动一个线程<BR>
 */
public class ThreadPool
{
	// 记录日志标识符
	private static final String TAG = "ThreadPool";

	// 主线程池核心线程数大小
	private static final int MAIN_CORE_POOL_SIZE = 4;

	// 主线程池最大线程数大小
	private static final int MAIN_MAX_POOL_SIZE = 8;

	// 高优先级线程池核心线程数大小
	private static final int HIGH_CORE_POOL_SIZE = 2;

	// 高优先级线程池最大线程数
	private static final int HIGH_MAXPOOLSIZE = 4;

	// 主线程池队列大小
	private int MAIN_QUEUE_POOL_SIZE = 4;

	// 高优先级线程池队列大小
	private int HIGHT_PRIORITY_QUEUE_POOL_SIZE = 1;

	// 主线程池
	private ThreadPoolExecutor mainThreadpool;

	// 高优先级线程
	private ThreadPoolExecutor highPriorityThreadpool;

	// 标识调度线程是否正在执行
	private boolean running;

	// 低优先级线程
	private static class LowPriorityThread extends Thread
	{

		private LowPriorityThread(Runnable r,String name)
		{
			super(r,name);
		}

		public void run()
		{
			// 设置为后台运行线程
			Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
			super.run();
		}
	}

	// 主线程工厂
	private static final ThreadFactory mainPriorityThreadFactory = new ThreadFactory()
	{
		private final AtomicInteger mCount = new AtomicInteger(1);

		public Thread newThread(Runnable r)
		{
			Thread thread = new LowPriorityThread(r,"LowPriorityAsyncTask #" + mCount.getAndIncrement());
			// 设置低优先级线程
			thread.setPriority(Thread.MIN_PRIORITY);

			return thread;
		}
	};

	// 高优先级线程工厂
	private static final ThreadFactory hightPriorityThreadFactory = new ThreadFactory()
	{

		private final AtomicInteger mCount = new AtomicInteger(1);

		public Thread newThread(Runnable r)
		{
			return new Thread(r,"HighPriorityAsyncTask #" + mCount.getAndIncrement());
		}
	};

	// 单例
	private static ThreadPool instance = new ThreadPool();

	/**
	 * 获取单例
	 * 
	 * @return
	 */
	public static final ThreadPool instance()
	{
		return instance;
	}

	/**
	 * 构造方法
	 */
	private ThreadPool()
	{
		this(MAIN_CORE_POOL_SIZE,MAIN_MAX_POOL_SIZE,HIGH_CORE_POOL_SIZE,HIGH_MAXPOOLSIZE);
	}

	/**
	 * 构造方法
	 * 
	 * @param mainCoreSize
	 * @param mainMaxSize
	 * @param highCoreSize
	 * @param highMaxSize
	 */
	private ThreadPool(int mainCoreSize,int mainMaxSize,int highCoreSize,int highMaxSize)
	{
		init(mainCoreSize,mainMaxSize,highCoreSize,highMaxSize);
	}

	/**
	 * 线程池初始化
	 * 
	 * @param mainCoreSize
	 * @param mainMaxSize
	 * @param highCoreSize
	 * @param highMaxSize
	 */
	private void init(int mainCoreSize,int mainMaxSize,int highCoreSize,int highMaxSize)
	{
		// 低优先级线程池
		mainThreadpool = new ThreadPoolExecutor(mainCoreSize,mainMaxSize,20,TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(MAIN_QUEUE_POOL_SIZE),mainPriorityThreadFactory,new AbortPolicy(false));

		// 高优先级线程池
		highPriorityThreadpool = new ThreadPoolExecutor(highCoreSize,highMaxSize,20,TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(HIGHT_PRIORITY_QUEUE_POOL_SIZE),hightPriorityThreadFactory,new AbortPolicy(
						true));

		// 启动线程池
		running = true;

		Log.d(TAG,"create instance:" + this.toString());
	}

	/**
	 * 处理被拒绝的任务
	 */
	private static class AbortPolicy implements RejectedExecutionHandler
	{

		private boolean retry;

		public AbortPolicy(boolean retry)
		{
			this.retry = retry;
		}

		/**
		 * 被拒绝的任务上报错误
		 */
		public void rejectedExecution(final Runnable r,ThreadPoolExecutor e)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (Exception ex){}
			Log.e(TAG,"Task " + r.toString() + " rejected from " + e.toString());

			// 高优先级任务不可以丢弃，直接重试
			if (retry)
			{
				Log.e(TAG,"retry Task!");
				runNow(new NewRunnable()
				{
					@Override
					public void runInTryCatch()
					{
						if(r!=null)
						{
							r.run();
						}
					}
				});
			}
		}
	}

	/**
	 * 立即运行或需要独立线程, 运行在高优先级线程池, <BR>
	 * 虽说是立即运行，只是表示会启动新线程来运行，实际运行时间取决于线程调度<BR>
	 * 
	 * @param runnable
	 * @return
	 */
	public static void runNow(NewRunnable runnable)
	{
		ThreadPool.instance().runTaskNow(runnable);
	}

	/**
	 * 后台运行，用于运行优先级比较低的任务，这些任务当系统忙时需要排队执行
	 * 
	 * @param runnable
	 * @return
	 */
	public static void runInBackground(NewRunnable runnable)
	{
		ThreadPool.instance().runTaskInBackground(runnable);
	}

	/**
	 * 立即运行, 运行在高优先级线程池, <BR>
	 * 虽说是立即运行，只是表示会启动新线程来运行，实际运行时间取决于线程调度
	 * 
	 * @param runnable
	 */
	public void runTaskNow(NewRunnable runnable)
	{
		addTask(runnable,true);
	}

	/**
	 * 后台运行
	 * 
	 * @param runnable
	 */
	public void runTaskInBackground(NewRunnable runnable)
	{
		addTask(runnable,false);
	}

	/*
	 * 添加任务
	 * @param task
	 * @param isHighPriority
	 */
	private void addTask(Runnable task,boolean isHighPriority)
	{
		if (!running)
		{
			Log.e(TAG,"addTask failed! thread pool running is false!");
			return;
		}
		Log.d(TAG,"addTask! an " + (isHighPriority ? "high" : "low") + " priority task enter!");

		if (task == null)
		{
			return;
		}

		excuteTask(isHighPriority ? highPriorityThreadpool : mainThreadpool,task,isHighPriority);
	}

	/**
	 * 恢复调度线程
	 */
	public void resume()
	{
		Log.d(TAG,"resume!");

		init(MAIN_CORE_POOL_SIZE,MAIN_MAX_POOL_SIZE,HIGH_CORE_POOL_SIZE,HIGH_MAXPOOLSIZE);
	}

	/**
	 * 立即结束线程池 清除未处理任务 关闭线程池
	 */
	public void destory()
	{
		Log.d(TAG,"destroy!");

		running = false;
		highPriorityThreadpool.shutdownNow();
		mainThreadpool.shutdownNow();
		highPriorityThreadpool = null;
		mainThreadpool = null;
	}

	/*
	 * 执行一个任务
	 * @return boolean 返回值，是否有任务需要执行
	 */
	private void excuteTask(ThreadPoolExecutor executor,Runnable task,boolean isHighPriority)
	{
		// debug状态下可以观察一下任务是否执行完成
		if (Settings.IS_SHOW_DEBUG)
		{
			taskCount.incrementAndGet();
		}
		// 线程数到达核心线程时，让其立即执行
		if (executor.getActiveCount() == executor.getCorePoolSize() && isHighPriority)
		{
			Runnable emptyTask = new EmptyTask();
			executor.execute(emptyTask);
			executor.remove(emptyTask);
		}
		// 线程池未达到最大线程数
		executor.execute(task);

		Log.d(TAG,"executeTask! an " + (isHighPriority ? "high" : "low") + " priority task running!");
	}

	/**
	 * 类AsyncTask.java的实现描述：<BR>
	 * 空任务，解决java线程池队列里的任务不会被启动线程立即执行
	 */
	class EmptyTask implements Runnable
	{

		@Override
		public void run()
		{
			// 空任务，用于让线程池立即执行任务
		}
	}

	// 任务数
	private static AtomicInteger taskCount = new AtomicInteger(0);

}
