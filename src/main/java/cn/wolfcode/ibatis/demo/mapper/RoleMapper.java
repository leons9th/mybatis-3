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
package cn.wolfcode.ibatis.demo.mapper;

import cn.wolfcode.ibatis.demo.domain.Role;

/**
 * @author Leon
 * @date 2021/3/9
 */
public interface RoleMapper {

  /**
   * 根据id获取角色
   *
   * @param id
   * @return
   */
  Role getById(Long id);
}
