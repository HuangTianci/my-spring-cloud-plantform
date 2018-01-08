package com.tianci.iframework.usermanager.admin.model;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Huang Tianci
 */
public class AdminTest {

    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        String resource = "SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession();
    }

    @Test
    public void test() {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setUserName("admin");
        admin.setPassword("password");
        admin.setCreateUser("system");
        admin.setCreateDate(new Date());
        System.out.println(admin.toString());
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testFindById() {
        Admin admin = sqlSession.selectOne("test.findAdminById", 1);
        System.out.println(admin.toString());
        Assert.assertEquals(1, admin.getId());
    }

    @Test
    public void testFindByName() {
        List<Admin> list = sqlSession.selectList("test.findAdminByUserName", "管理员");
        System.out.println(list.toString());
        Assert.assertEquals(1, list.size());
    }

    @After
    public void after() {
        sqlSession.close();
    }

}