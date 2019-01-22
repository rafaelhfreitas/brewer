package com.bigworks.brewer.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.repository.Beers;

@Controller
public class BeersController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BeersController.class);
		
	@Autowired
	private Beers beers;

    @RequestMapping("/beers/new")
    public String newOne(Beer beer){
    	
    	logger.error("Aqui é um log nível error");
//    	logger.info("Aquié um log nível info");
//    	
//    	if (logger.isDebugEnabled()) {
//    		logger.info("Erro " + beer);
//    	}
    	
    	beers.findAll();
        return "beer/InsertBeer";
    }
    
    @RequestMapping(value = "/beers/new", method= RequestMethod.POST)
    public String create(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes ) {
    	if(result.hasErrors()) {
    		//model.addAttribute(beer);
    		return newOne(beer);
    	}
    	attributes.addFlashAttribute("message", "Beer saved with sucess.");
    	System.out.println(">>>> sku " + beer.getSku());
    	return "redirect:/beers/new";
    	
    }
    
}
