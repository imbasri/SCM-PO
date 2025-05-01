package com.example.scm_app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.scm_app.service.ExcelService;
import com.example.scm_app.service.XmlExportService;

@RestController
public class DataController {

    private final ExcelService excelService;
    private final XmlExportService xmlExportService;

    public DataController(ExcelService excelService, XmlExportService xmlExportService) {
        this.excelService = excelService;
        this.xmlExportService = xmlExportService;
    }

    @Value("${app.excel-path}")
    private String excelPath;

    @Value("${app.xml-output}")
    private String xmlOutput;

    @GetMapping("/import-excel")
    public String importExcel() throws Exception {
        excelService.importFromExcel(excelPath);
        return "✅ Data berhasil diimpor dari Excel ke database!";
    }

    @PostMapping("/upload-excel")
    public String uploadExcelFile(@RequestParam MultipartFile file) throws Exception {
        excelService.uploadFromExcel(file);
        return "✅ Data berhasil diimpor dari Excel ke database!";
    }

    @GetMapping("/export-xml")
    public String exportXml() throws Exception {
        xmlExportService.exportToXml(xmlOutput);
        return "✅ Data berhasil diekspor ke XML di OUTBOX!";
    }
    @GetMapping(value = "/download-xml", produces = "application/xml")
    public String downloadXml() throws Exception {
        return xmlExportService.downloadXml(xmlOutput);
    }
}