package com.neko.common.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.neko.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

@Service
public class CaptchaService
{
    
    public static Logger log = Logger.getLogger(CaptchaService.class.getName());
    
    /**
     * 取得验证码图片
     * @return 验证码图片
     */
    public ByteArrayInputStream getCaptcha()
    {
        log.info("开始获取验证码");
        
        // 在内存中创建图象
        int width = 70, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        drawRandomLine(image);
        // 取随机产生的认证码(6位数字)
        String sRand = getRandomNumber(g);
        // 将认证码存入SESSION
        ActionContext.getContext().getSession().put("randomNo", sRand);
        // 图象生效
        g.dispose();
        ByteArrayInputStream input = outputImage(image);
        return input;
    }

    /**
     * 取得随机数
     * @param g 图形
     * @return 随机数
     */
    private String getRandomNumber(Graphics g)
    {
        log.info("开始取得随机数");
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < 5; i++)
        {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 16);
        }
        return sRand;
    }

    /**
     * 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
     * @param image 图片
     */
    private void drawRandomLine(BufferedImage image)
    {
        log.info("开始取得干扰线");
        Random random = new Random();
        image.getGraphics().setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++)
        {
            int x = random.nextInt(image.getWidth());
            int y = random.nextInt(image.getWidth());
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            image.getGraphics().drawLine(x, y, x + xl, y + yl);
        }
    }

    /**
     * 取得图片的输入流
     * @param image 图片
     * @return 输入流
     */
    private ByteArrayInputStream outputImage(BufferedImage image)
    {
        log.info("开始取得输入流");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageOutputStream imageOut;
        try
        {
            imageOut = ImageIO.createImageOutputStream(output);
            ImageIO.write(image, "JPEG", imageOut);
            imageOut.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(output.toByteArray());
    }
    
    /**
     * 给定范围获得随机颜色
     * @param fc frontcolor
     * @param bc backcolor
     * @return 随机颜色
     */
    private Color getRandColor(int fc, int bc)
    {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    /**
     * 判断验证信息是否正确
     * @param inputNo 输入的验证码
     * @return 是否通过验证
     */
    public static Boolean validate(String inputNo)
    {
        Boolean isvalidated = true;                                                               // 是否通过验证
        String randomNo = (String) ActionContext.getContext().getSession().get("randomNo");       // 取得SESSION中的验证码
        if(StringUtil.IsNullOrWhiteSpace(inputNo) 
                || StringUtil.IsNullOrWhiteSpace(randomNo) 
                || !randomNo.equals(inputNo))
        {
            isvalidated = false;
        }
            
        return isvalidated;
    }
}
