package cn.vskendo.demo.dao;


import cn.vskendo.demo.common.pojo.JavaObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JavaObjectMapper {
    int deleteByPrimaryKey(Integer jcoId);

    int insert(JavaObject row);

    int insertSelective(JavaObject row);

    JavaObject selectByPrimaryKey(Integer jcoId);

    int updateByPrimaryKeySelective(JavaObject row);

    int updateByPrimaryKey(JavaObject row);
}