## 项目介绍

大三作业项目

sharePlatForm是一个数码产品分享平台项目，包括前台系统与后台的管理功能，基于SpringBoot+MyBatis实现，前台功能包括平台首页、用户注册登录、数码产品搜索、产品浏览、订单查看、购物车、物品委托、个人信息等模块。

1. 用户注册功能：平台新用户将在注册页面填写注册资料进行新用户注册。
2. 用户登录功能：平台注册用户将在登录页面使用账号、密码进行登录。
3. 物品搜索功能：用户在平台任一页面顶部导航栏输入物品关键字进行搜索。
4. 物品分类搜索功能：用户在平台任一页面顶部导航栏进入物品分类菜单，点击对应分类进行搜索。
5. 物品浏览功能：在物品浏览页面，用户可以把物品加入购物车，可以直接租赁物品。
6. 订单浏览功能：在订单浏览页面，用户可以看到本账户已经下单每一条账单内容。
7. 购物车功能：在购物车页面，用户可以查看已经加入到本账户购物车的所有物品，可以进行批量租赁下单，可以对租赁物品进行编辑。
8. 委托浏览功能：用户可以在发布委托页面进行委托发布，管理员可以在委托审核页面对用户发布的委托进行审核发布。
9. 个人信息浏览：用户在个人信息浏览页面可以编辑本账户的个人信息。





### 项目演示

---

#### HOME PAGE

![img](img/wpsontgIi.jpg)

![img](img/wpsObtaY2.png)

![img](img/wpsSlZXuH.png)

![img](img/wpsjpVslI.png)

### CART PAGE

![img](img/wpsNA28Cx.png)

![img](img/wpsWnCWGz.png)


### PRODUCT DETAIL PAGE

![img](img/wpsld1iKy.png)


### LOGIN PAGE

![img](img/wpsYwChds.png)



### MY INFO PAGE

![img](img/wpsqW5Zj0.png)



### MY ORDER PAGE

![img](img/wpsseaZt0.png)

![img](img/wps4apNve.png)



### PUBLISH ENTRUST PAGE

![img](img/wpslg3gHI.png)



### REGISTER PAGE

![img](img/wpsfYofYh.png)


### SEARCH PAGE

![img](img/wpsoPuzCk.png)



### VERIFY ENTRUST PAGE

![img](img/wpsSPjrtN.png)

![img](img/wpspJihen.png)



### 组织结构

sharePlatForm

> - sharePlatform后端
>
>   - src-com.shareplatform.demo.config -- 跨域与Shiro等配置文件
>
>   - src-com.shareplatform.demo.controller -- 平台控制器
>
>   - src-com.shareplatform.demo.entity -- 实体类
>
>   - src-com.shareplatform.demo.mapper -- mapper类
>
>   - src-com.shareplatform.demo.service -- 服务类
>
>   - src-com.shareplatform.demo.util -- 一些工具类
>
>   - src-com.shareplatform.demo.test -- 测试代码
>
> - share_Platform前端
>
>   - src-api各个页面请求后端的api
>   - src-components页面的组件
>   - src-pages页面
>   - src-router页面路由配置
>   - src-static静态资源
>   - src-store全局状态管理配置
>   - src-utils有axios配置



### 运行

>- 后端
>
>  - Maven更新对应版本依赖
>  - application.yml设置数据库配置、设置端口号
>
>- 前端
>
>  - utils-axios.js设置后端请求地址
>
>  - ```
>    # Install dependencies
>    npm install
>    
>    # Serve with hot reload at localhost:8080
>    npm run dev
>    
>    npm run build
>    ```



### 技术栈

- SpringBoot
- SpringMVC
- MyBatis
- MySQL8
- Shiro
- Tomcat
- Maven
- Vue
- Vuex
- Vue Router
- Element UI
- ES6
- axios
