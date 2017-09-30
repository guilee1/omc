using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace omc_simulator
{
    public partial class SyncMsg : Form
    {

        ClientObj client;

        /// <summary>
        /// 默认1代表消息同步-->seq
        /// 2代表文件同步-->流水日志-->seq
        /// 3代表文件同步-->流水日志-->time
        /// 4代表文件同步-->活动告警-->time
        /// </summary>
        int msgType = 1;

        public SyncMsg(ClientObj c)
        {
            InitializeComponent();
            this.client = c;
        }

        /// <summary>
        /// 关闭取消
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        


        private void ChangeControlEnable(bool enable)
        {
            this.txtSeq.Enabled = enable;
            this.txtBeginTime.Enabled = enable;
            this.txtEndTime.Enabled = enable;
            this.btnSwitch.Enabled = enable;
        }

       

        /// <summary>
        /// 切换到消息同步
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rtBtnRtime_CheckedChanged(object sender, EventArgs e)
        {
            if (this.rtBtnRtime.Checked)
            {
                ChangeControlEnable(false);
                this.groupBox4.Enabled = false;
                this.txtSeq.Enabled = true;
                this.msgType = 1;
            }
        }

        /// <summary>
        /// 切换到文件同步
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rtbtnFile_CheckedChanged(object sender, EventArgs e)
        {
            if (this.rtbtnFile.Checked)
            {
                ChangeControlEnable(false);
                this.groupBox4.Enabled = true;
                rtBtnLog.Checked = true;
                this.btnSwitch.Enabled = true;
                this.txtSeq.Enabled = true;
                this.msgType = 2;
            }
        }

        /// <summary>
        /// 切换到流水日志同步
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rtBtnLog_CheckedChanged(object sender, EventArgs e)
        {
            if (this.rtBtnLog.Checked)
            {
                this.txtSeq.Enabled = true;
                this.btnSwitch.Enabled = true;
                this.txtBeginTime.Enabled = false;
                this.txtEndTime.Enabled = false;
                this.seq = true;
                this.msgType = 2;
            }
        }

        /// <summary>
        /// 切换到活动告警同步
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rtBtnActive_CheckedChanged(object sender, EventArgs e)
        {
            if (this.rtBtnActive.Checked)
            {
                this.txtSeq.Enabled = false;
                this.btnSwitch.Enabled = false;
                this.txtBeginTime.Enabled = true;
                this.txtEndTime.Enabled = true;
                this.msgType = 4;
            }
        }

        

        bool seq = true;
        /// <summary>
        /// 流水日志情况下切换序号/时间
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSwitch_Click(object sender, EventArgs e)
        {
            seq = !seq;
            this.txtSeq.Enabled = seq;
            this.txtBeginTime.Enabled = !seq;
            this.txtEndTime.Enabled = !seq;
            this.msgType = seq?2:3;
        }

        /// <summary>
        /// 发送同步消息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnOk_Click(object sender, EventArgs e)
        {
            int seqNumber = 0;
            string beginTime;
            string endTime;
            switch (this.msgType)
            {
                   
                case 1:
                    
                    try
                    {
                        seqNumber = int.Parse(txtSeq.Text.Trim());
                    }
                    catch
                    {
                        MessageBox.Show("pls input valid seqNumber first!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        return;
                    }
                    client.sendReqSyncAlarmMsg(seqNumber);
                    break;
                case 2:
                    try
                    {
                        seqNumber = int.Parse(txtSeq.Text.Trim());
                    }
                    catch
                    {
                        MessageBox.Show("pls input valid seqNumber first!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        return;
                    }
                    client.sendReqSyncAlarmFile(1,seqNumber);
                    break;
                case 3:
                    beginTime = this.txtBeginTime.Text.Trim();
                    endTime = this.txtEndTime.Text.Trim();
                    client.sendReqSyncAlarmFile(1,beginTime, endTime);
                    break;
                case 4:
                    beginTime = this.txtBeginTime.Text.Trim();
                    endTime = this.txtEndTime.Text.Trim();
                    client.sendReqSyncAlarmFile(0, beginTime, endTime);
                    break;
                default:
                    //error here~
                    break;
            }
            this.Close();
            
        }


 
    }
}
