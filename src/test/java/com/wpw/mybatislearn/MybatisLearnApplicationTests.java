package com.wpw.mybatislearn;

import com.wpw.mybatislearn.entity.User;
import com.wpw.mybatislearn.mapper.UserMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class MybatisLearnApplicationTests {
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    @Test
    void contextLoads() {
    }

    private static SqlSessionFactory sqlSessionFactory = null;
    private static InputStream inputStream = null;

    static {
        try {
            //加载mybatis连接数据库的配置信息，获取文件流
            inputStream = Resources.getResourceAsStream("Configuration.xml");
            //获取会话工厂信息
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话工厂
     *
     * @return 会话工厂
     */
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    @Test
    @Ignore
    public void searchUser() {
        //获取数据库会话sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //获取代理类UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.searchUser(1);
            //输出获取的信息
            System.out.println("user = " + user);
        } finally {
            //释放当前会话连接
            sqlSession.close();
        }
    }

    @Test
    @Ignore
    public void listUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.listUser();
            userList.stream().filter(Objects::nonNull).forEachOrdered(x -> {
                System.out.println(String.format("用户id:%d,用户名称:%s,年纪:%d", x.getId(), x.getUserName(), x.getAge()));
            });
            System.out.println("查询出的数据量为" + userList.size());
        } finally {
            //释放当前会话的连接
            sqlSession.close();
        }
    }

    @Test
    @Ignore
    public void insert() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Integer insert = userMapper.insert(User.builder().build().setUserName("admin").setAge(10));
            System.out.println("insert = " + insert);
            //默认不开启事务自动提交,可以在openSession()设置自动提交
            sqlSession.commit();
        } finally {
            //释放连接
            sqlSession.close();
        }
    }

    @Test
    @Ignore
    public void update() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Integer update = userMapper.update(User.builder().build().setId(12).setUserName("admin-info2").setAge(12));
            System.out.println("update = " + update);
            //手动提交事务
            sqlSession.commit();
        } finally {
            //释放连接
            sqlSession.close();
        }
    }

    @Test
    @Ignore
    public void delete() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Integer delete = userMapper.delete(13);
            System.out.println("delete = " + delete);
            sqlSession.commit();
        } finally {
            //释放连接
            sqlSession.close();
        }
    }

    final String statementPrefix = "com.wpw.mybatislearn.mapper.UserMapper";

    @Test
    @Ignore
    public void searchByNamespace() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            final String statement = statementPrefix + ".searchUser";
            User user = sqlSession.selectOne(statement, 1);
            System.out.println("user = " + user);
        } finally {
            //释放连接
            sqlSession.close();
        }
    }

    @Test
    @Ignore
    public void searchByNamespaceAndName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<User> userList = sqlSession.selectList(statementPrefix + ".listUser");
            userList.stream().filter(Objects::nonNull).forEach(x -> {
                System.out.println(String.format("id:%d,userName:%s,age:%d", x.getId(), x.getUserName(), x.getAge()));
            });
        } finally {
            //释放连接
            sqlSession.close();
        }
    }

    @Test
    @Ignore
    public void insertByNamespace() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert(statementPrefix + ".insert", User.builder().build().setUserName("statement").setAge(10));
            //手动提交事务
            sqlSession.commit();
        } finally {
            //释放连接
            sqlSession.close();
        }
    }

    @Test
    @Ignore
    public void updateByNamespace() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int update = sqlSession.update(statementPrefix + ".update", User.builder().build()
                    .setId(14).setUserName("statement-update").setAge(10));
            System.out.println("update = " + update);
            sqlSession.commit();
        } finally {
            //释放连接
            sqlSession.close();
        }
    }

    @Test
    public void deleteByNamespace() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int delete = sqlSession.delete(statementPrefix + ".delete", 14);
            System.out.println("delete = " + delete);
            sqlSession.commit();
        } finally {
            //释放连接
            sqlSession.close();
        }
    }
}
