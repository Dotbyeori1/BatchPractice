package com.nuriapp.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nuriapp.demo.domain.AuthCheck;
import com.nuriapp.demo.dto.AuthCheckDTO;
import com.nuriapp.demo.manage.CsvUploadManage;
import com.nuriapp.demo.mapper.AuthCheckMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class DashBoardService {

    private final AuthCheckMapper authCheckMapper;
    private final ModelMapper modelMapper;

    public List<AuthCheckDTO> findAll() {
        List<AuthCheck> authCheckList  = authCheckMapper.findAll();
        return authCheckList.stream()
                .map(i -> modelMapper.map(i, AuthCheckDTO.class))
                .collect(Collectors.toList());
    }

    public void insertAuthCheck(MultipartFile file) {
        try {
            List<AuthCheck> authChecks = CsvUploadManage.csvToAuthChecks(file.getInputStream());
            log.info("test : " + authChecks);
            for (AuthCheck authCheck : authChecks) {
                authCheckMapper.insertAuthCheck(authCheck);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }

    }

    public void deleteAuthCheck() {
       authCheckMapper.deleteAuthCheck();
    }
}
