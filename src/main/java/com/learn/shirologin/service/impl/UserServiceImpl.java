package com.learn.shirologin.service.impl;

import com.learn.shirologin.dao.UserDao;
import com.learn.shirologin.exception.CenterException;
import com.learn.shirologin.pojo.User;
import com.learn.shirologin.service.UserService;
import com.learn.shirologin.util.*;
import com.learn.shirologin.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void dologin(String name, String password) {
        /*验证信息*/
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
            Session session = subject.getSession();
            User userName = userDao.findByName(name);
            session.setAttribute(Contants.SESSION_USER, userName);
        } catch (AuthenticationException e) {
            log.error("【用户登录】" + e.getMessage());
            throw new CenterException(ResultEnum.LOGIN_ERROR);
        }
    }

    @Override
    public List<Integer> getUserRoleIds(Integer userId) {
        return userDao.getUserRoleIds(userId);
    }

    @Override
    @Transactional
    public boolean delUserRole(Integer userId) {
        try {
            userDao.delUserRole(userId);
        } catch (Exception e) {
            throw new CenterException(ResultEnum.valueOf("解除用户角色错误"));
        }
        return true;
    }

    @Override
    @Transactional
    public boolean insertUserRole(Integer userId, List<Integer> roleList) {
        delUserRole(userId);
        for (Integer roleId : roleList) {
            try {
                userDao.insertUserRole(userId, roleId);
            } catch (Exception e) {
                throw new CenterException(ResultEnum.valueOf("给用户赋予角色错误"));
            }
        }
        return true;
    }

    @Override
    @Transactional
    public ResultVO addUser(User user) {
        user.setUpdatetime(DateFormatUtil.DATE_TIME_FORMAT.format(new Date()));
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            log.error("【新增用户】userName={},userPassword={},userEmail={}", user.getUsername(), user.getUserpassword(), user.getUseremail());
            throw new CenterException(ResultEnum.ADD_USER_ERROR);
        }
        return ResultVOUtil.success("新增用户成功");
    }

    @Override
    public ResultVO getAllUser() {
        List<User> allUser=null;
        try {
            allUser = userDao.getAllUser();
        } catch (Exception e) {
            log.error("【获取用户列表】失败");
            throw new CenterException(ResultEnum.GET_USER_LIST_ERROR);
        }
        return ResultVOUtil.success(allUser);
    }

    @Override
    @Transactional
    public boolean delUser(Integer id) {
        try {
            userDao.delUser(id);
        } catch (Exception e) {
            log.error("【删除用户】失败");
            throw new CenterException(ResultEnum.DEL_USER_ERROR);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        try {
            if (user.getUserpassword().length() < 1) {
                userDao.updateUserNoPwd(user);
            } else {
                user.setUserpassword(UseMD5.createKey(user.getUserpassword()));
                userDao.updateUser(user);
            }
        } catch (Exception e) {
            throw new CenterException(ResultEnum.UPDATE_USER_ERROR);
        }
        return true;
    }
}
