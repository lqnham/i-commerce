package com.icommerce.iaudit.service.impl;

import com.icommerce.iaudit.dao.AuditDAO;
import com.icommerce.iaudit.entity.AuditEntity;
import com.icommerce.iaudit.model.Audit;
import com.icommerce.iaudit.model.AuditRequest;
import com.icommerce.iaudit.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AuditServiceImpl implements AuditService {

    private final AuditDAO dao;

    @Autowired
    public AuditServiceImpl(AuditDAO productDAO) {
        this.dao = productDAO;

    }

    @Override
    public List<Audit> findAll() {
        ModelMapper mapper = new ModelMapper();
        List<Audit> list = new ArrayList<>();
        dao.findAll().stream().forEach(entity -> {
            list.add(mapper.map(entity, Audit.class));
        });
        return list;
    }

    @Override
    public Audit store(AuditRequest request) {
        ModelMapper mapper = new ModelMapper();
        request.setDate(new Date());
        AuditEntity entity = mapper.map(request, AuditEntity.class);
        return mapper.map(dao.save(entity), Audit.class);
    }
}
