package com.icommerce.iaudit.service;

import com.icommerce.iaudit.model.Audit;
import com.icommerce.iaudit.model.AuditRequest;

import java.util.List;

public interface AuditService {

    List<Audit> findAll();

    Audit store(AuditRequest request);
}
