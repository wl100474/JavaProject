package com.wl1004.project.member;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Synthesizer;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl1004.project.MailUtil;
import com.wl1004.project.volunteer.VolunteerMapper;

@Service
public class MemberDAO {
	@Autowired
	private SqlSession ss;

	// 로그인 확인
	public boolean loginCheck(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null) {
			// 로그인 성공 or 로그인 상태 유지 시
			req.setAttribute("lp", "member/welcome.jsp");
			return true;
		}
		// 로그인 상태가 아니거나 로그인 실패 시
		req.setAttribute("lp", "member/sign-in.jsp");
		return false;
	}

	// ID 중복 확인
	public int checkID(String m_id, HttpServletRequest req) {
		// System.out.println("입력한 아이디 : " + m_id);

		return ss.getMapper(MemberMapper.class).checkID(m_id);
	}

	// email 중복 확인
	public int checkEmail(String m_email, HttpServletRequest req) {
		// System.out.println("입력한 email : " + m_email);

		return ss.getMapper(MemberMapper.class).checkEmail(m_email);
	}

	// 회원가입
	public void regMember(Member m, HttpServletRequest req) {
		try {
			MemberMapper mm = ss.getMapper(MemberMapper.class);

			if (mm.regMember(m) == 1) {
				System.out.println("회원 등록 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 등록 실패");
		}
	}

	// 로그인
	public void login(Member m, HttpServletRequest req, HttpServletResponse res) {
		try {
			req.getSession().setAttribute("loginMember", ss.getMapper(MemberMapper.class).login(m));
			req.getSession().setMaxInactiveInterval(300);

			String remember = req.getParameter("remember");
			System.out.println(remember);
			if (remember != null) { // remember me에 체크가 되어 있으면 쿠키에 ID/ PW 저장
				Cookie c = new Cookie("lastLoginId", m.getM_id());
				Cookie c2 = new Cookie("lastLoginPw", m.getM_pw());
				c.setMaxAge(60 * 60 * 24 * 5); // 5일
				c2.setMaxAge(60 * 60 * 24 * 5); // 5일
				res.addCookie(c);
				res.addCookie(c2);
			} else { // remember me에 체크가 안되어 있으면 쿠키에 저장된 ID/ PW값 null로 변경
				Cookie c = new Cookie("lastLoginId", null);
				Cookie c2 = new Cookie("lastLoginPw", null);
				c.setMaxAge(0);
				c2.setMaxAge(0);
				res.addCookie(c);
				res.addCookie(c2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그아웃
	public void logout(HttpServletRequest req) {

		// Member에 대한 session만 null로 변경
		req.getSession().setAttribute("loginMember", null);
	}

	// 회원 정보 수정
	public void updateInfo(Member m, HttpServletRequest req) {
		try {
			MemberMapper mm = ss.getMapper(MemberMapper.class);

			if (mm.updateInfo(m) == 1) {
				m = ss.getMapper(MemberMapper.class).login(m);
				req.getSession().setAttribute("loginMember", m);
				System.out.println("회원 정보 수정 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 수정 실패");
		}
	}

	// 회원 탈퇴
	public void delMember(HttpServletRequest req, HttpServletResponse res) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			// System.out.println(m.getM_id());

			MemberMapper mm = ss.getMapper(MemberMapper.class);

			if (mm.delMember(m) == 1) {
				req.getSession().setAttribute("loginMember", null); // 로그아웃
				Cookie c = new Cookie("lastLoginId", null); // 쿠키 삭제
				Cookie c2 = new Cookie("lastLoginPw", null);
				c.setMaxAge(0);
				c2.setMaxAge(0);
				res.addCookie(c);
				res.addCookie(c2);
				System.out.println("회원 탈퇴 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 탈퇴 실패");
		}
	}

	// 임시 비밀번호 발급
	public String findPW(Member m, HttpServletRequest req) {
		try {
			String result = null;
			// 회원정보 불러오기
			Member m1 = ss.getMapper(MemberMapper.class).searchPwd(m);
			System.out.println(m1);

			// 가입된 이메일이 존재한다면 이메일 전송
			if (m1 != null) {

				// 임시 비밀번호 생성(UUID이용)
				String tempPw = UUID.randomUUID().toString().replace("-", "");// -를 제거
				tempPw = tempPw.substring(0, 10);// tempPw를 앞에서부터 10자리 잘라줌

				m1.setM_pw(tempPw);// 임시 비밀번호 담기

				MailUtil mail = new MailUtil(); // 메일 전송하기
				mail.sendEmail(m1);

				ss.getMapper(MemberMapper.class).updatePwd(m1);

				result = "true";
			} else {
				result = "false";
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
}
