package com.wl1004.project.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl1004.project.volunteer.VolunteerDAO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private VolunteerDAO vDAO;

	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String signUp(HttpServletRequest req) {
		
		return "member/sign-up";
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String regMember(Member m, HttpServletRequest req) {
		mDAO.regMember(m, req);
		mDAO.loginCheck(req);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	public @ResponseBody int idCheck(@RequestParam("m_id") String m_id, HttpServletRequest req) {
		int cnt = mDAO.checkID(m_id, req);
		
		return cnt;
	}
	
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	public @ResponseBody int emailCheck(@RequestParam("m_email") String m_email, HttpServletRequest req) {
		int cnt = mDAO.checkEmail(m_email, req);
		
		return cnt;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Member m, HttpServletRequest req, HttpServletResponse res) {
		mDAO.login(m, req, res);
		mDAO.loginCheck(req);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		mDAO.logout(req);
		mDAO.loginCheck(req);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String showInfo(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}
		
		return "member/userInfo";
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.POST)
	public String updateInfo(Member m, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}
		
		mDAO.updateInfo(m, req);
		
		return "member/userInfo"; // 회원정보 수정 후 바로 확인할 수 있도록
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String delMember(HttpServletRequest req, HttpServletResponse res) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}
		
		mDAO.delMember(req, res);
		mDAO.loginCheck(req);
		
		return "redirect:/"; // /index GET방식으로 호출된 경우 Home컨트롤러 / 리턴해서 화면 보여줌
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgotPW(HttpServletRequest req) {
		
		return "member/forgot";
	}
	
	@RequestMapping(value = "/finduserpwd", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String findPW(Member m, HttpServletRequest req) {
		
		return mDAO.findPW(m, req);
	}
}
