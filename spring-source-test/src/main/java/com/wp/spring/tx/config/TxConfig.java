package com.wp.spring.tx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 事务测试
 *
 * @date 2021/03/08 18:24
 * @author panfeng.wen@hand-china
 */
@ComponentScan("com.wp.spring.tx")
@Configuration
@EnableTransactionManagement
public class TxConfig {

	/**
	 * 注入数据源
	 *
	 * @return javax.sql.DataSource
	 * @date 2021/3/8 6:51 下午 Mr_wenpan@163.com
	 */
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("wenpanwenpan");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/project-test");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}

	/**
	 * 注入 JdbcTemplate，方便CRUD，这里不用mybatis
	 *
	 * @return org.springframework.jdbc.core.JdbcTemplate
	 * @date 2021/3/8 6:51 下午 Mr_wenpan@163.com
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}

	/**
	 * 注册事务管理器到容器，用事务管理器结合数据源来实现数据库事务
	 *
	 * @return org.springframework.transaction.PlatformTransactionManager
	 * @date 2021/3/8 6:50 下午 Mr_wenpan@163.com
	 */
	@Bean
	public PlatformTransactionManager transactionManager() throws PropertyVetoException {
		return new DataSourceTransactionManager(dataSource());
	}
}
