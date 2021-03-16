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
package cn.wolfcode.ibatis.demo.handler;

import cn.wolfcode.ibatis.demo.enums.RoleSnEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Leon
 * @date 2021/3/11
 */
public class RoleSnEnumHandler extends BaseTypeHandler<RoleSnEnum> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, RoleSnEnum parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.name());
  }

  @Override
  public RoleSnEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return RoleSnEnum.valueOf(rs.getString(columnName));
  }

  @Override
  public RoleSnEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return RoleSnEnum.valueOf(rs.getString(columnIndex));
  }

  @Override
  public RoleSnEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return RoleSnEnum.valueOf(cs.getString(columnIndex));
  }
}
