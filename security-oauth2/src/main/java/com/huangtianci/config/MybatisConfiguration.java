package com.huangtianci.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Huang Tianci
 * @date 2017/11/21
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        SqlSessionFactory sessionFactory = factoryBean.getObject();

        return sessionFactory;
    }

}
