package com.chen.mycardsystembackstage.utils;

import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

/**
 * Project : MyCardSystem - ImageUtils
 * Powered by GeorgeChen On 2023-03-01 23:50:14
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Component
public class ImageUtils {
    private static int color_range = 210;
    /**
     * 将图片设置为透明的
     * @param small
     */
    private void rangeImage(BufferedImage small) {
        int alpha;
        for (int y = small.getMinY(); y < small.getHeight(); y++) {
            // 内层遍历是X轴的像素
            for (int x = small.getMinX(); x < small.getWidth(); x++) {
                int rgb = small.getRGB(x, y);
                // 对当前颜色判断是否在指定区间内
                if (colorInRange(rgb)) {
                    alpha = 0;
                } else {
                    // 设置为不透明
                    alpha = 255;
                }
                // #AARRGGBB 最前两位为透明度
                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                small.setRGB(x, y, rgb);
            }
        }
    }

    /**
     * 处理时判断图片中的元素属于背景还是内容
     * @param color
     * @return
     */
    private static boolean colorInRange(int color) {
        // 获取color(RGB)中R位
        int red = (color & 0xff0000) >> 16;
        // 获取color(RGB)中G位
        int green = (color & 0x00ff00) >> 8;
        // 获取color(RGB)中B位
        int blue = (color & 0x0000ff);
        // 通过RGB三分量来判断当前颜色是否在指定的颜色区间内
        if (red >= color_range && green >= color_range && blue >= color_range) {
            return true;
        }
        ;
        return false;
    }
}
