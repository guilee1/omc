using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using omc_simulator.alm;
using System.Collections;
using System.Threading;
using System.Net;
using System.Windows.Forms;

namespace omc_simulator
{
    public class ClientObj
    {
        private static log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        Form1 mainFrame;

        bool heartBeat = true;

        bool closed = false;

        bool isLogModel = true;

        public bool IsLogModel
        {
            get { return isLogModel; }
            set { isLogModel = value; }
        }

       

        string id = Guid.NewGuid().ToString();

        TcpClient socketClient = new TcpClient();
        string remoteIp;
        int remotePort;
        string account;
        string pwd;
        DateTime createTime = DateTime.Now;

        ArrayList operInfoList = new ArrayList();
        ArrayList rtAlmList = new ArrayList();
        HeartBeartTask task;

       public ArrayList getOperInfoList()
        {
            return this.operInfoList;
        }
       public ArrayList getRtAlmList()
        {
            return this.rtAlmList;
        }

        public ClientObj(Form1 mainFrame,string remoteIp, int remotePort, string account, string pwd)
        {
            this.remoteIp = remoteIp;
            this.remotePort = remotePort;
            this.account = account;
            this.pwd = pwd;
            this.mainFrame = mainFrame;
            task = new HeartBeartTask(this);
            task.startHeartBeatTask();//开启心跳检查线程
        }


        public bool Closed
        {
            get { return closed; }
            set { closed = value; }
        }

        public bool HeartBeat
        {
            get { return heartBeat; }
            set { heartBeat = value; }
        }

        public string Id
        {
            get { return id; }
            set { id = value; }
        }

        public string RemoteIp
        {
            get { return remoteIp; }
            set { remoteIp = value; }
        }


        public int RemotePort
        {
            get { return remotePort; }
            set { remotePort = value; }
        }


        public string Account
        {
            get { return account; }
            set { account = value; }
        }


        public string Pwd
        {
            get { return pwd; }
            set { pwd = value; }
        }
        


        /// <summary>
        /// 发送心跳消息
        /// </summary>
        internal void sendHeartBeatMessage()
        {
            this.sendMessage(Message.buildHeartBeatMsg());
        }


        public void sendMessage(Message message)
        {
            if (socketClient.Connected)
            {
                NetworkStream clientStream = socketClient.GetStream();
                byte[] buffer = message.ToBytes();
                if (clientStream.CanWrite)
                {
                    clientStream.Write(buffer, 0, buffer.Length);
                    clientStream.Flush();
                    OperInfo operObj = OperInfo.Parse(message);
                    this.operInfoList.Add(operObj);
                    log.Info(operObj.toString());
                    mainFrame.RefrashOperInfoGrid(this.Id, this.operInfoList);
                }
            }
        }

        /// <summary>
        /// 发送登录消息
        /// </summary>
        internal void sendLoginMessage(int type)
        {
            this.sendMessage(Message.buildLoginMsg(this.account, this.pwd,type));
        }

        /// <summary>
        /// 发送关闭消息
        /// </summary>
        internal void sendCloseMessage()
        {
            this.sendMessage(Message.buildCloseMsg());
        }

        /// <summary>
        /// 处理接收到的OMC消息
        /// </summary>
        /// <param name="buffer"></param>
        void receiveMsg(byte[] buffer)
        {
            Message omcMsg = Message.parse(buffer);
            if (omcMsg.MsgType == 0)
            {
                //realTimeAlarm
                AlarmVo almObj = AlarmVo.ParseFromJson(omcMsg);
                almObj.LogTime = DateTime.Now.ToLongTimeString();
                //如果告警时间与当前时间相差超过5s，则认为延迟问题
                TimeSpan time = DateTime.Now-Util.getTime(almObj.EventTime);
                if (time.TotalSeconds >= 5)
                {
                    log.Info("exceed 5 sec:"+"time is :"+time.TotalMilliseconds+" user:" + this.Account + " msg:" + almObj.toString());
                }
                else
                {
                    log.Info("time is :" + time.TotalSeconds + " user:" + this.Account + " msg:" + almObj.toString());
                }
                if (!isLogModel)
                {
                    this.rtAlmList.Add(almObj);
                    mainFrame.RefrashRtAlmGrid(this.Id, this.rtAlmList);
                }
            }
            else
            {
                OperInfo operObj = OperInfo.Parse(omcMsg);
                this.operInfoList.Add(operObj);
                log.Info("user:"+this.Account+" msg:"+operObj.toString());
                mainFrame.RefrashOperInfoGrid(this.Id, this.operInfoList);
            }
        }

