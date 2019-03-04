package com.bigworks.brewer.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.model.Flavor;
import com.bigworks.brewer.model.Origin;
import com.bigworks.brewer.repository.Beers;
import com.bigworks.brewer.repository.Styles;
import com.bigworks.brewer.repository.filter.BeerFilter;
import com.bigworks.brewer.service.RegisterBeerService;

@Controller
@RequestMapping("/beers")
public class BeersController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BeersController.class);
		
//	@Autowired
//	private Beers beers;
		
	@Autowired
	private Styles styles;
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private RegisterBeerService registerBeerService;

    @RequestMapping("/new")
    public ModelAndView newOne(Beer beer){
    	
    	logger.error("Aqui é um log nível error");
//    	logger.info("Aquié um log nível info");
//    	
//    	if (logger.isDebugEnabled()) {
//    		logger.info("Erro " + beer);
//    	}    	
//    	Optional<Beer> beerOptional = beers.findBySkuIgnoreCase("AAA1111");
//    	System.out.println(beerOptional.isPresent());
    	
    	ModelAndView mv = new ModelAndView("beer/InsertBeer");
    	mv.addObject("flavors", Flavor.values());
    	mv.addObject("styles", styles.findAll());
    	mv.addObject("origins", Origin.values());
    	
        return mv;
    }
    
    @RequestMapping(value = "/new", method= RequestMethod.POST)
    public ModelAndView create(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes ) {
    	if(result.hasErrors()) {
    		//model.addAttribute(beer);
    		return newOne(beer);
    	}
    	
//    	System.out.println(">>>> sku " + beer.getSku());
//    	System.out.println(">>>> flavor " + beer.getFlavor());
//    	System.out.println(">>>> origin " + beer.getOrigin());
//
//    	System.out.println(">>>> beer.getStyle() " + beer.getStyle() );
//    	if (beer.getStyle() != null) {
//    		System.out.println(">>>> style " + beer.getStyle().getId());	
//    	}
    	
    	registerBeerService.save(beer);
    	attributes.addFlashAttribute("message", "Beer saved with sucess.");
    	return new ModelAndView("redirect:/beers/new");
    	
    }
    
    @GetMapping
    public ModelAndView find(BeerFilter beerFilter, BindingResult result) {
    	ModelAndView mv = new ModelAndView("beer/FindBeers");
    	mv.addObject("styles",styles.findAll());
    	mv.addObject("flavors", Flavor.values());
    	mv.addObject("origins", Origin.values());
    	
    	mv.addObject("beers", beers.filter(beerFilter));
    	
    	return mv;    	
    }
    
}
