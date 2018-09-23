package com.user.management.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.user.management.model.InterviewShedules;
import com.user.management.model.Qualification;
import com.user.management.model.RecruitmentRequests;
import com.user.management.model.ResultStatus;
import com.user.management.model.SelectedCandidates;
import com.user.management.model.User;
import com.user.management.model.UserRole;
import com.user.management.service.InterviewShedulesService;
import com.user.management.service.QualificationService;
import com.user.management.service.RecruitmentRequestsService;
import com.user.management.service.ResultStatusService;
import com.user.management.service.SelectedCandidatesService;
import com.user.management.service.UserRoleSevice;
import com.user.management.service.UserService;
import com.user.management.aes.AESEncrypt;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	UserService service;

	List<User> users = null;

	@Autowired
	RecruitmentRequestsService rrservice;

	List<RecruitmentRequests> requests = null;

	@Autowired
	SelectedCandidatesService scService;

	List<SelectedCandidates> selCandidates = null;

	@Autowired
	InterviewShedulesService sheService;

	List<InterviewShedules> shedules = null;

	@Autowired
	UserRoleSevice urService;

	List<UserRole> roles = null;

	@Autowired
	QualificationService qService;

	List<Qualification> qualifications = null;

	@Autowired
	ResultStatusService rsService;

	List<ResultStatus> rStatuses = null;

	boolean bEdit = false, bDelete = false, bAdd = false;

	User curruser = null;

	String currUserName = null;

	@Autowired
	MessageSource messageSource;

	final String secretKey = "ajinkya.bhosale";

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session, HttpServletRequest request) {
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);
		
		String username = null;
		String password = null;
		
		try {
			Cookie[] cookies = request.getCookies();
			
			for(int i = 0; i < cookies.length; i++)
			{ 	
				
				Cookie c = cookies[i];
				if (c.getName().equals("username"))
				{
					username= c.getValue();
				}
				if (c.getName().equals("password"))
				{
					password= c.getValue();
				}
			}
		}
		catch(Exception ex) {}
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return "login";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpSession session) {

		return "index";
	}
	
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String CheckLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response ) {

		session.setAttribute("failed", null);
		session.setAttribute("success", null);
		
		users = service.findAllUsers();

		logger.info("user size {}.", users.size());

		String aesPassword = AESEncrypt.encrypt(password, secretKey);

		for (User user: users) {

			if (user.getUsername().equals(username) && user.getPassword().equals(aesPassword))
			{	
				curruser = user;
				
				String chkbox = request.getParameter("remember");

				if (chkbox != null)
				{
					Cookie cu = new Cookie("username", username);
					Cookie cp = new Cookie("password", password);
				    cu.setMaxAge(24*60*60);
				    cp.setMaxAge(24*60*60);
				    response.addCookie(cu);
				    response.addCookie(cp);
				}
				else
				{
					Cookie[] cookies = request.getCookies();
					
					for(int i = 0; i < cookies.length; i++)
					{ 	
						
						Cookie c = cookies[i];
						if (c.getName().equals("username"))
						{
							c.setValue("");
							c.setPath("/");
							c.setMaxAge(0);
						}
						if (c.getName().equals("password"))
						{
							c.setValue("");
							c.setPath("/");
							c.setMaxAge(0);
						}
					}
				}
				
				requests = rrservice.findAllRequests();
				
				shedules = sheService.findAllShedules();
				
				selCandidates = scService.findAllCandidates();

				session.setAttribute("totalusers", users.size());
				session.setAttribute("totalrequests", requests.size());
				session.setAttribute("totalshedules", shedules.size());
				session.setAttribute("totalcandidates", selCandidates.size());
				
				session.setAttribute("userrole", urService.findById(curruser.getRole_id()).getRole());
				session.setAttribute("curruser", curruser);

				return "index";
			}
		}
		
		session.setAttribute("message", "Login Failed!");
		session.setAttribute("failed", "1");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LogInG(Model model, HttpSession session) {
		return "logout";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String LogOutG(Model model, HttpSession session) {
		return "logout";
	}

	@RequestMapping(value = "/viewusers", method = RequestMethod.GET)
	public String CheckLoginG(Model model, HttpSession session) {

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}

		users = service.findAllUsers();
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (roles == null)
			roles = urService.findAllUserRoles();

		if (qualifications == null)
			qualifications = qService.findAllQualifications();

		model.addAttribute("roles", roles);
		model.addAttribute("qualifications", qualifications);
		model.addAttribute("users", users);			
		return "viewusers";
	}

	@RequestMapping(value = "/userregistration", method = RequestMethod.GET)
	public String editUserG(HttpServletRequest request, Model model, HttpSession session) {

		String chkbox = request.getParameter("chkbox");
		String vsub = request.getParameter("mysub");

		logger.info("Chkbox {}.", chkbox);
		logger.info("Submit {}.", vsub);

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (chkbox != null)
		{
			User myuser = null;

			if (vsub.equalsIgnoreCase("Edit") || vsub.equalsIgnoreCase("Delete"))
			{
				myuser = service.findById(Long.parseLong(chkbox));
	
				if (myuser == null)
				{
					session.setAttribute("message", "User Not Found!");
					session.setAttribute("failed", "1");
					model.addAttribute("roles", roles);
					model.addAttribute("qualifications", qualifications);
					model.addAttribute("users", users);	
					return "viewusers";
				}
	
				String decryptPass = AESEncrypt.decrypt(myuser.getPassword(), secretKey);
	
				myuser.setPassword(decryptPass);
			}
			else
			{
				myuser = new User();
			}

			if (users == null)
				users = service.findAllUsers();

			if (roles == null)
				roles = urService.findAllUserRoles();

			if (qualifications == null)
				qualifications = qService.findAllQualifications();

			model.addAttribute("roles", roles);
			model.addAttribute("qualifications", qualifications);			
			model.addAttribute("user", myuser);
			
			if (vsub.equalsIgnoreCase("Edit"))
			{
				session.setAttribute("hmessage", "Edit User!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
				bAdd = false;
				bEdit = true;
				bDelete = false;

			}
			else if (vsub.equalsIgnoreCase("Delete"))
			{
				session.setAttribute("hmessage", "Delete User!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				bAdd = false;
				bEdit = false;
				bDelete = true;
			}
			else if (vsub.equalsIgnoreCase("Add"))
			{
				session.setAttribute("hmessage", "Add User!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
				bAdd = true;
				bEdit = false;
				bDelete = false;
			}
		}
		else if (vsub.equalsIgnoreCase("Add"))
		{
			User myuser = new User();

			if (users == null)
				users = service.findAllUsers();

			if (roles == null)
				roles = urService.findAllUserRoles();

			if (qualifications == null)
				qualifications = qService.findAllQualifications();

			model.addAttribute("roles", roles);
			model.addAttribute("qualifications", qualifications);			
			model.addAttribute("user", myuser);

			session.setAttribute("hmessage", "Add User!");
			session.setAttribute("add", true);
			session.setAttribute("edit", false);
			session.setAttribute("delete", false);
			bAdd = true;
			bEdit = false;
			bDelete = false;
		}
		else
		{
			session.setAttribute("message", "Please Select User!");
			session.setAttribute("failed", "1");
			model.addAttribute("roles", roles);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("users", users);			
			return "viewusers";
		}

		return "userregistration";
	}

	@RequestMapping(value = "/userregistration", method = RequestMethod.POST)
	public String editUserP(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {

		if (result.hasErrors()) {

			if (bAdd)
			{
				session.setAttribute("hmessage", "Add User!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
			}		
			else if (bEdit)
			{
				session.setAttribute("hmessage", "Edit User!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
			}
			else if (bDelete)
			{
				session.setAttribute("hmessage", "Delete User!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
			}

			session.setAttribute("roles", roles);
			session.setAttribute("qualifications", qualifications);	

			return "userregistration";
		}

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}

		session.setAttribute("failed", null);
		session.setAttribute("success", null);
		
		if (urService.findById(curruser.getRole_id()).getRole().equalsIgnoreCase("Admin"))
		{
			for (User myuser:users)
			{
				if((myuser.getUsername().equalsIgnoreCase(user.getUsername()) || 
						myuser.getEmailID().equalsIgnoreCase(user.getEmailID())   ||
						myuser.getNumber().equalsIgnoreCase(user.getNumber()))  &&
						(user.getUserid() != myuser.getUserid())
						)
				{	
					session.setAttribute("message", "Username or Number or Email ID Already Exists!");
					session.setAttribute("failed", "1");
					model.addAttribute("roles", roles);
					model.addAttribute("qualifications", qualifications);
					model.addAttribute("users", users);
					return "userregistration";
				}
			}
		}
		else
		{
			session.setAttribute("message", "User Not Admin!");
			session.setAttribute("failed", "1");
			model.addAttribute("roles", roles);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("users", users);			
			return "viewusers";
		}

		String aesPassword = AESEncrypt.encrypt(user.getPassword(), secretKey);

		user.setPassword(aesPassword);

		if (bAdd)
		{
			service.saveUser(user);
			users = service.findAllUsers();
			session.setAttribute("message", "Add Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;

		}		
		else if (bEdit)
		{
			service.updateUser(user);
			users = service.findAllUsers();
			session.setAttribute("message", "Edit Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}
		else if (bDelete)
		{	
			
			try {
				
				for (InterviewShedules myshe:shedules)
				{
						if (user.getUserid() == myshe.getUser_id())
							throw new Exception();
				}
				
				service.deleteUserById(user.getUserid());
			}
			catch(Exception ex)
			{
				session.setAttribute("message", "Can not Delete! Already in Use!");
				session.setAttribute("failed", "1");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				model.addAttribute("roles", roles);
				model.addAttribute("qualifications", qualifications);
				model.addAttribute("users", users);			
				return "viewusers";
			}
			users = service.findAllUsers();
			session.setAttribute("message", "Delete Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}

		//session.setAttribute("roles", roles);
		//session.setAttribute("qualifications", qualifications);	
		session.setAttribute("success", "1");
		model.addAttribute("roles", roles);
		model.addAttribute("qualifications", qualifications);
		model.addAttribute("users", users);			
		return "viewusers";

	}

	@RequestMapping(value = "/viewrequests", method = RequestMethod.GET)
	public String viewReqG(Model model, HttpSession session) {

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		requests = rrservice.findAllRequests();

		logger.info("requests size {}.", requests.size());

		model.addAttribute("requests", requests);
		return "viewrequests";
	}

	@RequestMapping(value = "/requestregistration", method = RequestMethod.GET)
	public String editReqG(HttpServletRequest request, Model model, HttpSession session) {

		String chkbox = request.getParameter("chkbox");
		String vsub = request.getParameter("mysub");

		session.setAttribute("failed", null);
		session.setAttribute("success", null);
		
		logger.info("Chkbox {}.", chkbox);
		logger.info("Submit {}.", vsub);

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}

		if (chkbox != null)
		{
			RecruitmentRequests myreq = null;
			
			if (vsub.equalsIgnoreCase("Edit") || vsub.equalsIgnoreCase("Delete"))
			{
				myreq = rrservice.findById(Long.parseLong(chkbox));
	
				if (myreq == null)
				{
					session.setAttribute("message", "Request Not Found!");
					session.setAttribute("failed", "1");
					model.addAttribute("requests", requests);
					return "viewrequests";
				}
			}
			else
			{
				myreq = new RecruitmentRequests();
			}
			
			model.addAttribute("request", myreq);

			if (vsub.equalsIgnoreCase("Edit"))
			{
				session.setAttribute("hmessage", "Edit Request!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
				bAdd = false;
				bEdit = true;
				bDelete = false;
			}
			else if (vsub.equalsIgnoreCase("Delete"))
			{
				session.setAttribute("hmessage", "Delete Request!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				bAdd = false;
				bEdit = false;
				bDelete = true;
			}
			else if (vsub.equalsIgnoreCase("Add"))
			{
				session.setAttribute("hmessage", "Add Request!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
				bAdd = true;
				bEdit = false;
				bDelete = false;
			}
		}
		else if (vsub.equalsIgnoreCase("Add"))
		{
			RecruitmentRequests myreq = new RecruitmentRequests();			

			model.addAttribute("request", myreq);
			session.setAttribute("hmessage", "Add Request!");
			session.setAttribute("add", true);
			session.setAttribute("edit", false);
			session.setAttribute("delete", false);
			bAdd = true;
			bEdit = false;
			bDelete = false;
		}
		else
		{
			session.setAttribute("message", "Please Select Request!");
			session.setAttribute("failed", "1");
			model.addAttribute("requests", requests);
			return "viewrequests";
		}

		return "requestregistration";
	}

	@RequestMapping(value = "/requestregistration", method = RequestMethod.POST)
	public String editReqP(@Valid @ModelAttribute("request") RecruitmentRequests request, BindingResult result, Model model, HttpServletRequest httprequest, HttpSession session) {

		if (result.hasErrors()) {

			if (bAdd)
			{
				
				session.setAttribute("hmessage", "Add Request!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
			}
			else if (bEdit)
			{
				session.setAttribute("hmessage", "Edit Request!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
			}
			else if (bDelete)
			{
				session.setAttribute("hmessage", "Delete Request!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
			}

			return "requestregistration";
		}

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (urService.findById(curruser.getRole_id()).getRole().equalsIgnoreCase("Admin"))
		{
			for (RecruitmentRequests myreq:requests)
			{
				if(myreq.getLanguage().equalsIgnoreCase(request.getLanguage())         &&
						myreq.getYears_Of_Experience().equalsIgnoreCase(request.getYears_Of_Experience()) &&
						(myreq.getReqid() != request.getReqid())
						)
				{
					session.setAttribute("message", "Request Already Exists!");
					session.setAttribute("failed", "1");
					model.addAttribute("requests", requests);
					return "viewrequests";
				}
			}
		}
		else
		{
			session.setAttribute("message", "User Not Admin!");
			session.setAttribute("failed", "1");
			model.addAttribute("roles", roles);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("users", users);			
			return "viewusers";
		}

		if (bAdd)
		{
			rrservice.saveRequest(request);
			requests = rrservice.findAllRequests();
			session.setAttribute("message", "Add Request Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}
		else if (bEdit)
		{
			rrservice.updateRequest(request);
			requests = rrservice.findAllRequests();
			session.setAttribute("message", "Edit Request Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}
		else if (bDelete)
		{
			try {
				
				for (InterviewShedules myshe:shedules)
				{
						if (request.getReqid() == myshe.getRequest_id())
							throw new Exception();
				}
				
				rrservice.deleteRequestById(request.getReqid());
			}
			catch(Exception ex)
			{
				session.setAttribute("message", "Can not Delete! Already in Use!");
				session.setAttribute("failed", "1");
				model.addAttribute("requests", requests);
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				return "viewrequests";
			}
			requests = rrservice.findAllRequests();
			session.setAttribute("message", "Delete Request Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}

		session.setAttribute("success", "1");
		model.addAttribute("requests", requests);
		return "viewrequests";

	}

	@RequestMapping(value = "/viewSelectedCandidates", method = RequestMethod.GET)
	public String viewCandidatesG(Model model, HttpSession session) {

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (qualifications == null)
			qualifications = qService.findAllQualifications(); 

		if (requests == null)
			requests = rrservice.findAllRequests();

		if(users == null)
			users = service.findAllUsers();

		selCandidates = scService.findAllCandidates();

		if (shedules == null)
			shedules = sheService.findAllShedules();

		if (rStatuses == null)
			rStatuses = rsService.findAllResultStatus();

		ResultStatus selected = null;
		for (ResultStatus rs:rStatuses)
		{
			if (rs.getStatus().equalsIgnoreCase("Selected"))
			{
				selected = rs;
				break;
			}
		}

		if (selected != null)
		{
			for (InterviewShedules sh:shedules)
			{
				for (SelectedCandidates can:selCandidates)
				{
					if (can.getInterview_id() == sh.getSheid())
					{
						if (sh.getResult_Status_Id() != selected.getId())
						{
							sh.setResult_Status_Id(selected.getId());
							sheService.updateShedule(sh);
						}
					}

				}
			}
		}

		shedules = sheService.findAllShedules();
		selCandidates = scService.findAllCandidates();

		model.addAttribute("qualifications", qualifications);
		model.addAttribute("shedules", shedules);
		model.addAttribute("users", users);
		model.addAttribute("requests", requests);
		model.addAttribute("candidates", selCandidates);			
		return "viewSelectedCandidates";
	}

	@RequestMapping(value = "/editSelectedCandidate", method = RequestMethod.GET)
	public String editCanG(HttpServletRequest request, Model model, HttpSession session) {

		String chkbox = request.getParameter("chkbox");
		String vsub = request.getParameter("mysub");

		logger.info("Chkbox {}.", chkbox);
		logger.info("Submit {}.", vsub);

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (chkbox != null)
		{
			if (selCandidates == null)
				selCandidates = scService.findAllCandidates();

			if (requests == null)
				requests = rrservice.findAllRequests();

			if(users == null)
				users = service.findAllUsers();

			if (shedules == null)
				shedules = sheService.findAllShedules();

			SelectedCandidates mycan = null;

			if (vsub.equalsIgnoreCase("Edit") || vsub.equalsIgnoreCase("Delete"))
			{
				mycan = scService.findById(Long.parseLong(chkbox));
	
				if (mycan == null)
				{
					session.setAttribute("message", "Candidate Not Found!");
					session.setAttribute("failed", "1");
					model.addAttribute("qualifications", qualifications);
					model.addAttribute("shedules", shedules);
					model.addAttribute("users", users);
					model.addAttribute("requests", requests);
					model.addAttribute("candidates", selCandidates);			
					return "viewSelectedCandidates";
				}
			}
			else
			{
				mycan = new SelectedCandidates();
			}

			model.addAttribute("candidate", mycan);
			model.addAttribute("users", users);
			model.addAttribute("requests", requests);
			model.addAttribute("shedules", shedules);

			if (vsub.equalsIgnoreCase("Edit"))
			{
				session.setAttribute("hmessage", "Edit Candidate!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
				bAdd = false;
				bEdit = true;
				bDelete = false;
			}
			else if (vsub.equalsIgnoreCase("Delete"))
			{
				session.setAttribute("hmessage", "Delete Candidate!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				bAdd = false;
				bEdit = false;
				bDelete = true;
			}
			else if (vsub.equalsIgnoreCase("Add"))
			{
				session.setAttribute("hmessage", "Add Candidate!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
				bAdd = true;
				bEdit = false;
				bDelete = false;
			}
		}
		else if (vsub.equalsIgnoreCase("Add"))
		{
			if (selCandidates == null)
				selCandidates = scService.findAllCandidates();

			if (requests == null)
				requests = rrservice.findAllRequests();

			if(users == null)
				users = service.findAllUsers();

			if (shedules == null)
				shedules = sheService.findAllShedules();

			SelectedCandidates mycan = new SelectedCandidates();			

			model.addAttribute("candidate", mycan);
			model.addAttribute("users", users);
			model.addAttribute("requests", requests);
			model.addAttribute("shedules", shedules);
			session.setAttribute("hmessage", "Add Candidate!");
			session.setAttribute("add", true);
			session.setAttribute("edit", false);
			session.setAttribute("delete", false);
			bAdd = true;
			bEdit = false;
			bDelete = false;
		}
		else
		{
			session.setAttribute("message", "Please Select Candidate!");
			session.setAttribute("failed", "1");
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("shedules", shedules);
			model.addAttribute("users", users);
			model.addAttribute("requests", requests);
			model.addAttribute("candidates", selCandidates);			
			return "viewSelectedCandidates";
		}

		return "editSelectedCandidate";
	}

	@RequestMapping(value = "/editSelectedCandidate", method = RequestMethod.POST)
	public String editCanP(@Valid @ModelAttribute("candidate") SelectedCandidates candidate, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {

		if (result.hasErrors()) {

			if (bAdd)
			{
				session.setAttribute("message", "Add Candidate!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
			}
			else if (bEdit)
			{
				session.setAttribute("message", "Edit Candidate!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
			}
			else if (bDelete)
			{
				session.setAttribute("message", "Delete Candidate!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
			}

			session.setAttribute("users", users);
			session.setAttribute("requests", requests);
			session.setAttribute("shedules", shedules);

			return "editSelectedCandidate";
		}

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (urService.findById(curruser.getRole_id()).getRole().equalsIgnoreCase("Admin"))
		{
			for (SelectedCandidates mycan:selCandidates)
			{
				if(mycan.getInterview_id() == candidate.getInterview_id() &&
						mycan.getRequest_id() == candidate.getRequest_id()     &&
						mycan.getSelid() != candidate.getSelid()
						)
				{
					session.setAttribute("message", "Candidate Already Selected!");
					session.setAttribute("failed", "1");
					model.addAttribute("qualifications", qualifications);
					model.addAttribute("shedules", shedules);
					model.addAttribute("users", users);
					model.addAttribute("requests", requests);
					model.addAttribute("candidates", selCandidates);			
					return "viewSelectedCandidates";
				}
			}
		}
		else
		{
			session.setAttribute("message", "User Not Admin!");
			session.setAttribute("failed", "1");
			model.addAttribute("roles", roles);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("users", users);			
			return "viewusers";
		}

		if (!candidate.getJoiningDate().after(new Date(System.currentTimeMillis())) && bAdd)
		{
			session.setAttribute("message", "Please Select a Valid Date!");
			session.setAttribute("failed", "1");
			model.addAttribute("users", users);
			model.addAttribute("requests", requests);
			model.addAttribute("shedules", shedules);
			return "editSelectedCandidate";
		}

		if (bAdd)
		{
			scService.saveCandidate(candidate);;
			selCandidates = scService.findAllCandidates();
			session.setAttribute("message", "Add Candidate Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}
		else if (bEdit)
		{
			scService.updateCandidate(candidate);
			selCandidates = scService.findAllCandidates();
			session.setAttribute("message", "Edit Candidate Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}
		else if (bDelete)
		{
			try {
				scService.deleteCandidateById(candidate.getSelid());
			}
			catch(Exception ex)
			{
				session.setAttribute("message", "Can not Delete! Already in Use!");
				session.setAttribute("failed", "1");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				model.addAttribute("users", users);
				model.addAttribute("qualifications", qualifications);
				model.addAttribute("shedules", shedules);
				model.addAttribute("users", users);
				model.addAttribute("requests", requests);
				model.addAttribute("candidates", selCandidates);	
				return "viewSelectedCandidates";
			}
			selCandidates = scService.findAllCandidates();
			session.setAttribute("message", "Delete Candidate Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}

		session.setAttribute("success", "1");
		model.addAttribute("qualifications", qualifications);
		model.addAttribute("shedules", shedules);
		model.addAttribute("users", users);
		model.addAttribute("requests", requests);
		model.addAttribute("candidates", selCandidates);			
		return "viewSelectedCandidates";

	}

	@RequestMapping(value = "/viewInterviewShedule", method = RequestMethod.GET)
	public String viewShedulesG(Model model, HttpSession session) {

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (rStatuses == null)
			rStatuses = rsService.findAllResultStatus();

		if (qualifications == null)
			qualifications = qService.findAllQualifications();

		if (requests == null)
			requests = rrservice.findAllRequests();

		if(users == null)
			users = service.findAllUsers();

		shedules = sheService.findAllShedules();

		model.addAttribute("rStatuses", rStatuses);
		model.addAttribute("qualifications", qualifications);
		model.addAttribute("requests", requests);
		model.addAttribute("users", users);
		model.addAttribute("shedules", shedules);			
		return "viewInterviewShedule";
	}

	@RequestMapping(value = "/editInterviewShedule", method = RequestMethod.GET)
	public String editSheduleG(HttpServletRequest request, Model model, HttpSession session) {

		String chkbox = request.getParameter("chkbox");
		String vsub = request.getParameter("mysub");

		logger.info("Chkbox {}.", chkbox);
		logger.info("Submit {}.", vsub);

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);
		
		if (chkbox != null)
		{
			if (rStatuses == null)
				rStatuses = rsService.findAllResultStatus();

			if (qualifications == null)
				qualifications = qService.findAllQualifications();

			if (requests == null)
				requests = rrservice.findAllRequests();

			if(users == null)
				users = service.findAllUsers();

			if (shedules == null)
				shedules = sheService.findAllShedules();

			InterviewShedules myshe = null;
			
			if (vsub.equalsIgnoreCase("Edit") || vsub.equalsIgnoreCase("Delete"))
			{
				myshe = sheService.findById(Long.parseLong(chkbox));
	
				if (myshe == null)
				{
					session.setAttribute("message", "Interview Shedule Not Found!");
					session.setAttribute("failed", "1");
					model.addAttribute("rStatuses", rStatuses);
					model.addAttribute("qualifications", qualifications);
					model.addAttribute("requests", requests);
					model.addAttribute("users", users);
					model.addAttribute("shedules", shedules);			
					return "viewInterviewShedule";
				}
			}
			else
			{
				myshe = new InterviewShedules();
			}

			model.addAttribute("rStatuses", rStatuses);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("shedule", myshe);
			model.addAttribute("users", users);
			model.addAttribute("requests", requests);

			if (vsub.equalsIgnoreCase("Edit"))
			{
				session.setAttribute("hmessage", "Edit Interview Shedule!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
				bAdd = false;
				bEdit = true;
				bDelete = false;
			}
			else if (vsub.equalsIgnoreCase("Delete"))
			{
				session.setAttribute("hmessage", "Delete Interview Shedule!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				bAdd = false;
				bEdit = false;
				bDelete = true;
			}
			else if (vsub.equalsIgnoreCase("Add"))
			{
				session.setAttribute("hmessage", "Add Interview Shedule!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
				bAdd = true;
				bEdit = false;
				bDelete = false;
			}
		}
		else if (vsub.equalsIgnoreCase("Add"))
		{
			if (rStatuses == null)
				rStatuses = rsService.findAllResultStatus();

			if (qualifications == null)
				qualifications = qService.findAllQualifications();

			if (requests == null)
				requests = rrservice.findAllRequests();

			if(users == null)
				users = service.findAllUsers();

			if (shedules == null)
				shedules = sheService.findAllShedules();

			InterviewShedules myshe = new InterviewShedules();			

			model.addAttribute("rStatuses", rStatuses);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("shedule", myshe);
			model.addAttribute("users", users);
			model.addAttribute("requests", requests);
			session.setAttribute("hmessage", "Add Interview Shedule!");
			session.setAttribute("add", true);
			session.setAttribute("edit", false);
			session.setAttribute("delete", false);
			bAdd = true;
			bEdit = false;
			bDelete = false;
		}
		else
		{
			session.setAttribute("message", "Please Select Interview Shedule!");
			session.setAttribute("failed", "1");
			model.addAttribute("rStatuses", rStatuses);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("requests", requests);
			model.addAttribute("users", users);
			model.addAttribute("shedules", shedules);			
			return "viewInterviewShedule";
		}

		return "editInterviewShedule";
	}

	@RequestMapping(value = "/editInterviewShedule", method = RequestMethod.POST)
	public String editSheduleP(@Valid @ModelAttribute("shedule") InterviewShedules shedule, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {

		if (result.hasErrors()) {

			if (bAdd)
			{
				session.setAttribute("hmessage", "Add Interview Shedule!");
				session.setAttribute("add", true);
				session.setAttribute("edit", false);
				session.setAttribute("delete", false);
			}
			else if (bEdit)
			{
				session.setAttribute("hmessage", "Edit Interview Shedule!");
				session.setAttribute("add", false);
				session.setAttribute("edit", true);
				session.setAttribute("delete", false);
			}
			else if (bDelete)
			{
				session.setAttribute("hmessage", "Delete Interview Shedule!");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
			}

			session.setAttribute("rStatuses", rStatuses);
			session.setAttribute("qualifications", qualifications);
			session.setAttribute("users", users);
			session.setAttribute("requests", requests);
			return "editInterviewShedule";
		}

		if (curruser == null || (User)session.getAttribute("curruser") == null)
		{
			session.setAttribute("message", "Please Login Again!");
			session.setAttribute("failed", "1");
			return "login";
		}
		
		session.setAttribute("failed", null);
		session.setAttribute("success", null);

		if (urService.findById(curruser.getRole_id()).getRole().equalsIgnoreCase("Admin"))
		{
			for (InterviewShedules myshe:shedules)
			{				
				if(myshe.getName().equalsIgnoreCase(shedule.getName())       &&
						myshe.getEmailID().equalsIgnoreCase(shedule.getEmailID()) &&
						myshe.getRequest_id() == shedule.getRequest_id() 		 &&
						myshe.getNumber() == shedule.getNumber()					 &&
						myshe.getSheid() != shedule.getSheid()
						)
				{
					session.setAttribute("message", "Interview Shedule Already Exists!");
					session.setAttribute("failed", "1");
					model.addAttribute("rStatuses", rStatuses);
					model.addAttribute("qualifications", qualifications);
					model.addAttribute("requests", requests);
					model.addAttribute("users", users);
					model.addAttribute("shedules", shedules);			
					return "viewInterviewShedule";
				}
			}

			for (InterviewShedules myshe:shedules)
			{				
				if((myshe.getName().equalsIgnoreCase(shedule.getName())      ||
						myshe.getEmailID().equalsIgnoreCase(shedule.getEmailID()) ||
						myshe.getNumber().equalsIgnoreCase(shedule.getNumber()))	 &&
						myshe.getSheid() != shedule.getSheid()
						)
				{
					session.setAttribute("message", "Name or Email or Number Already Exists!");
					session.setAttribute("failed", "1");
					model.addAttribute("rStatuses", rStatuses);
					model.addAttribute("qualifications", qualifications);
					model.addAttribute("users", users);
					model.addAttribute("requests", requests);
					return "editInterviewShedule";
				}
			}
		}
		else
		{
			session.setAttribute("message", "User Not Admin!");
			session.setAttribute("failed", "1");
			model.addAttribute("roles", roles);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("users", users);			
			return "viewusers";
		}

		if (!shedule.getInterview_datetime().after(new Date(System.currentTimeMillis())) && bAdd)
		{
			model.addAttribute("rStatuses", rStatuses);
			model.addAttribute("qualifications", qualifications);
			model.addAttribute("requests", requests);
			model.addAttribute("users", users);
			model.addAttribute("shedules", shedules);
			session.setAttribute("message", "Please Select a Valid Date Time!");
			session.setAttribute("failed", "1");
			return "editInterviewShedule";
		}

		if (bAdd)
		{
			sheService.saveShedule(shedule);;
			shedules = sheService.findAllShedules();
			session.setAttribute("message", "Add Interview Shedule Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}
		else if (bEdit)
		{
			sheService.updateShedule(shedule);
			shedules = sheService.findAllShedules();
			session.setAttribute("message", "Edit Interview Shedule Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}
		else if (bDelete)
		{
			try {
					for (SelectedCandidates mycan: selCandidates)
					{
						if (mycan.getInterview_id() == shedule.getSheid())
							throw new Exception();
					}
				
				sheService.deleteSheduleById(shedule.getSheid());
			}
			catch(Exception ex)
			{
				model.addAttribute("rStatuses", rStatuses);
				model.addAttribute("qualifications", qualifications);
				model.addAttribute("requests", requests);
				model.addAttribute("users", users);
				model.addAttribute("shedules", shedules);	
				session.setAttribute("message", "Can not Delete! Already Selected!");
				session.setAttribute("failed", "1");
				session.setAttribute("add", false);
				session.setAttribute("edit", false);
				session.setAttribute("delete", true);
				return "viewInterviewShedule";
			}
			
			shedules = sheService.findAllShedules();
			session.setAttribute("message", "Delete Interview Shedule Success!");
			bAdd = false;
			bEdit = false;
			bDelete = false;
		}

		session.setAttribute("success", "1");
		model.addAttribute("rStatuses", rStatuses);
		model.addAttribute("qualifications", qualifications);
		model.addAttribute("requests", requests);
		model.addAttribute("users", users);
		model.addAttribute("shedules", shedules);			
		return "viewInterviewShedule";

	}	

	@InitBinder("shedule")
	public void DateBinder(WebDataBinder binder){

		binder.registerCustomEditor(       Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), true));
	}

	@InitBinder("candidate")
	public void DateTimeBinder(WebDataBinder binder){

		binder.registerCustomEditor(       Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
