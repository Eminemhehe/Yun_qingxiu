package com.qiingxiu.demo.Service;

import com.qiingxiu.demo.Bean.Visitors;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wz on 2019/11/18.
 */
@Service
public interface VisitorService {

    List<Visitors> getVisitors();

}
