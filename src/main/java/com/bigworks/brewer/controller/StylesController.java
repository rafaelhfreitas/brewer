package com.bigworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigworks.brewer.model.Style;
import com.bigworks.brewer.service.RegisterStyleService;
import com.bigworks.brewer.service.exception.NameStyleAlreadyExistsException;

@Controller
public class StylesController {	
	
	@Autowired
	private RegisterStyleService registerStyleService;
	
	@RequestMapping("/styles/new")
	public ModelAndView newOne(Style style) {
		
    	ModelAndView mv = new ModelAndView("style/InsertStyle");
    	
        return mv;
	}	
		
	@RequestMapping(value = "/styles/new", method= RequestMethod.POST)
    public ModelAndView create(@Valid Style style, BindingResult result, Model model, RedirectAttributes attributes ) {
    	if(result.hasErrors()) {
    		return newOne(style);
    	}
    	    	
    	try {
			registerStyleService.save(style);
		} catch (NameStyleAlreadyExistsException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return newOne(style);
		}
    	attributes.addFlashAttribute("message", "Style saved with sucess.");
    	return new ModelAndView("redirect:/styles/new");		
	}
	
	@RequestMapping(value = "/styles", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid Style style, BindingResult result) {
		
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("name").getDefaultMessage());
			
		}
		
		try {
			style = registerStyleService.save(style);
		} catch (NameStyleAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(style);
	}

}
