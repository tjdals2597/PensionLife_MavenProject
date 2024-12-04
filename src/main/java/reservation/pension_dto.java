package reservation;

import java.util.ArrayList;
import java.util.Collections;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class pension_dto {
	private int ridx, rgnum, rmnum, rprice;
	private String pname, rname, rcomp, rimg;
	
	ArrayList<Object> toList() {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(this.getRidx());
		al.add(this.getPname());
		al.add(this.getRname());
		al.add(this.getRcomp());
		al.add(this.getRgnum());
		al.add(this.getRmnum());
		al.add(this.getRprice());
		al.add(this.getRimg());
		al.removeAll(Collections.singletonList(null));
		return al;
	}
}
