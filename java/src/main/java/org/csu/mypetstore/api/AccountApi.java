package org.csu.mypetstore.api;


import com.alibaba.fastjson.JSONObject;

import org.csu.mypetstore.annotation.UserLoginToken;
import org.csu.mypetstore.domain.User;
import org.csu.mypetstore.domain.Admin;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.PhotoService;
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



    @PostMapping("/user")
    public ResponseTemplate userRegister (@RequestBody JSONObject req)throws Exception
    {
        User user = new User();
        user.setPhone(req.getString("phone"));
        user.setEmail(req.getString("email"));
        user.setAddress(req.getString("address"));
        user.setPassword(req.getString("password"));
        user.setUsername(req.getString("username"));
        String file = req.getString("url");
        String fileType = file.substring(file.lastIndexOf(".") , file.length()).toLowerCase();
        String fileName =req.getString("username")+fileType;
        PhotoService.uploadImage(file, fileName);
        user.setUrl(PhotoService.domain+fileName);
        accountService.insertUser(user);


        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/admin")
    public ResponseTemplate adminRegister(@RequestBody JSONObject req)
    {
        Admin admin = new Admin();
        admin.setUsername(req.getString("username"));
        admin.setPassword(req.getString("password"));
        admin.setPhone(req.getString("phone"));
        String file = req.getString("url");
        String fileType = file.substring(file.lastIndexOf(".")).toLowerCase();
        String fileName =req.getString("username")+fileType;
        PhotoService.uploadImage(file, fileName);
        admin.setUrl(PhotoService.domain+fileName);

        accountService.insertAdmin(admin);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/token")
    @ResponseBody
    public ResponseTemplate login(@RequestBody  JSONObject req,  HttpServletRequest httpServletRequest)
    {
        String username = req.getString("username");
        String password = req.getString("password");
        String verifyCode = req.getString("verifyCode");
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
                    //System.out.println(token);
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
                            .statusText("用户名或密码错误")
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

    @PostMapping("/verifyCode")

    public ResponseTemplate sendVerificationCode( @RequestBody JSONObject data, HttpServletRequest httpServletRequest)
    {

        String phone = data.getString("phone");

        //  System.out.println(JSONObject.toJSONString(httpServletRequest.getParameterMap()));
        //String code =String.valueOf((int)(Math.random()*1000000));
        String code = "188234";
        httpServletRequest.getSession().setAttribute("code",code);
        //此处仅为模拟短信发送
        System.out.println("成功发送短信给"+phone+"，验证码为"+code);

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
//        request.putQueryParameter("PhoneNumbers", phone);
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

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();

    }


    @PutMapping("/user")
    @UserLoginToken
    public ResponseTemplate updateUser(@RequestBody JSONObject req)
    {
        User user = new User();
        user.setUsername(req.getString("username"));
        user.setPassword(req.getString("password"));
        user.setAddress(req.getString("address"));
        user.setEmail(req.getString("email"));
        user.setPhone(req.getString("phone"));
        String file = req.getString("url");
        if(file.substring(0,file.indexOf(":")).equals("http"))
            user.setUrl(file);
        else
        {
            String fileType = file.substring(file.lastIndexOf(".")).toLowerCase();
            String fileName =req.getString("username")+fileType;
            PhotoService.uploadImage(file, fileName);
            user.setUrl(PhotoService.domain+fileName);
        }
        accountService.updateUser(user);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }



    @PutMapping("/admin")
    @UserLoginToken
    public ResponseTemplate updateAdmin(@RequestBody JSONObject req)
    {
        Admin admin = new Admin();
        admin.setUsername(req.getString("username"));
        admin.setPassword(req.getString("password"));
        admin.setPhone(req.getString("phone"));
        String file = req.getString("url");
        if(file.substring(0,file.indexOf(":")).equals("http"))
            admin.setUrl(file);
        else
        {
            String fileType = file.substring(file.lastIndexOf(".")).toLowerCase();
            String fileName =req.getString("username")+fileType;
            PhotoService.uploadImage(file, fileName);
            admin.setUrl(PhotoService.domain+fileName);
        }
        accountService.updateAdmin(admin);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/userList")
    public ResponseTemplate getUserList()
    {
       JSONObject data = new JSONObject();

        data.put("userList", accountService.getUserList());

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/user/{username}")
    public ResponseTemplate getUser(@PathVariable String username)
    {
        JSONObject data = new JSONObject();

        data.put("user", accountService.getUser(username));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/adminList")
    public ResponseTemplate getAdminList()
    {
        JSONObject data = new JSONObject();

        data.put("adminList", accountService.getAdminList());

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/admin/{username}")
    public ResponseTemplate getAdmin(@PathVariable String username)
    {
        JSONObject data = new JSONObject();

        data.put("admin", accountService.getAdmin(username));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @DeleteMapping("/user/{username}")
    public ResponseTemplate deleteUser(@PathVariable String username)
    {

        accountService.deleteUser(username);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/userList")
    public ResponseTemplate deleteUserList()
    {
        accountService.deleteUserList();
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/admin/{username}")
    public ResponseTemplate deleteAdmin(@PathVariable String username)
    {
        accountService.deleteAdmin(username);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/adminList")
    public ResponseTemplate deleteAdminList()
    {
        accountService.deleteAdminList();
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //用户退出，清除用户缓存，返回登录页面
    @UserLoginToken
    @DeleteMapping("/token")
    public ResponseTemplate logOut( SessionStatus sessionStatus)
    {
            sessionStatus.setComplete();
        return ResponseTemplate.builder()
                .status(204)
                .statusText("删除成功")
                .build();

    }


}
