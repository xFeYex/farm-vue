package org.trpg.farming.production.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.trpg.farming.production.entity.EnvironmentSnapshot;

@Mapper
public interface EnvironmentSnapshotRepository {

    /**
     * 查询最新环境快照
     */
    EnvironmentSnapshot findLatestByResourceId(@Param("resourceId") Long resourceId);
}