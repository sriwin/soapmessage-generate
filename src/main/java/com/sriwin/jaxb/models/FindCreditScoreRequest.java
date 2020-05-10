package com.sriwin.jaxb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "findPolicyRequest",
    namespace = "http://www.temp.com/common")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindCreditScoreRequest {
  private AppInfo appInfo;
  private SearchCriteria searchCriteria;
}