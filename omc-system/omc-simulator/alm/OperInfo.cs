using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace omc_simulator.alm
{
   public class OperInfo
    {
        int id;

        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        string type;

        public string Type
        {
            get { return type; }
            set { type = value; }
        }

        string msgType;

        public string MsgType
        {
            get { return msgType; }
            set { msgType = value; }
        }

        string body;

        public string Body
        {
            get { return body; }
            set { body = value; }
        }



        string time;

        public string Time
        {
            get { return time; }
            set { time = value; }
        }

        internal static OperInfo Parse(Message omcMsg)
        {
            OperInfo result = new OperInfo();

            if (omcMsg.MsgType == 0)
                result.msgType = "realTimeAlarm";
            else if (omcMsg.MsgType == 1)
                result.msgType = "reqLoginAlarm";
            else if (omcMsg.MsgType == 2)
                result.msgType = "ackLoginAlarm";
            else if (omcMsg.MsgType == 3)
                result.msgType = "reqSyncAlarmMsg";
            else if (omcMsg.MsgType == 4)
                result.msgType = "ackSyncAlarmMsg";
            else if (omcMsg.MsgType == 5)
                result.msgType = "reqSyncAlarmFile";
            else if (omcMsg.MsgType == 6)
                result.msgType = "ackSyncAlarmFile";
            else if (omcMsg.MsgType == 7)
                result.msgType = "ackSyncAlarmFileResult";
            else if (omcMsg.MsgType == 8)
                result.msgType = "reqHeartBeat";
            else if (omcMsg.MsgType == 9)
                result.msgType = "ackHeartBeat";
            else if (omcMsg.MsgType == 10)
                result.msgType = "closeConnAlarm";

            if (result.msgType.StartsWith("req") || result.msgType.StartsWith("close"))
                result.Type = "request";
            else
                result.Type = "response";
            result.Time = Util.getTime((long)omcMsg.TimeStamp * 1000);
            result.Body = omcMsg.Body;
            
            return result;
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("Id=").Append(this.id)
               .Append("Type=").Append(this.type)
              .Append("MsgType=").Append(this.msgType).Append("Body=").Append(this.body)
              .Append("Time=").Append(this.time);
            return sb.ToString();
        }
    }
}
