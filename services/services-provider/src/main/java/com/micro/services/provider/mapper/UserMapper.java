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

    /**
     * @since 2023/4/18 17:06
     * @description <p>
     *  根据用户ID查询用户信息
     * </p>
     * @param userId 用户ID
     * @return 用户信息
     */
    User selectById(String userId);

    /**
     * @since 2023/4/18 17:07
     * @description <p>
     *  根据用户名模糊查询用户信息
     * </p>
     * @param userName 用户名
     * @return 用户信息列表
     */
    @Select("select * from t_user where user_name like CONCAT('%', #{userName}, '%')")
    List<User> listUser(String userName);
}
