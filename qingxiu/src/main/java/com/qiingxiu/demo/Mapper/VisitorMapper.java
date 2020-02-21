package com.qiingxiu.demo.Mapper;

import com.qiingxiu.demo.Bean.Visitors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by wz on 2019/11/18.
 */
@Mapper
public interface VisitorMapper {
    //数据库名称
    String TABLE_NAME = "visitor_copy";

    @Update({"update ", TABLE_NAME, " set num = #{num}"})
    void setNum(@Param("num") int num);

    List<Visitors> selectAll();
}
