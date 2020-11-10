package xxh.auth.common;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xxh.auth.enntity.Menu;
import xxh.auth.enntity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: elyuan
 * @Date: 2020/11/6 2:47 下午
 */
@Component("rbacPermission")
public class RbacPermission {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserEntity) {
            // 读取用户所拥有的权限菜单
            List<Menu> menus = ((UserEntity) principal).getRoleMenus();
            System.out.println(menus.size());
            for (Menu menu : menus) {
                if (antPathMatcher.match(menu.getMenuUrl(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
