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
package cn.wolfcode.ibatis.demo.jdbc;

import cn.wolfcode.ibatis.demo.domain.Role;
import cn.wolfcode.ibatis.demo.enums.RoleSnEnum;
import cn.wolfcode.ibatis.jdbc.JdbcTemplate;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author Leon
 * @date 2021/3/7
 */
public class JdbcTest {

  @Test
  public void test() {
    String sql = "select * from role where id = ?";
    Role role = JdbcTemplate.getInstance().query(sql, rs -> {
      try {
        if (rs.next()) {
          Role r = new Role();

          long id = rs.getLong("id");
          r.setId(id);
          r.setName(rs.getString("name"));
          r.setSn(RoleSnEnum.valueOf(rs.getString("sn")));

          return r;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return null;
    }, 2L);
    System.out.println(role);
  }
}
