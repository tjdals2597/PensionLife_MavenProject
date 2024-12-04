package member;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class member_dto {
	private int midx;
	private String mid, mname, mpass, memail, mphone, mindate;
	private String consent_useterms, consent_information, consent_provision, consent_consignment, consent_marketing;
	
	public ArrayList<Object> toList() {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(this.getMidx());
		al.add(this.getMid());
		al.add(this.getMpass());
		al.add(this.getMname());
		al.add(this.getMphone());
		al.add(this.getMemail());
		return al;
	}
}
