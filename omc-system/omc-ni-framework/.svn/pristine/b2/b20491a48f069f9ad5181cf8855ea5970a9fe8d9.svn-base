/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltln.modules.ni.omc.framework.start;

//import com.ltln.modules.ptn.ptnssdx.server.maintain.common.PtnSsdxFtpClientUtil;
//import com.ltln.modules.ni.omc.framework.start.GZIPUtil;
//import com.ltln.modules.ptn.ptnssdx.server.ni.util.PtnSsdxDataUtil;
import com.ltln.netmgt.common.util.CommonUtil;
import com.ltln.netmgt.common.util.XMLDataReader;
import com.ltln.netmgt.common.util.XMLNode;
import com.ltln.netmgt.server.faultd.Alarm;
import com.ltln.netmgt.server.util.FtpClientUtil;
import com.ltln.netmgt.server.util.NmsUtil;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * @author Wangyd
 */
public class NIUtil {
  private static String emsIp = null;
  
  private static boolean isUTCTime;
  private static Properties serverParamsTable = new Properties();
  private static boolean LISTENERSTART = true;
  private static final Map<String, Map<String, String>> deviceVersionMap = Collections.synchronizedMap(new HashMap<String, Map<String, String>>());
  private static Hashtable<String, Vector<String>> niAlarmFilter = null; // key=alarmName，value=Vector<NeType>
  private static final SimpleDateFormat sdf=new SimpleDateFormat();
  
  // 界面锁定时间
  private static int NI_INTERFACE_LOCK = 10;
  private static int NI_HEARBEAT_INTERVAL = 0;
  private static boolean NI_ALARM_RANGE = false;
  private static String NI_EMS_PORT = "1099";
  
  // 根路径
  public static String NI_AIM_ROOT = ".";
  private static final String XMLDIR = "com/ltln/modules/ni/config/xml/";
  private static String orbNameRef;
  private static String namingServiceRef;
  private static String notifyServiceRef;
  private static String localHost = "127.0.0.1";
  private static String ftpServer;
  private static boolean isShowCompanyName;
  private static int maxnumsession = 8;
  private static final String CONFDIR = "conf/";
  public static String TrapIP = "";
  public static String snmpTrap = "";
  private static boolean THREAD_TAG = true;
  private static String STMCTPName = "/sts3c_au4-j=";
  private static boolean cpeSTM = false;

  /**
   * 常用的符号
   */
  public static String EQUAL = "=";
  public static String DIVIDE = "/";
  public static String COLON = ":";
  public static String SEMICOLON = ";";
  public static String QUOTATION = "'";
  public static String UNDERLINE = "_";
  public static String SEPARATOR = ",";
  public static String POUND_SEPARATOR = "#";
  public static String MINUS = "-";
  public static String DOT = ".";
  public static String OPERATE_NOLESS = ">=";
  public static String OPERATE_NOLARGER = "<=";
  public static String SEPECIAL_SEPARATOR = " ";
  public static String DOUBLE_VERTICAL_LINES = "||";
  public static String ADD = "+";

  private static DocumentBuilderFactory factory=null;
  private static DocumentBuilder db = null;
  /**
   * 各种get\set方法
   */
  public static boolean getNI_LISTENER_START() {
    return LISTENERSTART;
  }

  public static void setNI_LISTENER_START(boolean start) {
    LISTENERSTART = start;
  }

  public static int getNI_INTERFACE_LOCK() {
    return NI_INTERFACE_LOCK;
  }

  public static void setNI_INTERFACE_LOCK(int lock) {
    NI_INTERFACE_LOCK = lock;
  }

  public static boolean getAlarmRange() {
    return NI_ALARM_RANGE;
  }

  public static String getEMSPort() {
    return NI_EMS_PORT;
  }
  
  public static boolean isUTCTime() {
    return isUTCTime;
  }
  
  static{
    try {
      factory=DocumentBuilderFactory.newInstance();
      db = factory.newDocumentBuilder();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void init() {
    try {
      localHost = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    try {
      File file = new File(NI_AIM_ROOT + "/conf/ni.properties");
      serverParamsTable.load(new FileInputStream(file));
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    emsIp = serverParamsTable.getProperty("ni.emsAddress",localHost);
  }
  
  public static String getRootDir() {
    return NI_AIM_ROOT;
  }

  public static String getString(String str) {
    String result = CommonUtil.getString(NIUtil.class, str);

    return result;
  }
  
  /**
   * Frame图标
   * @return Image
   */
  public static Image getFrameIcon() {
    Image frameIcon = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + File.separator + "images" + File.separator + "NI.jpg");

    return frameIcon;
  }

  /**
   * 设置Frame居中
   * @param frame JFrame
   */
  public static void centerWindow(JFrame frame) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();

    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }

    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }

    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
  }

