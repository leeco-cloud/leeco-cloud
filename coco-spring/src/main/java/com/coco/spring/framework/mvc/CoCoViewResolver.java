package com.coco.spring.framework.mvc;

import java.io.File;
import java.net.URL;
import java.util.Locale;

/**
 * @author liuqiang@ourdocker.cn
 * ViewResolver
 * 解析器
 * 将一个静态文件变为一个动态文件
 * 根据用户传送参数不同，产生不同的结果
 * 最终输出字符串，交给 Response 输出
 */
public class CoCoViewResolver {

    private final static String DEFAULT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;

    public CoCoViewResolver(String templateRoot) {
        URL url = this.getClass().getClassLoader().getResource(templateRoot);
        assert url != null;
        String templateRootPath = url.getFile();
        templateRootDir = new File(templateRootPath);
    }

    public CoCoView resolveViewName(String viewName, Locale locale) throws Exception{
        if(null == viewName || "".equals(viewName.trim())){return null;}
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFIX);
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+","/"));
        return new CoCoView(templateFile);
    }

}
