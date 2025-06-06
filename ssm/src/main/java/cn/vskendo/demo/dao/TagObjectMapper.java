package cn.vskendo.demo.dao;


import cn.vskendo.demo.common.pojo.TagObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagObjectMapper {
    int deleteByPrimaryKey(Integer toId);

    int insert(TagObject row);

    int insertSelective(TagObject row);

    TagObject selectByPrimaryKey(Integer toId);

    int updateByPrimaryKeySelective(TagObject row);

    int updateByPrimaryKey(TagObject row);
}