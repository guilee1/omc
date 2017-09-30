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
    public partial class AlmConn : Form
    {
        Form1 mainFrame;

        public AlmConn(Form1 mainFrame)
        {
            this.mainFrame = mainFrame;

            InitializeComponent();
            IPHostEntry ipEntry = Dns.GetHostEntry(Dns.GetHostName());
            foreach (IPAddress addr in ipEntry.AddressList)
            {
                if (addr.AddressFamily == AddressFamily.InterNetwork)
                {
                    this.textBox3.Text = addr.ToString();
                    break;
                }
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string remoteIp = this.textBox3.Text.Trim();
            string remotePort = this.textBox4.Text.Trim();
            string account = this.textBox1.Text.Trim();
            string pwd = this.textBox2.Text.Trim();
            int number = 1;
            if(!Util.IsIP(remoteIp)){
                MessageBox.Show("pls input a valid ip address!","error",MessageBoxButtons.OK,MessageBoxIcon.Error);
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
            try
            {
                number = int.Parse(txtNumber.Text.Trim());
            }
            catch (Exception err)
            {
                MessageBox.Show("pls input a valid number!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            //succ
            for (int i = 0; i < number; ++i)
            {
                ClientObj clientObj = new ClientObj(this.mainFrame, remoteIp, port, account, pwd);

                if (!clientObj.connect())
                {
                    MessageBox.Show("can not connect to remote ip!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                else
                {
                    mainFrame.AddNewClient(clientObj);
                   
                }
            }
            this.Close();
        }
    }
}
