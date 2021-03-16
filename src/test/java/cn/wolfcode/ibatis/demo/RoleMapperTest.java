/**
 * Copyright 2009-2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.wolfcode.ibatis.demo;

import cn.wolfcode.ibatis.demo.domain.Role;
import cn.wolfcode.ibatis.demo.mapper.RoleMapper;
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

  public static void main(String[] args) {
    switch (1) {
      case 1:
        System.out.println("1");
      case 2:
        System.out.println("2");
      default:
        System.out.println("3");
    }
    int i = 1;
    ++i;
    i = i++;
    System.out.println(i);
  }

  @Test
  public void test() throws Exception {
    // 加载 mybatis 全局配置
    InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
    // 工厂模式&建造者模式：基于配置文件构建 SqlSession 工厂
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(stream);

    // 获取 sql 操作会话对象
    SqlSession sqlSession = sessionFactory.openSession();
    // 发送 SQL 查询并封装结果集返回
//    Role role = sqlSession.selectOne("cn.wolfcode.ibatis.demo.mapper.RoleMapper.getById", 2);
    System.out.println("=============== 第一次查询 ===============");
    RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
    Role role = mapper.getById(2L); // 第一次没有缓存，发送SQL查询数据库
    System.out.println(role);

    System.out.println("=============== 第二次查询 ===============");
    // 使用不同的 SqlSession 进行相同的查询
//    sqlSession = sessionFactory.openSession(); // 再重新创建 SqlSession，此时下面与上面就不再是同一个 SqlSession，因此不会命中一级缓存
    // 使用同一个 SqlSession 进行相同的查询
    mapper = sqlSession.getMapper(RoleMapper.class);
    role = mapper.getById(2L); // 由于是从同一个 SqlSession 中取到的 Mapper，且SQL+参数都相同，因此会命中一级缓存，不会查询数据库
    System.out.println(role);
  }
}
