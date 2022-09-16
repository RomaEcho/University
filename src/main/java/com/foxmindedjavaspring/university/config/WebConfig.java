package com.foxmindedjavaspring.university.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@EnableWebMvc
@Configuration
// @ComponentScan("com.foxmindedjavaspring.university")
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext applicationContext;

    // public WebConfig(ApplicationContext applicationContext) {
    //     this.applicationContext = applicationContext;
    // }

    // @Bean
    // @Description("Thymeleaf Template Resolver")
    // public ServletContextTemplateResolver templateResolver() {
    //     ServletContextTemplateResolver templateResolver = 
    //             new ServletContextTemplateResolver();
    //     templateResolver.setPrefix("/WEB-INF/templates/");
    //     templateResolver.setSuffix(".html");
    //     templateResolver.setTemplateMode("HTML5");

    //     return templateResolver;
    // }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateEngineMessageSource(messageSource());
        return templateEngine;
    }

    @Bean
    public ViewResolver thymeleafResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        // viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = 
                new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = 
                new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    // @Bean
    // public SpringTemplateEngine templateEngine() {
    //     SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    //     templateEngine.setTemplateResolver(templateResolver());
    //     templateEngine.setEnableSpringELCompiler(true);
    //     return templateEngine;
    // }

    // @Bean
    // public ThymeleafViewResolver viewResolver(){
    //     ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    //     viewResolver.setTemplateEngine(templateEngine());
    //     return viewResolver;
    // }

    // @Override
    // public void configureViewResolvers(ViewResolverRegistry registry) {
    //     ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    //     resolver.setTemplateEngine(templateEngine());
    //     registry.viewResolver(resolver);
    // }   

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler("/resources/**")
    //             .addResourceLocations("/resources/");
    // }
}
