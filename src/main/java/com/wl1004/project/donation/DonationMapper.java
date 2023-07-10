package com.wl1004.project.donation;

import org.apache.ibatis.annotations.Param;


public interface DonationMapper {
	public abstract Donation getDonation();
	public abstract int updateAmount(@Param("amount") int amount, @Param("d_no") int d_no);
	public abstract int insert(@Param("amount") int amount, @Param("d_no") int d_no, @Param("d_regno") String d_regno, @Param("m_id") String m_id);
	public abstract int regDonation(Donation d);
}
