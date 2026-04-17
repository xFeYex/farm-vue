package org.trpg.farming.production.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.trpg.farming.production.entity.OrchestrationConfig;

@Mapper
public interface OrchestrationConfigRepository {

    /**
     * 根据资源ID查询当前编排配置
     */
    OrchestrationConfig findByResourceId(@Param("resourceId") Long resourceId);
}