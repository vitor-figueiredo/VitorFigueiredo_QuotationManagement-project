package br.inatel.idp.qm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aux")
public class AuxController {
	
	@GetMapping
	public String get(@RequestBody String body) {
		System.out.println( body );
		return "ok";
	}

}
