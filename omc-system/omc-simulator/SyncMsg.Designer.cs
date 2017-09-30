namespace omc_simulator
{
    partial class SyncMsg
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.rtBtnActive = new System.Windows.Forms.RadioButton();
            this.rtBtnLog = new System.Windows.Forms.RadioButton();
            this.rtbtnFile = new System.Windows.Forms.RadioButton();
            this.rtBtnRtime = new System.Windows.Forms.RadioButton();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.btnSwitch = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.txtSeq = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.btnOk = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.txtBeginTime = new System.Windows.Forms.DateTimePicker();
            this.txtEndTime = new System.Windows.Forms.DateTimePicker();
            this.groupBox1.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.groupBox3);
            this.groupBox1.Controls.Add(this.groupBox2);
            this.groupBox1.Location = new System.Drawing.Point(3, 2);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(412, 219);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "同步配置";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.groupBox4);
            this.groupBox3.Controls.Add(this.rtbtnFile);
            this.groupBox3.Controls.Add(this.rtBtnRtime);
            this.groupBox3.Location = new System.Drawing.Point(6, 20);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(127, 193);
            this.groupBox3.TabIndex = 3;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "同步条件";
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.rtBtnActive);
            this.groupBox4.Controls.Add(this.rtBtnLog);
            this.groupBox4.Enabled = false;
            this.groupBox4.Location = new System.Drawing.Point(13, 88);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(108, 99);
            this.groupBox4.TabIndex = 4;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "文件同步方式";
            // 
            // rtBtnActive
            // 
            this.rtBtnActive.AutoSize = true;
            this.rtBtnActive.Location = new System.Drawing.Point(17, 60);
            this.rtBtnActive.Name = "rtBtnActive";
            this.rtBtnActive.Size = new System.Drawing.Size(71, 16);
            this.rtBtnActive.TabIndex = 1;
            this.rtBtnActive.Text = "活动告警";
            this.rtBtnActive.UseVisualStyleBackColor = true;
            this.rtBtnActive.CheckedChanged += new System.EventHandler(this.rtBtnActive_CheckedChanged);
            // 
            // rtBtnLog
            // 
            this.rtBtnLog.AutoSize = true;
            this.rtBtnLog.Location = new System.Drawing.Point(17, 28);
            this.rtBtnLog.Name = "rtBtnLog";
            this.rtBtnLog.Size = new System.Drawing.Size(71, 16);
            this.rtBtnLog.TabIndex = 0;
            this.rtBtnLog.Text = "流水日志";
            this.rtBtnLog.UseVisualStyleBackColor = true;
            this.rtBtnLog.CheckedChanged += new System.EventHandler(this.rtBtnLog_CheckedChanged);
            // 
            // rtbtnFile
            // 
            this.rtbtnFile.AutoSize = true;
            this.rtbtnFile.Location = new System.Drawing.Point(13, 60);
            this.rtbtnFile.Name = "rtbtnFile";
            this.rtbtnFile.Size = new System.Drawing.Size(71, 16);
            this.rtbtnFile.TabIndex = 3;
            this.rtbtnFile.Text = "文件同步";
            this.rtbtnFile.UseVisualStyleBackColor = true;
            this.rtbtnFile.CheckedChanged += new System.EventHandler(this.rtbtnFile_CheckedChanged);
            // 
            // rtBtnRtime
            // 
            this.rtBtnRtime.AutoSize = true;
            this.rtBtnRtime.Checked = true;
            this.rtBtnRtime.Location = new System.Drawing.Point(13, 28);
            this.rtBtnRtime.Name = "rtBtnRtime";
            this.rtBtnRtime.Size = new System.Drawing.Size(71, 16);
            this.rtBtnRtime.TabIndex = 2;
            this.rtBtnRtime.TabStop = true;
            this.rtBtnRtime.Text = "消息同步";
            this.rtBtnRtime.UseVisualStyleBackColor = true;
            this.rtBtnRtime.CheckedChanged += new System.EventHandler(this.rtBtnRtime_CheckedChanged);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.txtEndTime);
            this.groupBox2.Controls.Add(this.txtBeginTime);
            this.groupBox2.Controls.Add(this.btnSwitch);
            this.groupBox2.Controls.Add(this.label3);
            this.groupBox2.Controls.Add(this.label2);
            this.groupBox2.Controls.Add(this.txtSeq);
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Location = new System.Drawing.Point(139, 20);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(267, 193);
            this.groupBox2.TabIndex = 2;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "同步内容";
            // 
            // btnSwitch
            // 
            this.btnSwitch.Enabled = false;
            this.btnSwitch.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSwitch.Location = new System.Drawing.Point(90, 20);
            this.btnSwitch.Name = "btnSwitch";
            this.btnSwitch.Size = new System.Drawing.Size(75, 23);
            this.btnSwitch.TabIndex = 6;
            this.btnSwitch.Text = "序号/时间";
            this.btnSwitch.UseVisualStyleBackColor = true;
            this.btnSwitch.Click += new System.EventHandler(this.btnSwitch_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(8, 131);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(65, 12);
            this.label3.TabIndex = 4;
            this.label3.Text = "结束时间：";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(8, 88);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(65, 12);
            this.label2.TabIndex = 2;
            this.label2.Text = "开始时间：";
            // 
            // txtSeq
            // 
            this.txtSeq.Location = new System.Drawing.Point(90, 49);
            this.txtSeq.Name = "txtSeq";
            this.txtSeq.Size = new System.Drawing.Size(171, 21);
            this.txtSeq.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(8, 52);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(65, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "消息序号：";
            // 
            // btnOk
            // 
            this.btnOk.Location = new System.Drawing.Point(12, 227);
            this.btnOk.Name = "btnOk";
            this.btnOk.Size = new System.Drawing.Size(75, 23);
            this.btnOk.TabIndex = 1;
            this.btnOk.Text = "确定";
            this.btnOk.UseVisualStyleBackColor = true;
            this.btnOk.Click += new System.EventHandler(this.btnOk_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(329, 227);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 2;
            this.btnCancel.Text = "取消";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // txtBeginTime
            // 
            this.txtBeginTime.CustomFormat = "yyyy-MM-dd HH:mm:ss";
            this.txtBeginTime.Enabled = false;
            this.txtBeginTime.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.txtBeginTime.Location = new System.Drawing.Point(90, 88);
            this.txtBeginTime.Name = "txtBeginTime";
            this.txtBeginTime.Size = new System.Drawing.Size(172, 21);
            this.txtBeginTime.TabIndex = 7;
            // 
            // txtEndTime
            // 
            this.txtEndTime.CustomFormat = "yyyy-MM-dd HH:mm:ss";
            this.txtEndTime.Enabled = false;
            this.txtEndTime.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.txtEndTime.Location = new System.Drawing.Point(90, 131);
            this.txtEndTime.Name = "txtEndTime";
            this.txtEndTime.Size = new System.Drawing.Size(172, 21);
            this.txtEndTime.TabIndex = 8;
            // 
            // SyncMsg
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(416, 262);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnOk);
            this.Controls.Add(this.groupBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
            this.Name = "SyncMsg";
            this.Text = "告警同步";
            this.groupBox1.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.groupBox4.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnOk;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.RadioButton rtbtnFile;
        private System.Windows.Forms.RadioButton rtBtnRtime;
        private System.Windows.Forms.TextBox txtSeq;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.RadioButton rtBtnActive;
        private System.Windows.Forms.RadioButton rtBtnLog;
        private System.Windows.Forms.Button btnSwitch;
        private System.Windows.Forms.DateTimePicker txtBeginTime;
        private System.Windows.Forms.DateTimePicker txtEndTime;
    }
}