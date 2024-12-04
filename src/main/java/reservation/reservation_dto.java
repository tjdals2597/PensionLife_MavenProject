package reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class reservation_dto {

	private int reser_idx, room_idx, member_idx, reser_price, reser_gnum;
	private String reser_date, reser_gname, reser_gtel, reser_email, reser_sign_date;
	
	// VIEW TABLE
	private int rgnum, rmnum;
	private String pname, rname, rcomp;
}
