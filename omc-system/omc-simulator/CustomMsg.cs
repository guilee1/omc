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
    public partial class CustomMsg : Form
    {
        ClientObj client;

        public CustomMsg(ClientObj c)
        {
            InitializeComponent();
            this.client = c;
        }

        /// <summary>
        /// 发送消息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnOk_Click(object sender, EventArgs e)
        {
            if (cbMsgType.SelectedItem == null)
            {
                MessageBox.Show("pls select message type first!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            client.sendMessage(Message.buildCustomMsg(cbMsgType.SelectedItem.ToString(), txtMsgContent.Text));
            this.Close();
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
