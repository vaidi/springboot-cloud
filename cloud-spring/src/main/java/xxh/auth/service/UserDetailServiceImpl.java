package xxh.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xxh.auth.enntity.Menu;
import xxh.auth.enntity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: elyuan
 * @Date: 2020/11/6 2:43 下午
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private RoleDao roleDao;
//    @Autowired
//    private MenuDao menuDao;

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername:" + username);
        Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
        if(!StringUtils.isEmpty(username)){
            authorities.add(new SimpleGrantedAuthority(username));
        }
        List<Menu> menus= new ArrayList<>();
        return new UserEntity(username,"user.getPassword()",authorities,menus);
//        // 根据用户名查找用户
//        UserEntity user = userDao.getUserByUsername(username);
//        System.out.println(user);
//        if (user != null) {
//            System.out.println("UserDetailsService");
//            //根据用户id获取用户角色
//            List<Role> roles = roleDao.getUserRoleByUserId(user.getId());
//            // 填充权限
//            Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
//            for (Role role : roles) {
//                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//            }
//            //填充权限菜单
//            List<Menu> menus=menuDao.getRoleMenuByRoles(roles);
//            return new UserEntity(username,user.getPassword(),authorities,menus);
//        } else {
//            System.out.println(username +" not found");
//            throw new UsernameNotFoundException(username +" not found");
//        }
    }

}
