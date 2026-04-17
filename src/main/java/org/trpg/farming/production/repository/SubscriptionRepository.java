package org.trpg.farming.production.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.trpg.farming.production.entity.Subscription;

@Mapper
public interface SubscriptionRepository {

    /**
     * 查某资源当前有效订阅
     */
    Subscription findActiveByResourceId(@Param("resourceId") Long resourceId);
}