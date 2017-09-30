package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.sbi.msg.JmsAyncReceiver;
import com.ltln.modules.ni.omc.system.util.Constants;

public class StormTest extends JDialog {
	
	static Properties prop = new Properties();
	private static final long serialVersionUID = 702108767234792403L;
	Timer timer = new Timer();
	static JProgressBar progressBar;
	JPanel panelNull;
	JPanel panelStorm;
	JPanel panelBottom;
	JPanel panelMain;
	MyTimer mt = new MyTimer();

	static JSpinner spinnerObjectN;
	JSpinner spinnerLastT;
	static JLabel lblSendCount;
	static JLabel lblTime = new JLabel();
	
	public StormTest() {
		drawStorm();
		this.add(panelMain);
		this.setModal(true);
		this.setTitle(I18NLanguage.ALARM_STORM_TEST);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(400, 250);
		this.setResizable(false);
		this.setLocation(300, 200);
		String filePath = Constants.rootDir +"envconf/alarmTemplateCfg.properties";
		InputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(fis!=null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void drawStorm() {
		panelMain = new JPanel(new BorderLayout());
		drawTopNull();
		panelMain.add(panelNull, BorderLayout.NORTH);
		drawCenter();
		panelMain.add(panelStorm, BorderLayout.CENTER);
		panelBottom();
		panelMain.add(panelBottom, BorderLayout.SOUTH);
	}

	public void drawTopNull() {
		panelNull = new JPanel();
	}

	public void drawCenter() {
		panelStorm = new JPanel(new GridBagLayout());
		TitledBorder titleStorm = new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)),
				I18NLanguage.STORM_TEST_AREA);
		titleStorm
				.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelStorm.setBorder(titleStorm);
		// 每毫秒发送对象数量
		JLabel lblObjectNum = new JLabel(
				I18NLanguage.SENT_OBJECT_COUNTS_EACH_MILLISECOND);
		lblObjectNum.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		SpinnerModel modelObjectN = new SpinnerNumberModel(1, 1, 3000, 1);
		spinnerObjectN = new JSpinner(modelObjectN);
		spinnerObjectN.setPreferredSize(new Dimension(100, 20));
		// 持续时长
		JLabel lblLastTime = new JLabel(I18NLanguage.CONTINUE_TIME);
		lblLastTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		SpinnerModel modelLastT = new SpinnerNumberModel(3, 1, null, 1);
		spinnerLastT = new JSpinner(modelLastT);
		spinnerLastT.setPreferredSize(new Dimension(100, 20));
		// 计时器
		lblTime.setText("00h:00min:00sec");
		lblTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		// 进度条
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setMinimum(0);
		progressBar.setMaximum((int) spinnerLastT.getValue());

