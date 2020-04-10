package sumeng.student.uap.common.utils;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sumeng.student.uap.common.config.FileConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    /**
     * 多文件上传工具类
     *
     * @author ZCL
     * @date 2019/12/23 10:02
     * @param request       用户的上传请求
     * @param nameToForm    前端表单中file字段的名字
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> multiUpload(HttpServletRequest request, String nameToForm){
        List<String> URLs = new ArrayList<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles(nameToForm);
        String filePath = FileConfig.FileTurePath;
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                System.out.println("上传第" + (i++) + "个文件失败");
                return null;
            }
            String fileName = file.getOriginalFilename();
            String suffix = fileName
                    .substring(fileName.lastIndexOf(".") + 1);
            // 图片新名字
            String newfileName = creatUUID32()+"."+suffix;
            File dest = new File(filePath + newfileName);
            try {
                file.transferTo(dest);
                System.out.println("第" + (i + 1) + "个文件上传成功");
                String url = FileConfig.FileURL + newfileName;
                URLs.add(url);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传第" + (i++) + "个文件失败");
                return null;
            }
        }
        return URLs;
    }

    public static String creatUUID32(){
        String uuid = UUID.randomUUID().toString();
        //转化为String对象
        System.out.println(uuid);
        //打印UUID
        uuid = uuid.replace("-", "");
        //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
        return uuid;
    }
}
