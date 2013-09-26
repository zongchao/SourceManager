package com.neko.util;

import java.io.File;

public class FileInfo extends File
{

    private String directory; 
    private String directoryName; 
    
    /**
     * 初始化文件信息类
     * @param pathname 文件路径
     */
    public FileInfo(String pathname)
    {
        super(pathname);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
