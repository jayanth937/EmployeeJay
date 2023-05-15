package controller;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emp.model.EmployeeHibernate;
import com.emp.service.EmpServiceInterface;
import com.emp.service.EmployeeService2;




@Configuration
@EnableTransactionManagement
public class HibernateConfig {
 
  @Autowired
  private ApplicationContext context;
 
  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
    factoryBean.setAnnotatedClasses(EmployeeHibernate.class);
    return factoryBean;	
  }

 
  @Bean
  public HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(getSessionFactory().getObject());
    return transactionManager;
  }
  @Bean
  public EmpServiceInterface getservice() {
	  EmpServiceInterface emp=new EmployeeService2();
	  return emp;
  }
  
 
}