package com.jpro.studentsmeetbackend.config;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jpro.studentsmeetbackend.model.Blog;
import com.jpro.studentsmeetbackend.model.Chat;
import com.jpro.studentsmeetbackend.model.Comment;
import com.jpro.studentsmeetbackend.model.Forum;
import com.jpro.studentsmeetbackend.model.ForumMessage;
import com.jpro.studentsmeetbackend.model.Job;
import com.jpro.studentsmeetbackend.model.JobApplication;
import com.jpro.studentsmeetbackend.model.ReportUserChat;
import com.jpro.studentsmeetbackend.model.User;


@Configuration
@ComponentScan(basePackages="com.jpro.studentsmeetbackend")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
private static final Logger logger=LoggerFactory.getLogger(ApplicationContextConfig.class);
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		logger.debug("Starting of the method getOracleDataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/collabtest");

		dataSource.setUsername("sa");
		dataSource.setPassword("");

		Properties connectionProperties = new Properties();

		connectionProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		dataSource.setConnectionProperties(connectionProperties);
		logger.debug("Setting the data source :" + dataSource.getConnectionProperties());
		logger.debug("Ending of the method getOracleDataSource");
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		logger.debug("Starting of the method getSessionFactory");
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		Properties connectionProperties = new Properties();

		connectionProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		connectionProperties.put("hibernate.hbm2ddl.auto", "update");
		connectionProperties.put("hibernate.format_sql", "true");
		connectionProperties.put("hibernate.show_sql", "true");
		sessionBuilder.addProperties(connectionProperties);
		sessionBuilder.addAnnotatedClass(ForumMessage.class);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(ReportUserChat.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		sessionBuilder.addAnnotatedClass(Comment.class);
		logger.debug("Ending of the method getSessionFactory");
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		logger.debug("Starting of the method getTransactionManager");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		logger.debug("Ending of the method getTransactionManager");
		return transactionManager;
	}


}
