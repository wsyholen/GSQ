package com.sixtyrobbers.GSQ.fourm.common.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * <pre>
 * Explain:
 * Author: holennnnnn_
 * Create_Time: 2019/4/26 14:44
 * Version: V1.0
 * </pre>
 */
public class ALiSendMessageUtil {

    public static String sendMessage(String mobile, String code, String noteCode) throws ClientException {
        String result = null;
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIZcvVAuIiHWIM", "MgAsSLKJazJ0vaIrB9Gq8oJXBkftTG");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "GSQ论坛");
        request.putQueryParameter("TemplateCode", noteCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        CommonResponse response = client.getCommonResponse(request);
        result = response.getData();
        return result;
    }

}
