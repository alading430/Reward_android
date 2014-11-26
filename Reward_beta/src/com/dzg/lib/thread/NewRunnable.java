package com.dzg.lib.thread;

import com.dzg.lib.app.Settings;


/**
 * 可捕获异常的线程池异步任务虚类 <br/>
 * 实现方法runInTryCatch执行任务 <br/>
 * 当发生异常回调OnRunnableException.onUnCatchException(Exception e,Object p)
 * 
 */
public abstract class NewRunnable implements Runnable
{
	protected OnRunnableException exception = null; // 发生异常的回调
	protected Object para = null; // 异步任务可选参数

	public NewRunnable()
	{
	}

	public NewRunnable(OnRunnableException cb)
	{
		this(cb,null);
	}

	public NewRunnable(Object p)
	{
		this(null,p);
	}

	public NewRunnable(OnRunnableException cb,Object p)
	{
		exception = cb;
		para = p;
	}

	@Override
	public void run()
	{
		try
		{
			runInTryCatch();
		}
		catch (Throwable e)
		{
			if (exception != null)
			{
				exception.onUnCatchException(e,para);
			}
			else
			{
				// 调试环境，将异常在控制台显示出来，便于发现问题
				if (Settings.IS_SHOW_DEBUG)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 异步任务执行方法，可以设置发生异常后回调 <br/>
	 * <font color=red>默认情况忽略异常</font>，设置回调后发生异常执行OnRunnableException.
	 * onUnCatchException(Throwable e,Object p) <br/>
	 * 未设置异常后回调并且AliSettings.isDebug()=true，控制台打印出异常，便于发现问题
	 * 
	 * @see setUnCatchExceptionCallBack(OnRunnableException cb)
	 */
	abstract public void runInTryCatch();

	public void setUnCatchExceptionCallBack(OnRunnableException cb)
	{
		this.exception = cb;
	}

	/**
	 * 可以选择的传入参数
	 * 
	 * @param p 异步任务实现者可以传入的参数
	 */
	public void setInputParameter(Object p)
	{
		this.para = p;
	}

	/**
	 * 异步任务异常回调接口
	 * 
	 * @author fengyun.zl
	 */
	public interface OnRunnableException
	{
		/**
		 * 异步任务执行时，发生runtime异常回调函数
		 * 
		 * @param e 发生的异常
		 * @param para 创建异步任务时，传入的参数
		 */
		public void onUnCatchException(Throwable e,Object para);
	}
}
