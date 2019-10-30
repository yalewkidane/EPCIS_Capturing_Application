package com.oliot.eca;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oliot.eca.service.epcis.CaptureEPCISService;

@Controller
public class ControllerTest {
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Welcome to EPCIS Capturing Application";
    }
	
	@ResponseBody
	@RequestMapping(value = "/captureEvent", method = RequestMethod.GET)
    public String captureEvent() {
		CaptureEPCISService captureEPCISService= new CaptureEPCISService();
		String status=captureEPCISService.captureEvent();
        return status+ " -> Event Captured to EPCIS";
    }
	
	@ResponseBody
	@RequestMapping(value = "/captureMaster", method = RequestMethod.GET)
    public String captureMaster() {
		CaptureEPCISService captureEPCISService= new CaptureEPCISService();
		String status=captureEPCISService.captureMaster();
        return status+ " -> Master Captured to EPCIS";
    }
	
	

}
