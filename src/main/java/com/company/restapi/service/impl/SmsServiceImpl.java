package com.company.restapi.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.company.common.constant.BaseConstant;
import com.company.restapi.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SmsServiceImpl implements SmsService {

    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static final String accessKeyId = "LTAIaow50kTg1aqN";
    private static final String accessKeySecret = "U0dbwM54L8NZw8eW3tkEEtTSdPMJz9";

    @Override
    public Map<Object, Object> sendVerificationCode(String phone, String code) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phone);
            request.setSignName("北京凌顶科技");
            request.setTemplateCode("SMS_110540017");
            request.setTemplateParam("{\"code\":\"" + code + "\"}");

            SendSmsResponse sendSmsResponse=null;
			try {
				sendSmsResponse = acsClient.getAcsResponse(request);
			} catch (Exception e) {
			
			}

            Map<Object, Object> introspected = new org.apache.commons.beanutils.BeanMap(sendSmsResponse);
            Map<Object, Object> rMap = new HashMap<>();
            for(Object key: introspected.keySet()) {
                rMap.put(key + "", introspected.get(key));
            }
            map.put("code", BaseConstant.appUserSuccessStatus);
            map.put("msg", "成功发送验证码");
            map.put("extra",null);
            map.put("resultMap", rMap);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra",null);
            map.put("resultMap", null);
            return map;
        }
    }

}
