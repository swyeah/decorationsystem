package dao;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:07 2018/2/21
 */
public class FileTest {

    @Test
    public void Test() throws IOException {
        File file = new File("F:\\bb\\aa");
        boolean result =file.mkdirs();
        System.out.println(result);
    }

    @Test
    public void Test1() throws IOException {
        //创建文件夹
        File filepath = new File("F:\\ProjectUpload\\housepic\\"+10000);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.exists()) {
            filepath.mkdirs();

        }
        FileUtils.deleteDirectory(filepath);
    }


}
