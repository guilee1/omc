using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace omc_simulator
{
    //2+1+4+2
    public class Message
    {
        public static ushort StartSign = (ushort)0xffff;

        private int msgType;


        private int timeStamp = (int)(Util.currentTimeMillis() / 1000);

        public int TimeStamp 
        {
            get { return timeStamp; }
            set { timeStamp = value; }
        }

        private int lenOfBody;

        private String body;


        public String Body
        {
            get { return body; }
            set { body = value; }
        }


        public int MsgType
        {
            get { return msgType; }
            set { msgType = value; }
        }


        public byte[] ToBytes()
        {
            byte[] sign = intToDoubleBytes(StartSign);
            byte msg = intToBytes(msgType);
            byte[] time = intTo4Bytes(timeStamp);
            byte[] len = intToDoubleBytes(lenOfBody);
            UTF8Encoding encoder = new UTF8Encoding();

            byte[] buffer = encoder.GetBytes(this.body);

            byte[] result = new byte[9 + buffer.Length];
            Array.Copy(sign, result,2);
            result[2] = msg;
            Array.Copy(time,0, result,3,4);
            Array.Copy(len,0, result,7, 2);
            Array.Copy(buffer,0, result,9, buffer.Length);
            return result;
        }

        public static Message buildLoginMsg(string user,string pwd,int type)
        {
            Message result = new Message();
            result.body = "reqLoginAlarm;user=" + user + "; key=" + pwd + "; type="+(type==0?"msg":"ftp");
            result.lenOfBody = result.body.Length;
            result.msgType = 1;
            return result;
        }

        /// <summary>
        /// 构建自定义消息，可以不符合规范要求
        /// </summary>
        /// <param name="msgType"></param>
        /// <param name="msgContent"></param>
        /// <returns></returns>
        public static Message buildCustomMsg(String msgType,String msgContent)
        {
            Message result = new Message();
            result.body = msgType + ";" + msgContent;
            result.lenOfBody = result.body.Length;
            result.msgType = getMsgTypeByTxt(msgType);
            return result;
        }

        private static int getMsgTypeByTxt(string msgType)
        {
            if (msgType == "realTimeAlarm")
                return 0;
            else if (msgType == "reqLoginAlarm")
                return 1;
            else if (msgType == "ackLoginAlarm")
                return 2;
            else if (msgType == "reqSyncAlarmMsg")
                return 3;
            else if (msgType == "ackSyncAlarmMsg")
                return 4;
            else if (msgType == "reqSyncAlarmFile")
                return 5;
            else if (msgType == "ackSyncAlarmFile")
                return 6;
            else if (msgType == "ackSyncAlarmFileResult")
                return 7;
            else if (msgType == "reqHeartBeat")
                return 8;
            else if (msgType == "ackHeartBeat")
                return 9;
            else
                return 10;
        }

        public static int doubleBytesToInt(byte[] src)
        {
            int value;
            value = (int)((src[1] & 0xFF) | ((src[0] & 0xFF) << 8));
            return value;
        }

        public static byte[] intToDoubleBytes(int value)
        {
            byte[] src = new byte[2];
            src[0] = (byte)((value >> 8));
            src[1] = (byte)(value);
            return src;
        }

        public static int bytesToInt(byte src)
        {
            int value;
            value = (int)(src & 0xFF);
            return value;
        }

        public static byte intToBytes(int value)
        {
            return (byte)(value & 0xFF);
        }
        public static byte[] intTo4Bytes(int value)
        {
            byte[] src = new byte[4];
            src[0] = (byte)((value >> 24) & 0xFF);
            src[1] = (byte)((value >> 16) & 0xFF);
            src[2] = (byte)((value >> 8) & 0xFF);
            src[3] = (byte)(value & 0xFF);
            return src;
        }

        internal static Message buildHeartBeatMsg()
        {
            Message result = new Message();
            result.body = "reqHeartBeat;reqID=82";
            result.lenOfBody = result.body.Length;
            result.msgType = 8;
            return result;
        }

        public static Message parse(byte[] buffer)
        {
            if (buffer.Length < 9)
                throw new Exception("buffer less than 9!");
            Message result = new Message();
            result.msgType = bytesToInt(buffer[2]);
            byte[] lengthOfRead = new byte[2];
            Array.Copy(buffer, 7, lengthOfRead, 0, 2);
            result.lenOfBody = doubleBytesToInt(lengthOfRead);
            byte[] contents = new byte[result.lenOfBody];
            Array.Copy(buffer, 9, contents, 0, result.lenOfBody);
            UTF8Encoding encoder = new UTF8Encoding();

            result.body = encoder.GetString(contents);
            return result;
        }
        


        public string ToString()
        {
            return "msgType:" + this.msgType + " body:" + this.body;
        }

        internal static Message buildCloseMsg()
        {
            Message result = new Message();
            result.body = "";
            result.lenOfBody = result.body.Length;
            result.msgType = 10;
            return result;
        }



        internal static Message buildSyncAlarmMsg(int seqNumber)
        {
            Message result = new Message();
            result.body = "reqSyncAlarmMsg;reqID=33; alarmSeq=" + seqNumber;
            result.lenOfBody = result.body.Length;
            result.msgType = 3;
            return result;
        }

        internal static Message buildSyncAlarmFile(int p, int seqNumber)
        {
            Message result = new Message();
            result.body = "reqSyncAlarmFile;reqID=33; alarmSeq=" + seqNumber + ";syncSource= " + p;
            result.lenOfBody = result.body.Length;
            result.msgType = 5;
            return result;
        }

        internal static Message buildSyncAlarmFile(int p, string beginTime, string endTime)
        {
            Message result = new Message();
            result.body = "reqSyncAlarmFile;reqID=33; startTime=" + beginTime + ";endTime=" + endTime + ";syncSource= " + p;
            result.lenOfBody = result.body.Length;
            result.msgType = 5;
            return result;
        }
    }
}
