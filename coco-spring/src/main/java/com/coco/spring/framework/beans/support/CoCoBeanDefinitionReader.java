package com.coco.spring.framework.beans.support;

import com.coco.spring.framework.beans.config.CoCoBeanDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author liuqiang@ourdocker.cn
 * BeanDefinitionReader
 * 扫描加载解析bean
 * date : 2019-11-30
 */
public class CoCoBeanDefinitionReader {

    private Logger logger = LoggerFactory.getLogger(CoCoBeanDefinitionReader.class);

    private List<String> registerBeanClasses = new ArrayList<String>();

    private Properties config = new Properties();

    /**
     * 固定配置文件中的 key，相对于 xml 的规范
     */
    private static final String SCAN_PACKAGE = "scanPackage";

    public CoCoBeanDefinitionReader(String... locations) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));
        try{
            config.load(is);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(),e);
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage(),e);
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        assert url != null;
        File classPath = new File(url.getFile());
        assert classPath.listFiles() != null;
        File[] listFiles = classPath.listFiles();
        assert listFiles != null;
        for (File file : listFiles) {
            if (file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else{
                if(!file.getName().endsWith(".class")){
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class",""));
                registerBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig(){
        return this.config; 
    }

    /**
     * 把配置文件中扫描到的所有的配置信息转换为 GPBeanDefinition 对象
     * 用于之后 IOC 操作方
     */
    public List<CoCoBeanDefinition> loadBeanDefinitions(){ 
        List<CoCoBeanDefinition> result = new ArrayList<CoCoBeanDefinition>(); 
        try {
            for (String className : registerBeanClasses) { 
                Class<?> beanClass = Class.forName(className); 
                if(beanClass.isInterface()) { 
                    continue; 
                } 
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),beanClass.getName( ))); 
                Class<?> [] interfaces = beanClass.getInterfaces(); 
                for (Class<?> i : interfaces) { 
                    result.add(doCreateBeanDefinition(i.getName(),beanClass.getName())); 
                } 
            } 
        }catch (Exception e){
            logger.error(e.getLocalizedMessage(),e);
        }
        return result; 
    }

    /**
     * 把每一个bean的配信息解析成一个 BeanDefinition
     */
    private CoCoBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        CoCoBeanDefinition beanDefinition = new CoCoBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    /**
     * 因为是demo版本 所以默认是首字母小写的beanName方式
     * 之后会完善和补全
     */
    private String toLowerFirstCase(String simpleName) { 
        char [] chars = simpleName.toCharArray(); 
        chars[0] += 32; 
        return String.valueOf(chars);
    }

}