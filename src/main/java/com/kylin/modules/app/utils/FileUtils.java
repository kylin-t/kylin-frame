package com.kylin.modules.app.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Description: 文件传输工具集合
 * @author: kylin
 * @create: 2018-01-23 11:23
 **/
public class FileUtils {
    public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            String filename = file.getName();
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();

            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("下载异常");
        }
        return response;
    }
}
