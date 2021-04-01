package my.jes.ai.test2.voice;

//Cafe2 접속 예제
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;


public class HttpConTest {

 public static void main(String[] args) {
     try {
         URL url = new URL("http://localhost:8090/login.jes"); //카페 연동
         URL url2 = new URL("https://openapi.naver.com/v1/datalab/shopping/categories"); //네이버 API - 쇼핑 리스트
         URL url3 = new URL("https://openapi.naver.com/v1/search/news"); //네이버 API - 쇼핑 리스트  
         String text = URLEncoder.encode("이효리", "UTF-8");
         URL url4 = new URL("https://dapi.kakao.com/v2/search/web?query="+text); //카카오
         
         
         HttpURLConnection con = (HttpURLConnection)url4.openConnection(); //여기에 알맞은 url 넘버 넣어
         
         con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK a2d10a4ca6d589b9c7144c7f44d62a20");
         
         
//       String postParams="id=qwer&pw=asdf";
         con.setDoOutput(true);
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
         wr.writeBytes(text);
         wr.flush();
         wr.close();
         
         int responseCode = con.getResponseCode();
         BufferedReader br;
         if(responseCode==200) { // 정상 호출
             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = br.readLine()) != null) {
                 response.append(inputLine);
             }
             br.close();
             System.out.println(response.toString());
         } else {  // 오류 발생
             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = br.readLine()) != null) {
                 response.append(inputLine);
             }
             br.close();
             System.out.println(response.toString());
         }
     } catch (Exception e) {
         System.out.println(e);
     }
 }
}