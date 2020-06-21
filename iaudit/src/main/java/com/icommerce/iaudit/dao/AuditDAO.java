package com.icommerce.iaudit.dao;

import com.icommerce.iaudit.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditDAO extends JpaRepository<AuditEntity, Long> {
}
