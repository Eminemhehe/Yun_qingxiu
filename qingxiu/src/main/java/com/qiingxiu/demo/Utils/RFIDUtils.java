package com.qiingxiu.demo.Utils;

import com.jlrfid.service.AntStruct;
import com.jlrfid.service.GetReadData;
import com.jlrfid.service.MainHandler;
import com.jlrfid.service.RFIDException;
import com.qiingxiu.demo.Mapper.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by wz on 2019/11/19.
 */

@Service
public class RFIDUtils implements GetReadData {


    @Autowired
    VisitorMapper visitorMapper;

    static  String data ="";
    private static String readData="";//标签数据
    static int num = 0;//对数据库进行刷新标记位
    static String ip = "10.10.100.254";//阅读器设置IP地址
    static int port = 8899;//阅读器设置端口号


    public void RFID() throws RFIDException {
        MainHandler handler = new MainHandler();
        if (handler.dllInit("R2k.dll")) {
            if (handler.deviceInit(ip, 0, port)) {
                //设置开启天线数，1为开启，0为关闭
                byte[] antEnable = new byte[]{1, 0, 0, 0};
                //设置天线的读取时间
                long[] dwellTime = new long[]{1500, 1000, 1000, 1000};
                //设置天线的功率
                long[] power = new long[]{300, 33, 33, 33};
                if (handler.SetAnt(antEnable, dwellTime, power)) {
                    System.out.println("设置天线参数成功！");
                    AntStruct struct = handler.GetAnt();
                    for (int i = 0; i < 4; i++) {
                        System.out.println("天线" + (i + 1) + (struct.antEnable[i] == 1 ? "——已连接" : "——未连接")
                                + "——工作时间:" + struct.dwellTime[i] + "ms——功率:" + struct.power[i].longValue() / 10 + "dBm");
                    }
                    //handler.BeginInv(new RFIDUtils());
                    handler.InvOnce(new RFIDUtils());
                    try {
                        Thread.sleep(2000);
                        visitorMapper.setNum(num);
                        //handler.StopInv();
                    } catch (InterruptedException e) {
                        Logger.getLogger(RFIDUtils.class.getName()).log(Level.SEVERE, null, e);
                    } finally {
                        num = 0;
                    }
                }
            }
        }
    }



   /* private void stop() throws RFIDException {
        MainHandler handler2 = new MainHandler();
        if (handler2.dllInit("R2k.dll")) {
            if (handler2.deviceInit(ip, 0, port)) {
                byte[] antEnable2 = new byte[]{1, 0, 0, 0};
                //设置天线的读取时间
                long[] dwellTime2 = new long[]{1000, 1000, 1000, 1000};
                //设置天线的功率
                long[] power2 = new long[]{3300, 33, 33, 33};
                if (handler2.SetAnt(antEnable2, dwellTime2, power2)) {
                    System.out.println("设置天线参数成功！");
                    AntStruct struct2 = handler2.GetAnt();
                    for (int i = 0; i < 4; i++) {
                        System.out.println("天线" + (i + 1) + (struct2.antEnable[i] == 1 ? "——已连接" : "——未连接")
                                + "——工作时间:" + struct2.dwellTime[i] + "ms——功率:" + struct2.power[i].longValue() / 10 + "dBm");
                    }
                    handler2.deviceUnInit();
                }
                System.out.println("天线断开");
            }
        }
    }*/

    public void getReadData(String data, int antNo) {
        if ("F0".equals(data)) {
            System.out.println("天线1寻卡结束！");
        } else if ("F1".equals(data)) {
            System.out.println("天线2寻卡结束！");
        } else if ("F2".equals(data)) {
            System.out.println("天线3寻卡结束！");
        } else if ("F3".equals(data)) {
            System.out.println("天线4寻卡结束！");
        } else if (!"".equals(data)) {
            System.out.println("数据：" + data + "  天线：" + antNo);
            //数据通过“，”进行分割，因为会有重复数据，利用hashset进行去重，然后储存写入数据库
            readData += data + ",";
            //System.out.println("数据：" + data + "  天线：" + antNo);
            String[] readDataString = readData.split(",");
            HashSet<String> readDataSet = new HashSet<String>();
            for (int i = 0; i < readDataString.length; i++) {
                readDataSet.add(readDataString[i]);
            }
            num = readDataSet.size();
            System.out.println("长度：" + num + " 数据：" + readDataSet.toString());
        }

    }
}
