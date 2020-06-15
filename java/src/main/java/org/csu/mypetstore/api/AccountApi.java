package org.csu.mypetstore.api;

import com.alibaba.fastjson.JSONObject;
import org.csu.mypetstore.annotation.UserLoginToken;
import org.csu.mypetstore.domain.User;
import org.csu.mypetstore.domain.Admin;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.TokenService;
import org.csu.mypetstore.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/account")
public class AccountApi {

    @Autowired
    private AccountService accountService;

    //判断账户合法性，合法则跳转到主页否则跳转回到登录页面
    @PostMapping("/token")
    public ResponseTemplate login(String username, String password, String verifyCode, HttpServletRequest httpServletRequest)
    {
        JSONObject data=new JSONObject();


        HttpSession session=  httpServletRequest.getSession();
        String code =(String) session.getAttribute("code");
        session.setAttribute("code",null);
        if(verifyCode.equals(code))
    {
        User user = accountService.getUser(username,password);
        if(user != null)
        {
            String token = TokenService.getToken(username, password);

            data.put("user", user);
            data.put("token", token);
            return ResponseTemplate.builder()
                    .status(206)
                    .statusText("用户登录成功")
                    .data(data)
                    .build();
        }
        else
        {
            Admin admin = accountService.getAdmin(username, password);
            if(admin != null)
            {
                String token = TokenService.getToken(username, password);
               data.put("token", token);
               System.out.println(token);
                data.put("admin", admin);
                return ResponseTemplate.builder()
                        .status(207)
                        .statusText("管理员登录成功")
                        .data(data)
                        .build();
            }
            else
            {
                return ResponseTemplate.builder()
                        .status(423)
                        .statusText("用户不存在")
                        .build();

            }

        }
    }
        else {

            return ResponseTemplate.builder()
                    .status(424)
                    .statusText("验证码错误")
                    .build();
        }


    }

    //用户退出，清除用户缓存，返回登录页面
    @UserLoginToken
    @DeleteMapping("/token/{id}")
    public ResponseTemplate logOut(@PathVariable String username, SessionStatus sessionStatus)
    {
            sessionStatus.setComplete();
        return ResponseTemplate.builder()
                .status(204)
                .statusText("删除成功")
                .build();

    }

    @PostMapping("/verifyCode")
    public void sendVerificationCode(@RequestParam String phoneNumber,  HttpServletRequest httpServletRequest)
    {


        String code =String.valueOf((int)(Math.random()*1000000));
        httpServletRequest.getSession().setAttribute("code",code);
        //此处仅为模拟短信发送
        System.out.println("成功发送短信给"+phoneNumber+"，验证码为"+code);

        //此处为真实的短信发送
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "**********", "***********");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", phoneNumber);
//        request.putQueryParameter("SignName", "MyPetStore");
//        request.putQueryParameter("TemplateCode", "SMS_186968418");
//        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }

    }

}
