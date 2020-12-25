package xxh.shiro.curstom;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import java.util.Arrays;
import java.util.Collection;

/**
 * @Author: elyuan
 * @Date: 2020/12/19 4:24 下午
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        return Arrays.asList((Permission)new MyPermission("menu:*"));
    }
}
