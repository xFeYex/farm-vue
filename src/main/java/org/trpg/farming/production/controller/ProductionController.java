package org.trpg.farming.production.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.trpg.farming.production.dto.ProductionDashboardResponse;
import org.trpg.farming.production.service.ProductionViewService;
import org.trpg.farming.production.common.*;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionViewService productionViewService;

    /**
     * 智慧生产主页
     * 示例：
     * GET /api/production/dashboard/101?userId=2001
     */
    @GetMapping("/dashboard/{resourceId}")
    public Result<ProductionDashboardResponse> getDashboard(
            @PathVariable Long resourceId,
            @RequestParam Long userId) {

        ProductionDashboardResponse response = productionViewService.getDashboard(resourceId, userId);
        return Result.success(response);
    }
}