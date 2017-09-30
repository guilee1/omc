using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net.Sockets;
using System.Net;
using System.Threading;

namespace omc_simulator
{
    public partial class FtpConn : Form
    {
        Form1 mainFrame;

        public FtpConn(Form1 mainFrame)
        {
            this.mainFrame = mainFrame;

            InitializeComponent();
            IPHostEntry ipEntry = Dns.GetHostEntry(Dns.GetHostName());
            foreach (IPAddress addr in ipEntry.AddressList)
            {
                if (addr.AddressFamily == AddressFamily.InterNetwork)
                {
                    this.txtFtpAddr.Text = addr.ToString();
                    break;
                }
            }
        }

        /// <summary>
        /// 关闭界面
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// 创建ftp连接
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button1_Click(object sender, EventArgs e)
        {
            string remoteIp = this.txtFtpAddr.Text.Trim();
            string remotePort = this.txtFtpPort.Text.Trim();
            string account = this.txtUser.Text.Trim();
            string pwd = this.txtPwd.Text.Trim();

            if (!Util.IsIP(remoteIp))
            {
                MessageBox.Show("pls input a valid ip address!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            int port = 0;
            try
            {
                port = int.Parse(remotePort);
            }
            catch
            {
                MessageBox.Show("pls input a valid port!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (string.IsNullOrEmpty(account) || string.IsNullOrEmpty(pwd))
            {
                MessageBox.Show("pls input a valid acc&pwd!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            FtpClient clientObj = new FtpClient(remoteIp, port, account, pwd);
            if (clientObj.connect())
                mainFrame.AddNewFtp(clientObj);
            else
                MessageBox.Show("can not login!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            this.Close();

        }
    }
}
