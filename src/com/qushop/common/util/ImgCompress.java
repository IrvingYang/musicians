package com.qushop.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

public class ImgCompress {

	private Image img;
	private int width;
	private int height;

	/**
	 * 构造函数
	 */
	public ImgCompress(String fileName) throws IOException {
		File file = new File(fileName);// 读入文件
		img = ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	/**
	 * 按照宽度还是高度进行压缩
	 * 
	 * @param w
	 *            int 最大宽度
	 * @param h 
	 *            int 最大高度
	 */
	public void resizeFix(int w, int h, File outputFile) throws IOException {
		
		//edit by Irving
//		if (width / height > w / h) {
//			resizeByWidth(w, outputFile);
//		} else {
//			resizeByHeight(h, outputFile);
//		}
		resize(w, h,outputFile);
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            int 新宽度
	 */
	public void resizeByWidth(int w, File outputFile) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h, outputFile);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            int 新高度
	 */
	public void resizeByHeight(int h, File outputFile) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h, outputFile);
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 */
	public void resize(int w, int h, File outputFile) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		// 可以正常实现bmp、png、gif转jpg
		Builder<BufferedImage> builder = Thumbnails.of(image)
				.size(w, h);
		builder.toFile(outputFile);
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		System.out.println("开始：" + System.currentTimeMillis());
		ImgCompress imgCom = new ImgCompress("E:\\eee.jpg");
		// imgCom.resizeFix(730, 730);
		System.out.println("结束：" + System.currentTimeMillis());
	}
}