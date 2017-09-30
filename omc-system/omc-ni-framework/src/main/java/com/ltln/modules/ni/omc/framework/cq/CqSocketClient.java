package com.ltln.modules.ni.omc.framework.cq;


import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import org.openide.util.Exceptions;

/**
 * 
 * @author Administrator
 *
 */
public class CqSocketClient extends Thread{
    
	public int ID;
	private Socket socket = null;
	private InputStream input ;
	private OutputStream output;
	private BufferedInputStream bis;
	private DataOutputStream bos;
	private String host = null;
	private int port = -1;
	private boolean initFalg = false;
	private boolean connectStatus = false;
        private CqAlarmChannel<AlarmVo> createAlarmChannel;
	public static final String CREATE_ALARM_CHANNEL_KEY = "CREATE_ALARM_CHANNEL_KEY";   
        
	public CqSocketClient(String host, int port){
		this.host = host;
		this.port = port;
                createAlarmChannel = CqAlarmChannelFactory.getMessageChannel(CREATE_ALARM_CHANNEL_KEY, 50000);
	}
	
	public CqSocketClient(Socket socket, String host){}
	
	/**
	 * 连接到omc服务端
	 * @throws Exception
	 */
	public  void connect() throws Exception {
            if(socket!=null){
                 close();
                 socket.shutdownInput();
                 socket.shutdownOutput();
            }
            socket = new Socket();
            try {
                socket.setTcpNoDelay(true);
                socket.setSoTimeout(0);
                socket.setKeepAlive(true);
                socket.connect(new InetSocketAddress(host, port));
                init();
                this.setConnectStatus(true);//连接成功
            } catch (SocketException e) {
                if (socket != null && socket.isConnected()) {
                    try {
                        socket.shutdownInput();
                        socket.shutdownOutput();
                    } catch (Exception e1) {
                    }
                }
                this.setConnectStatus(false);
                throw e;
            } catch (IOException e) {
                if (socket != null) {
                        socket.shutdownInput();
                        socket.shutdownOutput();
                }
                  this.setConnectStatus(false);
                throw e;
            } catch (Exception e) {
                if (socket != null) {
                        socket.shutdownInput();
                        socket.shutdownOutput();
                        this.setConnectStatus(false);
                }
            }
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {
		input = socket.getInputStream();
		output = socket.getOutputStream();
		bis = new BufferedInputStream(socket.getInputStream());
		bos = new DataOutputStream(socket.getOutputStream());
	}
	
	public  void writeMsg(Message message) throws IOException, Exception {
                JaxbUtil jaxbUtil= new JaxbUtil(Message.class);
                bos.writeUTF(jaxbUtil.toXml(message, "utf-8"));
		bos.flush();
		
	}
	public void run(){
            while(!this.connectStatus){
                try {
                    connect();
                    Thread.sleep(10000L);
                }catch (Exception ex) {    
                    Exceptions.printStackTrace(ex);
               }    
            }
            while(true) {
              //实时告警
              try {
                  AlarmVo alarm = createAlarmChannel.poll();                
                  if (alarm != null&&this.connectStatus) {
                      DataRecord dataRecord = CqAlarmUtil.convertAlarm(alarm);
                      Message message = new Message();
                      DataSet dataset = new DataSet();
                      dataset.setDatarecord(dataRecord);
                      message.setDataSet(dataset);
                      writeMsg(message);                    
                  }else{
                      while(!this.connectStatus){
                          connect();
                          try {
                               Thread.sleep(10000L);
                          } catch (InterruptedException e) {
                                e.printStackTrace();
                          }    
                      }
                  }
              } catch (Exception ex) {
                  Exceptions.printStackTrace(ex);
                  this.setConnectStatus(false);
              }
              //sleep
              try {
                      Thread.sleep(20L);
              } catch (InterruptedException e) {
                      e.printStackTrace();
              }            
          }
        }
	/**
	 * 关闭资源
	 */
	public  void close(){
		try {
			if (input != null) {
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (output != null) {
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (socket != null) {
				socket.close();
				socket = null;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重新连接
	 */
	public synchronized void reConnect() {
		try {
			this.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public boolean isInitFalg() {
		return initFalg;
	}

	public void setConnectStatus(boolean connectStatus) {
		this.connectStatus = connectStatus;
	}

	public boolean isConnectStatus() {
		return connectStatus;
	}
	
	public static void main(String[] args){
//		System.out.println(Short.MAX_VALUE);
//		SocketClient socketClient = new SocketClient("192.168.1.48",9997);
//		try {
//			socketClient.connect();
//			socketClient.output = socketClient.socket.getOutputStream();
//			socketClient.output.write(Msg.StartSign);
//			socketClient.output.flush();
//			socketClient.oos.writeShort(555);
//			socketClient.oos.flush();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("2222222222222222222222222");
//		while(true){
//			
//		}
		
		
	}
	
}
