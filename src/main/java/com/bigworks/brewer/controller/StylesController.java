package com.bigworks.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigworks.brewer.controller.page.PageWrapper;
import com.bigworks.brewer.model.Style;
import com.bigworks.brewer.repository.Styles;
import com.bigworks.brewer.repository.filter.StyleFilter;
import com.bigworks.brewer.service.RegisterStyleService;
import com.bigworks.brewer.service.exception.NameStyleAlreadyExistsException;

@Controller
@RequestMapping("/styles")
public class StylesController {	
	
	@Autowired
	private RegisterStyleService registerStyleService;
	
	@Autowired
	private Styles styles;	
	
	@RequestMapping("/new")
	public ModelAndView newOne(Style style) {
		
    	ModelAndView mv = new ModelAndView("style/InsertStyle");
    	
        return mv;
	}	
		
	@RequestMapping(value = "/new", method= RequestMethod.POST)
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
	
	@RequestMapping( method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> save(@RequestBody @Valid Style style, BindingResult result) {
		
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("name").getDefaultMessage());
			
		}
		
		style = registerStyleService.save(style);
		
		return ResponseEntity.ok(style);
	}
	
	
    @GetMapping
    public ModelAndView find(StyleFilter styleFilter, BindingResult result, @PageableDefault(size = 3 ) Pageable pageable, HttpServletRequest httpServletRequest) {
    	ModelAndView mv = new ModelAndView("style/FindStyles");
   	
    	//mv.addObject("beers", beers.findAll(pageable));
    	
    	PageWrapper<Style> pageWrapper = new PageWrapper<>(styles.filter(styleFilter, pageable), httpServletRequest);
    	mv.addObject("page", pageWrapper);    	
    	return mv;    	
    }	

}
