package com.coco.cloud.spring.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuqiang@ourdocker.cn
 * View
 * 视图
 */
public class CoCoView {

    private File viewFile;

    CoCoView(File viewFile) {
        this.viewFile = viewFile;
    }

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.viewFile,"r");
        String line;
        while (null != (line = randomAccessFile.readLine())){
            line = new String(line.getBytes("ISO-8859-1"),"utf-8");
            Pattern pattern = Pattern.compile("￥\\{[^}]+}",Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                String paramName = matcher.group();
                paramName = paramName.replaceAll("￥\\{|}","");
                Object paramValue = model.get(paramName);
                if(null == paramValue){ continue;}
                line = matcher.replaceFirst(makeStringForRegExp(paramValue.toString()));
                matcher = pattern.matcher(line);
            }
            stringBuilder.append(line);
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(stringBuilder.toString());
    }

    /**
     * 处理特殊字符
     */
    private static String makeStringForRegExp(String str) {
        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }

}
