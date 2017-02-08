package com.yangyang.controller;

import com.yangyang.dao.EmployeeDao;
import com.yangyang.exception.UsernameNotMatchException;
import com.yangyang.model.xml.Employee;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;

import static com.yangyang.controller.EmployeeHandler.REDIRECT_EMPS;
import static com.yangyang.util.StaticUtils.SUCCESS;

@Controller
public class SpringMVCTest {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private ResourceBundleMessageSource messageSource;


    @GetMapping("/testSimpleHandlerException")
    public String testSimpleHandlerException(@RequestParam("i")int i){
        String[] vals = new String[10];
        System.out.println(vals[i]);

        return SUCCESS;
    }
    //标识异常类或异常方法
    //@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "测试")
    @RequestMapping("/testStatusResponse")
    public String testStatusResponse(@RequestParam("i")Integer i){
        if(i == 13){
            throw new UsernameNotMatchException();
        }
        System.out.println("result: "+ (10/i) );
        return SUCCESS;
    }

    //@ExceptionHandler({ArithmeticException.class})
    //public String handException(){
    //    System.out.println("ArithmeticException ");
    //    return "error";
    //}
    @RequestMapping("/testException")
    public String testException(@RequestParam("i")Integer i){
        System.out.println("result: "+ (10/i) );
        return SUCCESS;
    }

    @RequestMapping("/testFileUpoad")
    public String fileUpload(@RequestParam("desc") String desc,
                             @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("desc: "+desc);
        System.out.println("originFileName: "+file.getOriginalFilename());

        String context= org.springframework.util.StreamUtils.copyToString(file.getInputStream(), Charset.defaultCharset());
        System.out.println("context:"+context);

        return SUCCESS;
    }

    @RequestMapping("/testi18n")
    public String testi18n(Locale local){
        String value = messageSource.getMessage("i18n.username",null,local);
        System.out.println("value: "+value);

        return "i18n";
    }
    //this method has some errors!!!!
    @RequestMapping("/testEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {

        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/abc.txt");
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-disposition","attachment;filename=abc.txt");

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body, httpHeaders, httpStatus);
        return responseEntity;
    }
    @ResponseBody
    @PostMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){

        System.out.println("body: "+body);

        return "Hello world! "+ LocalDateTime.now();
    }

    @ResponseBody
    @PostMapping("/testJson")
    public Collection<Employee> testJson(){
        return employeeDao.getEmployees();
    }
    @PostMapping("testConverter")
    public String testConverter(@RequestParam("employee")Employee employee){

        employeeDao.save(employee);
        System.out.println("add employee: "+employee);

        return REDIRECT_EMPS;
    }
}
