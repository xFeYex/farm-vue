package org.trpg.farming.production.service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trpg.farming.production.dto.ProductionDashboardResponse;
import org.trpg.farming.production.entity.CameraView;
import org.trpg.farming.production.entity.EnvironmentSnapshot;
import org.trpg.farming.production.entity.OrchestrationConfig;
import org.trpg.farming.production.entity.Subscription;
import org.trpg.farming.production.repository.CameraViewRepository;
import org.trpg.farming.production.repository.EnvironmentSnapshotRepository;
import org.trpg.farming.production.repository.OrchestrationConfigRepository;
import org.trpg.farming.production.repository.SubscriptionRepository;
import org.trpg.farming.production.service.ProductionViewService;

@Service
@RequiredArgsConstructor
public class ProductionViewServiceImpl implements ProductionViewService {

    private final SubscriptionRepository subscriptionRepository;
    private final OrchestrationConfigRepository orchestrationConfigRepository;
    private final EnvironmentSnapshotRepository environmentSnapshotRepository;
    private final CameraViewRepository cameraViewRepository;

    @Override
    public ProductionDashboardResponse getDashboard(Long resourceId, Long userId) {

        // 1. 校验当前用户是否对该资源有有效订阅
        Subscription subscription = subscriptionRepository.findActiveByResourceId(resourceId);

        if (subscription == null || !userId.equals(subscription.getTenantUserId())) {
            return ProductionDashboardResponse.unavailable(resourceId, "当前订阅已失效，智慧生产模块不可用");
        }

        // 2. 查询编排配置
        OrchestrationConfig orchestrationConfig =
                orchestrationConfigRepository.findByResourceId(resourceId);

        // 3. 查询最新环境快照
        EnvironmentSnapshot environmentSnapshot =
                environmentSnapshotRepository.findLatestByResourceId(resourceId);

        // 4. 查询摄像头信息
        CameraView cameraView =
                cameraViewRepository.findByResourceId(resourceId);

        // 5. 组装 dashboard 返回
        return ProductionDashboardResponse.available(
                resourceId,
                orchestrationConfig,
                environmentSnapshot,
                cameraView
        );
    }
}