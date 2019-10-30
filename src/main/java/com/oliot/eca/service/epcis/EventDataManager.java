package com.oliot.eca.service.epcis;

import com.oliot.eca.model.epcis.EventListType;
import com.oliot.eca.model.epcis.VocabularyListType;

public class EventDataManager {

	protected EPCISEventMarshaller marshaller;
	protected EventListType eventList;
	protected VocabularyListType vocabularyList;
	
	public EventDataManager() {
		
		marshaller = new EPCISEventMarshaller();
		eventList = new EventListType();
		vocabularyList = new VocabularyListType();
	}
}
