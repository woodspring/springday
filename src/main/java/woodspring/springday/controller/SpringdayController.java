package woodspring.springday.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import woodspring.springday.model.UserOne;
import woodspring.springday.service.SpringdayService;

public class SpringdayController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("USER")
	SpringdayService userService;
	
	@Autowired
	@Qualifier("USERONE")
	SpringdayService useroneService;
	
	
	@GetMapping("/springday/user/{firstname}")
	public String findUserByFirstname(@PathVariable(value = "firstname") String firstname) {
		logger.info("GateController, findUserByFirstname-----"+ firstname );
		//String retStr = gateService.forBinaryTreeService( numberList);
		return null;
	}
	@GetMapping("/springday/user/{firstname}/{lastname}")
	public String findUserByName(@PathVariable(value = "firstname") String firstname, @PathVariable(value="lastname")String lastname) {
		logger.info("GateController, findUserByname-----"+ firstname+" "+lastname );
		//String retStr = gateService.forBinaryTreeService( numberList);
		return null;
	}
	
	@PostMapping(path = "/UserOne", consumes = "application/json", produces = "application/json")
	public void addUserOneMember(@RequestBody UserOne userOne) {
	    //code
	}


}
