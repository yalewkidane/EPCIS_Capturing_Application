package com.oliot.eca.service.epcis;

import java.util.List;

import com.oliot.eca.model.epcis.ObjectEventType;

public class CaptureEPCISService extends EventDataManager{

	
	public String captureEvent() {
		String result="Staus";
		ObjectEventType objectEvent=ConvertUtility.translate();
		
		this.eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(objectEvent);
		
		
		
		marshaller.make(eventList);
		String epcdoc = marshaller.marshal();
		System.out.println(epcdoc);
		int status=CaptureUtility.registerEPCIS(epcdoc);
		//System.out.println(epcdoc);
		result="status..." +status;
		System.out.println(result);
		
		return result;
	}
	
	
	public String captureMaster() {
		String result="Staus";
		//ObjectEventType objectEvent=new ObjectEventType();
		//this.eventList.getObjectEventsAndAggregationEventsAndQuantityEvents().add(objectEvent);
		
		this.vocabularyList=ConvertUtility.translateMaster();
		
		marshaller.make(vocabularyList);
		marshaller.make(eventList);
		String epcdoc = marshaller.marshal();
		//System.out.println(epcdoc);
		int status=CaptureUtility.registerEPCIS(epcdoc);
		System.out.println(epcdoc);
		result="status..." +status;
		System.out.println(result);
		return result;
	}
}
