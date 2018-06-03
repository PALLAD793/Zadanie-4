package proz;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
public class NBPAnswers {

	private String table;
	private String currency;
	private String code;
	private List<Answer> answer;
	
	@XmlElement(name = "Table")
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	@XmlElement(name = "Currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@XmlElement(name = "Code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@XmlElementWrapper(name = "Rates")
	@XmlElement(name = "Rate")
	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	public static class Answer {
		private String ask;
		private String mid;
		
		@XmlElement(name = "Ask")
		public String getAsk() {
			return ask;
		}

		public void setAsk(String ask) {
			this.ask = ask;
		}

		@XmlElement(name = "Mid")
		public String getMid() {
			return mid;
		}

		public void setMid(String mid) {
			this.mid = mid;
		}
	}

	public Double average(NBPAnswers a) {
		Double sum = new Double(0);

		for (Answer answer : a.getAnswer()) {
			if (Double.parseDouble(answer.getAsk()) > 0)
				sum += Double.parseDouble(answer.getAsk());
			else if (Double.parseDouble(answer.getMid()) > 0)
				sum += Double.parseDouble(answer.getMid());
		}

		return (double) Math.round(sum / a.getAnswer().size() * 1000) / 1000;
	}

}
