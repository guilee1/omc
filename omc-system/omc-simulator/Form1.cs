using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using omc_simulator.alm;
using System.Text.RegularExpressions;
using System.IO;


namespace omc_simulator
{
    public partial class Form1 : Form
    {
        
        /// <summary>
        /// socket连接对象集合
        /// </summary>
       public Hashtable almClientTable = new Hashtable();

        /// <summary>
        /// ftp连接对象集合
        /// </summary>
       public Hashtable ftpClientTable = new Hashtable();

       /// <summary>
       /// telnet连接对象集合
       /// </summary>
       public Hashtable telnetClientTable = new Hashtable();


        /// <summary>
        /// 当前活动对象ID
        /// </summary>
        string activeClientObjId;

        /// <summary>
        /// 当前活动FTP对象
        /// </summary>
        FtpClient currentFtpClient;

        /// <summary>
        /// 当前活动telnet
        /// </summary>
        TelnetClient currentTelnet;

        public Form1()
        {
            InitializeComponent();
            
        }

 
        /// <summary>
        /// 弹出socket连接窗体
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button6_Click(object sender, EventArgs e)
        {
            AlmConn connDialog = new AlmConn(this);
            connDialog.StartPosition = FormStartPosition.CenterScreen;
            connDialog.ShowDialog();
        }

        /// <summary>
        /// 同步告警消息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button7_Click(object sender, EventArgs e)
        {
            ClientObj client = chkIfSelectedTree();
            if (client != null)
            {
                SyncMsg syncDialog = new SyncMsg(client);
                syncDialog.StartPosition = FormStartPosition.CenterScreen;
                syncDialog.ShowDialog();
            }
        }

        
        /// <summary>
        /// 新增连接对象
        /// </summary>
        /// <param name="client"></param>
        public void AddNewClient(ClientObj client)
        {
             TreeNode clientNode = new TreeNode();
             clientNode.Text = client.RemoteIp + ":" + client.RemotePort  + "("+client.Account+")"+"[已连接]";
             clientNode.Tag = client.Id;
             almClientTable.Add(clientNode.Tag, client);
             treeView2.Nodes.Add(clientNode);

        }

        /// <summary>
        /// 新增FTP连接对象
        /// </summary>
        /// <param name="client"></param>
        public void AddNewFtp(FtpClient client)
        {
            this.buildFtpListView(client);

            TreeNode clientNode = new TreeNode();
            clientNode.Text = client.ftpServerIP + "-" + client.ftpUserID;
            clientNode.Tag = Guid.NewGuid().ToString();
            ftpClientTable.Add(clientNode.Tag, client);
            treeFtp.Nodes.Add(clientNode);
            this.currentFtpClient = client;

        }

        /// <summary>
        /// 构建FTP树
        /// </summary>
        /// <param name="allFiles"></param>
        private void buildFtpListView(FtpClient client)
        {
            if (client == null)
                return;
            string[] allFiles = client.getAllFiles();
            this.listFtp.Items.Clear();
            foreach (string txt in allFiles)
            {
                string[] contents = Regex.Split(txt,"\\?");
                if (contents.Length != 5)
                    continue;
                //if (contents.Length != 9)
                //    throw new Exception("ftp list??");
                ListViewItem item = new ListViewItem();
                item.Text = contents[1];
                //item.Text = (contents[8]);//名称
                item.SubItems.Add(contents[3]);//修改日期
                item.SubItems.Add(contents[0] == "Directory" ? "文件夹" : "文件");//类型
                item.SubItems.Add(contents[2]);//大小
      
                this.listFtp.Items.Add(item);
            }
        }

