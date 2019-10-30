package com.oliot.eca.service.epcis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.oliot.eca.model.epcis.ActionType;
import com.oliot.eca.model.epcis.AttributeType;
import com.oliot.eca.model.epcis.BusinessTransactionListType;
import com.oliot.eca.model.epcis.BusinessTransactionType;
import com.oliot.eca.model.epcis.EPC;
import com.oliot.eca.model.epcis.EPCISEventExtensionType;
import com.oliot.eca.model.epcis.EPCListType;
import com.oliot.eca.model.epcis.ObjectEventType;
import com.oliot.eca.model.epcis.VocabularyElementListType;
import com.oliot.eca.model.epcis.VocabularyElementType;
import com.oliot.eca.model.epcis.VocabularyListType;
import com.oliot.eca.model.epcis.VocabularyType;

public class ConvertUtility {
	
	
	public static ObjectEventType translate() {
		ObjectEventType objectEventType=new ObjectEventType();
		
		
		//GregorianCalendar gRecordTime = new GregorianCalendar();
		Calendar recordTime=Calendar.getInstance();
		//recordTime = (Calendar)DatatypeFactory.newInstance().newXMLGregorianCalendar(gRecordTime);
		objectEventType.setEventTime(recordTime);
		objectEventType.setRecordTime(recordTime);
		objectEventType.setEventTimeZoneOffset("+9:00");

		//objectEventType.setEventTimeZoneOffset("-06:00");

		EPCISEventExtensionType epcisEventExtension = new EPCISEventExtensionType();
		epcisEventExtension.setEventID(UUID.randomUUID().toString());
		objectEventType.setBaseExtension(epcisEventExtension);

		EPCListType objectEventEPCs = new EPCListType();
		EPC epc1 = new EPC();
		//urn:epc:id:sgtin:CompanyPrefix.ItemReference.SerialNumber 
		epc1.setValue("urn:gs1:12345");	
		objectEventEPCs.getEpc().add(epc1);
		objectEventType.setEpcList(objectEventEPCs);

		
		objectEventType.setAction(ActionType.fromValue("OBSERVE"));
		
		objectEventType.setBizStep("urn:epcglobal:cbv:bizstep:driving");
		
		objectEventType.setDisposition("urn:epcglobal:cbv:disp:on_the_line");

		//ReadPointType readPoint = new ReadPointType();
		//urn:epc:id:sgtin:CompanyPrefix.ItemReference.SerialNumber 
		//readPoint.setId("urn:epcapp:id:sgln:8800026900016." + FormatedID);
		//objectEventType.setReadPoint(readPoint);

		//BusinessLocationType businessLocation = new BusinessLocationType();
		//urn:epc:id:sgtin:CompanyPrefix.ItemReference.SerialNumber
		//businessLocation.setId("urn:epcapp:id:sgln:8800026900016." + FormatedID);
		//objectEventType.setBizLocation(businessLocation);

		BusinessTransactionListType businessTransactionList = new BusinessTransactionListType();
		BusinessTransactionType businessTransaction1 = new BusinessTransactionType();
		businessTransaction1.setType("urn:epcglobal:cbv:Bus:status");
		businessTransaction1.setValue("urn:epcglobal:cbv:bizstep:driving");
		businessTransactionList.getBizTransaction().add(businessTransaction1);
		objectEventType.setBizTransactionList(businessTransactionList);

		List<Object> elementList = new ArrayList<Object>();

		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.newDocument();
//			Element leafElemtStr = doc.createElementNS("http://ns.example.com/StringExample", "BIS:StringExample");
//			leafElemtStr.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type", "xs:string");
//			leafElemtStr.setTextContent("Sample string");
//			elementList.add(leafElemtStr);

			// id
			Element idElement = doc.createElementNS("urn:gs1:epcisapp:BIS:busEstimation:id", "BIS:id");
			//idElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type", "xs:string");
			idElement.setTextContent("ID");
			elementList.add(idElement);

			// refBusStop
			Element refBusStopElement = doc.createElementNS("urn:gs1:epcisapp:BIS:busEstimation:refBusStop", "BIS:refBusStop");
			//refBusStopElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type", "xs:string");
			refBusStopElement.setTextContent("busEstimation.getRefBusStop()");
			elementList.add(refBusStopElement);


		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		objectEventType.setAnies(elementList);

		return objectEventType;
	}
	
	
	
public static VocabularyListType translateMaster() {
		
		VocabularyListType vocabularyListType =new VocabularyListType();
		
		List<VocabularyType> VocabularyTypeList= new ArrayList<>();
		try {
			VocabularyType voc = new VocabularyType();
			
			voc.setType("urn:gs1:epcisapp:capture:test:info");
			VocabularyElementListType vocElementList = new VocabularyElementListType();
			for(int i = 0; i < 1; i++) {
				VocabularyElementType vocElement=new VocabularyElementType();
				
				
				vocElement.setId("urn:epc:id:gsrn:880968822.124."+i);
				//id
				
				AttributeType id = new AttributeType();
				id.setId("urn:gs1:epcisapp:BIS:line:id");
				id.getContent().add("ID-"+i);
				vocElement.getAttribute().add(id);
				
				
			
				//localID
				
				AttributeType localID = new AttributeType();
				localID.setId("urn:gs1:epcisapp:BIS:line:localID");
				localID.getContent().add("LocalID-"+i);
				vocElement.getAttribute().add(localID);
				
				vocElementList.getVocabularyElement().add(vocElement);
			}
			
			voc.setVocabularyElementList(vocElementList);
			VocabularyTypeList.add(voc);
			
		}catch(Exception e) {
			
		}
		vocabularyListType.setVocabulary(VocabularyTypeList);
		return vocabularyListType;
		
	}

}
