package com.ltln.modules.ni.omc.system.start.ui;

import java.util.Locale;
import java.util.ResourceBundle;

import com.ltln.modules.ni.omc.system.util.Constants;

public class I18NLanguage {
	
	public static String STORMTEST_INFO_POPUPWINDOW;
	public static String FONT_NAME;
	public static String MONITOR_TITLE;
	public static String MONITOR_SYSTEM;
	public static String MONITOR_CONNECTION_CONFIG ;
	public static String MONITOR_FTP_CONFIG;
	public static String MONITOR_DUMP_CONFIG ;
	public static String MONITOR_DIAGNOSE;
	public static String MONITOR_TIME_CONFIG;
	public static String MONITOR_EXIT;
	public static String MONITOR_EXIT_POPUPWINDOW;
	public static String MONITOR_HELP;
	public static String MONITOR_ABOUT;
	public static String MONITOR_LANGUAGE;
	public static String MONITOR_CHINESE;
	public static String MONITOR_ENGLISH;

	public static String MONITOR_TITLE_PANEL_SYSTEM;
	public static String MONITOR_LBL_STOPSTA;
	public static String MONITOR_LBL_STOPSTO;
	public static String MONITOR_BTN_STARTSTA;
	public static String MONITOR_BTN_STARTSTO;

	public static String MONITOR_TITLE_PANEL_CONNECTION;
	public static String MONITOR_TITLE_PANEL_OMC;
	public static String MONITOR_TITLE_PANEL_NMS;
	public static String MONITOR_HEADERONE;
	public static String MONITOR_HEADERTWO;

	public static String ABOUT_OK;

	public static String CONNECTION_OMC_TITLETOP;
	public static String CONNECTION_LBLIP;
	public static String CONNECTION_LBLPORT;

	public static String CONNECTION_TITLE;
	public static String CONNECTION_PORT_TITLETOP;
	public static String CONNECTION_LBLALARMPORT;
	public static String CONNECTION_LBLINSTRUCTIONPORT;
	public static String CONNECTION_PORT_POPUPWINDOW;
	public static String CONNECTION_IP_LEGAL;
	public static String CONNECTION_PORT_ENTER; 
    public static String CONNECTION_PORT_SAME;
	public static String CONNECTION_PORT_RANGE;
	public static String CONNECTION_SUCCESS_POPUPWINDOW;
	public static String CONNECTION_MODIFY_POPUPWINDOW;
	
	public static String CONNECTION_BTNOK;
	public static String CONNECTION_BTNCANCEL;


	public static String FTPCONFIG_TITLE;
	public static String FTPCONFIG_TITLETOP;
	public static String FTPCONFIG_LBLALARMPORT;
	public static String FTPCONFIG_BTNADD;
	public static String FTPCONFIG_ADD_POPUPWINDOW;
	public static String FTPCONFIG_BTNDELETE;
	public static String FTPCONFIG_DELPOPWINDOW;
	public static String FTPCONFIG_DELETE_POPUPWINDOW;
	public static String FTPCONFIG_BTNOK;
	public static String FTPCONFIG_BTNCANCEL;
	public static String FTPCONFIG_SUCCESS;


	public static String FTPUSER_TITLE;
	public static String FTPUSER_LBLACCOUNT;
	public static String FTPUSER_LBLPASSW;
	public static String FTPUSER_LBLROOT;
	public static String FTPUSER_READ;
	public static String FTPUSER_WRITE;
	public static String FTPUSER_BTNOK;
	public static String FTPUSER_ENTER_COMPLETE;
	public static String FTPUSER_EXIST;
	public static String FTPUSER_BTNCANCEL;


	public static String DRAWDUMP_TITLE;
	public static String DRAWDUMP_LBLDUMPDIRECTORY;
	public static String DRAWDUMP_OPEN;
	public static String DRAWDUMP_CLOSE;
	public static String DRAWDUMP_DAY;
	public static String DRAWDUMP_WEEK;
	public static String DRAWDUMP_MONTH;
	public static String DRAWDUMP_LBLTIME;
	public static String DRAWDUMP_TEXTFIELD_TIME;