        /// <summary>
        /// 告警关闭对应连接
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button5_Click(object sender, EventArgs e)
        {
            TreeNode selClientNode = this.treeView2.SelectedNode;
            if (selClientNode == null)
            {
                MessageBox.Show("请先选中对应节点！", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            closeAlmClient(selClientNode.Tag.ToString());
            MessageBox.Show("selected node has removed!", "ok", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        /// <summary>
        /// 告警根据key获取节点并关闭公共方法
        /// </summary>
        /// <param name="key"></param>
        private void changeTreeNodeText(TreeNode selClientNode)
        {
            selClientNode.Text = selClientNode.Text.Replace("已连接", "未连接");
        }

        public delegate void DelCloseAlmClient(TreeNode selClientNode);

        /// <summary>
        /// 告警根据key获取节点并关闭公共方法
        /// </summary>
        /// <param name="key"></param>
        public void closeAlmClient(string key)
        {
            TreeNode selClientNode = null;
            foreach (TreeNode node in treeView2.Nodes)
            {
                if (node.Tag.ToString() == key)
                {
                    selClientNode = node;
                    break;
                }
            }
            ClientObj clientObj = (ClientObj)almClientTable[key];

            clientObj.sendCloseMessage();
            clientObj.Closed = true;

            treeView2.Invoke(new DelCloseAlmClient(changeTreeNodeText), new object[] { selClientNode });
            //almClientTable.Remove(key);
            //this.treeView2.Nodes.Remove(selClientNode);
            //this.activeClientObjId = null;
        }


        /// <summary>
        /// 指令根据key获取节点并关闭公共方法
        /// </summary>
        /// <param name="key"></param>
        public void closeTelnet(string key)
        {
            TreeNode selClientNode = null;
            foreach (TreeNode node in treeTelnet.Nodes)
            {
                if (node.Tag.ToString() == key)
                {
                    selClientNode = node;
                    break;
                }
            }

            if(selClientNode!=null)
                treeTelnet.Invoke(new DelCloseAlmClient(changeTreeNodeText), new object[] { selClientNode });
        }


        /// <summary>
        /// 弹出ftp窗体
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button12_Click(object sender, EventArgs e)
        {
            FtpConn connDialog = new FtpConn(this);
            connDialog.StartPosition = FormStartPosition.CenterScreen;
            connDialog.ShowDialog();
        }

        /// <summary>
        /// 弹出指令连接
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button18_Click(object sender, EventArgs e)
        {
            TelConn connDialog = new TelConn(this);
            connDialog.StartPosition = FormStartPosition.CenterScreen;
            connDialog.ShowDialog();
        }

   

        public delegate void DelRefrashRtAlmGrid(ArrayList arrayList);


        void refrashRtAlmGrid(ArrayList arrayList)
        {
            this.alarmVoBindingSource.DataSource = arrayList;
            this.alarmVoBindingSource.ResetBindings(true);
            if(this.dataGridView4.Rows.Count>0)
              this.dataGridView4.CurrentCell = this.dataGridView4.Rows[this.dataGridView4.Rows.Count - 1].Cells[0];
        }

        /// <summary>
        /// 刷新实时告警DataGridView
        /// </summary>
        /// <param name="arrayList"></param>
        public void RefrashRtAlmGrid(string clientObjId,ArrayList arrayList)
        {
            if (activeClientObjId == clientObjId)
                this.dataGridView4.Invoke(new DelRefrashRtAlmGrid(refrashRtAlmGrid), new object[] { arrayList });
        }


        public delegate void DelRefrashOperInfoGrid(ArrayList arrayList);


        void refrashOperInfoGrid(ArrayList arrayList)
        {
            operInfoBindingSource.DataSource = arrayList;
            operInfoBindingSource.ResetBindings(true);
            if (this.dataGridView2.Rows.Count > 0)
             this.dataGridView2.CurrentCell = this.dataGridView2.Rows[this.dataGridView2.Rows.Count - 1].Cells[0];
        }
        /// <summary>
        /// 刷新操作DataGridView
        /// </summary>
        /// <param name="arrayList"></param>
        public void RefrashOperInfoGrid(string clientObjId, ArrayList arrayList)
        {
            if(activeClientObjId == clientObjId)
                this.dataGridView2.Invoke(new DelRefrashOperInfoGrid(refrashOperInfoGrid), new object[] { arrayList });
        }


        public delegate void DelRefrashTextBox(ArrayList contents);


        void refrashTextBox(ArrayList arrayList)
        {
            this.txtResponse.Text = "";
            for (int i = 0; i < arrayList.Count; ++i)
            {
                String contents = arrayList[i].ToString();
                this.txtResponse.Text += contents;
            }
            this.txtResponse.SelectionStart = this.txtResponse.Text.Length;
            this.txtResponse.SelectionLength = 0;
            this.txtResponse.ScrollToCaret(); 
        }

        /// <summary>
        /// 刷新指令操作TextBox
        /// </summary>
        /// <param name="arrayList"></param>
        public void RefrashTxtResponse(TelnetClient clientObjId, ArrayList contents)
        {
            if (currentTelnet == clientObjId)
                this.txtResponse.Invoke(new DelRefrashTextBox(refrashTextBox), new object[] { contents });
        }



        /// <summary>
        /// 发送登录消息
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnLogin_Click(object sender, EventArgs e)
        {
           ClientObj clientObj =chkIfSelectedTree();
           if (clientObj != null)
           {
               clientObj.sendLoginMessage(this.radioButton1.Checked?0:1);
           }
        }

        /// <summary>
        /// 告警树切换不同连接对象
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void treeView2_AfterSelect(object sender, TreeViewEventArgs e)
        {
            this.activeClientObjId = e.Node.Tag.ToString();
            ClientObj client = (ClientObj)almClientTable[activeClientObjId];
            
            this.checkBox2.Checked = client.HeartBeat;
            this.chkLog.Checked = client.IsLogModel;
            this.RefrashOperInfoGrid(this.activeClientObjId, client.getOperInfoList());
            this.RefrashRtAlmGrid(this.activeClientObjId, client.getRtAlmList());
        }

        /// <summary>
        /// 切换是否需要发送心跳包
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void checkBox2_CheckedChanged(object sender, EventArgs e)
        {
            ClientObj clientObj =chkIfSelectedTree();
            if (clientObj!=null)
            {
                clientObj.HeartBeat = this.checkBox2.Checked;
            }
        }

        /// <summary>
        /// 告警树公共方法
        /// </summary>
        /// <returns></returns>
        public ClientObj chkIfSelectedTree()
        {
            TreeNode selClientNode = this.treeView2.SelectedNode;
            if (selClientNode == null)
            {
                MessageBox.Show("请先选中对应节点！", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return null;
            }
            string key = selClientNode.Tag.ToString();
            ClientObj clientObj = (ClientObj)almClientTable[key];

            return clientObj;
        }

        /// <summary>
        /// 自动生成ID列
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void dataGridView2_RowPostPaint(object sender, DataGridViewRowPostPaintEventArgs e)
        {
            //自动编号，与数据无关
            Rectangle rectangle = new Rectangle(e.RowBounds.Location.X,
               e.RowBounds.Location.Y,
               dataGridView1.RowHeadersWidth - 4,
               e.RowBounds.Height);
            TextRenderer.DrawText(e.Graphics,
                  (e.RowIndex + 1).ToString(),
                   dataGridView1.RowHeadersDefaultCellStyle.Font,
                   rectangle,
                   dataGridView1.RowHeadersDefaultCellStyle.ForeColor,
                   TextFormatFlags.VerticalCenter | TextFormatFlags.Right);
        }

        /// <summary>
        /// 同上
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void dataGridView4_RowPostPaint(object sender, DataGridViewRowPostPaintEventArgs e)
        {
            //自动编号，与数据无关
            Rectangle rectangle = new Rectangle(e.RowBounds.Location.X,
               e.RowBounds.Location.Y,
               dataGridView1.RowHeadersWidth - 4,
               e.RowBounds.Height);
            TextRenderer.DrawText(e.Graphics,
                  (e.RowIndex + 1).ToString(),
                   dataGridView1.RowHeadersDefaultCellStyle.Font,
                   rectangle,
                   dataGridView1.RowHeadersDefaultCellStyle.ForeColor,
                   TextFormatFlags.VerticalCenter | TextFormatFlags.Right);
        }
        /// <summary>
        /// 关闭ftp连接
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnFtpClose_Click(object sender, EventArgs e)
        {
            TreeNode selClientNode = this.treeFtp.SelectedNode;
            if (selClientNode == null)
            {
                MessageBox.Show("请先选中对应节点！", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            string key = selClientNode.Tag.ToString();
            FtpClient ftpClientObj = (FtpClient)this.ftpClientTable[key];
            if (ftpClientObj != null)
                ftpClientObj.close();
            this.ftpClientTable.Remove(key);

            this.listFtp.Items.Clear();
            this.treeFtp.Nodes.Remove(selClientNode);
            this.currentFtpClient = null;
        }

        /// <summary>
        /// 刷新ftp列表
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnFtpRefrash_Click(object sender, EventArgs e)
        {
            TreeNode selClientNode = this.treeFtp.SelectedNode;
            if (selClientNode == null)
            {
                MessageBox.Show("请先选中对应节点！", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            string key = selClientNode.Tag.ToString();
            FtpClient ftpClientObj = (FtpClient)this.ftpClientTable[key];
            this.buildFtpListView(ftpClientObj);
        }

        /// <summary>
        /// 下载事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void menuItemDownLoad_Click(object sender, EventArgs e)
        {
            if (currentFtpClient == null)
                throw new Exception("error here~");
            DialogResult result = this.folderBrowserDialog1.ShowDialog();
            if (result == DialogResult.OK)
            {
                string path = this.folderBrowserDialog1.SelectedPath;
                string fileName = this.listFtp.SelectedItems[0].Text;
                currentFtpClient.Download(path, fileName);
                MessageBox.Show("save successful！");
            }
        }

        /// <summary>
        /// 判断是否选中项
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void menuFtpList_Opening(object sender, CancelEventArgs e)
        {
            if (currentFtpClient == null)
                e.Cancel = true;
            ListView.SelectedIndexCollection c = this.listFtp.SelectedIndices;
            if (c.Count <= 0)
            {
                //e.Cancel = true;
                this.menuFtpList.Items[0].Enabled = false;
                this.menuFtpList.Items[2].Enabled = false;
            }
            else
            {
                string type = this.listFtp.SelectedItems[0].SubItems[2].Text;
                if (type != "文件夹")
                {
                    this.menuFtpList.Items[0].Enabled = true;
                    this.menuFtpList.Items[2].Enabled = true;
                }
                else
                {
                    this.menuFtpList.Items[0].Enabled = false;
                    this.menuFtpList.Items[2].Enabled = false;
                }
            }
        }

        /// <summary>
        /// FTP操作树选中
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void treeFtp_AfterSelect(object sender, TreeViewEventArgs e)
        {
            TreeNode selClientNode = e.Node;
            string key = selClientNode.Tag.ToString();
            currentFtpClient = (FtpClient)this.ftpClientTable[key];
            this.buildFtpListView(currentFtpClient);
        }

        /// <summary>
        /// 双击进入选中目录
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listFtp_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            if (this.listFtp.SelectedItems.Count == 0)
                return;
            string type = this.listFtp.SelectedItems[0].SubItems[2].Text;
            if (type != "文件夹")
            {
                MessageBox.Show("pls choose a folder to enter!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            string directory = this.listFtp.SelectedItems[0].Text;
            if (currentFtpClient == null)
                throw new Exception("error occa~");
            currentFtpClient.GotoDirectory(directory, false);
            this.buildFtpListView(currentFtpClient);
        }

        /// <summary>
        /// 向上目录
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            currentFtpClient.GotoDirectory("../", false);
            this.buildFtpListView(currentFtpClient);
        }

        /// <summary>
        /// 选择一个告警文件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnChooseAlarm_Click(object sender, EventArgs e)
        {
            DialogResult dr = this.openFileDialog1.ShowDialog();
            if (dr == DialogResult.OK)
            {
                this.txtAlarmFilePath.Text = this.openFileDialog1.FileName;
            }
        }

        /// <summary>
        /// 读取告警文件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnReadAlarm_Click(object sender, EventArgs e)
        {
            if (this.txtAlarmFilePath.Text == "")
            {
                MessageBox.Show("pls choose a file to read first!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            ArrayList alarmList = new ArrayList();
            string path = this.txtAlarmFilePath.Text;
            StreamReader sr = new StreamReader(path, Encoding.UTF8);
            String line;
            try
            {
                while ((line = sr.ReadLine()) != null)
                {
                    AlarmVo vo = Util.DeserializeJsonToObject<AlarmVo>(line);
                    alarmList.Add(vo);
                }
                MessageBox.Show("parse over!");
                this.dgAlarmFile.DataSource = alarmList;
            }
            catch
            {
                MessageBox.Show("bad alarm file format!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            finally{
                sr.Close();
            } 
            
        }

        /// <summary>
        /// 新增telnet连接
        /// </summary>
        /// <param name="telnetClient"></param>
        internal void AddNewTelnet(TelnetClient client)
        {
            TreeNode clientNode = new TreeNode();
            clientNode.Text = client.RemoteIp + "-" + client.RemotePort + "[已连接]";
            clientNode.Tag = client.Id;
            telnetClientTable.Add(clientNode.Tag, client);
            treeTelnet.Nodes.Add(clientNode);
            this.currentTelnet = client;
        }

        /// <summary>
        /// Telnet 指令发送
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button15_Click(object sender, EventArgs e)
        {
            if (currentTelnet == null)
            {
                MessageBox.Show("请先选中对应节点！", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (this.txtRequest.Text.Trim() == "")
            {
                MessageBox.Show("pls input send text!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            currentTelnet.sendMessage(this.txtRequest.Text.Trim());
        }

        /// <summary>
        /// 关闭telnet连接
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnTelClose_Click(object sender, EventArgs e)
        {
            TreeNode selClientNode = this.treeTelnet.SelectedNode;
            if (selClientNode == null)
            {
                MessageBox.Show("请先选中对应节点！", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string key = selClientNode.Tag.ToString();
            TelnetClient client = (TelnetClient)this.telnetClientTable[key];
            this.telnetClientTable.Remove(key);
            if (client != null)
                client.Close();
            this.txtRequest.Text = "";
            this.txtResponse.Text = "";
            this.treeTelnet.Nodes.Remove(selClientNode);
            this.currentTelnet = null;
        }

        /// <summary>
        /// 打开自定义消息界面
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnCustomMsg_Click(object sender, EventArgs e)
        {
            ClientObj client = chkIfSelectedTree();
            if (client != null)
            {
                CustomMsg dialog = new CustomMsg(client);
                dialog.StartPosition = FormStartPosition.CenterScreen;
                dialog.ShowDialog();
            }
            
        }

        /// <summary>
        /// 日志模式，不显示界面UI
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void chkLog_CheckedChanged(object sender, EventArgs e)
        {
            ClientObj clientObj = chkIfSelectedTree();
            if (clientObj != null)
            {
                clientObj.IsLogModel = this.chkLog.Checked;
            }
            
        }

        /// <summary>
        /// 切换telnet树
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void treeTelnet_AfterSelect(object sender, TreeViewEventArgs e)
        {
            TelnetClient client = (TelnetClient)this.telnetClientTable[e.Node.Tag.ToString()];
            this.currentTelnet = client;

            this.RefrashTxtResponse(client, client.getOperInfoList());
        }

        private void dgAlarmFile_RowPostPaint(object sender, DataGridViewRowPostPaintEventArgs e)
        {
            //自动编号，与数据无关
            Rectangle rectangle = new Rectangle(e.RowBounds.Location.X,
               e.RowBounds.Location.Y,
               dgAlarmFile.RowHeadersWidth - 4,
               e.RowBounds.Height);
            TextRenderer.DrawText(e.Graphics,
                  (e.RowIndex + 1).ToString(),
                   dgAlarmFile.RowHeadersDefaultCellStyle.Font,
                   rectangle,
                   dgAlarmFile.RowHeadersDefaultCellStyle.ForeColor,
                   TextFormatFlags.VerticalCenter | TextFormatFlags.Right);
        }


        private void 持续下载5分钟ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (currentFtpClient == null)
                throw new Exception("error here~");
            DialogResult result = this.folderBrowserDialog1.ShowDialog();
            if (result == DialogResult.OK)
            {
                string path = this.folderBrowserDialog1.SelectedPath;
                string fileName = this.listFtp.SelectedItems[0].Text;
                currentFtpClient.DownloadcContinuously(path, fileName);
                
            }
        }

    
    }
}
