package chapter9.embeddedtype.normal;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

//@Embeddable
public class Period {
	//근무 기간
	@Temporal(TemporalType.DATE) Date startDate;
	@Temporal(TemporalType.DATE) Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isWork(Date date) {
		return true;
	}
}
