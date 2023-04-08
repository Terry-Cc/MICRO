package com.micro.www.mapper;

import com.micro.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 用户信息关系映射
 * @since 2022-09-09 16:24
 **/
@Mapper
public interface UserMapper {

    //@Select("select * from t_user_define where user_id = #{userId}")
    User selectById(String userId);

    //List<User> listUser(String userName);
    User listUser(String userName);
}
