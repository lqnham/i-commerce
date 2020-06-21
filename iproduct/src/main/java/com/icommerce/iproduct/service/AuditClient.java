package com.icommerce.iproduct.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "audit-service")
public interface AuditClient {
    @GetMapping("/")
    Object getAuditForProduct(@RequestParam("productId") Long productId);
}
