# SpringMVC

## SpringMVC处理静态资源

> REST风格的资源URL 不希望携带 .html  .jsp  .do 等后缀,无法加载静态资源
>
> 警告: No mapping found for HTTP request with URI [/springMVC_CRUD_war_exploded/js/jquery-3.3.1.min.js] in DispatcherServlet with name 'dispatcher'

- 原因：配置DispatchServlet请求配置为 / ，会拦截所有资源， 包括静态资源。

- 解决：配置文件中配置<mvc:default-servlet-handler/>  

- 注意：添加 <mvc:annotation-driven/>

## 关于国际化

#### 方式

1. 对页面上能够根据浏览器设置的情况对文本，时间，数值进行本地化处理
2. 可以在bean获得国际化资源Locale对应消息
3. 可以通过超链接切换Locale，不依赖浏览器的设置

#### 解决

- 使用jstl的fml标签

- 在bean中注入ResourceBundleMessageSource的实例，使用对应的getMessage的方法

- 配置LocalResolver 和 LocalChangeInterceptor

   

## 文件上传

1. 加入架包 commons-fileupload-1.4.jar  commons-io-2.6.jar
2. 配置MultipartResolver 

```java
<bean class="org.springframework.web.multipart.commons.CommonsMultipartResolver" id="multipartResolver">
    <property name="defaultEncoding" value="UTF-8"/>
    <property name="maxInMemorySize" value="1024000"/>
</bean>
```

```html
<form action="testFileUpload" method="post" enctype="multipart/form-data">
    File:<input type="file" name="file"/>
    Desc:<input type="text" name="desc">
    <input type="submit" value="submit">
</form>
```

```java
@RequestMapping(value = "/testFileUpload")
public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException {
    System.out.println("desc：" + desc);
    System.out.println("filename:" + file.getOriginalFilename());
    System.out.println("InputStream" + file.getInputStream());
    return "success";
}
```






