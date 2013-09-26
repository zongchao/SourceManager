package com.neko.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zc
 * @date 20120821
 */
public class StringUtil
{
    /**
     * 判断字符串是否空或空白字符
     * 
     * @param str
     *            字符串
     * @return 判断结果
     */
    public static Boolean IsNullOrWhiteSpace(String str)
    {
        Boolean isNullOrWhiteSpace = false;
        if (str == null || str.trim().equals(""))
        {
            isNullOrWhiteSpace = true;
        }
        return isNullOrWhiteSpace;
    }

    /**
     * TODO 未测试 通过分隔符，拼接字符串数据
     * 
     * @param separator 分隔符
     * @param value 字符串数组
     * @return 判断结果
     */
    public static String join(String separator, String[] value)
    {
        StringBuffer builder = new StringBuffer("");
        if (value.length > 0)
        {
            int i = 0;
            for (String val : value)
            {
                if (i == 0)
                {
                    builder.append(val);
                }
                else
                {
                    builder.append(separator);
                    builder.append(val);
                }
                i++;
            }
        }
        return builder.toString();
    }

    /**
     * 取得md5加密后的字符串
     * @param string 原字符串
     * @return 加密后的字符串
     */
    public static String getMd5String(String string)
    {
        char hexDigits[] =
        {                                           
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'    // 用来将字节转换成 16 进制表示的字符
        };
        String md5String = string;
        byte[] bytesOfMessage;
        try
        {
            bytesOfMessage = string.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytesOfMessage);
            byte[] thedigest = md.digest(bytesOfMessage);           // MD5 的计算结果是一个 128 位的长整数，

            char str[] = new char[16 * 2];                          // 每个字节用 16 进制表示的话，使用两个字符，
            int k = 0;                                              // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++)
            {                                                       // 从第一个字节开始，对 MD5 的每一个字节
                                                                    // 转换成 16 进制字符的转换
                byte byte0 = thedigest[i];                          // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];            // 取字节中高 4 位的数字转换,
                                                                    // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf];                  // 取字节中低 4 位的数字转换
            }
            md5String = new String(str);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return md5String;
    }
}
