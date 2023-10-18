package com.mypet.petmily.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PaymentDTO {

    private int payRecordCode;
    private int payPrice;
    private String payStat;
    private Date payDate;
    private int paymentCode;
    private int reservCode;

}
