package com.sriwin.jaxb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "ai")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppInfo {
  @XmlElement(name = "userId", namespace = "http://www.temp.com/common")
  private String appId;

  @XmlElement(name = "address", namespace = "http://www.temp.com/common")
  private String address;
}
