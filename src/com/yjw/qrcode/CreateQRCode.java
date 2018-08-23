package com.yjw.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {
	
	public static void main(String[] args) throws Exception {
		
		Qrcode x=new Qrcode();
		x.setQrcodeErrorCorrect('M');//纠错等级
		x.setQrcodeEncodeMode('B');//N代表数字,A代表a-z,B代表其他字符
		x.setQrcodeVersion(7);//版本
		String qrData="医界网";
		//利用公式求得长和高
		int width=67+12*(7-1);
		int height=67+12*(7-1);
		
		BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//java的画图工具
		Graphics2D gs=bufferedImage.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, width, height);
		
		int pixoff=2;//偏移量
		
		//往画板里面填充内容，将填充内容转成字节数往里填充
		byte[] d=qrData.getBytes("utf-8");
		if(d.length>0 && d.length<120) {
			//计算qrcode
			boolean[][]s=x.calQrcode(d);
			for(int i=0;i<s.length;i++) {
			for(int j=0;j<s.length;j++) {
				if(s[j][i]) {
					gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
				}
			}
			}
		}
		
		gs.dispose();
		bufferedImage.flush();
		ImageIO.write(bufferedImage, "png", new File("D:/code/qrcode.png"));
		
	}

}
