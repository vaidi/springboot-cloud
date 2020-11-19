package xxh.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.junit.jupiter.api.Test;
import xxh.easyexcel.bean.MobileNo;

import java.io.File;

/**
 * @Author: elyuan
 * @Date: 2020/11/12 11:26 上午
 */
public class EasyExcelMain {


    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "/Users/erlong/Desktop/xh.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, MobileNo.class, new DemoDataListener()).sheet().doRead();

    }

}
