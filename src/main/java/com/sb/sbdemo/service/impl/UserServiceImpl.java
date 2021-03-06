package com.sb.sbdemo.service.impl;

import com.sb.sbdemo.constant.KeyConst;
import com.sb.sbdemo.dao.UserMapper;
import com.sb.sbdemo.exception.BusiException;
import com.sb.sbdemo.model.User;
import com.sb.sbdemo.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ye on 3/8/18.
 */
@Service(KeyConst.BeanName.USER_SERVICE)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(String key, User loginUser) throws BusiException {
        if (StringUtils.isBlank(loginUser.getUserName())) {
            throw new BusiException("loginUser.userName is empty!");
        }
        User user = this.userMapper.selectByUserName(loginUser.getUserName());
        if (user != null && StringUtils.equals(user.getPassword(), DigestUtils.md5Hex(loginUser.getPassword()))) {
            return user;
        } else {
            throw new BusiException("user invalid");
        }
    }
}
