package com.learn.shirologin.service;

import com.learn.shirologin.pojo.User;
import com.learn.shirologin.vo.ResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User findByName(@Param("name") String name);

    void dologin(String name, String password);

    List<Integer> getUserRoleIds(Integer userId);

    boolean delUserRole(Integer userId);

    boolean insertUserRole(Integer userId, List<Integer> roleList);

    ResultVO addUser(User user);

    ResultVO getAllUser();

    boolean delUser(Integer id);

    boolean updateUser(User user);
}

