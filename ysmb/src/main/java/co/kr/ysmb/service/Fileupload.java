package co.kr.ysmb.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class Fileupload {
	//서버 입장에서 가상 폴더있는데 거기있는 풀 주소를 가져야 함, 그러면 클라이언트에서는 풀 주소 + 현재 스테틱으로 resources밑에 있는
	//그러니까 클라이언트가 받을 수 있는 폴더로 데이터를 빼온다.(내 문서 열기)
	
	//resources를 c드라이브로 생각해보면 css는 로컬 호스트로 생각해서 접근하여 안에 있는 폴더와 파일 이름이 하위 이름이 되는 것!
	private String CLASSPATH = "C:\\work_sts_ys\\ysmb\\src\\main\\webapp"; //서버 파일이 저장될 위치
    private String PATHNAME = "/resources/storage/"; //db위치(위의 설명처럼!)
//    String ABSOLUTE_PATH = CLASSPATH+PATHNAME;

    public Map<String,String> Save(MultipartFile file){
        String rs = RandomString(40);
        long date = System.currentTimeMillis();
        String originalFileName = file.getOriginalFilename();
        String ext = originalFileName.split("[.]")[1];
        String safe_pathname = (CLASSPATH+PATHNAME +date+rs+"."+ext).trim(); //실질적 저장 위치 
        String save_pathname = (PATHNAME+date + rs+"."+ext).trim(); //db저장 시 경로이름
        try {
            file.transferTo(new File(safe_pathname));
            Map<String,String> value = new HashMap<String, String>();
            value.put("ABSOLUTE_PATH",safe_pathname);//원본 서버의 경로
            value.put("PATH",save_pathname);//db에 저장할 url(파일이름을 안쓸 때는 이  url만!) 
            value.put("FILENAME",date+ rs+"."+ext);//원본 파일 이름의 url
            //이는 db에 저장하기 위해 url을 저정하는 것
            return value;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}
    public String RandomString(int len){
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < len; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }
        return temp.toString();
    }
   }