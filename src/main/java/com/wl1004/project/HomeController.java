package com.wl1004.project;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wl1004.project.member.Member;
import com.wl1004.project.member.MemberDAO;
import com.wl1004.project.volunteer.VolunteerDAO;

@Controller
public class HomeController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired 
	private VolunteerDAO vDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		vDAO.countBoards();
		mDAO.loginCheck(req);
		
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null && m.getM_id().equals("admin")) {
			return "admin";
		}
		return "index";
	}
	
}
