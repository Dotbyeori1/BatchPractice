package com.nuriapp.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCheckDTO {

    Long id; // id

    private String AuthMethod; // 인증 수단
    // appCard, ars, sms, homepage, samsungPay, bioData

    private boolean AuthResult; //인증 결과

}
