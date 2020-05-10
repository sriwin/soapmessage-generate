package com.sriwin.jaxb.test;

import com.sriwin.jaxb.models.AppInfo;
import com.sriwin.jaxb.models.FindCreditScoreRequest;
import com.sriwin.jaxb.models.SearchCriteria;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class JABXBTest {
  public static void main(String[] args) {
    try {
      AppInfo appInfo = new AppInfo();
      appInfo.setAddress("127.0.0.1");
      appInfo.setAppId("testApp");

      SearchCriteria searchCriteria = new SearchCriteria();
      searchCriteria.setCreditCardNumber("1234123412341234");

      FindCreditScoreRequest request = new FindCreditScoreRequest();
      request.setAppInfo(appInfo);
      request.setSearchCriteria(searchCriteria);

      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setNamespaceAware(true);

      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();

      Marshaller marshaller = JAXBContext.newInstance(FindCreditScoreRequest.class).createMarshaller();
      marshaller.marshal(request, document);
      SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();

      // build SOAP Part
      SOAPPart soapPart = soapMessage.getSOAPPart();

      // build SOAP  Envelope from SOAP Part & add namespaces
      SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
      soapEnvelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
      soapEnvelope.addNamespaceDeclaration("aaan", "http://www.temp.com/common");
      soapEnvelope.addNamespaceDeclaration("aaan1", "http://www.temp.com/credit");
      soapEnvelope.removeNamespaceDeclaration("SOAP-ENV");

      // Obtain Header from Envelope
      SOAPHeader soapHeader = soapEnvelope.getHeader();

      // SOAPBodyElement must have an associated QName object.
      SOAPBody soapBody = soapEnvelope.getBody();
      //soapMessage.getSOAPBody().setPrefix("soap");
      soapBody.addDocument(document);

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      soapMessage.writeTo(outputStream);
      String output = new String(outputStream.toByteArray());
      System.out.println(output);

      FileUtils.writeStringToFile(new File("c:/temp/1.xml"), output, "UTF-8");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
