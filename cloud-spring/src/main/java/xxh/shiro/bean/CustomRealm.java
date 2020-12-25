package xxh.shiro.bean;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: elyuan
 * @Date: 2020/11/25 3:37 下午
 */
public class CustomRealm extends AuthorizingRealm {

    //和权限相关的
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------身份权限相关--------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Subject subObj = SecurityUtils.getSubject();
      //  subObj.isPermitted("user:show");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("user:show");
        stringSet.add("user:admin");
        info.setStringPermissions(stringSet);
        //角色
        Set<String> roles =new HashSet<>();
        info.setRoles(roles);
        return info;
    }

    //用户认证相关的
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据用户名从数据库获取密码
        String password = "123";
        if (userName == null) {
            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(password)) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(userName, password, getName());
    }

}
