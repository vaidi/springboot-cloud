package xxh.excel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @Author: elyuan
 * @Date: 2020/7/15 4:38 下午
 */
public class EasyExcels {

    /**
     *
     * @param file 导入的Excel文件
     * @param tClass  对应excel实体bean
     * @return 对应excel实体bean的list
     */
    public static<T> List<Map<String ,String>> getExcelContent(MultipartFile file, Class<T> tClass){
        List<Map<String ,String>> excelPropertyIndexModelList = new ArrayList<>();
        try {
            //监听器
            AnalysisEventListener<Map<String ,String>> listener = new AnalysisEventListener<Map<String ,String>>() {
                //读取每一行的数据
                @Override
                public void invoke(Map<String ,String> excelPropertyIndexModel, AnalysisContext analysisContext) {
                    excelPropertyIndexModelList.add(excelPropertyIndexModel);
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    //读取之后的操作
                }
            };
            /* file.getInputStream() 输入流
             * listener 自定义的监听器
             */
            ExcelReader excelReader = EasyExcel.read(file.getInputStream(),  listener).build();
            //readSheet(0) 指定读取哪一页的数据
            ReadSheet sheet = EasyExcel.readSheet(0).build();
            excelReader.read(sheet);
            //读取所有的sheet页的数据
            //excelReader.readAll();
            //关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelPropertyIndexModelList;
    }

}
