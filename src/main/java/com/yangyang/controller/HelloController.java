package com.yangyang.controller;

import com.yangyang.model.xml.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import static com.yangyang.util.StaticUtils.REDIRECT;
import static com.yangyang.util.StaticUtils.SUCCESS;

//SessionAttributes only in class type
@SessionAttributes(value = {"user"},types = {String.class})
@Controller
public class HelloController {

    @GetMapping("testRedirect")
    public String testRedirect(){
        System.out.println("test redirect ");
        return REDIRECT+"/index.jsp";
    }

    @GetMapping("testHelloView")
    public String testView(){
        System.out.println("hello view ... ");
        return "helloView";
    }

    @GetMapping("testViewAndInternal")
    public String testInternalView(){

        System.out.println("hello world");

        return SUCCESS;
    }
    /**
     *
     * 执行流程: 1:运行ModelAttribute标示的方法,从数据库中获取对象user,并放入model中,key为user
     *  2: springmvc 从map中取出user对象并把表单的请求参数赋给该对象的对应属性
     *  3: springmvc 把上述对象传入目标参数
     */
    //有ModelAttribute标记的方法在每个方法调用之前都会调用
    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map){
        System.out.println("ModelAttribute");
        if(id != null){
            //select from database
            User user = new User(1,"tom","123123",12,"tom@qq.com");
            System.out.println("select from database: "+user);
            //这里的必须和参数名一样或者放入类型
            map.put("user",user);
        }
    }
    @PostMapping("testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user){

        System.out.println("update: "+user);

        return SUCCESS;
    }
    @GetMapping("testSession")
    public String testSessionAttribute(Map<String,Object> map){
        User user = new User(1,"yangyang","123123",123,"145@qq.com");

        map.put("user",user);
        map.put("school","anhui school");

        return SUCCESS;
    }

    @GetMapping("testMap")
    public String testMap(Map<String,Object> map){
        map.put("names", Arrays.asList("tom","jerry","yangyang"));
        return SUCCESS;
    }

    /**
     * springmvc will put model attribute to request scope
     */
    @GetMapping("testModelAndView")
    public ModelAndView testModelAndView(){
        String viewName = SUCCESS;
        ModelAndView mv = new ModelAndView(viewName);

        //add model data to modelAndView
        mv.addObject("time", LocalDateTime.now());

        return mv;
    }


    /**
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Local
     * InputStream
     * OutputStream
     * Reader
     * Writer
     */
    @GetMapping("testServletApi")
    public void testServletApi(HttpServletRequest request,
                               HttpServletResponse response,
                               Writer out
    ) throws IOException {
        System.out.println(request +" : "+response);
        out.write("hello Springmvc");
        //return SUCCESS;
    }

    @PostMapping("springmvc/testpojo")
    public String testPOJO(User user){
        System.out.println(user);
        return SUCCESS;
    }


    @GetMapping("/cookie")
    public String testCookieValue(@CookieValue("JSESSIONID") String cookieID){
        System.out.println("test cookieValue ... "+cookieID);
        return SUCCESS;
    }
    @GetMapping(value = "requestParam")
    public String testRequestParam(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "age",required = false,defaultValue = "0") int age
    ){
        System.out.println(username+" : "+age);
        return SUCCESS;
    }
    /**
     * add     order   POST
     * modify  order/1 PUT
     * delete  order/1 DELETE
     * get     order/1 GET
     */
    @GetMapping("rest/get")
    public String testRest1() {
        System.out.println("get method");
        return SUCCESS;
    }
    @PostMapping("rest/post")
    public String testRest2() {
        System.out.println("post method");
        return SUCCESS;
    }
    @DeleteMapping("rest/delete")
    public String testRest3() {
        System.out.println("delete method");
        return SUCCESS;
    }
    @PutMapping("rest/put")
    public String testRest4() {
        System.out.println("put method");
        return SUCCESS;
    }

    @GetMapping("/path/{id}")
    public String testPathVariable(@PathVariable("id") Integer id){

        System.out.println("testPathVariable ..."+id);

        return SUCCESS;
    }

    @GetMapping("/path/*/abc")
    public String testAntPath(){
        //ant path -- */**/
        System.out.println("testAntPath ...");
        return SUCCESS;
    }
    @GetMapping(value = "params",params = {"username","age!=10"})
    public String testParams(){
        System.out.println("username and age!=10");
        return SUCCESS;
    }
    @GetMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return SUCCESS;
    }
}
