package com.mypet.petmily.member.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String address;
    private String title;
    private String message;
}