	public static String DUMP_TITLE;
	public static String DUMP_TABBEDPANELDUMP_ADDALARM;
	public static String DUMP_TABBECPANELDUMP_ADDMESSAGE;
	public static String DUMP_TABBEDPANELDUMP_ADDOPERATION;
	public static String DUMP_TABBEDPANELDUMP_ADDFILEBACKUP;
	public static String DUMP_BTNOK;
	public static String DUMP_IFPOPUPWINDOW;
	public static String DUMP_ELSEPOPUPWINDOW;
	public static String DUMP_SUCCESS;
	public static String DUMP_BTNCANCEL;


	public static String DIAGNOSE_TITLETOP;
	public static String DIAGNOSE_LBLDATACONNEC;
	public static String DIAGNOSE_LBLOMC_RMT;
	public static String DIAGNOSE_LBLOMC_JMS;
	public static String DIAGNOSE_LBLALARMPORT;
	public static String DIAGNOSE_LBLINSTRUCTIONPORT;
	public static String DIAGNOSE_FTP;
	public static String DIAGNOSE_BTNOK;
	public static String DIAGNOSE_BTNCANCEL;


	public static String TIMECONFIG_TITLETOP;
	public static String TIMECONFIG_OMCTIME;
	public static String TIMECONFIG_SYSTEMTIME;
	public static String TIMECONFIG_BTNTIME;
	public static String TIMECONFIG_BTNCANCEL;
	
	//
	public static String STORM_TEST;
	public static String ALARM_STORM_TEST;
	public static String STORM_TEST_AREA;
	public static String SENT_OBJECT_COUNTS_EACH_MILLISECOND;
	public static String CONTINUE_TIME;
	public static String CURRENT_ARTICLE_HAS_SENT_THE_NUMBER;
	public static String START_TEST;
	public static String CONFIGURE_THE_ALARM_TEMPLATE;
	public static String CANCEL;
	public static String ALARM_TEMPLATE_CONFIGURE;
	public static String ALARM_OBJECT;
	
	//
	public static String RESOURCE_ACQUISITION_CONFIGURATION;
	public static String ACQUISITION_TIMING_CONFIGURATION;
	public static String NUMBER;
	public static String TIME;
	public static String STATE;
	public static String ADD_TIMING_TASK;
	public static String EXE_TIMING_TASK;
	public static String DELETE_TASK;
	public static String TASK_ALLOCATION;
	public static String TIMING_TASK_TIME;
	public static String TIMING_TASK_STATE;
	public static String PLEASE_SELECT_TIME;
	public static String THIS_TIMING_TASK_HAS_BEEN_IN_EXISTENCE;
	public static String HOUR;
	public static String MINUTE;
	public static String SECOND;
	public static String OPEN;
	public static String CLOSE;
	public static String SEND_COMPLETE;
	
