package xxh.security.service;

import xxh.security.bean.User;

import java.util.Set;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:10 下午
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 查找用户的菜单权限标识集合
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}
