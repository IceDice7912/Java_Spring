package my.jes.AI_Exam02.TTS;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class TtsTest {

    public static void main(String[] args) {
        String clientId = "학원에서 제공하는 클라 아이디값";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "학원에서 제공하는 클라 시크릿값";//애플리케이션 클라이언트 시크릿값";
        try {
            String text ="안녕하세요? 저는 AI Platform 기반의 지능형 서비스 개발 프로젝트 A반 이상묵 입니다. 이것은 과정 실습평가 샘플 음성입니다. 이 음성 인식 결과가 텍스트로 출력될 것입니다";
            System.out.println("나라 say... : " + text);
            text = URLEncoder.encode(text, "UTF-8");
            String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "speaker=nara&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 mp3 파일 생성
//                String tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File("sample2.mp3");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
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
