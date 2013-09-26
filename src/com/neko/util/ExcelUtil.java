package com.neko.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

import org.springframework.format.annotation.DateTimeFormat;

import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtil
{
    
    /**
     * 创建excel文件
     * @param file
     * @return WritableWorkbook
     */
    public static WritableWorkbook createWorkbook(File file)
    {
        WritableWorkbook workbook = null;
        try
        {
            workbook = Workbook.createWorkbook(file);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return workbook;
    }
    
    /**
     * 创建工作簿
     * @param workbook
     * @param sheetName 工作簿名称
     * @return
     */
    public static WritableSheet createSheet(WritableWorkbook workbook, String sheetName)
    {
        WritableSheet sheet = workbook.createSheet(sheetName, workbook.getSheets().length);
        return sheet;
    }
    
    /**
     * 添加单元格内容
     * @param sheet 工作簿
     * @param c 第几列
     * @param r 第几行
     * @param value 值（字符串、数字、日期、布尔）
     * @param option （0=字符串，1=数字、2=日期、3=布尔）
     */
    public static void addCell(WritableSheet sheet,int c, int r, Object value, int option)
    {
        try
        {
            switch (option)
            {
                case 0:
                    sheet.addCell(new Label(c,r,(String)value));
                    break;
                case 1:
                    sheet.addCell(new jxl.write.Number(c,r,Double.valueOf(value.toString())));
                    break;
                case 2:
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sheet.addCell(new jxl.write.DateTime(c,r,sdf.parse(value.toString())));
                    break;
                case 3:
                    sheet.addCell(new jxl.write.Boolean(c,r,(Boolean)value));
                    break;
                default:
                    sheet.addCell(new Label(c,r,(String)value));
                    break;
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        File file = new File("test.xls");
        if(file.exists()) file.delete();
        WritableWorkbook book = createWorkbook(file);
        WritableSheet sheet = createSheet(book, "aa");
        addCell(sheet, 1, 1, "asd", 0);
        try
        {
            book.write();
            book.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
