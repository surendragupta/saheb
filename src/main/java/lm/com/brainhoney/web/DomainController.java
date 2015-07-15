/**
 * 
 */
package lm.com.brainhoney.web;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import lm.com.brainhoney.model.Domain;
import lm.com.brainhoney.model.Models;
import lm.com.brainhoney.model.Session;
import lm.com.brainhoney.model.User;
import lm.com.brainhoney.service.DomainService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author mithun.mondal
 *
 */
@Controller
public class DomainController {
	
	private static final Logger log = LoggerFactory.getLogger(DomainController.class);

	String message = "";
	Document result = null;
	
	@Autowired
    private Environment environment;
	
	@Autowired
	Session agilixSession;
	
	@Autowired
	private DomainService domainService;
	
	Models model;
	
	@RequestMapping(value = "/addDomain")
    public String addDomain(
    		@ModelAttribute("domain") Domain domain) {
 
//		domainService.addDomain(domain);

        return "redirect:/index";
    }
	
	@RequestMapping(value = "/deleteDomain/{id}")
	public @ResponseBody String deleteDomain(
			@PathVariable("id") Integer id) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		
		// The DLAP server uses cookies, so be sure to keep them
        CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );
        result = agilixSession.Login("sinet-lm-dev", "administrator", "g#c9=WW9");
        
        if (!Session.IsSuccess(result))
        {
//            System.out.println("Unable to login: " + Session.GetMessage(result));
            log.debug("deleteDomain() is executed, value {}", id);
            log.error("Unable to login: " + Session.GetMessage(result));
            message = Session.GetMessage(result);	
            
            // return response                       
            return message;
        }
        
        // delete the domain
        Map<String, String> getuserMap = new HashMap<String, String>();
        getuserMap.put("domainid", String.valueOf(id));
        agilixSession.Get("deletedomain", getuserMap);
        
        Element userElement = (Element)result.getElementsByTagName("user").item(0);
        String domainid = userElement.getAttribute("domainid");
        getuserMap.put("domainid", domainid);        
     
        
        String formatedDomainJson = agilixSession.generateJsonFromXml(
        		agilixSession.Get("listdomains", getuserMap), model = new Domain(
        				environment.getRequiredProperty("domain.basename"),
        				environment.getRequiredProperty("domain.omittedlist")));
        
        message = "Domain deleted successfully.";
        log.info(message);
		// return response       
        message = "";
        return formatedDomainJson;
	}
	
	@RequestMapping(value = "/findAllDomain", method = RequestMethod.GET)
    public ModelAndView findAll() {
		List<Domain> domainList = domainService.listDomain();
 
		ModelAndView mv = new ModelAndView("actions");
        mv.addObject("domains", domainList);
 
        return mv;
    }
	
	@RequestMapping(value = "/domain/{id}", method = RequestMethod.GET)
    public ModelAndView findById(@PathVariable("id") Long id){
        Domain domain = domainService.findById(id);
 
        ModelAndView mv = new ModelAndView("actions");
        mv.addObject("domain", domain);
 
        return mv;
    }
				
			

}
