package com.coco.spring.framework.mvc.servlet;

import com.coco.spring.framework.annotation.CoCoController;
import com.coco.spring.framework.annotation.CoCoRequestMapping;
import com.coco.spring.framework.context.CoCoApplicationContext;
import com.coco.spring.framework.mvc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuqiang@ourdocker.cn
 * 初始化spring IOC容器和MVC九大组件
 * date : 2019-12-02
 */
public class CoCoDispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -5511765378806587548L;

    private static Logger logger = LoggerFactory.getLogger(CoCoDispatcherServlet.class);

    private final static String LOCATION = "contextConfigLocation";

    /**
     * HandlerMapping
     */
    private List<CoCoHandlerMapping> handlerMappings = new ArrayList<CoCoHandlerMapping>();

    /**
     * HandlerMapping -> HandlerAdapter
     */
    private Map<CoCoHandlerMapping, CoCoHandlerAdapter> handlerAdapters = new HashMap<CoCoHandlerMapping, CoCoHandlerAdapter>();

    /**
     * ViewResolver
     */
    private List<CoCoViewResolver> viewResolvers = new ArrayList<CoCoViewResolver>();

    /**
     * ApplicationContext
     */
    private CoCoApplicationContext context;

    @Override
    public void init(ServletConfig config) {
        /*
         * 初始化IOC容器
         */
        context = new CoCoApplicationContext(config.getInitParameter(LOCATION));
        initStrategies(context);
    }

    /**
     * 初始化MVC九大组件
     * 有九种策略
     * 针对于每个用户请求，都会经过一些处理的策略之后，最终才能有结果输出
     * 每种策略可以自定义干预，但是最终的结果都是一致
     * @param context ApplicationContext
     */
    private void initStrategies(CoCoApplicationContext context) {
        /*
         * 文件上传解析，如果请求类型是 multipart 将通过 MultipartResolver 进行文件上传解析
         */
        initMultipartResolver(context);
        /*
         * 本地化解析
         */
        initLocaleResolver(context);
        /*
         * 主题解析
         */
        initThemeResolver(context);
        /*
         * HandlerMapping 用来保存 Controller 中配置的 RequestMapping 和 Method 的一个对应关系
         */
        initHandlerMappings(context);
        /*
         * 通过 HandlerMapping，将请求映射到处理器 HandlerAdapters 用来动态匹配 Method 参数，包括类转换，动态赋值
         * 通过 HandlerAdapter 进行多类型的参数动态匹配
         */
        initHandlerAdapters(context);
        /*
         * 如果执行过程中遇到异常，将交给 HandlerExceptionResolver 来解析
         */
        initHandlerExceptionResolvers(context);
        /*
         * 直接解析请求到视图名
         */
        initRequestToViewNameTranslator(context);
        /*
         * 通过 ViewResolvers 实现动态模板的解析
         * 自己解析一套模板语言
         * 通过 viewResolver 解析逻辑视图到具体视图实现
         */
        initViewResolvers(context);
        /*
         * flash 映射管理器
         */
        initFlashMapManager(context);
    }

    private void initMultipartResolver(CoCoApplicationContext context) {
        logger.info("initMultipartResolver... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initLocaleResolver(CoCoApplicationContext context) {
        logger.info("initLocaleResolver... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initThemeResolver(CoCoApplicationContext context) {
        logger.info("initThemeResolver... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initHandlerMappings(CoCoApplicationContext context) {
        //从容器中取到所有的实例
        String [] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {
                //到了 MVC 层，对外提供的方法只有一个 getBean 方法
                Object controller = context.getBean(beanName);
                assert controller != null;
                Class<?> clazz = controller.getClass();
                if (!clazz.isAnnotationPresent(CoCoController.class)) {
                    continue;
                }
                String baseUrl = "";
                if (clazz.isAnnotationPresent(CoCoRequestMapping.class)) {
                    CoCoRequestMapping requestMapping = clazz.getAnnotation(CoCoRequestMapping.class);
                    baseUrl = requestMapping.value();
                }
                //扫描所有的 public 方法
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(CoCoRequestMapping.class)) {
                        continue;
                    }
                    CoCoRequestMapping requestMapping = method.getAnnotation(CoCoRequestMapping.class);
                    String regex = ("/" + baseUrl + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new CoCoHandlerMapping(controller, method,pattern));
                    logger.info("Mapping: " + regex + " , " + method);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        logger.info("initHandlerMappings... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initHandlerAdapters(CoCoApplicationContext context) {
        /*
         * 把一个request请求变成一个handler，参数都是字符串的，自动配到handler中的形参
         * 可想而知，要拿到HandlerMapping才能继续执行
         * 这就意味着，有几个HandlerMapping就有几个HandlerAdapter
         */
        for (CoCoHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping,new CoCoHandlerAdapter());
        }
        logger.info("initHandlerAdapters... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initHandlerExceptionResolvers(CoCoApplicationContext context) {
        logger.info("initHandlerExceptionResolvers... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initRequestToViewNameTranslator(CoCoApplicationContext context) {
        logger.info("initRequestToViewNameTranslator... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initViewResolvers(CoCoApplicationContext context) {
        //拿到模板的存放目录
        String templateRoot = context.getConfig().getProperty("templateRoot");
        URL url = this.getClass().getClassLoader().getResource(templateRoot);
        assert url != null;
        String templateRootPath = url.getFile();
        File templateRootDir = new File(templateRootPath);
        String[] templates = templateRootDir.list();
        assert templates != null;
        for (int i = 0; i < templates.length; i ++) {
            //这里主要是为了兼容多模板，所有模仿Spring用List保存
            this.viewResolvers.add(new CoCoViewResolver(templateRoot));
        }
        logger.info("initViewResolvers... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    private void initFlashMapManager(CoCoApplicationContext context) {
        logger.info("initFlashMapManager... current bean count : {d}" + context.getBeanDefinitionCount());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            this.doDispatch(request,response);
        }catch(Exception e){
            response.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("[\\[\\]]", "").replaceAll(",\\s", "\r\n"));
            logger.error(e.getMessage(),e);
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1. 通过从request中拿到URL，去匹配一个HandlerMapping
        CoCoHandlerMapping handler = getHandler(request);
        if(handler == null){
            processDispatchResult(request,response,new CoCoModelAndView("404"));
            return;
        }
        //2. 准备调用前的参数
        CoCoHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        //3. 真正的调用方法,返回ModelAndView存储了要穿页面上值，和页面模板的名称
        assert handlerAdapter != null;
        CoCoModelAndView modelAndView = handlerAdapter.handle(request,response,handler);
        //4. 这一步才是真正的输出
        processDispatchResult(request, response, modelAndView);
    }

    private CoCoHandlerMapping getHandler(HttpServletRequest request) {
        if(this.handlerMappings.isEmpty()){
            return null;
        }
        String contextPath = request.getContextPath();
        final String requestUrl = request.getRequestURI().replace(contextPath, "").replaceAll("/+", "/");
        for(CoCoHandlerMapping handlerMapping : this.handlerMappings){
            Pattern pattern = handlerMapping.getPattern();
            if (pattern != null){
                Matcher matcher = pattern.matcher(requestUrl);
                if (!matcher.matches()){
                    continue;
                }
                return handlerMapping;
            }
        }
        return null;
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, CoCoModelAndView modelAndView) throws Exception {
        //把传来的ModelAndView变成一个HTML、OutputStream、json
        if(null == modelAndView){
            return;
        }
        if(this.viewResolvers.isEmpty()){
            return;
        }
        for (CoCoViewResolver viewResolver : this.viewResolvers) {
            CoCoView view = viewResolver.resolveViewName(modelAndView.getViewName(),null);
            view.render(modelAndView.getModel(),request,response);
            return;
        }
    }

    private CoCoHandlerAdapter getHandlerAdapter(CoCoHandlerMapping handler) {
        if(this.handlerAdapters.isEmpty()){return null;}
        CoCoHandlerAdapter handlerAdapter = this.handlerAdapters.get(handler);
        if(handlerAdapter.supports(handler)){
            return handlerAdapter;
        }
        return null;
    }

    @Override
    public void destroy() {
        context.getBeanDefinitionCount();
        logger.info("destroy... current bean count : {d}" + context.getBeanDefinitionCount());
    }

}