package com.jxx.Controller;

import cn.hutool.core.util.RandomUtil;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.jxx.Service.DataService;
import com.jxx.Service.IndexService;
import com.jxx.Service.impl.DataServiceImpl;
import com.jxx.common.ResultInfo;
import com.jxx.crawler.mapper.ChnRegisterMapper;
import com.jxx.crawler.model.ChnRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("index/")
public class ComputeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ChnRegisterMapper chnRegisterMapper;

    @Resource(name = "DataServiceImpl")
    private DataService dataService;

    @Autowired
    private IndexService indexService;


    @RequestMapping("/get")
    public ResultInfo get(){
        ResultInfo resultInfo = new ResultInfo();
        ChnRegister chnRegister = chnRegisterMapper.getChnRegisterById(150019);
        resultInfo.setData(chnRegister);
        return resultInfo;
    }
    @RequestMapping("/getId")
    public ResultInfo getId(Integer id){
        ResultInfo resultInfo = new ResultInfo();
        ChnRegister chnRegister = chnRegisterMapper.getChnRegisterById(id);
        resultInfo.setData(chnRegister);
        return resultInfo;
    }

    @RequestMapping("/avi")
    public ResultInfo avi() throws Exception {
        String aa = "name != nil ? ('hello,' + name) : 'who are u ?'";
        Expression compile = AviatorEvaluator.compile(aa);
        String s = (String) compile.execute();
        System.out.println(s);
        s = (String) compile.execute(compile.newEnv("name","da"));
        System.out.println(s);
        return null;
    }

    @RequestMapping("/insert")
    public void  insert() throws Exception {
        dataService.insert();
    }

    @RequestMapping("/thread")
    public void thread(){
        double random = Math.random();
        dataService.thread(random);
    }

    @RequestMapping("/thread2")
    public void thread2(){
       indexService.thread();
    }

    @RequestMapping("/getpdf")
    public ModelAndView getPdf(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("value","pdf");
        mv.setViewName("index.html");
        return mv;
    }

    @RequestMapping("/click")
    public ModelAndView click(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpServletRequest.getCookies();
        HttpSession session = httpServletRequest.getSession();
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("clickjacking.html");
        return mv;
    }

    @RequestMapping("/canvas")
    public ModelAndView canvas(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("canvas.html");
        return mv;
    }
}
