using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Net;
using System.Windows.Forms;
using System.Globalization;
using FtpLib;
using System.Threading;

namespace omc_simulator
{

    public class FtpClient
    {

        FtpConnection conn;
        public string ftpServerIP;
        public string ftpUserID;

        public FtpClient(string ip, int port, string user, string pwd)
        {
            this.ftpServerIP = ip;
            this.ftpUserID = user;
            conn = new FtpConnection(ip, port, user, pwd);
        }

        /// <summary>
        /// connect and login
        /// </summary>
        /// <returns></returns>
        public bool connect()
        {
            try
            {
                conn.Open();
                conn.Login();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message, "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false;
            }
            return true;
        }


        /// <summary>
        /// 下载
        /// </summary>
        /// <param name="filePath"></param>
        /// <param name="fileName"></param>
        public void Download(string filePath, string fileName)
        {
            try
            {
                conn.GetFile(conn.GetCurrentDirectory() + "/" + fileName, filePath + "\\" + fileName, false);
            }
            catch
            {
                MessageBox.Show("ftp has lost connection!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }



        /// <summary>
        /// 持续下载
        /// </summary>
        /// <param name="filePath"></param>
        /// <param name="fileName"></param>
        public void DownloadcContinuously(string filePath, string fileName)
        {
            ThreadPool.QueueUserWorkItem(new WaitCallback(DownloadcContinuously_CallBack),new String[]{filePath,fileName});
        }

        public void DownloadcContinuously_CallBack(object param)
        {
            DateTime startTime = DateTime.Now;
            DateTime currentTime = startTime;
            int delay = 5;
            string[] filePath = (string[])param;
            TimeSpan diff = currentTime - startTime;
            while (diff.Minutes<5)
            {
                Download(filePath[0], filePath[1]);
                currentTime = DateTime.Now;
                diff = currentTime - startTime;
                Thread.Sleep(500);
            }
            MessageBox.Show("DownloadcContinuously Finish！");
        }


        /// <summary>
        /// 获取目录下的directory 和files
        /// </summary>
        /// <returns></returns>
        public String[] getAllFiles()
        {
            StringBuilder result = new StringBuilder();
            try
            {
                foreach (var dir in conn.GetDirectories(conn.GetCurrentDirectory()))
                {
                    result
                    .Append(dir.Attributes.ToString()).Append("?")
                    .Append(dir.Name).Append("?")
                    .Append("0").Append("?")
                    .Append(dir.LastWriteTime).Append("?")
                    .Append("\n");
                }
                foreach (var f in conn.GetFiles(conn.GetCurrentDirectory()))
                {
                    result
                    .Append(f.Attributes).Append("?")
                    .Append(f.Name).Append("?")
                    .Append(conn.GetFileSize(conn.GetCurrentDirectory() + "/" + f.Name)).Append("?")
                    .Append(f.LastWriteTime).Append("?")
                    .Append("\n");
                }
            }
            catch
            {
                MessageBox.Show("ftp has lost connection!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            return result.ToString().Split('\n');
        }



        /// <summary>
        /// 切换当前目录
        /// </summary>
        /// <param name="DirectoryName"></param>
        /// <param name="IsRoot">true 绝对路径   false 相对路径</param>
        public void GotoDirectory(string DirectoryName, bool IsRoot)
        {
            try
            {
                conn.SetCurrentDirectory(DirectoryName);
            }
            catch
            {
                MessageBox.Show("ftp has lost connection!", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }



        public void close()
        {
            if (conn != null)
                conn.Close();
        }
    }
 
  
}