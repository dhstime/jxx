package com.jxx.Controller;

import com.jxx.Service.DataService;
import com.jxx.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private DataService dataService;

    @RequestMapping("/msg")
    public String msg() {
        return indexService.getMsg();
    }
    @RequestMapping("/data")
    public String data() {
        return dataService.getData();
    }
    @RequestMapping("/order")
    public String order(){
        return  indexService.getorder();
    }
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        String data = dataService.getData();
        mv.addObject("data",data);
        return mv;
    }
}
