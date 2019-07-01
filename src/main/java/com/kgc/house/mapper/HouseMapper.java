package com.kgc.house.mapper;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.entity.HouseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);
    List<House> selectPageHouseByUsersId(Integer uid);
    List<House> selectHouseToUsers(HouseCondition houseCondition);
    List<House> selectAllHouseByIspass(Integer ispass);
    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);
    House selectOneHouse(String id);
    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    int optMoreHouseIspass(@Param("array")String[] ids,@Param("ispass")Integer ispass);
}