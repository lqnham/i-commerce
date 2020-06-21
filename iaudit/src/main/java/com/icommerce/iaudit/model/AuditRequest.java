package com.icommerce.iaudit.model;

import lombok.Data;

import java.util.Date;

@Data
public class AuditRequest {
    private String action;
    private String actionContent;
    private Date date;
}
