package edu.sollers.javaprog.CalendarDemo;

/**
 * @author rutpatel
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {
	
	@GetMapping
	public String index() {
		return "home";
	}
}