/**
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package cn.wolfcode.ibatis.demo;

import cn.wolfcode.ibatis.demo.domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author Leon
 * @date 2021/3/6
 */
public class RoleMapperTest {

  @Test
  public void test() throws Exception {
    // 加载 mybatis 全局配置
    InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
    // 工厂模式&建造者模式：基于配置文件构建 SqlSession 工厂
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(stream);

    // 建立连接
    SqlSession sqlSession = sessionFactory.openSession();
    // 发送 SQL 查询并封装结果集返回
    Role role = sqlSession.selectOne("cn.wolfcode.ibatis.demo.RoleMapper.getById", 2);
    System.out.println(role);
  }
}
