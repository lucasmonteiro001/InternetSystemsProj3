package utilities;

public class JsonHelper {
	private String SUCCESS 	= "success";
	private String msg;
	
	public JsonHelper(String data) {
		setMsg(data);
	}
	
	private String getMsg() {
		return msg;
	}
	private void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Return the formatted json string
	 * @param param Receives either "success" or "failed" and builds the string accordingly to it.
	 * @return json formatted
	 */
	public String getJsonFormatted(String param) {
		
		if (param.equals(SUCCESS)) {
			return " {\"success\":\"true\", \"data\":" + this.getMsg() + "}";
		}
		else {
			return " {\"success\":\"false\", \"data\":\"" + this.getMsg() + "\"}";
		}
		
	}
	
	
}