        /// <summary>
        /// 连接服务器
        /// </summary>
        /// <returns></returns>
        internal bool connect()
        {
            try
            {
                socketClient.NoDelay = true;
                socketClient.Connect(new IPEndPoint(IPAddress.Parse(remoteIp), this.remotePort));
            }
            catch(Exception error)
            {
                MessageBox.Show(error.Message, "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false;
            }
            asyncread(socketClient);
            return true;
        }


        private void asyncread(TcpClient sock)
        {
            StateObject state = new StateObject();
            state.client = sock;
            NetworkStream stream = sock.GetStream();

            if (stream.CanRead)
            {
                try
                {
                    IAsyncResult ar = stream.BeginRead(state.buffer, 0, 100, new AsyncCallback(TCPReadCallBack), state);
                }
                catch
                {
                    stream.Close();
                    sock.Close();
                    this.Closed = true;
                    mainFrame.closeAlmClient(this.Id);
                }
            }
        }

        private void TCPReadCallBack(IAsyncResult ar)
        {
            StateObject state = (StateObject)ar.AsyncState;

            if ((state.client == null) || (!state.client.Connected))
                return;
            int numberOfBytesRead;
            NetworkStream mas = state.client.GetStream();

            numberOfBytesRead = mas.EndRead(ar);
            state.totalBytesRead += numberOfBytesRead;
            if (numberOfBytesRead > 0)
            {
                byte[] dd = new byte[numberOfBytesRead];
                Array.Copy(state.buffer, 0, dd, 0, numberOfBytesRead);
                if (numberOfBytesRead < 9)
                {
                    //go on
                    
                }
                else
                {
                    //获取length字段，并判断是否整包
                    byte[] lengthOfBody = new byte[2];
                    Array.Copy(state.buffer, 7, lengthOfBody, 0, 2);
                    int len = Message.doubleBytesToInt(lengthOfBody);
                    int frameLength = 9 + len;
                    if (state.totalBytesRead < frameLength)
                    {
                        //go on

                    }
                    else
                    {
                        byte[] totalFrame = new byte[frameLength];
                        Array.Copy(state.buffer, 0, totalFrame, 0, frameLength);
                        state.totalBytesRead -= frameLength;
                        byte[] newBuff = new byte[StateObject.BufferSize];
                        Array.Copy(state.buffer, frameLength, newBuff, 0, state.totalBytesRead);
                        state.buffer = newBuff;
                        receiveMsg(totalFrame);
                    }
                }
                mas.BeginRead(state.buffer, state.totalBytesRead, 100, new AsyncCallback(TCPReadCallBack), state);
                
            }
            else
            {
                mas.Close();
                state.client.Close();

                mas = null;
                state = null;
                this.Closed = true;
                mainFrame.closeAlmClient(this.Id);
            }
        }

        internal class StateObject
        {
            public TcpClient client = null;
            public int totalBytesRead = 0;
            public const int BufferSize = 4096;
            public byte[] buffer = new byte[BufferSize];
        }

        /// <summary>
        /// 发送同步消息
        /// </summary>
        /// <param name="seqNumber"></param>
        internal void sendReqSyncAlarmMsg(int seqNumber)
        {
            this.sendMessage(Message.buildSyncAlarmMsg(seqNumber));
        }

        internal void sendReqSyncAlarmFile(int p, int seqNumber)
        {
            this.sendMessage(Message.buildSyncAlarmFile(p,seqNumber));
        }

        internal void sendReqSyncAlarmFile(int p, string beginTime, string endTime)
        {
            this.sendMessage(Message.buildSyncAlarmFile(p, beginTime, endTime));
        }
    }
}
