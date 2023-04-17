package com.micro.services.provider.mapper;

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

    User selectById(String userId);

    @Select("select * from t_user where user_name like CONCAT('%', #{userName}, '%')")
    List<User> listUser(String userName);
}
