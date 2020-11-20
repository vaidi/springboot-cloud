package xxh.controller;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: elyuan
 * @Date: 2020/11/19 3:44 下午
 * https://gumutianqi1.gitbooks.io/specification-doc/content/tools-doc/spring-boot-swagger2-guide.html
 */
/* 类注解 */
@Api(value = "swagger of class")
@RestController
public class HelloSwaggerController {
    private static Logger logger = LoggerFactory.getLogger(HelloSwaggerController.class);

    /* 方法注解 */
    @ApiOperation(value = "desc of method", notes = "")
    @GetMapping(value="/hello")
    public Object hello( /* 参数注解 */ @ApiParam(value = "desc of param" , required=true ) @RequestParam String name) {
        return "Hello " + name + "!";
    }
    @ApiOperation(value = "查询车辆接口", notes = "此接口描述xxxxxxxxxxxxx<br/>xxxxxxx<br>值得庆幸的是这儿支持html标签<hr/>", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vno", value = "车牌", required = false,
                    dataType = "string", paramType = "query", defaultValue = "辽A12345"),
            @ApiImplicitParam(name = "page", value = "page", required = false,
                    dataType = "Integer", paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "count", value = "count", required = false,
                    dataType = "Integer", paramType = "query",defaultValue = "10")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap findVehicles(@RequestParam(value = "vno", required = false) String vno,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "count", required = false) Integer count)
            throws Exception {

        logger.info("http://localhost:8501/api/v1/vehicles");
        logger.info("## {},{},{}", vno, page, count);
        logger.info("## 请求时间：{}", new Date());

        ModelMap map = new ModelMap();
        map.addAttribute("vno", vno);
        map.addAttribute("page", page);
        return map;
    }
}
