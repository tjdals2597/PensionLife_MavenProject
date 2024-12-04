package member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class qaboard_dto {

	private int qidx, qwriter, awriter;
	private String qcategory, qname, qphone, qemail, qtitle, qtext, qfile, qindate, answer, aindate;
}
