package com.nuriapp.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nuriapp.demo.domain.AuthCheck;

@Mapper
public interface AuthCheckMapper {
    
    List<AuthCheck> findAll();

    void insertAuthCheck(AuthCheck authCheck);

    void deleteAuthCheck();
}