		// 当前已发送的条数
		lblSendCount = new JLabel(
				I18NLanguage.CURRENT_ARTICLE_HAS_SENT_THE_NUMBER);
		lblSendCount.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		// 将组件加到容器中
		panelStorm.add(lblObjectNum, new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelStorm.add(spinnerObjectN, new GridBagConstraints(1, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelStorm.add(lblLastTime, new GridBagConstraints(0, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelStorm.add(spinnerLastT, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelStorm.add(lblTime, new GridBagConstraints(0, 2, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelStorm.add(progressBar, new GridBagConstraints(0, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelStorm.add(lblSendCount, new GridBagConstraints(0, 4, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

	}

	public void panelBottom() {
		panelBottom = new JPanel(new GridBagLayout());
		final JButton btnStart = new JButton(I18NLanguage.START_TEST);
		btnStart.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		final JButton btnAlarm = new JButton(
				I18NLanguage.CONFIGURE_THE_ALARM_TEMPLATE);
		btnAlarm.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!MonitorFrame.updateFlag) {
					mt.endTime = ((int) spinnerLastT.getValue());
					timer.schedule(mt, 0, 1000);
					progressBar.setMaximum(mt.endTime * 60);
					progressBar.setMinimum(0);
					progressBar.setStringPainted(true);
					spinnerObjectN.setEnabled(false);
					spinnerLastT.setEnabled(false);
					btnStart.setEnabled(false);
					btnAlarm.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null,
							I18NLanguage.STORMTEST_INFO_POPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		btnAlarm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AlarmTemplateCfg atc = new AlarmTemplateCfg(prop);
				atc.setVisible(true);
			}
		});
		JButton btnCancel = new JButton(I18NLanguage.CANCEL);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mt.cancel();
				dispose();

			}
		});
		btnCancel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelBottom.add(btnStart, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBottom.add(btnAlarm, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBottom.add(btnCancel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
	}
	
	static class MyTimer extends TimerTask {
		JmsAyncReceiver msgReceiver = SelfBeanFactoryAware.getBean("JmsReceiver");
		int totalNum = 0;
		
		int endTime;
		
		String s1;
		String s2;
		String s3;
		String s;
		int m1;
		int m2;
		int m3;
	
		@Override
		public void run() {
			m1++;
			if (m1 == 60) {
				m1 = 0;
				m2++;
			}
			if (m2 == 60) {
				m2 = 0;
				m3++;
			}
			if (m3 < 10) {
				s3 = "0" + m3;
			} else {
				s3 = String.valueOf(m3);
			}
			if (m2 < 10) {
				s2 = "0" + m2;
			} else {
				s2 = String.valueOf(m2);
			}
			if (m1 < 10) {
				s1 = "0" + m1;
			} else {
				s1 = String.valueOf(m1);
			}
			s = s3 + "h:" + s2 + "min:" + s1 + "sec";
			lblTime.setText(s);
			progressBar.setValue(3600 * m3 + 60 * m2 + m1);
	
			if (m3 * 60 + m2 == endTime) {
				this.cancel();
				progressBar.setString(I18NLanguage.SEND_COMPLETE);
			}
			sendAlarm((int)spinnerObjectN.getValue());
			lblSendCount.setText(I18NLanguage.CURRENT_ARTICLE_HAS_SENT_THE_NUMBER+": "+totalNum);
		}
	
		void sendAlarm(int numberPerSec){
			String alarmId = "";
			for(int i=0;i<numberPerSec;++i){
				AlarmVo almVoObj = makeAlarmVoByTemplate();
				almVoObj.setEventTime(new Date().toLocaleString());
				almVoObj.setEventTimeMills(System.currentTimeMillis());
				if(i%2==0){
					//偶数情况下设置为产生告警以及唯一的ID
					alarmId = String.valueOf(System.currentTimeMillis());
					almVoObj.setAlarmStatus(1);
				}else{
					almVoObj.setAlarmStatus(0);
				}
				almVoObj.setAlarmId(alarmId);
				msgReceiver.receive(almVoObj);
				totalNum++;
			}
		}
		private AlarmVo makeAlarmVoByTemplate() {
			AlarmVo alarmObj = new AlarmVo();
			alarmObj.setAddInfo(prop.getProperty("addInfo"));
			alarmObj.setAlarmCheck(prop.getProperty("alarmCheck"));
			alarmObj.setAlarmType(prop.getProperty("alarmType"));
			alarmObj.setAlarmTitle(prop.getProperty("alarmTitle"));
			alarmObj.setHolderType(prop.getProperty("holderType"));
			alarmObj.setIpAddress(prop.getProperty("ipAddress"));
			alarmObj.setLayer(Integer.parseInt(prop.getProperty("layer")));
			alarmObj.setLocationInfo(prop.getProperty("locationInfo"));
			alarmObj.setNeName(prop.getProperty("neName"));
			alarmObj.setNeType(prop.getProperty("neType"));
			alarmObj.setNeUID(prop.getProperty("neUID"));
			alarmObj.setObjectName(prop.getProperty("objectName"));
			alarmObj.setObjectType(prop.getProperty("objectType"));
			alarmObj.setObjectUID(prop.getProperty("objectUID"));
			alarmObj.setOrigSeverity(Integer.parseInt(prop.getProperty("origSeverity")));
			alarmObj.setSpecificProblem(prop.getProperty("specificProblem"));
			alarmObj.setSpecificProblemID(prop.getProperty("specificProblemID"));
			return alarmObj;
		}
	
	}
}

	
