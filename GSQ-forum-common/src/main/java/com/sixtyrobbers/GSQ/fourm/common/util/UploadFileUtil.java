package com.sixtyrobbers.GSQ.fourm.common.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Explain: 上传文件
 * Author: holennnnnn_
 * Create_Time: 2019/5/9 14:44
 * Version: V1.0
 * </pre>
 */
public class UploadFileUtil {


    /**
     * <pre>
     * Explain: 上传文件
     * Author: holennnnnn_
     * Create_Time: 2019/5/9 14:45
     * Version: V1.0
     * </pre>
     */
    public static List<String> uploadFile(CommonsMultipartFile[] files,String userBackPath,String onlinePath) throws IOException {
        List<String> pathList = new ArrayList<>();
        for (CommonsMultipartFile commonsMultipartFile : files) {
            long batch = System.currentTimeMillis();
            // 获取文件的格式
            String fileType = commonsMultipartFile.getFileItem().getName().substring(commonsMultipartFile.getFileItem().getName().lastIndexOf('.'));
            // 时间戳防止文件重名
            String fileName = batch + fileType;
            // 新的文件
            File newFile = new File(userBackPath + fileName);
            InputStream inputStream;
            inputStream = commonsMultipartFile.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            int index = 0;
            byte[] bytes = new byte[inputStream.available()];
            while ((index = inputStream.read(bytes, 0, bytes.length)) != -1) {
                fileOutputStream.write(bytes, 0, bytes.length);
            }
            fileOutputStream.flush();
            inputStream.close();
            fileOutputStream.close();
            String imagePath = onlinePath + fileName;
            pathList.add(imagePath);
        }
        return pathList;
    }

    public static List<String> uploadFile1(CommonsMultipartFile[] files,String userBackPath,String onlinePath) throws IOException {
        List<String> pathList = new ArrayList<>();
        for (CommonsMultipartFile commonsMultipartFile : files) {
            long batch = System.currentTimeMillis();
            // 获取文件的格式
            String fileType = commonsMultipartFile.getFileItem().getName().substring(commonsMultipartFile.getFileItem().getName().lastIndexOf('.'));
            // 时间戳防止文件重名
            String fileName = batch + fileType;
            // 新的文件
            File newFile = new File(userBackPath + fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(commonsMultipartFile.getInputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFile));
            int index = 0;
            byte[] bytes = new byte[1024];
            while ((index = bufferedInputStream.read(bytes, 0, bytes.length)) != -1) {
                bufferedOutputStream.write(bytes, 0, bytes.length);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
            String imagePath = onlinePath + fileName;
            pathList.add(imagePath);
        }
        return pathList;
    }

}
