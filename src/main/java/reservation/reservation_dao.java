package reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("reservation_module")
public class reservation_dao {

	@Resource(name = "sstemplate")
	private SqlSessionTemplate sstem;
	
	Map<String, String> keycode = null;
	
	public int reser_insert(reservation_dto dto) {
		return this.sstem.insert("reservationdb.addReservation", dto);
	}
	
	public List<reservation_dto> reservation_list(String midx) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "3");
		this.keycode.put("midx", midx);
		return this.sstem.selectList("reservationdb.printReservation", this.keycode);
	}
	
	public reservation_dto reservation_check(String midx) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		this.keycode.put("midx", midx);
		return this.sstem.selectOne("reservationdb.printReservation", this.keycode);
	}
	
	public reservation_dto reservation_check(Integer reser_idx) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "2");
		this.keycode.put("reser_idx", reser_idx.toString());
		return this.sstem.selectOne("reservationdb.printReservation", this.keycode);
	}
	
	public int reser_delete(String reser_idx) {
		return this.sstem.delete("reservationdb.deleteReservation", reser_idx);
	}
}