	private static String ACQUISITION__TIMING__CONFIGURATION;
	public static String LAYERRATE_MUST_INTEGER;
	public static String OMC_USER;
	public static String OMC_USER_ADD_TITLE;
	public static String SEND_LOG;
        public static String REFRESH;
        public static String TOTAL;
        public static String PREV;
        public static String NEXT;
        public static String CURRENT;
        public static String STARTTIME;
        public static String STOPTIME;
	public static void init(){
		String basename = "myproperties";
		ResourceBundle resource = null;
		if(Constants.LANGUAGE_SELECT == 0){
			//设置中文环境
			Locale cn = Locale.CHINA;
			resource = ResourceBundle.getBundle(basename,cn);
		}else {
			//设置英文环境
			Locale en = Locale.US;
			resource = ResourceBundle.getBundle(basename, en);
		}
		 FONT_NAME = resource.getString("FONT_NAME");
		 MONITOR_TITLE = resource.getString("MONITOR_TITLE");
		 MONITOR_SYSTEM = resource.getString("MONITOR_SYSTEM");
		 MONITOR_CONNECTION_CONFIG = resource.getString("MONITOR_CONNECTION_CONFIG");
		 MONITOR_FTP_CONFIG = resource.getString("MONITOR_FTP_CONFIG");
		 MONITOR_DUMP_CONFIG = resource.getString("MONITOR_DUMP_CONFIG");
		 MONITOR_DIAGNOSE = resource.getString("MONITOR_DIAGNOSE");
		 MONITOR_TIME_CONFIG = resource.getString("MONITOR_TIME_CONFIG");
		 MONITOR_EXIT = resource.getString("MONITOR_EXIT");
		 MONITOR_EXIT_POPUPWINDOW = resource.getString("MONITOR_EXIT_POPUPWINDOW");
		 MONITOR_HELP = resource.getString("MONITOR_HELP");
		 MONITOR_ABOUT = resource.getString("MONITOR_ABOUT");
		 MONITOR_LANGUAGE = resource.getString("MONITOR_LANGUAGE");
		 MONITOR_CHINESE = resource.getString("MONITOR_CHINESE");
		 MONITOR_ENGLISH = resource.getString("MONITOR_ENGLISH");
		 
		 MONITOR_TITLE_PANEL_SYSTEM = resource.getString("MONITOR_TITLE_PANEL_SYSTEM");
		 MONITOR_LBL_STOPSTA = resource.getString("MONITOR_LBL_STOPSTA");
		 MONITOR_LBL_STOPSTO = resource.getString("MONITOR_LBL_STOPSTO");
		 MONITOR_BTN_STARTSTA = resource.getString("MONITOR_BTN_STARTSTA");
		 MONITOR_BTN_STARTSTO = resource.getString("MONITOR_BTN_STARTSTO");

		 MONITOR_TITLE_PANEL_CONNECTION = resource.getString("MONITOR_TITLE_PANEL_CONNECTION");
		 MONITOR_TITLE_PANEL_OMC = resource.getString("MONITOR_TITLE_PANEL_OMC");
		 MONITOR_TITLE_PANEL_NMS = resource.getString("MONITOR_TITLE_PANEL_NMS");
		 MONITOR_HEADERONE = resource.getString("MONITOR_HEADERONE");
		 MONITOR_HEADERTWO = resource.getString("MONITOR_HEADERTWO");
		 
		 ABOUT_OK = resource.getString("ABOUT_OK");
		 
		 CONNECTION_TITLE = resource.getString("CONNECTION_TITLE");
		 CONNECTION_OMC_TITLETOP = resource.getString("CONNECTION_OMC_TITLETOP");
		 CONNECTION_LBLIP = resource.getString("CONNECTION_LBLIP");
		 CONNECTION_LBLPORT = resource.getString("CONNECTION_LBLPORT");

		 CONNECTION_PORT_TITLETOP = resource.getString("CONNECTION_PORT_TITLETOP");
		 CONNECTION_LBLALARMPORT = resource.getString("CONNECTION_LBLALARMPORT");
		 CONNECTION_LBLINSTRUCTIONPORT = resource.getString("CONNECTION_LBLINSTRUCTIONPORT");
		 CONNECTION_PORT_POPUPWINDOW = resource.getString("CONNECTION_PORT_POPUPWINDOW");
		 CONNECTION_IP_LEGAL = resource.getString("CONNECTION_IP_LEGAL");
		 CONNECTION_PORT_ENTER = resource.getString("CONNECTION_PORT_ENTER"); 
		 CONNECTION_PORT_SAME = resource.getString("CONNECTION_PORT_SAME");
		 CONNECTION_PORT_RANGE = resource.getString("CONNECTION_PORT_RANGE"); 
		 CONNECTION_SUCCESS_POPUPWINDOW = resource.getString("CONNECTION_SUCCESS_POPUPWINDOW"); 
		 CONNECTION_MODIFY_POPUPWINDOW = resource.getString("CONNECTION_MODIFY_POPUPWINDOW");

		 CONNECTION_BTNOK = resource.getString("CONNECTION_BTNOK");
		 CONNECTION_BTNCANCEL = resource.getString("CONNECTION_BTNCANCEL");


		 FTPCONFIG_TITLE = resource.getString("FTPCONFIG_TITLE");
		 FTPCONFIG_TITLETOP = resource.getString("FTPCONFIG_TITLETOP");
		 FTPCONFIG_LBLALARMPORT = resource.getString("FTPCONFIG_LBLALARMPORT");
		 FTPCONFIG_BTNADD = resource.getString("FTPCONFIG_BTNADD");
		 FTPCONFIG_ADD_POPUPWINDOW = resource.getString("FTPCONFIG_ADD_POPUPWINDOW");
		 FTPCONFIG_BTNDELETE = resource.getString("FTPCONFIG_BTNDELETE");
		 FTPCONFIG_DELPOPWINDOW = resource.getString("FTPCONFIG_DELPOPWINDOW");
		 FTPCONFIG_DELETE_POPUPWINDOW = resource.getString("FTPCONFIG_DELETE_POPUPWINDOW");
		 FTPCONFIG_BTNOK = resource.getString("FTPCONFIG_BTNOK");
		 FTPCONFIG_BTNCANCEL = resource.getString("FTPCONFIG_BTNCANCEL");
		 FTPCONFIG_SUCCESS = resource.getString("FTPCONFIG_SUCCESS");


		 FTPUSER_TITLE = resource.getString("FTPUSER_TITLE");
		 FTPUSER_LBLACCOUNT = resource.getString("FTPUSER_LBLACCOUNT");
		 FTPUSER_LBLPASSW = resource.getString("FTPUSER_LBLPASSW");
		 FTPUSER_LBLROOT = resource.getString("FTPUSER_LBLROOT");
		 FTPUSER_READ = resource.getString("FTPUSER_READ");
		 FTPUSER_WRITE = resource.getString("FTPUSER_WRITE");
		 FTPUSER_BTNOK = resource.getString("FTPUSER_BTNOK");
		 FTPUSER_ENTER_COMPLETE = resource.getString("FTPUSER_ENTER_COMPLETE");
		 FTPUSER_EXIST = resource.getString("FTPUSER_EXIST");
		 FTPUSER_BTNCANCEL = resource.getString("FTPUSER_BTNCANCEL");


		 DRAWDUMP_TITLE = resource.getString("DRAWDUMP_TITLE");
		 DRAWDUMP_LBLDUMPDIRECTORY = resource.getString("DRAWDUMP_LBLDUMPDIRECTORY");
		 DRAWDUMP_OPEN = resource.getString("DRAWDUMP_OPEN");
		 DRAWDUMP_CLOSE = resource.getString("DRAWDUMP_CLOSE");
		 DRAWDUMP_DAY = resource.getString("DRAWDUMP_DAY");
		 DRAWDUMP_WEEK = resource.getString("DRAWDUMP_WEEK");
		 DRAWDUMP_MONTH = resource.getString("DRAWDUMP_MONTH");
		 DRAWDUMP_LBLTIME = resource.getString("DRAWDUMP_LBLTIME");
		 DRAWDUMP_TEXTFIELD_TIME = resource.getString("DRAWDUMP_TEXTFIELD_TIME");


		 DUMP_TITLE = resource.getString("DUMP_TITLE");
		 DUMP_TABBEDPANELDUMP_ADDALARM = resource.getString("DUMP_TABBEDPANELDUMP_ADDALARM");
		 DUMP_TABBECPANELDUMP_ADDMESSAGE = resource.getString("DUMP_TABBECPANELDUMP_ADDMESSAGE");
		 DUMP_TABBEDPANELDUMP_ADDOPERATION = resource.getString("DUMP_TABBEDPANELDUMP_ADDOPERATION");
		 DUMP_TABBEDPANELDUMP_ADDFILEBACKUP = resource.getString("DUMP_TABBEDPANELDUMP_ADDFILEBACKUP");
		 DUMP_BTNOK = resource.getString("DUMP_BTNOK");
		 DUMP_IFPOPUPWINDOW = resource.getString("DUMP_IFPOPUPWINDOW");
		 DUMP_ELSEPOPUPWINDOW = resource.getString("DUMP_ELSEPOPUPWINDOW");
		 DUMP_SUCCESS = resource.getString("DUMP_SUCCESS");
		 DUMP_BTNCANCEL = resource.getString("DUMP_BTNCANCEL");


		 DIAGNOSE_TITLETOP = resource.getString("DIAGNOSE_TITLETOP");
		 DIAGNOSE_LBLDATACONNEC = resource.getString("DIAGNOSE_LBLDATACONNEC");
		 DIAGNOSE_LBLOMC_RMT = resource.getString("DIAGNOSE_LBLOMC_RMT");
		 DIAGNOSE_LBLOMC_JMS = resource.getString("DIAGNOSE_LBLOMC_JMS");
		 DIAGNOSE_LBLALARMPORT = resource.getString("DIAGNOSE_LBLALARMPORT");
		 DIAGNOSE_LBLINSTRUCTIONPORT = resource.getString("DIAGNOSE_LBLINSTRUCTIONPORT");
		 DIAGNOSE_FTP = resource.getString("DIAGNOSE_FTP");
		 DIAGNOSE_BTNOK = resource.getString("DIAGNOSE_BTNOK");
		 DIAGNOSE_BTNCANCEL = resource.getString("DIAGNOSE_BTNCANCEL");


		 TIMECONFIG_TITLETOP = resource.getString("TIMECONFIG_TITLETOP");
		 TIMECONFIG_OMCTIME = resource.getString("TIMECONFIG_OMCTIME");
		 TIMECONFIG_SYSTEMTIME = resource.getString("TIMECONFIG_SYSTEMTIME");
		 TIMECONFIG_BTNTIME = resource.getString("TIMECONFIG_BTNTIME");
		 TIMECONFIG_BTNCANCEL = resource.getString("TIMECONFIG_BTNCANCEL");
		 
		 //
		 STORMTEST_INFO_POPUPWINDOW = resource.getString("STORMTEST_INFO_POPUPWINDOW");
		 STORM_TEST=resource.getString("STORM_TEST");
		 ALARM_STORM_TEST=resource.getString("ALARM_STORM_TEST");
		 STORM_TEST_AREA=resource.getString("STORM_TEST_AREA");
		 SENT_OBJECT_COUNTS_EACH_MILLISECOND=resource.getString("SENT_OBJECT_COUNTS_EACH_MILLISECOND");
		 CONTINUE_TIME=resource.getString("CONTINUE_TIME");
		 CURRENT_ARTICLE_HAS_SENT_THE_NUMBER=resource.getString("CURRENT_ARTICLE_HAS_SENT_THE_NUMBER");
		 START_TEST=resource.getString("START_TEST");
		 CONFIGURE_THE_ALARM_TEMPLATE=resource.getString("CONFIGURE_THE_ALARM_TEMPLATE");
		 CANCEL=resource.getString("CANCEL");
		 ALARM_TEMPLATE_CONFIGURE=resource.getString("ALARM_TEMPLATE_CONFIGURE");
		 ALARM_OBJECT=resource.getString("ALARM_OBJECT");
		 //
		 RESOURCE_ACQUISITION_CONFIGURATION=resource.getString("RESOURCE_ACQUISITION_CONFIGURATION");
		 ACQUISITION_TIMING_CONFIGURATION=resource.getString("ACQUISITION_TIMING_CONFIGURATION");
		 NUMBER=resource.getString("NUMBER");
		 TIME=resource.getString("TIME");
		 STATE=resource.getString("STATE");
		 ADD_TIMING_TASK=resource.getString("ADD_TIMING_TASK");
		 EXE_TIMING_TASK = resource.getString("EXE_TIMING_TASK");
		 DELETE_TASK=resource.getString("DELETE_TASK");
		 TASK_ALLOCATION=resource.getString("TASK_ALLOCATION");
		 TIMING_TASK_TIME=resource.getString("TIMING_TASK_TIME");
		 TIMING_TASK_STATE=resource.getString("TIMING_TASK_STATE");
		 PLEASE_SELECT_TIME=resource.getString("PLEASE_SELECT_TIME");
		 THIS_TIMING_TASK_HAS_BEEN_IN_EXISTENCE=resource.getString("THIS_TIMING_TASK_HAS_BEEN_IN_EXISTENCE");
		 HOUR=resource.getString("HOUR");
		 MINUTE=resource.getString("MINUTE");
		 SECOND=resource.getString("SECOND");
		 OPEN=resource.getString("OPEN");
		 CLOSE=resource.getString("CLOSE");
		 SEND_COMPLETE=resource.getString("SEND_COMPLETE");
		 LAYERRATE_MUST_INTEGER = resource.getString("LAYERRATE_MUST_INTEGER");
		 OMC_USER = resource.getString("OMC_USER");
		 OMC_USER_ADD_TITLE = resource.getString("OMC_USER_ADD_TITLE");
                 SEND_LOG = resource.getString("SEND_LOG");
                 REFRESH = resource.getString("REFRESH");
                 TOTAL = resource.getString("TOTAL");
                 PREV = resource.getString("PREV");
                 NEXT = resource.getString("NEXT");
                 CURRENT = resource.getString("CURRENT");
                 STARTTIME = resource.getString("STARTTIME");
                 STOPTIME = resource.getString("STOPTIME");
	}

}
