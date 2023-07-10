package com.wl1004.project.donation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wl1004.project.member.Member;
import net.utility.UploadSaveManager;
@Service
public class DonationDAO {
	@Autowired
	private SqlSession ss;

	public void regDonation(Donation d, HttpServletRequest request) {
		try {
			String path = request.getRealPath("resources/images");
			System.out.println(path);
			
			MultipartFile posterMF = d.getPosterMF(); // 파일 가져오기
			System.out.println(d.getD_goal());
			System.out.println(posterMF);
			String photo = UploadSaveManager.saveFileSpring30(posterMF, path);
			d.setD_photo(photo);
			
			// 줄 바꿈 처리
			d.getD_content().replace("\r\n", "<br>");
			ss.getMapper(DonationMapper.class).regDonation(d);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(HttpServletRequest req, int amount, int d_no, String d_regno) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			
			ss.getMapper(DonationMapper.class).insert(amount, d_no, d_regno, m.getM_id());
			System.out.println("기부 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDonation(HttpServletRequest req) {
		try {
			Donation d = ss.getMapper(DonationMapper.class).getDonation();
			req.setAttribute("donation", d);

			double per = ((double) d.getD_amount() / d.getD_goal()) * 100;
			req.setAttribute("per", per);
			// System.out.println(per);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateAmount(int amount, int d_no) {
		try {

			ss.getMapper(DonationMapper.class).updateAmount(amount, d_no);
			System.out.println("현재 모금액 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
