package xxh.shiro.curstom;


import org.apache.shiro.authz.Permission;

/**
 * @Author: elyuan
 * @Date: 2020/12/19 4:22 下午
 */
public class MyPermission implements Permission {


    String permissionCode;

    public MyPermission(String name){
        permissionCode = name;
    }


    @Override
    public boolean implies(Permission p) {
        //自定义去比较
        return false;
    }
}
