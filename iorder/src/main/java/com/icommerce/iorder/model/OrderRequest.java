package com.icommerce.iorder.model;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class OrderRequest {

    private Long orderid;

    @NotNull
    private Long customerId;

    private Date date;

    @NotNull
    private Double sumPrice;

    @NotNull
    @NotEmpty
    @Valid
    private Set<OrderDetail> details;
}
