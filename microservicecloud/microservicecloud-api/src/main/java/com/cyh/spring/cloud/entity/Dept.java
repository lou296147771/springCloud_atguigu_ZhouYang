package com.cyh.spring.cloud.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Dept implements Serializable {

    /** 主键 */
    private Long deptno;
    /** 部门名称 */
    private String dname;
    /** 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库 */
    private String db_source;
}
