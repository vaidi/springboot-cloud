package xxh.easyexcel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: elyuan
 * @Date: 2020/11/12 11:27 上午
 */
@Data
public class MobileNo {

    @ExcelProperty(index = 0)
    private String mobile;
    @ExcelProperty(index = 1)
    private String no;
}
