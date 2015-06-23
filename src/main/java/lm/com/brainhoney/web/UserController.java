/**
 * 
 */
package lm.com.brainhoney.web;

import java.util.List;

import lm.com.brainhoney.model.Domain;
import lm.com.brainhoney.model.User;
import lm.com.brainhoney.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mithun.mondal
 *
 */
@Controller
public class UserController {

//	private static final Logger log = Logger.getLogger(BrainHoneyController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping("/addUser")
    public ModelAndView addUser(
    		@ModelAttribute("user") User user) {
 
//		userService.addUser(user);
		
		ModelAndView mv = new ModelAndView("actions");
		
        return mv;
    }
	
	@RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    public ModelAndView findAll() {
		List<User> userList = userService.listUser();
 
		ModelAndView mv = new ModelAndView("actions");
        mv.addObject("users", userList);
 
        return mv;
    }

}
