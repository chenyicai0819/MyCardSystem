package com.chen.mycardsystembackstage.utils;

import com.chen.mycardsystembackstage.common.responce.RestResponse;
import com.chen.mycardsystembackstage.common.responsecode.CodeDefault;
import com.chen.mycardsystembackstage.generate.CycSnowFlakeGenerate;
import com.chen.mycardsystembackstage.utils.context.SpringContextUtils;
import com.chen.mycardsystembackstage.utils.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * 操作文件工具类
 *
 * @author xumj
 * @date 2020-10-22
 */
@Slf4j
@Component
public class FileUtils {


    private static String uploadImageBasePath;

    private static String uploadAudioBasePath;

    private static String uploadVideoBasePath;

    private static String uploadRootBasePath;

    @Value("${web.upload.root:}")
    public void setUploadRootBasePath(String uploadRootBasePath){
        FileUtils.uploadRootBasePath =uploadRootBasePath;
    }

    @Value("${web.upload.image:}")
    public void setUploadImageBasePath(String uploadImageBasePath) {
        FileUtils.uploadImageBasePath = uploadImageBasePath;
    }

    @Value("${web.upload.audio:}")
    public void setUploadAudioBasePath(String uploadAudioBasePath) {
        FileUtils.uploadAudioBasePath = uploadAudioBasePath;
    }

    @Value("${web.upload.video:}")
    public void setUploadVideoBasePath(String uploadVideoBasePath) {
        FileUtils.uploadVideoBasePath = uploadVideoBasePath;
    }

    public static final String TYPE_JPG = ".jpg";

    public static final String TYPE_GIF = ".gif";

    public static final String TYPE_PNG = ".png";

    public static final String TYPE_BMP = ".bmp";

    public static final String TYPE_MP3 = ".mp3";

    public static final String TYPE_WAV = ".wav";

    public static final String TYPE_M4A = ".m4a";

    public static final String TYPE_MP4 = ".mp4";

    public static final String TYPE_UNKNOWN = "unknown";


    /**
     * 删除文件
     * @param file
     * @return
     */
    public static boolean delFile(File file) {
        if (file.exists()) {
            String name = file.getName();
            file.delete();
            System.out.println("文件 [" + name + "] 已经删除！");
            return true;
        }
        return false;
    }

    /**
     * 下载文件
     * @param response
     * @param fileName
     */
    public static void downloadFile(HttpServletResponse response, String fileName) {
        InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream("templates/" + fileName);
        if (stream == null) {
            throw new GlobalException(CodeDefault.FILE_NO_FOUND,null);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            String name = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLDecoder.decode(name, "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("文件编码转换错误" + e.getMessage());
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            assert stream != null;
            bis = new BufferedInputStream(stream);
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            RestResponse.exception("系统找不到指定的文件");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            RestResponse.exception("Io流写入出现错误");
            return;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        RestResponse.ok("200");
    }

    /**
     * 获取文件类型
     * type:1-图片，2-音频，3-视频
     *
     * @return {@link String}
     */
    public static String getFileType(String suffixName) {
        String type = null;
        switch (suffixName) {
            case TYPE_JPG:
            case TYPE_GIF:
            case TYPE_PNG:
            case TYPE_BMP:
                type = "1";
                break;
            case TYPE_MP3:
            case TYPE_WAV:
            case TYPE_M4A:
                type = "2";
                break;
            case TYPE_MP4:
                type = "3";
                break;
            default:
                break;
        }
        return type;
    }

    /**
     * 上传文件
     *
     * @param file 文件:图片、视频、音频
     * @return {@link String}
     */
    public static String uploadFile(MultipartFile file) {
        String sdfyyyyMMdd = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMdd);
        // 以日期来做文件分类
        String dateDir = sdf.format(new Date());
        // 文件后缀
        CycSnowFlakeGenerate cycSnowFlakeGenerate = new CycSnowFlakeGenerate();
        String suffixName = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = cycSnowFlakeGenerate.nextId() + suffixName;
        // 文件类型:1-图片，2-音频，3-视频
        String fileType = getFileType(suffixName);
        // 文件存放在服务器上的路径
        String fileDir = "";
        // 完整路径
        String completePath = "";
        switch (fileType) {
            case "1":
                // 图片存放在服务器上的路径
                fileDir = uploadImageBasePath + dateDir;
                completePath = "images/" + dateDir + "/" + fileName;
                break;
            case "2":
                // 音频存放在服务器上的路径
                fileDir = uploadAudioBasePath + dateDir;
                completePath = "audio/" + dateDir + "/" + fileName;
                break;
            case "3":
                // 视频存放在服务器上的路径
                fileDir = uploadVideoBasePath + dateDir;
                completePath = "videos/" + dateDir + "/" + fileName;
                break;
            default:
                break;
        }
        File saveFile = new File(fileDir, fileName);
        try {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
            log.info("{} 文件类型的上传目录:{}", suffixName, fileDir + "/" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("FileNotFound:{}", e.getMessage());
            throw new RuntimeException("添加路径找不到异常");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("saveFile fail:" + e.getMessage());
            throw new RuntimeException("保存失败");
        }
        return uploadRootBasePath+completePath;
    }

}
