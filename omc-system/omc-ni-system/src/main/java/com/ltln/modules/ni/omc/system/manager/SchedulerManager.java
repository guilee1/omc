package com.ltln.modules.ni.omc.system.manager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.scheduler.IScheduler;
import com.ltln.modules.ni.omc.system.core.scheduler.PerfTask;
import com.ltln.modules.ni.omc.system.core.scheduler.ResourceTask;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;
import com.ltln.modules.ni.omc.system.util.ResTaskObj;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class SchedulerManager implements ISystem {

	IScheduler scheduler;
	
	@Override
	public boolean startUp() {
		scheduler = SelfBeanFactoryAware.getBean("defaultScheduler");
		startDumpTask(scheduler);
		return true;
	}

	private void startDumpTask(IScheduler scheduler) {
		for(ResTaskObj task : Constants.RESTASK.values()){
			if(task.isStart())
				schdulerDump(scheduler,task.getTime(),task.getCycle(),task.getName(),task.getDir());
		}
	}

	void schdulerDump(IScheduler scheduler,String boomTime,int cycle,String tblName,String dir) {
		long delay = 0;
		try {
			delay = Toolkit.computeDelayMill(boomTime, cycle);
		} catch (ParseException e) {
			Logger.error("bad time format to parse in schdulerDump!", e);
			return;
		}
		Logger.info(String.format("will start dump [%s] at : %s", tblName,boomTime));
		scheduler.schedule(new DumpDBTask(tblName, dir,boomTime,cycle), delay, TimeUnit.MILLISECONDS);
	}


	@Override
	public boolean shutDown() {
		IScheduler scheduler = SelfBeanFactoryAware.getBean("defaultScheduler");
		scheduler.close();
		return true;
	}

	@Override
	public String getName() {
		return "Scheduler Service[resourceTask,almLogTask,dumpTask]";
	}

	public class DumpDBTask implements Runnable{
		private String tblName;
		private String directory;
		String boomTime;
		int cycle;
		public DumpDBTask(String name,String dir,String boomTime,int cycle) {
			this.tblName = name;
			this.directory = dir;
			this.boomTime = boomTime;
			this.cycle = cycle;
		}
		
		@Override
		public void run() {
			if(StringUtils.isEmpty(directory))
				directory = Constants.DUMP_DEFAULT_DIR;
			File dir = new File(directory);
			if(!dir.exists())
				dir.mkdirs();
			if(StringUtils.isEmpty(tblName))
				executeDumpFile();
			else if(StringUtils.endsWithIgnoreCase(tblName, "resource"))
				exeResourceCollect();
			else if(StringUtils.endsWithIgnoreCase(tblName, "perf"))
				exePerfCollect();
			else
				executeDumpDB();
			try{
				Thread.sleep(1000);
			}catch (Exception e) {
			}
			schdulerDump(scheduler, boomTime, cycle, tblName, directory);
		}

		private Date getZeroDate() {
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        Date zero = calendar.getTime();
			return zero;
		}
		
		private void exePerfCollect() {
			String beginTime = OmcDateFormater.formatBasicDate(getZeroDate());
	    	String endTime = OmcDateFormater.formatBasicDate(new Date());
			PerfTask task = SelfBeanFactoryAware.getBean("perfTask");
			task.executeJob(beginTime,endTime);
		}

		void exeResourceCollect() {
			ResourceTask task = SelfBeanFactoryAware.getBean("resourceTask");
			task.executeJob();
		}

		void executeDumpFile(){
			String fileName = OmcDateFormater.formatFileDate(new Date());
			File targetDir = new File(directory+"/"+fileName);
			try {
				FileUtils.moveDirectoryToDirectory(new File(Constants.FTP_SERVER_PATH), targetDir, true);
			} catch (IOException e) {
				Logger.error("Dump File Task error:", e);
			}
		}
		
		void executeDumpDB() {
			String fileName = OmcDateFormater.formatFileDate(new Date())+"_"+tblName;
			StringBuilder cmd = new StringBuilder();
			cmd.append("cmd /c \"\"");
			cmd.append(DbManager.MYSQLPATH+"\\bin\\mysqldump.exe").append("\"\"");
			cmd.append(" -u").append(Constants.MYSQL_USERNAME);
			cmd.append(" -p").append(Constants.MYSQL_PASSWORD);
			cmd.append(" --databases nbidb");
			cmd.append(" --tables " + tblName);
			cmd.append(" > "+directory + "\\"+fileName+".sql");
			Logger.info(cmd.toString());
			try {
				Process p = Runtime.getRuntime().exec(cmd.toString());
				p.waitFor();
			} catch (IOException e) {
				Logger.error("Dump Task error:", e);
				return;
			}catch (InterruptedException e) {
				Logger.error("Dump Task error:", e);
				return;
			}
			try{
				SelfBeanFactoryAware.getDao().truncateTable(tblName);
			}catch (Exception e) {
				Logger.error("Dump Task DB error:", e);
			}
		}

	}

	public static void main(String[] args)throws Exception {
		DumpDBTask task = new SchedulerManager().new DumpDBTask("", "", "14:34", 1);
		task.executeDumpFile();
	}
}
