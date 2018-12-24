package com.learn.shirologin.dao;

import com.learn.shirologin.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户管理表
 *
 * @author IN 2018-9--10 U
 */
@Repository
@Mapper
public interface UserDao {

    /**
     * @param name 用户名
     * @return 用户对象
     */
    @Select("select * from manager_users where username=#{name}")
    User findByName(@Param("name") String name);


    /**
     * 根据用户的ID查找用户的角色
     *
     * @param userId
     * @return 单个角色还是多个角色
     */
    @Select("select role_id from manager_users_role where user_id=#{userId}")
    List<Integer> getUserRoleIds(Integer userId);

    @Delete("delete from manager_users_role where user_id=#{userId}")
    int delUserRole(Integer userId);

    @Insert("insert into manager_users_role(user_id,role_id) values(#{userId},#{roleId})")
    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 新增用户
     *
     * @param user
     */
    @Insert("insert into " +
            "manager_users(username,userpassword,useremail,status,updatetime) " +
            "values(#{username},#{userpassword},#{useremail},#{status},#{updatetime})")
    boolean addUser(User user);

    /**
     * 获取全部用户
     *
     * @return 返回用户列表
     */
    @Select("select * from manager_users")
    List<User> getAllUser();

    @Delete("delete from manager_users where id=#{id}")
    int delUser(Integer id);

    @Update("update manager_users " +
            "set username=#{username},useremail=#{useremail},status=#{status},updatetime=#{updatetime} " +
            "where id=#{id}")
    int updateUserNoPwd(User user);

    @Update("update manager_users " +
            "set username=#{username},userpassword=#{userpassword},useremail=#{useremail},status=#{status},updatetime=#{updatetime} " +
            "where id=#{id}")
    int updateUser(User user);

}
