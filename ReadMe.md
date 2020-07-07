# myPetStore 3.0
这回给它取了个名字叫做“Tom‘s Dog”, 意味Tom（就是你想的那只猫）的狐朋狗友们（雾）

Spring boot+Vue实现前后端分离, RESTful风格API实践，[API文档](https://documenter.getpostman.com/view/11734954/T17J877T?version=latest)在postman发布

本地测试地址为：localhost:3000

感谢我审美在线的前端@WX， 我们有一个有很多惊喜小细节的花里胡哨的前端
前端使用 element UI+ i view组件

后端fastjson实现序列化
jwt做登录token验证
lombok简化bean构造器（所以你需要安装lombok插件哦）

七牛云作为图床（不过没给配域名，你看见它的时候它可能已经过期了~）
调用阿里支付接口和短信接口

数据库密码MD5加密

不完善的：
 - 因为后端垃圾的数据处理方式， @vaild 数据验证似乎没有起到作用；
 - 图片转换采用传本地地址方式（不要问服务器它怎么本地传，垃圾后端不会处理……）
 - 本来向用OAuth代替jwt做身份认证来着……后来时间不够（好吧是水平不足啦……）就没搞，不过OAuth当然会比较好啦
 - 本意是云服务器部署， 这部分在弄，但是还没好
 - 前端订单模块未完善， 唔预计写实验报告的时候就完善了啦~

**未完待续……**

ps：后续预计会有技术栈和坑点的blog更新， 有时间就写嘛