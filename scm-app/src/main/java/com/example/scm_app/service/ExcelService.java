package com.example.scm_app.service;

import com.example.scm_app.model.Detail;
import com.example.scm_app.model.Header;
import com.example.scm_app.repository.DetailRepository;
import com.example.scm_app.repository.HeaderRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ExcelService {

    @Autowired
    private HeaderRepository headerRepo;

    @Autowired
    private DetailRepository detailRepo;

    public void importFromExcel(String filePath) throws IOException {

        System.out.println("🔧 Mencoba membaca file dari path: " + new File(filePath).getAbsolutePath());

        File excelFile = new File(filePath);
        if (!excelFile.exists()) {
            throw new IOException("File Excel tidak ditemukan di path: " + excelFile.getAbsolutePath());
        }

        FileInputStream fis = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet headerSheet = workbook.getSheet("HEADER");
        Sheet detailSheet = workbook.getSheet("DETAIL");

        Map<String, Header> headerMap = new HashMap<>();

        for (Row row : headerSheet) {
            if (row.getRowNum() == 0)
                continue;
            Cell cell0 = row.getCell(0);
            if (cell0 == null)
                continue;

            Header header = new Header();
            header.setPoNumber(cell0.getStringCellValue());
            header.setPoDate(row.getCell(1) != null ? row.getCell(1).getDateCellValue() : null);
            header.setBuyerName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
            header.setBuyerAddr(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
            headerMap.put(header.getPoNumber(), header);
        }

        List<Detail> details = new ArrayList<>();
        for (Row row : detailSheet) {
            if (row.getRowNum() == 0)
                continue;

            Detail detail = new Detail();
            detail.setPoNumber(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
            detail.setPartNo(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
            detail.setPartName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
            detail.setQty(row.getCell(3) != null ? (int) row.getCell(3).getNumericCellValue() : 0);
            detail.setUnit(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
            detail.setPrice(row.getCell(5) != null ? row.getCell(5).getNumericCellValue() : 0.0);
            detail.setHeader(headerMap.get(detail.getPoNumber()));

            details.add(detail);
        }

        headerRepo.saveAll(headerMap.values());
        detailRepo.saveAll(details);

        workbook.close();
        fis.close();
    }

    public void uploadFromExcel(MultipartFile file) throws IOException {
        // Method ini digunakan untuk menerima file dari user melalui form upload
        System.out.println("🔧 Menerima file dari user...");

        if (file == null || file.isEmpty()) {
            throw new IOException("File tidak boleh kosong.");
        }

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet headerSheet = workbook.getSheet("HEADER");
            Sheet detailSheet = workbook.getSheet("DETAIL");

            Map<String, Header> headerMap = new HashMap<>();

            for (Row row : headerSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell0 = row.getCell(0);
                if (cell0 == null)
                    continue;

                Header header = new Header();
                header.setPoNumber(cell0.getStringCellValue());
                header.setPoDate(row.getCell(1) != null ? row.getCell(1).getDateCellValue() : null);
                header.setBuyerName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
                header.setBuyerAddr(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
                headerMap.put(header.getPoNumber(), header);
            }

            List<Detail> details = new ArrayList<>();
            for (Row row : detailSheet) {
                if (row.getRowNum() == 0)
                    continue;

                Detail detail = new Detail();
                detail.setPoNumber(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
                detail.setPartNo(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
                detail.setPartName(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
                detail.setQty(row.getCell(3) != null ? (int) row.getCell(3).getNumericCellValue() : 0);
                detail.setUnit(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
                detail.setPrice(row.getCell(5) != null ? row.getCell(5).getNumericCellValue() : 0.0);
                detail.setHeader(headerMap.get(detail.getPoNumber()));

                details.add(detail);
            }

            headerRepo.saveAll(headerMap.values());
            detailRepo.saveAll(details);
        }
    }
}