package com.example.scm_app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tm_header")
@Data
public class Header {
    @Id
    @Column(name = "po_number", nullable = false, length = 50)
    private String poNumber;

    @Column(name = "po_date")
    private Date poDate;

    @Column(name = "buyer_name", length = 100)
    private String buyerName;

    @Column(name = "buyer_addr", length = 100)
    private String buyerAddr;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "header")
    private List<Detail> details;

    // Getter and Setter
    // public String getPoNumber() { return poNumber; }
    // public void setPoNumber(String poNumber) { this.poNumber = poNumber; }

    // public Date getPoDate() { return poDate; }
    // public void setPoDate(Date poDate) { this.poDate = poDate; }

    // public String getBuyerName() { return buyerName; }
    // public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    // public String getBuyerAddr() { return buyerAddr; }
    // public void setBuyerAddr(String buyerAddr) { this.buyerAddr = buyerAddr; }

    // public List<Detail> getDetails() { return details; }
    // public void setDetails(List<Detail> details) { this.details = details; }
}