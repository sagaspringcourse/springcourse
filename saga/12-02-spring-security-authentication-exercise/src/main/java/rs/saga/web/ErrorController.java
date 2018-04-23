package rs.saga.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;


@Controller
@RequestMapping("/errors")
public class ErrorController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String list(Model model, Locale locale) {
		model.addAttribute("problem", messageSource.getMessage("error.access.denied", null, locale));
		return "errors/403";
	}

}
