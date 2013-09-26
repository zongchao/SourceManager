package com.neko.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.sun.corba.se.spi.monitoring.StatisticMonitoredAttribute;

public class FileUtil {

    /**
     * 读取文件为二进制
     * @param path
     * @return 二进制
     */
    public static byte[] read(String path)
    {
        File file = new File(path);
        byte[] b = new byte[1];
        if(file.isFile() && file.exists())
        {
            FileInputStream fis = null;
            try
            {
                fis = new FileInputStream(file);
                fis.read(b, 0, (int) file.length());
                fis.close();
            }
            catch (IOException e)
            {
                System.out.println("文件读取错误");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("路径中文件不存在");
        }
        return b;
    }
    
    /**
     * 读取文件为输入流
     * @param path
     * @return 输入流
     */
    public static InputStream readStrem(String path)
    {
        File file = new File(path);
        FileInputStream fis = null;
        if(file.isFile() && file.exists())
        {
            try
            {
                fis = new FileInputStream(file);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("文件没有找到");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("路径中文件不存在");
        }
        return fis;
    }
    
    /**
     * 新建文件夹
     * @param folderPath 文件夹路径
     */
    public static void createFolder(String folderPath)
    {
        try
        {
            String filePath = folderPath;
            java.io.File myFilePath = new java.io.File(filePath);
            if (!myFilePath.exists())
            {
                myFilePath.mkdir();
            }
        }
        catch (Exception e)
        {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 新建文件
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @param fileContent String 文件内容
     * @return boolean
     */
    public static void newFile(String filePathAndName, String fileContent)
    {
        try
        {
            String filePath = filePathAndName;
            filePath = filePath.toString(); // 取的路径及文件名
            File myFilePath = new File(filePath);
            /** 如果文件不存在就建一个新文件 */
            if (!myFilePath.exists())
            {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath); // 用来写入字符文件的便捷类,
                                                                // 在给出 File
                                                                // 对象的情况下构造一个
                                                                // FileWriter 对象
            PrintWriter myFile = new PrintWriter(resultFile); // 向文本输出流打印对象的格式化表示形式,使用指定文件创建不具有自动行刷新的新
                                                              // PrintWriter。
            String strContent = fileContent;
            myFile.println(strContent);
            resultFile.close();
        }
        catch (Exception e)
        {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }

    }

    /**
     * 删除文件
     * 
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     */
    public static void delFile(String filePathAndName)
    {
        try
        {
            String filePath = filePathAndName;
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();
        }
        catch (Exception e)
        {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹
     * 
     * @param filePathAndName String 文件夹路径及名称 如c:/fqf
     */
    public static void delFolder(String folderPath)
    {
        try
        {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹

        }
        catch (Exception e)
        {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹里面的所有文件
     * 
     * @param path String 文件夹路径 如 c:/fqf
     */
    public static void delAllFile(String path)
    {
        File file = new File(path);
        if (!file.exists())
        {
            return;
        }
        if (!file.isDirectory())
        {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++)
        {
            if (path.endsWith(File.separator))
            {
                temp = new File(path + tempList[i]);
            }
            else
            {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile())
            {
                temp.delete();
            }
            if (temp.isDirectory())
            {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
            }
        }
    } 

    /**
     * 复制单个文件
     * 
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath)
    {
        try
        {
            // int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists())
            { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                // int length;
                while ((byteread = inStream.read(buffer)) != -1)
                {
                    // bytesum += byteread; //字节数 文件大小
                    // System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                fs.close();
                inStream.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    } 
 
    /**
     * 复制单个文件
     * 
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(File sourceFile, File targetFile)
    {
        try
        {
            // int bytesum = 0;
            int byteread = 0;
            if (sourceFile.exists())
            { 
                // 文件存在时
                InputStream inStream = new FileInputStream(sourceFile); // 读入原文件
                FileOutputStream fs = new FileOutputStream(targetFile);
                byte[] buffer = new byte[1444];
                // int length;
                while ((byteread = inStream.read(buffer)) != -1)
                {
                    // bytesum += byteread; //字节数 文件大小
                    // System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                fs.close();
                inStream.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    } 
    
    /**
     * 复制整个文件夹内容
     * 
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath)
    {
        try
        {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++)
            {
                if (oldPath.endsWith(File.separator))
                {
                    temp = new File(oldPath + file[i]);
                }
                else
                {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile())
                {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1)
                    {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory())
                {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 移动文件到指定目录
     * 
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath
     *            String 如：d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath)
    {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * 移动文件到指定目录
     * 
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void moveFolder(String oldPath, String newPath)
    {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }
    
    
    /**
     * 获取文件后缀名
     * @param fileName 文件名称
     * @return 后缀名(.xxx)
     */
    public static String getExtension(String fileName)
    {
        String extension = "";
        if(fileName.indexOf(".") != -1)
        {
            extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return extension;
    }
    
    /**
     * 去除文件后缀名
     * @param fileName 文件名称
     * @return 后缀名(.xxx)
     */
    public static String removeExtension(String fullFileName)
    {
        String fileName = "";
        if(fullFileName.indexOf(".") != -1)
        {
            fileName = fullFileName.substring(0, fullFileName.lastIndexOf("."));
        }
        return fileName;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    System.out.println(removeExtension("a.txt")); 
	}

}
