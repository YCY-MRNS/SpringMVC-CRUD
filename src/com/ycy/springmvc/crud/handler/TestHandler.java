package com.ycy.springmvc.crud.handler;

import com.ycy.springmvc.crud.dao.DepartmentDao;
import com.ycy.springmvc.crud.dao.EmployeeDao;
import com.ycy.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * @program: springMVC-CRUD
 * @description:
 * @author: ChangYue
 * @create: 2019-02-27 18:28
 */
@Controller
public class TestHandler {


    @Autowired
    public EmployeeDao employeeDao;

    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;


    @RequestMapping(value = "/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("desc：" + desc);
        System.out.println("filename:" + file.getOriginalFilename());
        System.out.println("InputStream" + file.getInputStream());

        return "success";
    }


    @RequestMapping(value = "/i18n")
    public String testI18n(Locale locale) {

        String message = resourceBundleMessageSource.getMessage("i18n.user", null, locale);
        System.out.println(message);

        return "i18n";
    }


    /**
     * 文件上传
     *
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream stream = servletContext.getResourceAsStream("/files/abc.txt");

        body = new byte[stream.available()];
        stream.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=abc.txt");

        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * 文件上传
     *
     * @param body
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body) {
        System.out.println(body);
        return "hello!" + new Date();
    }

    @ResponseBody
    @RequestMapping(value = "/testJson")
    public Collection<Employee> testJson() {
        return employeeDao.getAll();
    }

}
