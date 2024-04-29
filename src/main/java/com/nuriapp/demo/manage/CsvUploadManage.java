package com.nuriapp.demo.manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.nuriapp.demo.domain.AuthCheck;

public class CsvUploadManage {
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!Objects.equals(file.getContentType(), "text/csv")) {
            return false;
        }
        return true;
    }

    public static List<AuthCheck> csvToAuthChecks(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
              CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.builder()
                        .setHeader() 
                       .setIgnoreHeaderCase(true)
                       .setTrim(true)
                       .build())) {

            List<AuthCheck> authCheckList = new ArrayList();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String authMethod = csvRecord.get("AuthMethod");
                boolean authResult = csvRecord.get("AuthResult").equals("1");

                System.out.println("result : " + authMethod + authResult);

                AuthCheck authCheck = new AuthCheck(
                        authMethod,
                        authResult
                );

                authCheckList.add(authCheck);
            }

            return authCheckList;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}