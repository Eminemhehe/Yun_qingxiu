package com.qiingxiu.demo.Service;

import com.jlrfid.service.RFIDException;
import org.springframework.stereotype.Service;

/**
 * Created by wz on 2019/11/19.
 */
@Service
public interface RfidstartService {

    void OnceBegin() throws RFIDException;
}
