# 新用户注册
代码未实现新用户注册的功能。需要去数据库application_user中添加新的用户。其中的enabled字段需要是1，password字段需要使用如下方法加密原始明文密码：
```java
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    String encoded = passwordEncoder.encode("1234");
    System.out.println(encoded);
```
如上，其中的"1234"就是原始的明文密码。加密后的密码在存放在encoded字符串中。

# 正常登陆与授权
####  1. 使用角色role为ADMIN的用户名和密码登陆 http://localhost:9191/user/login 接口：
- Controller for login: pengliu.me.springsecuritydemo.controller.LoginController.login
- curl request for login, jwt token will be added to both response header "Authorization", and response body:

##### HTTP Request:
```bash
➜  ~ curl -i --location 'http://localhost:9191/user/login' \
--header 'Content-Type: application/json' \
--data '{
    "userName": "xxxx",
    "password": "xxxx"
}'
```

##### HTTP Response:
```bash
HTTP/1.1 200
Authorization: Bearer xxxx.xxxx
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Content-Length: 475
Date: Wed, 07 Jun 2023 02:24:39 GMT

{"Success":true,"Result":{"Authorization":"Bearer xxxx.xxxx"},"LocalDateTime":"2023-06-07T10:24:39.987","HttpStatus":"OK"}%

```

#### 2. 通过上面的登陆接口获得jwt token，之后所有HTTP Request的header都要附带上这个token，以便让spring security进行权限的鉴别：
- Sending PUT request with jwt token header:
```bash
➜  ~ curl --location --request PUT 'http://localhost:9191/api/v1/update/test' \
--header 'Authorization: Bearer xxxx.xxxx'

{"Success":true,"LocalDateTime":"2023-06-06T23:51:26.450","HttpStatus":"OK"}%
```


