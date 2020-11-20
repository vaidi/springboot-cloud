package xxh.security.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xxh.security.bean.User;
import xxh.security.service.UserService;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:11 下午
 */
@Service
public class SysUserServiceImpl implements UserService {
    @Override
    public User findByUsername(String username) {
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        String password = new BCryptPasswordEncoder().encode("123456");
        user.setPassword(password);
        return user;
    }

    @Override
    public Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<>();
        if("许新华".equals(username)){
            permissions.add("sys:user:view");
            permissions.add("sys:user:add");
            permissions.add("sys:user:edit");
            permissions.add("sys:user:delete");
        }else{
            permissions.add("sys:user:view");
        }
        return permissions;
    }
}
