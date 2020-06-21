package com.icommerce.iaudit.model;

import lombok.Data;

import java.util.Date;

@Data
public class Audit {
    private Long auditId;
    private String action;
    private String actionContent;
    private Date date;
}
