package com.qiingxiu.demo.Service.Imp;

import com.jlrfid.service.RFIDException;
import com.qiingxiu.demo.Mapper.VisitorMapper;
import com.qiingxiu.demo.Service.RfidstartService;
import com.qiingxiu.demo.Utils.RFIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wz on 2019/11/19.
 */
@Service
public class RfidstartServiceImp implements RfidstartService {

    @Autowired
    VisitorMapper visitorMapper;
    @Autowired
    RFIDUtils rfidUtils;

    @Override
    public void OnceBegin() throws RFIDException {

        rfidUtils.RFID();
        return;
    }
}
