package ra.demo6.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import ra.demo6.model.Order;
import ra.demo6.service.OrderService;

import java.io.IOException;

@Configuration // để hiểu đây là class cau hình
@EnableWebMvc // đang sử dụng spring web mvc
@ComponentScan("ra.demo6") // đọc các lớp ở trong packages
@PropertySource({"classpath:upload.properties"})
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {
    @Bean
    public OrderService orderService(){
        return  new OrderService();
    }

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // cau hinh upload file
    @Value("${upload-path}")
    private String pathUpload;

    //Cấu hình Thymleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("UTF-8");
        return viewResolver;
    }



    //       cau hinh file da phuong tien`
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800);
        return resolver;
    }




    // cấu hình lại đường dẫn tương đối
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        "/upload/**", "/css/**", "/fonts**", "/images/**", "/include/**", "/js/**", "/js2/**", "/css2/**", "/image2/**", "/fonts2/**")
                .addResourceLocations("file:" + pathUpload, "classpath:/css/", "classpath:/fonts/", "classpath:/images/", "classpath:/include/", "classpath:/js/", "classpath:/js2/", "classpath:/css2/", "classpath:/image2/", "classpath:/fonts2/");
    }



}

