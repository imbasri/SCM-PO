package com.example.scm_app.service;

import com.example.scm_app.model.Detail;
import com.example.scm_app.model.Header;
import com.example.scm_app.repository.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

@Service
public class XmlExportService {

    @Autowired
    private HeaderRepository headerRepo;

    public void exportToXml(String outputPath) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // Root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("HEADERS");
        doc.appendChild(rootElement);

        // Ambil semua header dari database
        List<Header> headers = headerRepo.findAll();
        for (Header header : headers) {
            Element headerElement = doc.createElement("HEADER");
            rootElement.appendChild(headerElement);

            addElementWithValue(doc, headerElement, "PO_NUMBER", header.getPoNumber());
            addElementWithValue(doc, headerElement, "PO_DATE", header.getPoDate().toString());
            addElementWithValue(doc, headerElement, "BUYER_NAME", header.getBuyerName());
            addElementWithValue(doc, headerElement, "BUYER_ADDR", header.getBuyerAddr());

            Element detailsElement = doc.createElement("DETAILS");
            headerElement.appendChild(detailsElement);

            for (Detail detail : header.getDetails()) {
                Element detailElement = doc.createElement("DETAIL");
                detailsElement.appendChild(detailElement);

                addElementWithValue(doc, detailElement, "PART_NO", detail.getPartNo());
                addElementWithValue(doc, detailElement, "PART_NAME", detail.getPartName());
                addElementWithValue(doc, detailElement, "QTY", String.valueOf(detail.getQty()));
                addElementWithValue(doc, detailElement, "UNIT", detail.getUnit());
                addElementWithValue(doc, detailElement, "PRICE", String.valueOf(detail.getPrice()));
            }
        }

        // Write content into XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outputPath));
        transformer.transform(source, result);

        System.out.println("File saved at: " + outputPath);
    }

    // download xml
    public String downloadXml(String outputPath) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // Root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("HEADERS");
        doc.appendChild(rootElement);

        // Ambil semua header dari database
        List<Header> headers = headerRepo.findAll();
        for (Header header : headers) {
            Element headerElement = doc.createElement("HEADER");
            rootElement.appendChild(headerElement);

            addElementWithValue(doc, headerElement, "PO_NUMBER", header.getPoNumber());
            addElementWithValue(doc, headerElement, "PO_DATE", header.getPoDate().toString());
            addElementWithValue(doc, headerElement, "BUYER_NAME", header.getBuyerName());
            addElementWithValue(doc, headerElement, "BUYER_ADDR", header.getBuyerAddr());

            Element detailsElement = doc.createElement("DETAILS");
            headerElement.appendChild(detailsElement);

            for (Detail detail : header.getDetails()) {
                Element detailElement = doc.createElement("DETAIL");
                detailsElement.appendChild(detailElement);

                addElementWithValue(doc, detailElement, "PART_NO", detail.getPartNo());
                addElementWithValue(doc, detailElement, "PART_NAME", detail.getPartName());
                addElementWithValue(doc, detailElement, "QTY", String.valueOf(detail.getQty()));
                addElementWithValue(doc, detailElement, "UNIT", detail.getUnit());
                addElementWithValue(doc, detailElement, "PRICE", String.valueOf(detail.getPrice()));
            }
        }

        // Convert XML document to string
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outputPath));
        transformer.transform(source, result);

        // Convert to XML and return as a byte array for XML format
        StreamResult stringResult = new StreamResult(new StringWriter());
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.transform(source, stringResult);
        String xmlContent = stringResult.getWriter().toString();
        return xmlContent;
        
    }

    // Helper method untuk menambahkan elemen ke XML
    private void addElementWithValue(Document doc, Element parent, String tagName, String value) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }
}