  /**
   * 设置Dialog居中
   * @param dialog JDialog
   */
  public static void centerWindow(JDialog dialog) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = dialog.getSize();

    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }

    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }

    dialog.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
  }

  public static String getCtpName(Properties properties) throws Exception {
    String nativeName = "";

    try {
      if ((properties.getProperty("ht") != null) && (Integer.parseInt(properties.getProperty("ht")) > 0)) {
        nativeName += ("/sts3c_au4" + properties.getProperty("ht"));
      }

      if ((properties.getProperty("lt") != null) && (Integer.parseInt(properties.getProperty("lt")) > 0)) {
        nativeName += ("/vt2_tu12" + parseLowTimeSlotId(properties.getProperty("lt")));
      }
    } catch (Exception exp) {
      exp.printStackTrace();
    }

    return nativeName;
  }

  /**
   * 计算低阶时隙号
   * @param id 低阶时隙号
   * @return klm串
   */
  public static String parseLowTimeSlotId(String id) {
    String klmStr = "";
    int timeslot = Integer.parseInt(id);

    if ((timeslot >= 1) && (timeslot <= 63)) {
      int k = 0;
      int rest1 = 0;
      int l = 0;
      int m = 0;
      timeslot -= 1;
      k = (timeslot / 21) + 1;
      rest1 = timeslot % 21;
      l = (rest1 / 7) + 1;
      m = (rest1 % 7) + 1;
      klmStr = "-k=" + k + "-l=" + l + "-m=" + m;
    }

    return klmStr;
  }

  public static String getLocalHost() {
    return localHost;
  }

  public static String getORBNameRef() {
    return orbNameRef;
  }

  public static String getNamingServiceRef() {
    return namingServiceRef;
  }

  public static String getNotifyServiceRef() {
    return notifyServiceRef;
  }

  public static String getFTPServer() {
    return ftpServer;
  }

  public static int getHearBeatInterval() {
    return NI_HEARBEAT_INTERVAL;
  }

  public static boolean isShowCompanyName() {
    return isShowCompanyName;
  }

  public static int getMaxNumNMSSession() {
    return maxnumsession;
  }

  public static String getCompany() {
    return serverParamsTable.getProperty("ni.Company", "SSGD");
  }

  public static String getLanguage() {
    return serverParamsTable.getProperty("ni.Language", "chn");
  }

  public static boolean isShowSubPort() {
    boolean isShowSubPort = Boolean.parseBoolean(serverParamsTable.getProperty("ni.ShowSubPort", "false"));

    return isShowSubPort;
  }

  public static String getNIParamValue(String key, String defaultValue) {
    return serverParamsTable.getProperty(key, defaultValue);
  }
  
  public static Map<String, String> getPtnCxtDeviceVersionMapByeDeviceType(String deviceType) {
    if (deviceVersionMap.isEmpty()) {
      loadPtnCxtDeviceVersionMap();
    }

    return deviceVersionMap.get(deviceType);
  }

  private static void loadPtnCxtDeviceVersionMap() {
    try {
      URL url = getNIConfigXMLURL("PtnCxtDeviceVersion.xml");
      XMLDataReader reader = new XMLDataReader(url.toString(), true);
      Vector vector = reader.getRootChildNodes();

      if (vector != null) {
        for (int i = 0; i < vector.size(); i++) {
          XMLNode xMLNode = (XMLNode)vector.elementAt(i);
          String deviceType = (String)xMLNode.getAttributeList().get("DeviceType");
          String software = (String)xMLNode.getAttributeList().get("Software");
          String hardware = (String)xMLNode.getAttributeList().get("Hardware");
          String serialNumber = (String)xMLNode.getAttributeList().get("SerialNumber");
          Map<String, String> versionMap = new HashMap<String, String>();

          versionMap.put("Software", software);
          versionMap.put("Hardware", hardware);
          versionMap.put("SerialNumber", serialNumber);
          deviceVersionMap.put(deviceType, versionMap);
        }
      }
    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }
  
  /**
   * 获取配置文件的URL
   * @param fileName
   * @return
   */
  public static URL getNIConfigXMLURL(String fileName) {
    ClassLoader classLoader = NIUtil.class.getClassLoader();
    URL url = classLoader.getResource(XMLDIR + fileName);

    return url;
  }

  /**
   * 获取配置文件的URL
   * @param fileName
   * @return
   */
  public static String getNIConfXMLFileDir(String fileName) {
    String path = System.getProperty("user.dir") + "/" + CONFDIR + fileName;

    return path;
  }

  public static boolean getTHREAD_TAG() {
    return THREAD_TAG;
  }

  public static void setTHREAD_TAG(boolean tag) {
    THREAD_TAG = tag;
  }

  public static String toDateStr(long dateTime) {
    Date date = new Date(dateTime);
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss.0");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf1.format(date);

    return sdf1.format(date) + "(" + sdf2.format(date) + ")";
  }

  private static Vector<String> getNiAlarmFilterNeTypeList(String alarmName) {
    if (alarmName == null) {
      return new Vector<String>();
    }

    if (niAlarmFilter == null) {
      initNiAlarmFilter();
    }

    if (niAlarmFilter.get(alarmName) != null) {
      return niAlarmFilter.get(alarmName);
    }

    return new Vector<String>();
  }

  private static void initNiAlarmFilter() {
    try {
      niAlarmFilter = new Hashtable<String, Vector<String>>();

      String filePath = getNIConfXMLFileDir("NiAlarmFilter.xml");
      XMLDataReader reader = new XMLDataReader(filePath, false);

      if (reader.getRootNode() == null) {
        return;
      }

      Vector root = reader.getRootChildNodes();

      if (root != null) {
        for (int i = 0; i < root.size(); i++) {
          XMLNode node = (XMLNode)root.elementAt(i);

          if (node.getAttributeList() == null) { // 跳过注释

            continue;
          }

          String c_Name = node.getAttribute("C_Name").toString();
          String neType = node.getAttribute("NeType").toString();
          Vector<String> neTypeList = null;

          if (niAlarmFilter.containsKey(c_Name)) {
            neTypeList = niAlarmFilter.get(c_Name);
          } else {
            neTypeList = new Vector<String>();
            niAlarmFilter.put(c_Name, neTypeList);
          }

          neTypeList.add(neType);
        }
      }
    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }

  public static boolean isAlarmInFilterList(String alarmName, String neType) {
    boolean result = false;
    Vector<String> neTypeList = getNiAlarmFilterNeTypeList(alarmName);

    if (neTypeList.size() < 1) {
      return false;
    }

    if (neTypeList.contains(neType)) {
      return true;
    }

    return result;
  }

  public static boolean isAlarmInFilterList(Alarm alarm) {
    return isAlarmInFilterList(alarm.getType(), alarm.getNeType());
  }

  public static String encodeString(String s) {
    try {
      return new String(s.getBytes(), "ISO-8859-1");
    } catch (UnsupportedEncodingException exp) {
      exp.printStackTrace();
    }

    return s;
  }

  public static String decodeString(String s) {
    try {
      return new String(s.getBytes("ISO-8859-1"));
    } catch (UnsupportedEncodingException exp) {
      exp.printStackTrace();
    }

    return s;
  }

  public static boolean getCPESTM() {
    return cpeSTM;
  }

  public static String getSTMCTPName() {
    return STMCTPName;
  }

  public static Properties getMsanInfoByTPName(String tpName) {
    if (tpName == null) {
      return null;
    }

    String[] tp = tpName.split("/");

    if ((tp == null) || (tp.length == 0)) {
      return null;
    }

    Properties properties = new Properties();

    for (String t : tp) {
      String[] str = t.split("=");
    
      if ((str != null) && (str.length == 2)) {
        properties.setProperty(str[0], str[1]);
      }
    }
    return properties;
  }
  
  public static long convertToUTCTIme(long millis) {
    if (isUTCTime)
    {
      Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(millis);

      int zoneOffset = cal.get(15);

      int dstOffset = cal.get(16);

      cal.add(14, -(zoneOffset + dstOffset));
      millis = cal.getTimeInMillis();
    }

    return millis;
  }

  
  public static String getPtnSsdxSlotMOName(long agentId, int slotID) {
    return "Agent" + agentId + "_Slot" + slotID;
  }
  
  /**
   * 压缩文件
   * 
   * @param srcFile File 需要压缩的文件(只能是单个文件)
   */
//  public synchronized static boolean zipFile(List<File> srcFile) {
//    for(File file:srcFile){
//      File zipFile=new File(file.getPath()+".zip.tmp");
//      GZIPUtil.pack(file, zipFile);
////      File packFile=GZIPUtil.pack(file, zipFile);
////      GZIPUtil.compress(packFile);
//    }
//    
//    return true;
//  }
//   public synchronized static List<File> zipFileToList(List<File> srcFile) {
//        List<File> filelist = new ArrayList<File>();
//        for(File file:srcFile){
//          File zipFile=new File(file.getPath()+".zip.tmp");
//          GZIPUtil.pack(file, zipFile);
//        //      File packFile=GZIPUtil.pack(file, zipFile);
//        //      GZIPUtil.compress(packFile);
//          filelist.add(zipFile);
//        }
//    
//    return filelist;
//  }
  public static int getPMDataCreateTaskPeriod() {
    String delay = serverParamsTable.getProperty("ni.PMDataCreateTaskPeriod", "15");
    
    return Integer.parseInt(delay);
  }

  public static int getPMDataTaskDelTime() {
    String delay = serverParamsTable.getProperty("ni.PMDataTaskDelTime", "24");
    
    return Integer.parseInt(delay);
  }
  
  public static String getEmsName() {
    return serverParamsTable.getProperty("ni.EmsName", "SSGD");
  }
  
  public static String getEmsUserLabel() {
    return serverParamsTable.getProperty("ni.EmsUserLabel", "EMS");
  }
  
  public static String getEmsNativeEMSName() {
    return serverParamsTable.getProperty("ni.EmsNativeEMSName", "EMS_1");
  }
  
  public static String getEmsLocation() {
    return serverParamsTable.getProperty("ni.EmsLocation", "");
  }

  public static String getEmsType() {
    return serverParamsTable.getProperty("ni.EmsType", "EMS");
  }
  
  public static String getEmsNBIVersion() {
    return serverParamsTable.getProperty("ni.EmsNBIVersion", "XView NI 2.2.0.0728");
  }
  
  public static String getEmsMaxMENumber() {
    return serverParamsTable.getProperty("ni.EmsMaxMENumber", "16000");
  }
  
  public static String getEMSAddress() {
    return emsIp;
  }

  public static String getNiFtpServer() {
    return serverParamsTable.getProperty("ni.FtpServer", "10.11.1.17");
  }
  
  public static String getNiFtpPort() {
    return serverParamsTable.getProperty("ni.FtpPort", "21");
  }
  public static String getNiFtpSaveDay() {
    return serverParamsTable.getProperty("ni.FtpSaveDay", "10");
  }
  public static String getNiFtpUser() {
    return serverParamsTable.getProperty("ni.FtpUser", "sherui");
  }
  
  public static String getNiFtpPassword() {
    return serverParamsTable.getProperty("ni.FtpPassword", "123");
  }
  
  public static String getNiEmsVersion() {
    String emsVersion=NmsUtil.GetString("XView Version x.x");
    if(emsVersion.equalsIgnoreCase("XView Version x.x")){
      emsVersion="";
    }
    return emsVersion;
  }
  
  public static String getNiHostNum() {
    return serverParamsTable.getProperty("ni.HostNum", "01");
  }
  
  public static String getNiCityCode() {
    String cityCode=serverParamsTable.getProperty("ni.CityCode", "4201");
    
    return cityCode;
  }
  
  public static String getNiOmcId() {
    String omcId=serverParamsTable.getProperty("ni.OmcId", "1");
    
    return omcId;
  }
  
  public static String getNiFactoryCode() {
    String factoryId=serverParamsTable.getProperty("ni.FactoryCode", "SS");
    
    return factoryId;
  }
  
  /**
   * 得到北向开关状态
   * @return 
   */
  public static boolean getNiSwitchFlag() {
    boolean flag=Boolean.valueOf(serverParamsTable.getProperty("ni.Switch", "false").trim());
    
    return flag;
  }
  
  public static String getNiSkillCode() {
    String skillCode=serverParamsTable.getProperty("ni.SkillCode", "CS");
    
    return skillCode;
  }

  public static String getNiDataVersion() {
    String dataVersion=serverParamsTable.getProperty("ni.DataVersion", "V1.0.0");
    
    return dataVersion;
  }
  
  public static String getEquipmemtDomain() {
    String equipmemtDomain=serverParamsTable.getProperty("ni.EquipmemtDomain", "PTN");
    
    return equipmemtDomain;
  }
  
  public static String getOmcCityName() {
    String equipmemtDomain=serverParamsTable.getProperty("ni.OmcCity", "HB");
    
    return equipmemtDomain;
  }
  
  public static String getOmcFtpRootDir() {
    String equipmemtDomain=serverParamsTable.getProperty("ni.FtpRootDir", "ftproot");
    
    return equipmemtDomain;
  }
  
  public static String getOmcZipFile() {                  
    String equipmemtDomain=serverParamsTable.getProperty("ni.ZipFile", "true");
    
    return equipmemtDomain;
  }

  public static String getNiFtpAllFileWay() {                  
    String equipmemtDomain=serverParamsTable.getProperty("ni.FtpAllFileWay", "true");
    
    return equipmemtDomain;
  }
  
  public static void FtpFiles(List<File> cmFileList,String fileType,String dataDir,String suffix) {
    FtpClientUtil ftpClientUtil = new FtpClientUtil("10.11.1.17", 21, "sherui", "123");
//    String ftpRoot=PtnSsdxDataUtil.getPtnSsdxOmcFtpPrefix();
//    String remoteDir=ftpRoot+fileType+File.separator+dataDir;
//    ftpClientUtil.
    
    for(File file:cmFileList){
       String filePath=file.getAbsolutePath();
       int splitIndex=filePath.lastIndexOf(File.separator);
       String localDir=filePath.substring(0, splitIndex);
       String fileName=filePath.substring(splitIndex)+suffix;
//       try {
//         if (ftpClientUtil.download(localDir,remoteDir,fileName,fileName)) {
//         }
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
    }
  }
  
  public static void main(String [] args){
//    PtnSsdxFtpClientUtil ftpClientUtil = new PtnSsdxFtpClientUtil("10.11.1.17", 21, "sherui", "123");
//    try {
//        ftpClientUtil.download("F:\\nms-modules_20160629\\module-ptn-cmcc\\build\\trunk\\ptn-server\\runenv\\.\\FTP\\CM","\\ftproot\\HB\\CS\\SS\\EMS_1\\CM\\20160725",  
//                              "ccc.txt", "ccc.txt");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }
  
  public static String getCmDataTime() {
    Calendar calendar=Calendar.getInstance();
    //资源文件在0点和12点生成
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    
    sdf.applyPattern("yyyyMMddHHmmss");
    String dataTime=sdf.format(calendar.getTime());
    
    
    return dataTime;
  }

  public static int getPageSize(String type) {
    
    Properties prop=new Properties();
    try {
      FileInputStream is=new FileInputStream("./conf/niPageSize.properties");
      prop.load(is);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    int pageSize=Integer.valueOf(prop.get(type).toString());
    
    return pageSize;
  }

    public static String getNiIsFtpFile() {
        return serverParamsTable.getProperty("ni.isFtpFile", "true");
    }
    
    private static Properties testProp=new Properties();
    
    public static boolean isPtnSsdxTestOn() {
        try {
            if (testProp.isEmpty()) {
                testProp.load(new FileInputStream("./conf/PtnSsdxTestCfg.properties"));
            }

            String testFlag = testProp.getProperty("TestOn", "false").trim();
            boolean flag = Boolean.valueOf(testFlag.trim());

            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
 public static String getNiProvince() {
    String province=serverParamsTable.getProperty("ni.province", "**");
    return province;
  }
 public static String getNiCity() {
    String city=serverParamsTable.getProperty("ni.city", "**");
    return city;
  }
  public static String getNIAlarmSource() {
    String alarmSource=serverParamsTable.getProperty("ni.alarmSource", "**");
    return alarmSource;
  }
  public static String getNIVendor_name() {
    String vendor_name=serverParamsTable.getProperty("ni.vendor_name", "**");
    return vendor_name;
  }
  public static String getNIResource_status() {
    String resource_status=serverParamsTable.getProperty("ni.Resource_status", "**");
    return resource_status;
  }
    public static String getIsCqNI() {
    String isCqNI=serverParamsTable.getProperty("ni.isCqNI", "false");
    return isCqNI;
  }
  public static String getNICqNi_ip() {
    String cqNi_ip=serverParamsTable.getProperty("ni.CQNI_IP", "127.0.0.1");
    return cqNi_ip;
  }
  public static String getNICqNi_port() {
    String cqNi_port=serverParamsTable.getProperty("ni.CQNI_PORT", "127.0.0.1");
    return cqNi_port;
  }
  
}
