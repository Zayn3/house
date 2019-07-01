package com.kgc.house.entity;

public class HouseCondition {
  private String title;
  private Long startPrice;
  private Long endPrice;
  private Integer districtId;
  private Integer streetId;
  private Integer typeId;
  private String area;
  private String startArea;
  private String endArea;
//分页条件
  private Integer page;
  private Integer rows=5;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getStartPrice() {
    return startPrice;
  }

  public void setStartPrice(Long startPrice) {
    this.startPrice = startPrice;
  }

  public Long getEndPrice() {
    return endPrice;
  }

  public void setEndPrice(Long endPrice) {
    this.endPrice = endPrice;
  }

  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer districtId) {
    this.districtId = districtId;
  }

  public Integer getStreetId() {
    return streetId;
  }

  public void setStreetId(Integer streetId) {
    this.streetId = streetId;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getRows() {
    return rows;
  }

  public void setRows(Integer rows) {
    this.rows = rows;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getStartArea() {
    return startArea;
  }

  public void setStartArea(String startArea) {
    this.startArea = startArea;
  }

  public String getEndArea() {
    return endArea;
  }

  public void setEndArea(String endArea) {
    this.endArea = endArea;
  }
}
