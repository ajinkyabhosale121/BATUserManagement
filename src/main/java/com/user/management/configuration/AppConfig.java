package com.user.management.configuration;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.user.management.model.InterviewShedules;
import com.user.management.model.Qualification;
import com.user.management.model.RecruitmentRequests;
import com.user.management.model.ResultStatus;
import com.user.management.model.SelectedCandidates;
import com.user.management.model.User;
import com.user.management.model.UserRole;

import static org.hibernate.cfg.Environment.*;

@EnableTransactionManagement
@EnableWebMvc
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScans(value = { @ComponentScan("com.user.management.dao"),
@ComponentScan("com.user.management.service") })
public class AppConfig implements WebMvcConfigurer{

  @Autowired
  private Environment env;

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
	  
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    
    Properties props = new Properties();
    
    
    props.put(DRIVER, env.getProperty("hibernate.driverClassName"));
    
    props.put(URL, env.getProperty("hibernate.url"));
    
    props.put(USER, env.getProperty("hibernate.username"));
    
    props.put(PASS, env.getProperty("hibernate.password"));
    
    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
    
    props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
    
    props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
    
    props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
    
    props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
    
    props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
    
    props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

    factoryBean.setHibernateProperties(props);
    
    factoryBean.setAnnotatedClasses(User.class, InterviewShedules.class, RecruitmentRequests.class, SelectedCandidates.class, UserRole.class, Qualification.class, ResultStatus.class);
    
    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager() {
	  
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    
    transactionManager.setSessionFactory(getSessionFactory().getObject());
    
    return transactionManager;
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry
      .addResourceHandler("/resources/**")
      .addResourceLocations("/resources/cooladmin/")
      .setCachePeriod(10000)
      .resourceChain(true)
      .addResolver(new PathResourceResolver());
  }
  
  @Bean
  public InternalResourceViewResolver resolver() {
     InternalResourceViewResolver resolver = new InternalResourceViewResolver();
     resolver.setViewClass(JstlView.class);
     resolver.setPrefix("/WEB-INF/views/");
     resolver.setSuffix(".jsp");
     return resolver;
  }
  
  
//  @Bean
//  public MessageSource messageSource() {
//      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//      messageSource.setBasename("messages");
//      return messageSource;
//  }
}
