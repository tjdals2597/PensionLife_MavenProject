package admin;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class admin_dto {

	private int aidx;
	private String aid, apass, aname, aindate;
	
	public ArrayList<Object> toList() {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(this.getAidx());
		al.add(this.getAid());
		al.add(this.getAname());
		al.add(this.getAindate());
		return al;
	}
}
