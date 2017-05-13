import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class BaseHttpPostWokerThread extends Thread {
	
	private String url;
	
	private HashMap<String,String> params;
	
	public BaseHttpPostWokerThread(String url,HashMap<String, String> params) {
		this.url=url;
		this.params = params;
	}
	
	@Override
	public void run() {
		URL url;
		try {
			url = new URL(this.url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			
			httpUrlConnection.getOutputStream().write(getParamsBytes(this.params));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line=null;
			while ((line=br.readLine())!=null) {
				sb.append(line).append("\n\r");
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private byte[] getParamsBytes(HashMap<String, String> params) {
		StringBuffer sb = new StringBuffer();
		
		for (String v:params.keySet()) {
			sb.append("&").append(v).append("=").append(params.get(v));
		}
		
		return sb.toString().substring(1).getBytes();
	}

	
}
