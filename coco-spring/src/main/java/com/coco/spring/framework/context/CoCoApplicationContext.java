package com.coco.spring.framework.context;

import com.coco.spring.framework.annotation.CoCoAutowired;
import com.coco.spring.framework.annotation.CoCoController;
import com.coco.spring.framework.annotation.CoCoService;
import com.coco.spring.framework.aop.CoCoAopProxy;
import com.coco.spring.framework.aop.CoCoCglibAopProxy;
import com.coco.spring.framework.aop.CoCoJdkDynamicAopProxy;
import com.coco.spring.framework.aop.config.CoCoAopConfig;
import com.coco.spring.framework.aop.support.CoCoAdvisedSupport;
import com.coco.spring.framework.beans.CoCoBeanWrapper;
import com.coco.spring.framework.beans.config.CoCoBeanDefinition;
import com.coco.spring.framework.beans.config.CoCoBeanPostProcessor;
import com.coco.spring.framework.beans.support.CoCoBeanDefinitionReader;
import com.coco.spring.framework.context.support.CoCoDefaultListableBeanFactory;
import com.coco.spring.framework.core.CoCoBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuqiang@ourdocker.cn
 * ApplicationContext
 * IOC容器的核心实现类
 * date : 2019-11-30
 */
public class CoCoApplicationContext extends CoCoDefaultListableBeanFactory implements CoCoBeanFactory {

    private Logger logger = LoggerFactory.getLogger(CoCoApplicationContext.class);

    /**
     * 配置文件路径
     */
    private String [] configLocations;

    /**
     * BeanDefinitionReader
     */
    private CoCoBeanDefinitionReader reader;

    /**
     *单例的 IOC 容器缓存
     */
    private Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    /**
     * 通用的 IOC 容器
     */
    private Map<String,CoCoBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, CoCoBeanWrapper>();

    public CoCoApplicationContext(String... configLocations){
        this.configLocations = configLocations;
        try {
            refresh();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public void refresh() throws Exception {
        //1、定位，定位配置文件 
        reader = new CoCoBeanDefinitionReader(this.configLocations); 
        //2、加载配置文件，扫描相关的类，把它们封装成 BeanDefinition 
        List<CoCoBeanDefinition> beanDefinitions = reader.loadBeanDefinitions(); 
        //3、注册，把配置信息放到容器里面(伪 IOC 容器) 
        doRegisterBeanDefinition(beanDefinitions); 
        //4、把不是延时加载的类，提前初始化
        doAutoWrite();
    }

    private void doRegisterBeanDefinition(List<CoCoBeanDefinition> beanDefinitions) throws Exception{
        for (CoCoBeanDefinition beanDefinition: beanDefinitions) {
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }

    private void doAutoWrite() {
        for (Map.Entry<String, CoCoBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().getLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }

    /**
     * 依赖注入 通过读取BeanDefinition中的信息 通过反射机制创建一个实例并返回
     * Spring不把最原始的对象放出去 用一个BeanWrapper进行包装
     * 装饰器模式
     * 1、保留原来的 OOP 关系
     * 2、需要对它进行扩展，增强
     */
    @Override
    public Object getBean(String beanName) {
        CoCoBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        try{
            CoCoBeanPostProcessor beanPostProcessor = new CoCoBeanPostProcessor();
            Object instance = instantiateBean(beanDefinition);
            if(null == instance){
                return null;
            }
            // postProcessBeforeInitialization
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
            CoCoBeanWrapper beanWrapper = new CoCoBeanWrapper(instance);
            this.factoryBeanInstanceCache.put(beanName,beanWrapper);
            // postProcessAfterInitialization
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            populateBean(beanName,instance);
            return this.factoryBeanInstanceCache.get(beanName).getWrapperInstance();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 依赖注入
     * 1.spring中构造函数方式的注入 会造成循环依赖的异常
     * 2.用set方式的注入则不会出现异常
     * 因为spring需要调用构造函数实例化bean
     * 利用set方式注入bean 在初始化的时候之前 会生成一个ObjectFactory对象作为bean的临时对象
     * 在初始化所依赖的bean时 用这个ObjectFactory作为引用 则不会出现循环依赖的异常
     * 这里先不考虑构造器方式的问题  后续完善
     */
    private void populateBean(String beanName, Object instance) {
        Class clazz = instance.getClass();
        if(!(clazz.isAnnotationPresent(CoCoController.class) || clazz.isAnnotationPresent(CoCoService.class))){
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(CoCoAutowired.class)){
                continue;
            }
            CoCoAutowired autowired = field.getAnnotation(CoCoAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if("".equals(autowiredBeanName)){
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                if(this.factoryBeanInstanceCache.get(autowiredBeanName) == null){
                    continue;
                }
                field.set(instance,this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    private Object instantiateBean(CoCoBeanDefinition beanDefinition) {
        //1、拿到要实例化的对象的类名
        String className = beanDefinition.getBeanClassName();
        //2、反射实例化，得到一个对象
        Object instance = null;
        try {
            //假设默认就是单例,细节暂且不考虑
            if(this.factoryBeanObjectCache.containsKey(className)){
                instance = this.factoryBeanObjectCache.get(className);
            }else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                // 构建代理信息
                CoCoAdvisedSupport config = instanceAopConfig(beanDefinition);
                config.setTargetClass(clazz);
                config.setTarget(instance);
                //符合PointCut的规则的话，闯将代理对象
                if(config.pointCutMatch()) {
                    instance = createProxy(config).getProxy();
                }
                this.factoryBeanObjectCache.put(className,instance);
                this.factoryBeanObjectCache.put(beanDefinition.getFactoryBeanName(),instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }

    private CoCoAdvisedSupport instanceAopConfig(CoCoBeanDefinition beanDefinition) {
        CoCoAopConfig config = new CoCoAopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new CoCoAdvisedSupport(config);
    }

    private CoCoAopProxy createProxy(CoCoAdvisedSupport config) {
        Class targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0){
            return new CoCoJdkDynamicAopProxy(config);
        }
        return new CoCoCglibAopProxy(config);
    }

    @Override
    public Object getBean(Class<?> beanClazz) {
        return getBean(beanClazz.getName());
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[0]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }

}