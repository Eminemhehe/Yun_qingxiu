package com.qiingxiu.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jlrfid.service.RFIDException;
import com.qiingxiu.demo.Bean.Visitors;
import com.qiingxiu.demo.Service.VisitorService;
import com.qiingxiu.demo.Service.RfidstartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wz on 2019/11/18.
 */

@RestController
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @ResponseBody
    @RequestMapping("getAll")
    public JSON getAll(){

        List<Visitors> visitorsList = visitorService.getVisitors();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data:",visitorsList);
        return jsonObject;
    }

    @Autowired
    RfidstartService RfidstartService;
    @RequestMapping("BeginInv")
    //rfid启动标值
    public void BeginInv() throws RFIDException {
        RfidstartService.OnceBegin();
        System.out.println("sssss");
         System.out.println("cccc");
        return ;
    }

}
