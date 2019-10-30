package com.oliot.eca.service.epcis;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.unece.cefact.namespaces.standardbusinessdocumentheader.DocumentIdentification;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.Partner;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.PartnerIdentification;

import com.oliot.eca.model.epcis.EventListType;
import com.oliot.eca.model.epcis.VocabularyListType;

public class EPCISEventMarshaller extends EPCISMarshaller {

	public EPCISEventMarshaller() {
		
		epcisDoc.setEPCISBody(epcisBody);
		epcisDoc.setCreationDate(Calendar.getInstance());
		epcisDoc.setSchemaVersion(new BigDecimal("1.2"));
		
	}

	public void make(EventListType eventList) {
		
		epcisBody.setEventList(eventList);
	}
	
	public void make(VocabularyListType vocabularyList) {
		epcisMasterData.setVocabularyList(vocabularyList);
		extension.setEPCISMasterData(epcisMasterData);
		epcisHeader.setExtension(extension);
		makestandardBusinessDocumentHeader();
		epcisHeader.setStandardBusinessDocumentHeader(standardBusinessDocumentHeader);
		epcisDoc.setEPCISHeader(epcisHeader);
	}
	
	public void makestandardBusinessDocumentHeader() {
		standardBusinessDocumentHeader.setHeaderVersion("1.2");
		
		PartnerIdentification identifier = new PartnerIdentification();
		identifier.setAuthority("");
		identifier.setValue("identifier");
		List<Partner> senders =new ArrayList<>();
		Partner partner =new Partner();
		partner.setIdentifier(identifier);
		senders.add(partner);
		standardBusinessDocumentHeader.setSender(senders);
		
		
		List<Partner> receivers =new ArrayList<>();
		receivers.add(partner);
		standardBusinessDocumentHeader.setReceiver(receivers);
		
		DocumentIdentification documentIdentification =new DocumentIdentification();
		documentIdentification.setStandard("EPCglobal");
		documentIdentification.setTypeVersion("1.2");
		documentIdentification.setInstanceIdentifier("InstanceIdentifier");
		documentIdentification.setType("MasterData");
		documentIdentification.setMultipleType(true);
		documentIdentification.setCreationDateAndTime(Calendar.getInstance());
		
		standardBusinessDocumentHeader.setDocumentIdentification(documentIdentification);
		
	}

}
