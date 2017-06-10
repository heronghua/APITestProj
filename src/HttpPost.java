import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPost extends Thread {
	
	private String url;
	
	private String params;
	
	public HttpPost(String url,String params) {
		this.url=url;
		this.params = params;
	}
	
	public static void main(String[] args) {
		HttpPost postWokerThread=new HttpPost(args[0], args[1]);
		postWokerThread.start();
	}
	
	@Override
	public void run() {
		URL url;
		HttpURLConnection httpUrlConnection = null;
		try {
			url = new URL(this.url);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			
			httpUrlConnection.getOutputStream().write(this.params.getBytes());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line=null;
			while ((line=br.readLine())!=null) {
				sb.append(line).append("\n");
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			httpUrlConnection.disconnect();
		}
		
	}
	
}
