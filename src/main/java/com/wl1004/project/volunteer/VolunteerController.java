package com.wl1004.project.volunteer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl1004.project.TokenManager;
import com.wl1004.project.comment.Comment;
import com.wl1004.project.comment.CommentDAO;
import com.wl1004.project.member.MemberDAO;

@Controller
public class VolunteerController {

	@Autowired
	MemberDAO mDAO;

	@Autowired
	VolunteerDAO vDAO;

	@Autowired
	CommentDAO cDAO;

	// input type=date 처리를 위해 추가함
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/regVolunteer", method = RequestMethod.GET)
	public String goRegVolunteer(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		return "volunteer/regVolunteer";
	}

	@RequestMapping(value = "/regVolunteer", method = RequestMethod.POST)
	public String regVolunteer(Volunteer v, HttpServletRequest req) {
		if (mDAO.loginCheck(req)) {
			// System.out.println("로그인 확인");
			vDAO.regVol(v, req);

		} else {
			return "redirect:/";
		}

		return "redirect:/list";
	}

	@RequestMapping(value = "/volunteerDetail", method = RequestMethod.GET)
	public String getDetail(int v_no, HttpServletRequest req) {
		mDAO.loginCheck(req);
		vDAO.getDetail(v_no, req);
		cDAO.countCmts(req);
		TokenManager.make(req);
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			cDAO.getAllCmt(p, v_no, req);
		} else {
			cDAO.getAllCmt(1, v_no, req);
		}

