package com.icommerce.iaudit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "audit")
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "auditId", unique = true, nullable = false)
    private Long auditId;

    @Column(name = "action")
    private String action;

    @Column(name = "action_content")
    private String actionContent;

    @Column(name = "date", updatable = false)
    private Date date;
}
