package co.kr.ysmb.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class Fileupload {
	//���� ���忡�� ���� �����ִµ� �ű��ִ� Ǯ �ּҸ� ������ ��, �׷��� Ŭ���̾�Ʈ������ Ǯ �ּ� + ���� ����ƽ���� resources�ؿ� �ִ�
	//�׷��ϱ� Ŭ���̾�Ʈ�� ���� �� �ִ� ������ �����͸� ���´�.(�� ���� ����)
	
	//resources�� c����̺�� �����غ��� css�� ���� ȣ��Ʈ�� �����ؼ� �����Ͽ� �ȿ� �ִ� ������ ���� �̸��� ���� �̸��� �Ǵ� ��!
	private String CLASSPATH = "C:\\work_sts_ys\\ysmb\\src\\main\\webapp"; //���� ������ ����� ��ġ
    private String PATHNAME = "/resources/storage/"; //db��ġ(���� ����ó��!)
//    String ABSOLUTE_PATH = CLASSPATH+PATHNAME;

    public Map<String,String> Save(MultipartFile file){
        String rs = RandomString(40);
        long date = System.currentTimeMillis();
        String originalFileName = file.getOriginalFilename();
        String ext = originalFileName.split("[.]")[1];
        String safe_pathname = (CLASSPATH+PATHNAME +date+rs+"."+ext).trim(); //������ ���� ��ġ 
        String save_pathname = (PATHNAME+date + rs+"."+ext).trim(); //db���� �� ����̸�
        try {
            file.transferTo(new File(safe_pathname));
            Map<String,String> value = new HashMap<String, String>();
            value.put("ABSOLUTE_PATH",safe_pathname);//���� ������ ���
            value.put("PATH",save_pathname);//db�� ������ url(�����̸��� �Ⱦ� ���� ��  url��!) 
            value.put("FILENAME",date+ rs+"."+ext);//���� ���� �̸��� url
            //�̴� db�� �����ϱ� ���� url�� �����ϴ� ��
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