		return "volunteer/VolunteerDetail";
	}

	// 목록에 처음 접근
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(HttpServletRequest req) {
		mDAO.loginCheck(req);
		vDAO.clearSearch(req);
		vDAO.updateStatus();
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			vDAO.getAllList(p, req);
		} else {
			vDAO.getAllList(1, req);
		}

		return "volunteer/list";
	}

	// 목록 페이징
	@RequestMapping(value = "/list_paging", method = RequestMethod.GET)
	public String getPage(HttpServletRequest req) {
		mDAO.loginCheck(req);
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			vDAO.getAllList(p, req);
		} else {
			vDAO.getAllList(1, req);
		}
		// System.out.println("세션에 있는 검색어 : " +
		// req.getSession().getAttribute("search"));

		return "volunteer/list";
	}

	// 목록 타입 선택
	@RequestMapping(value = "/list_type", method = RequestMethod.GET)
	public String getListType(HttpServletRequest req) {
		mDAO.loginCheck(req);
		vDAO.searchMsg(req);
		vDAO.getAllList(1, req);

		return "volunteer/list";
	}

	// 수정
	@RequestMapping(value = "/updateVolunteer", method = RequestMethod.GET)
	public String update(int v_no, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.getDetail(v_no, req);

		return "volunteer/updateVolunteer";
	}

	@RequestMapping(value = "/updateVolunteer", method = RequestMethod.POST)
	public String goUpdate(Volunteer v, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.update(v, req);
		// System.out.println(v.getV_no());

		return getDetail(v.getV_no(), req);
	}

	// 삭제
	@RequestMapping(value = "/deleteVolunteer", method = RequestMethod.GET)
	public String delete(int v_no, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.delete(v_no, req);

		return "redirect:/list";
	}

	// 신청
	@RequestMapping(value = "/applyVolunteer", method = RequestMethod.GET)
	public String apply(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.apply(req);
		vDAO.updateStatus();

		int v_no = Integer.parseInt(req.getParameter("v_no"));
		return getDetail(v_no, req);
	}

	// 봉사 신청 취소
	@RequestMapping(value = "/cancelApplication", method = RequestMethod.GET)
	public String cancel(int v_no, String v_id, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.cancel(v_no, v_id);
		vDAO.updateStatus();

		return getDetail(v_no, req);
	}

	// 나의 봉사리스트
	@RequestMapping(value = "/myVolunteerList", method = RequestMethod.GET)
	public String myVolunteerList(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.countUserBoards1(req);
		vDAO.countUserBoards2(req);
		vDAO.getMyList_5(req);
		vDAO.getMyList2_5(req);

		return "member/MyVolunteerList";
	}

	// 작성한 목록
	@RequestMapping(value = "/createdList", method = RequestMethod.GET)
	public String createdList(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.countUserBoards1(req);
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			vDAO.getMyList(p, req);
		} else {
			vDAO.getMyList(1, req);
		}

		return "member/createdList";
	}

	// 신청한 목록
	@RequestMapping(value = "/applicationList", method = RequestMethod.GET)
	public String applicationList(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.countUserBoards2(req);
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			vDAO.getMyList2(p, req);
		} else {
			vDAO.getMyList2(1, req);
		}

		return "member/applicationList";
	}

	// 생성한 목록 페이징
	@RequestMapping(value = "/createdList_paging", method = RequestMethod.GET)
	public String getMyPage(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.countUserBoards1(req);
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			vDAO.getMyList(p, req);
			;
		} else {
			vDAO.getMyList(1, req);
		}
		// System.out.println("세션에 있는 검색어 : " +
		// req.getSession().getAttribute("search"));

		return "member/createdList";
	}

	// 신청한 목록 페이징
	@RequestMapping(value = "/applicationList_paging", method = RequestMethod.GET)
	public String getMyPage2(HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		vDAO.countUserBoards2(req);
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			vDAO.getMyList2(p, req);
			;
		} else {
			vDAO.getMyList2(1, req);
		}
		// System.out.println("세션에 있는 검색어 : " +
		// req.getSession().getAttribute("search"));

		return "member/applicationList";
	}

	// 댓글 등록
	@RequestMapping(value = "/cmtWrite", method = RequestMethod.POST)
	public String regCmt(Comment cmt, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		TokenManager.make(req);
		cDAO.regCmt(cmt, req);

		int v_no = Integer.parseInt(req.getParameter("v_no"));
		return getDetail(v_no, req);
	}

	// 대댓글 등록
	@RequestMapping(value = "/cmtCmtWrite", method = RequestMethod.POST)
	public String regCmtCmt(Comment cmt, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		TokenManager.make(req);
		cDAO.regCmt(cmt, req);

		int v_no = Integer.parseInt(req.getParameter("v_no"));
		return getDetail(v_no, req);
	}

	// 댓글 페이징
	@RequestMapping(value = "/cmt_paging", method = RequestMethod.GET)
	public String getCmtPage(HttpServletRequest req) {
		mDAO.loginCheck(req);
		int v_no = Integer.parseInt(req.getParameter("v_no"));
		if (req.getParameter("p") != null) {
			int p = Integer.parseInt(req.getParameter("p"));
			cDAO.getAllCmt(p, v_no, req);
		} else {
			cDAO.getAllCmt(1, v_no, req);
		}

		return getDetail(v_no, req);
	}

	@RequestMapping(value = "/updateCmt", method = RequestMethod.POST)
	public @ResponseBody int goUpdateCmt(String c_content, int v_no, int c_no, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return 0;
		}

		return cDAO.updateCmt(v_no, c_no, c_content);
	}

	// 삭제
	@RequestMapping(value = "/deleteCmt", method = RequestMethod.GET)
	public String deleteCmt(int v_no, int c_no, HttpServletRequest req) {
		if (!mDAO.loginCheck(req)) {
			return "redirect:/";
		}

		cDAO.deleteCmt(v_no, c_no);

		return getDetail(v_no, req);
	}
	
	@RequestMapping(value = "/count.getJSON", method = RequestMethod.GET,
			produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Map<String, Object>> getErrorJSON(HttpServletRequest req, HttpServletResponse res) {
		// 이 사이트 외부에서 AJAX가 가능하게 하려면
		// 응답 파라미터를 추가하고(HttpServletResponse)
		// 코드를 헤더를 추가
		res.addHeader("Access-Control-Allow-Origin", "*");
		return vDAO.getJSON(req);
	}
}
