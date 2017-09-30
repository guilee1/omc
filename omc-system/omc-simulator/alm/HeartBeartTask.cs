using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;
using System.Threading;

namespace omc_simulator.alm
{
    public class HeartBeartTask
    {


        ClientObj hostObj;

        public HeartBeartTask(ClientObj host)
        {
            this.hostObj = host;
        }

        public void startHeartBeatTask()
        {
            ThreadPool.QueueUserWorkItem(new WaitCallback(sendHeartBeat));
        }


        public void sendHeartBeat(object para)
        {
            while (true)
            {
                if (!hostObj.Closed && hostObj.HeartBeat)
                {
                    hostObj.sendHeartBeatMessage();
                }
                Thread.Sleep(60 * 1000);
            }
        }
    }
}
