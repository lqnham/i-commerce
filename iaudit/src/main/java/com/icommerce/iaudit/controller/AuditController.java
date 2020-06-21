package com.icommerce.iaudit.controller;

import com.icommerce.iaudit.model.Audit;
import com.icommerce.iaudit.model.AuditRequest;
import com.icommerce.iaudit.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class AuditController {

    private final AuditService service;

    @Autowired
    public AuditController(AuditService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll")
    @ResponseBody
    public List<Audit> findAll() {
        log.info("Audit - findAll");
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Audit store(@Valid AuditRequest request) {
        log.info("Audit - was call By: ");
        return service.store(request);
    }

}
