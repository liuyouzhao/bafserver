package com.baf.server.tool.codegen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码生成器
 * 
 * @author dsna
 * 
 */
public class ValidateCode {
	// 图片的宽度。
	private int width = 120;
	// 图片的高度。
	private int height = 30;
	// 验证码字符个数
	private int codeCount = 3;
	// 验证码干扰线数
	private int lineCount = 100;
	// 验证码
	private String code = null;
	// 验证码图片Buffer
	private BufferedImage buffImg = null;

//	private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
//			'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
//			'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private char[] codeSequence = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public ValidateCode() {
		this.createCode();
	}

	/**
	 * 
	 * @param width
	 *            图片宽
	 * @param height
	 *            图片高
	 */
	public ValidateCode(int width, int height) {
		this.width = width;
		this.height = height;
		this.createCode();
	}

	/**
	 * 
	 * @param width
	 *            图片宽
	 * @param height
	 *            图片高
	 * @param codeCount
	 *            字符个数
	 * @param lineCount
	 *            干扰线条数
	 */
	public ValidateCode(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
		this.createCode();
	}

	public void createCode() {
		int x = 0, fontHeight = 0, codeY = 0;
		int red = 0, green = 0, blue = 0;

		x = width / (codeCount + 2);// 每个字符的宽度
		fontHeight = height - 2;// 字体的高度
		codeY = height - 4;

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 生成随机数
		Random random = new Random();
		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 创建字体
		ImgFontByte imgFont = new ImgFontByte();
		Font font = imgFont.getFont(fontHeight);
		g.setFont(font);

		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width / 8);
			int ye = ys + random.nextInt(height / 8);
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawLine(xs, ys, xe, ye);
		}

		// randomCode记录随机产生的验证码
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount个字符的验证码。
		System.out.println("codeCount: " + codeCount);
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random
					.nextInt(codeSequence.length)]);
			// 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			//g.setColor(new Color(red, green, blue));
			g.setColor(new Color(0, 0, 0));
			double rot = Math.random() * 3.14 * 0.5;
			g.drawString(strRand, (i + 1) * x, codeY);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		//drawRandomNum(g);
		// 将四位数字的验证码保存到Session中。
		code = randomCode.toString();
	}

	private String drawRandomNum(Graphics2D g) {
		System.out.println("------------ drawRandomNum");
		g.setColor(Color.RED);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		// [\u4e00-\u9fa5]中文数字区间
		String base = "\u4e00\u4f00\u5e00\u4e50\u4e89\u4f10\u4e09";
		StringBuffer sb = new StringBuffer();
		int x = 5;
		for (int i = 0; i < 4; i++) {
			int degree = new Random().nextInt() % 30;
			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			sb.append(ch);
			g.rotate(degree * Math.PI / 180, x, 20);
			g.drawString(ch, x, 20);
			g.rotate(-degree * Math.PI / 180, x, 20);
			x = x + 30;
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public void write(String path) throws IOException {
		OutputStream sos = new FileOutputStream(path);
		this.write(sos);
	}

	public void write(OutputStream sos) throws IOException {
		ImageIO.write(buffImg, "png", sos);
		sos.close();
	}

	public BufferedImage getBuffImg() {
		return buffImg;
	}

	public String getCode() {
		return code;
	}
}
