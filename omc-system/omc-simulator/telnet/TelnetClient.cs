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
    public class TelnetClient
    {
        private static log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        Form1 mainFrame;

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
 
        DateTime createTime = DateTime.Now;

        ArrayList operInfoList = new ArrayList();


       public ArrayList getOperInfoList()
        {
            return this.operInfoList;
        }
  

       public TelnetClient(Form1 mainFrame, string remoteIp, int remotePort)
        {
            this.remoteIp = remoteIp;
            this.remotePort = remotePort;

            this.mainFrame = mainFrame;
        }


        public bool Closed
        {
            get { return closed; }
            set { closed = value; }
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
        


        /// <summary>
        /// 发送消息
        /// </summary>
        /// <param name="strText"></param>
        public void sendMessage(String strText)
        {
            strText += "\r\n";
            if (socketClient.Connected)
            {
                NetworkStream clientStream = socketClient.GetStream();
                Byte[] buffer = new Byte[strText.Length];
                for (int i = 0; i < strText.Length; i++)
                {
                    Byte ss = Convert.ToByte(strText[i]);
                    buffer[i] = ss;
                }

                if (clientStream.CanWrite)
                {
                    clientStream.Write(buffer, 0, buffer.Length);
                    clientStream.Flush();

                    log.Info("Instruction Cmd:"+strText);

                }
            }
        }

  

        /// <summary>
        /// 处理接收到的OMC消息
        /// </summary>
        /// <param name="buffer"></param>
        void receiveMsg(byte[] buffer)
        {
            Encoding encoding = Encoding.GetEncoding("UTF-8");
            string str_converted = encoding.GetString(buffer);
            this.operInfoList.Add(str_converted);
            mainFrame.RefrashTxtResponse(this, this.operInfoList);

                       
        }

        /// <summary>
        /// 连接服务器
        /// </summary>
        /// <returns></returns>
        internal bool connect()
        {
            try
            {
                socketClient.Connect(new IPEndPoint(IPAddress.Parse(remoteIp), this.remotePort));
            }
            catch
            {
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
                    IAsyncResult ar = stream.BeginRead(state.buffer, 0, StateObject.BufferSize, new AsyncCallback(TCPReadCallBack), state);
                }
                catch
                {
                    stream.Close();
                    sock.Close();
                    this.Closed = true;
                    mainFrame.closeTelnet(this.Id);
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
                receiveMsg(dd);
                mas.BeginRead(state.buffer, 0, StateObject.BufferSize, new AsyncCallback(TCPReadCallBack), state);
            }
            else
            {
                mas.Close();
                state.client.Close();

                mas = null;
                state = null;
                this.Closed = true;
                mainFrame.closeTelnet(this.Id);
            }
        }

        internal class StateObject
        {
            public TcpClient client = null;
            public int totalBytesRead = 0;
            public const int BufferSize = 8192;
            public byte[] buffer = new byte[BufferSize];
        }



        internal void Close()
        {
            if (socketClient!=null && socketClient.Connected)
            {
                socketClient.Close();
            }
        }
    }
}
