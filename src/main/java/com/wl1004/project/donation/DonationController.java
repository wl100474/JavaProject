package com.wl1004.project.donation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wl1004.project.member.Member;
import com.wl1004.project.member.MemberDAO;


@Controller
public class DonationController {
	@Autowired
	private DonationDAO dDAO;

	@Autowired
	private MemberDAO mDAO;

	// input type=date 처리를 위해 추가함
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/donation", method = RequestMethod.GET)
	public String getDonation(HttpServletRequest req) {
		dDAO.getDonation(req);

		return "donation/donationDetail";
	}

	@RequestMapping(value = "/donation", method = RequestMethod.POST)
	public String postDonation(HttpServletRequest req, int amount, int d_no, String d_regno) {
		System.out.println(d_no + ", " + amount);
		dDAO.insert(req, amount, d_no, d_regno);
		dDAO.updateAmount(amount, d_no);

		return "redirect:/donation";
	}

	@RequestMapping(value = "/regDonation", method = RequestMethod.GET)
	public String regDonation(HttpServletRequest req) {
		mDAO.loginCheck(req);

		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m == null || !m.getM_id().equals("admin")) {
			return "redirect:/";
		}
		return "donation/regDonation";
	}
	
	@RequestMapping(value = "/regDonation", method = RequestMethod.POST)
	public String postDonation(Donation d, HttpServletRequest req) {
		mDAO.loginCheck(req);
		System.out.println("d : " + d.getD_goal());
		System.out.println("d : " + d.getPosterMF());
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m == null || !m.getM_id().equals("admin")) {
			return "redirect:/";
		} else {
			
			dDAO.regDonation(d, req);
		}
		return "redirect:/";
	}
}
