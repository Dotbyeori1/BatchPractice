package com.nuriapp.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCheck {

    Long id; // id

    private String AuthMethod; // 인증 수단
    // appCard, ars, sms, homepage, samsungPay, bioData

    private boolean AuthResult; //인증 결과

    public AuthCheck(String authMethod, boolean authResult) {
        this.AuthMethod = authMethod;
        this.AuthResult = authResult;
    }

}
