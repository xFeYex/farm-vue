package org.trpg.farming.production.service;
import org.trpg.farming.production.dto.ProductionDashboardResponse;

public interface ProductionViewService {

    /**
     * 查询智慧生产主页数据
     */
    ProductionDashboardResponse getDashboard(Long resourceId, Long userId);
}