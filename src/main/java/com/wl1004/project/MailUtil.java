package com.wl1004.project;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.wl1004.project.member.Member;

public class MailUtil {
	@Autowired
	private SqlSession ss;

	public void sendEmail(Member m) throws Exception {

		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "wl100474"; // 관리자 이메일 아이디
		String hostSMTPpw = "ktbhtroqhvdehigc"; // 관리자 이메일 비밀번호

		// 보내는 사람
		String fromEmail = "wl100474@gmail.com"; // 보내는 사람 이메일
		String fromName = "관리자"; // 보내는 사람 이름

		String subject = "[Walk with good neighbors]임시 비밀번호가 발급되었습니다."; // 메일 제목
		String msg = "";

		msg += "<div align='lift'";
		msg += "<h3>";
		msg += m.getM_id() + "님의 임시 비밀번호입니다. <br>로그인 후 비밀번호를 변경해 주세요</h3>";
		msg += "<p>임시 비밀번호:";
		msg += m.getM_pw() + "</p></div>";

		// email전송
		String mailRecipient = m.getM_email();// 받는 사람 이메일 주소
		try {
			// 객체 선언
			HtmlEmail mail = new HtmlEmail();
			mail.setDebug(true);
			mail.setCharset(charSet);
			mail.setSSLOnConnect(true);
			mail.setHostName(hostSMTP);
			mail.setSmtpPort(587);
			mail.setAuthentication(hostSMTPid, hostSMTPpw);
			mail.setStartTLSEnabled(true);
			mail.addTo(mailRecipient, charSet);
			mail.setFrom(fromEmail, fromName, charSet);
			mail.setSubject(subject);
			mail.setHtmlMsg(msg);
			mail.send();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findPw(HttpServletResponse response, Member m) {
		response.setContentType("text/html;charset=utf-8");

	}
}
