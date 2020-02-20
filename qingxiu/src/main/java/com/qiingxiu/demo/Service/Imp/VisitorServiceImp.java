package com.qiingxiu.demo.Service.Imp;
import com.qiingxiu.demo.Bean.Visitors;
import com.qiingxiu.demo.Mapper.VisitorMapper;
import com.qiingxiu.demo.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wz on 2019/11/18.
 */
@Service
public class VisitorServiceImp implements VisitorService {


    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public List<Visitors> getVisitors() {

        List<Visitors> visitors = visitorMapper.selectAll();

        return visitors;
    }
}
