package com.sriwin.jaxb.test;

import com.sriwin.jaxb.models.AppInfo;
import com.sriwin.jaxb.models.FindCreditScoreRequest;
import com.sriwin.jaxb.models.SearchCriteria;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

public class JacksonTest {
  public static void main(String[] args) {
    try {
      AppInfo appInfo = new AppInfo();
      appInfo.setAddress("127.0.0.1");
      appInfo.setAppId("testApp");

      SearchCriteria searchCriteria = new SearchCriteria();
      searchCriteria.setCreditCardNumber("1234123412341234");

      FindCreditScoreRequest findCreditScoreRequest = new FindCreditScoreRequest();
      findCreditScoreRequest.setAppInfo(appInfo);
      findCreditScoreRequest.setSearchCriteria(searchCriteria);

      // convert java object to XML
      convertJava2XML(findCreditScoreRequest);

      // convert xml to java object
      convertXml2Java();


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void convertJava2XML(FindCreditScoreRequest findCreditScoreRequest) {
    try {
      StringWriter stringWriter = new StringWriter();
      File file = new File("c:/temp/2.xml");

      JAXBContext jaxbContext = JAXBContext.newInstance(FindCreditScoreRequest.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      // convert java object to XML
      //marshaller.marshal(findCreditScoreRequest, stringWriter);
      marshaller.marshal(findCreditScoreRequest, file);

    } catch (Exception e) {

    }
  }

  private static void convertXml2Java() {
    try {
      StringWriter stringWriter = new StringWriter();
      File file = new File("c:/temp/2.xml");

      JAXBContext jaxbContext = JAXBContext.newInstance(FindCreditScoreRequest.class);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

      // convert xml to java object
      FindCreditScoreRequest findCreditScoreRequest =
          (FindCreditScoreRequest) unmarshaller.unmarshal(file);
      System.out.println("FindCreditScoreRequest => " + findCreditScoreRequest.toString());

    } catch (Exception e) {

    }
  }
}
