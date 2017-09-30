using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace omc_simulator.alm
{
    public class AlarmVo
    {

        private long id;

        public long Id
        {
            get { return id; }
            set { id = value; }
        }

        private int alarmSeq;

        public int AlarmSeq
        {
            get { return alarmSeq; }
            set { alarmSeq = value; }
        }

        private String alarmTitle;

        public String AlarmTitle
        {
            get { return alarmTitle; }
            set { alarmTitle = value; }
        }

        private int alarmStatus;

        public int AlarmStatus
        {
            get { return alarmStatus; }
            set { alarmStatus = value; }
        }

        private String alarmType;

        public String AlarmType
        {
            get { return alarmType; }
            set { alarmType = value; }
        }

        private int origSeverity;

        public int OrigSeverity
        {
            get { return origSeverity; }
            set { origSeverity = value; }
        }

        private String eventTime;

        public String EventTime
        {
            get { return eventTime; }
            set { eventTime = value; }
        }

        private long eventTimeMills;

        public long EventTimeMills
        {
            get { return eventTimeMills; }
            set { eventTimeMills = value; }
        }

        private String logTime;

        public String LogTime
        {
            get { return logTime; }
            set { logTime = value; }
        }

        private String alarmId;

        public String AlarmId
        {
            get { return alarmId; }
            set { alarmId = value; }
        }

        private String specificProblemID;

        public String SpecificProblemID
        {
            get { return specificProblemID; }
            set { specificProblemID = value; }
        }

        private String specificProblem;

        public String SpecificProblem
        {
            get { return specificProblem; }
            set { specificProblem = value; }
        }

        private String neUID;

        public String NeUID
        {
            get { return neUID; }
            set { neUID = value; }
        }

        private String neName;

        public String NeName
        {
            get { return neName; }
            set { neName = value; }
        }

        private String neType;

        public String NeType
        {
            get { return neType; }
            set { neType = value; }
        }

        private String objectUID;

        public String ObjectUID
        {
            get { return objectUID; }
            set { objectUID = value; }
        }

        private String objectName;

        public String ObjectName
        {
            get { return objectName; }
            set { objectName = value; }
        }

        private String objectType;

        public String ObjectType
        {
            get { return objectType; }
            set { objectType = value; }
        }

        private String locationInfo;

        public String LocationInfo
        {
            get { return locationInfo; }
            set { locationInfo = value; }
        }


        private String addInfo;

        public String AddInfo
        {
            get { return addInfo; }
            set { addInfo = value; }
        }

        private String holderType;

        public String HolderType
        {
            get { return holderType; }
            set { holderType = value; }
        }

        private String alarmCheck;

        public String AlarmCheck
        {
            get { return alarmCheck; }
            set { alarmCheck = value; }
        }

        private int layer;

        public int Layer
        {
            get { return layer; }
            set { layer = value; }
        }

        internal static AlarmVo Parse(Message omcMsg)
        {
            AlarmVo result = new AlarmVo();
            string[] allContents = omcMsg.Body.Split(new char[]{';'});
            foreach (string str in allContents)
            {
                Type t = result.GetType();
                foreach (PropertyInfo property in t.GetProperties())
                {
                    if (str.StartsWith(property.Name))
                    {
                        string newValue  = str.Replace(property.Name+"=","");
                        if (property.PropertyType == typeof(string))
                        {
                            property.SetValue(result, newValue, null);
                        }
                        else if (property.PropertyType == typeof(int))
                        {
                            property.SetValue(result, int.Parse(newValue), null);
                        }
                        else if (property.PropertyType == typeof(long))
                        {
                            property.SetValue(result, long.Parse(newValue), null);
                        }
                    }
                }
            }
            return result;
        }

        public   static AlarmVo ParseFromJson(Message omcMsg)
        {
            return Util.DeserializeJsonToObject<AlarmVo>(omcMsg.Body);
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("Id=").Append(this.id)
            .Append(";AlarmSeq=").Append(this.alarmSeq)
            .Append(";AlarmTitle=").Append(this.alarmTitle)
            .Append(";AlarmStatus=").Append(this.alarmStatus)
            .Append(";AlarmType=").Append(this.alarmType)
            .Append(";OrigSeverity=").Append(this.origSeverity)
            .Append(";EventTime=").Append(this.eventTime)
            .Append(";EventTimeMills=").Append(this.eventTimeMills)
            .Append(";LogTime=").Append(this.logTime)
            .Append(";AlarmId=").Append(this.alarmId)
            .Append(";SpecificProblemID=").Append(this.specificProblemID)
            .Append(";SpecificProblem=").Append(this.specificProblem)
            .Append(";NeUID=").Append(this.neUID)
            .Append(";NeName=").Append(this.neName)
            .Append(";NeType=").Append(this.neType)
            .Append(";ObjectUID=").Append(this.objectUID)
            .Append(";ObjectName=").Append(this.objectName)
            .Append(";ObjectType=").Append(this.objectType)
            .Append(";LocationInfo=").Append(this.locationInfo)
            .Append(";AddInfo=").Append(this.addInfo)
            .Append(";HolderType=").Append(this.holderType)
            .Append(";AlarmCheck=").Append(this.alarmCheck)
            .Append(";Layer=").Append(this.layer);
            return sb.ToString();
        }
    }
}
