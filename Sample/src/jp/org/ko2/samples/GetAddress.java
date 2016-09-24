/**
 *
 */
package jp.org.ko2.samples;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * @author YUICHI
 *
 */
public class GetAddress {

	public static void main(String[] args) {

		String address = null;
		StringBuilder builder = new StringBuilder();
		HttpClient httpClient = new DefaultHttpClient();

		String lat = "35.68381981";
		String lon = "139.77456498";
		String appId = "";

		String url = "http://reverse.search.olp.yahooapis.jp/OpenLocalPlatform/V1/reverseGeoCoder?"
				+ "lat=" + lat
				+ "&lon=" + lon
				+ "&output="+ "json"
				+ "&appid=" + appId;
		HttpGet httpGet = new HttpGet(url.toString());
		httpGet.setHeader("Connection", "Keep-Alive");

		try {
			HttpResponse response = httpClient.execute(httpGet);

			System.out.println("ステータスコード:" + response.getStatusLine().getStatusCode());

			if (HttpStatus.SC_OK  == response.getStatusLine().getStatusCode()) {

				JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity(), "UTF-8"));

				address = json.getJSONArray("Feature")
                         .getJSONObject(0)
                         .getJSONObject("Property")
                         .get("Address")
                         .toString();

				System.out.println(address);

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
