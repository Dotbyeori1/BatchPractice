package com.nuriapp.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nuriapp.demo.dto.AuthCheckDTO;
import com.nuriapp.demo.dto.ResponseDTO;
import com.nuriapp.demo.manage.CsvUploadManage;
import com.nuriapp.demo.service.DashBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequestMapping("/api") // API 경로를 "/api"로 지정
@RestController
@RequiredArgsConstructor
public class MainController {

    private final DashBoardService dashBoardService;

    @GetMapping("/main") // 구체적인 엔드포인트 지정
    public ResponseEntity<?> main(){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess("스프링부트에서 보내는 데이터, 보이면 성공");
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/authCheckList")
    public ResponseEntity<?> authCheckList() {
        List<AuthCheckDTO> authCheckDTOList = dashBoardService.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        log.info(authCheckDTOList);
        responseDTO.setData(authCheckDTOList);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/authCheckInsert")
    public ResponseEntity<?> authCheckListInsert(@RequestParam("file") MultipartFile file) {
        if (CsvUploadManage.hasCSVFormat(file)) {
            try {
                dashBoardService.deleteAuthCheck();
                dashBoardService.insertAuthCheck(file);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body("Could not upload the file: " + file.getOriginalFilename() + "!");
            }
        }
        return ResponseEntity.status(HttpStatus.OK)
                        .body("File uploaded and data saved successfully: " + file.getOriginalFilename());
    }
}
