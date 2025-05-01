package com.example.scm_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tm_detail")
@Data
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "part_no", length = 50)
    private String partNo;

    @Column(name = "part_name", length = 100)
    private String partName;

    private int qty;
    private String unit;
    private double price;

    @Column(name = "po_number")
    private String poNumber;

    @ManyToOne
    @JoinColumn(name = "po_number", referencedColumnName = "po_number", insertable = false, updatable = false)
    private Header header;

    // Getter and Setter
    // public Long getId() { return id; }
    // public void setId(Long id) { this.id = id; }

    // public String getPartNo() { return partNo; }
    // public void setPartNo(String partNo) { this.partNo = partNo; }

    // public String getPartName() { return partName; }
    // public void setPartName(String partName) { this.partName = partName; }

    // public int getQty() { return qty; }
    // public void setQty(int qty) { this.qty = qty; }

    // public String getUnit() { return unit; }
    // public void setUnit(String unit) { this.unit = unit; }

    // public double getPrice() { return price; }
    // public void setPrice(double price) { this.price = price; }

    // public String getPoNumber() { return poNumber; }
    // public void setPoNumber(String poNumber) { this.poNumber = poNumber; }

    // public Header getHeader() { return header; }
    // public void setHeader(Header header) { this.header = header; }
}