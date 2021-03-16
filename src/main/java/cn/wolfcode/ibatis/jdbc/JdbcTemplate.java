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
package cn.wolfcode.ibatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Leon
 * @date 2021/3/7
 */
public class JdbcTemplate {

  private JdbcTemplate(){}

  private static class JdbcTemplateHolder {
    private static final JdbcTemplate INSTANCE = new JdbcTemplate();
  }

  public static JdbcTemplate getInstance() {
    return JdbcTemplateHolder.INSTANCE;
  }

  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public <T> T query(String sql, ResultHandler<T> handler, Object ...params) {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", "root", "admin");
      pst = conn.prepareStatement(sql);
      if (params != null && params.length > 0) {
        for (int i = 0; i < params.length; i++) {
          pst.setObject(i+1, params[i]);
        }
      }
      rs = pst.executeQuery();
      return handler.handleResult(rs);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          if (pst != null) {
            pst.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try {
            if (conn != null) {
              conn.close();
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
    return null;
  }
}
