package com.sixtyrobbers.GSQ.fourm.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Explain: 上传文件
 * Author: holennnnnn_
 * Create_Time: 2019/5/9 14:44
 * Version: V1.0
 * </pre>
 */
public class UploadFileUtil {

    @Value("${user.background.path}")
    private static String userBackPath;

    /**
     * <pre>
     * Explain: 上传文件
     * Author: holennnnnn_
     * Create_Time: 2019/5/9 14:45
     * Version: V1.0
     * </pre>
     */
    public static Long uploadFile(CommonsMultipartFile[] files) throws IOException {
        long batch = System.currentTimeMillis();
        Map<String, Object> param = new HashMap<>(16);
        for (CommonsMultipartFile commonsMultipartFile : files) {
            // 获取图片的名字
            String imageName = commonsMultipartFile.getFileItem().getName().substring(commonsMultipartFile.getFileItem().getName().lastIndexOf('.'));
            // 时间戳防止文件重名
            String newFileName = System.currentTimeMillis() + imageName;
            // 新的文件
            File newFile = new File(userBackPath + newFileName);
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

            String imagePath = IMAGE_ONLINE_PATH + newFileName;
            if (caseEntity == null) {
                param.put("batch", batch);
            } else {
                if (caseEntity.getCaseImag() == null) {
                    param.put("batch", batch);
                } else {
                    param.put("batch", caseEntity.getCaseImag());
                }
            }
            param.put("imagPath", imagePath);
            myBatisDAO.findForObject("addCaseImage", param);
        }
        Long result = (Long) param.get("batch");
        return result;
    }

}
