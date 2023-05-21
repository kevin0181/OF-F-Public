package of_f.of_f_spring.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/img")
public class ImgController {

    @Value("${img.connect.path.mac}")
    private String macUrl;
    @Value("${img.connect.path.windows}")
    private String windowUrl;

    private String os;

    @GetMapping("/get")
    public ResponseEntity<Resource> showImage(@RequestParam Map<String, String> param) {

        // 사진이 저장된 폴더 경로 변수 선언
        String imageRoot = "";

        // os 정보 확인 및 사진이 저장된 서버 로컬 경로 지정 실시
        // 로컬 : Home/Resource/assets 폴더는 이미지 파일을 공통적으로 저장 관리
        os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            imageRoot = windowUrl;
        } else if (os.contains("mac")) {
            imageRoot = macUrl;
        } else {
            imageRoot = macUrl;
        }

        // 서버 로컬 경로 + 파일 명 저장 실시
        imageRoot = imageRoot + param.get("store") + "/" + param.get("kind") + "/" + String.valueOf(param.get("name"));

        // Resorce를 사용해서 로컬 서버에 저장된 이미지 경로 및 파일 명을 지정
        Resource resource = new FileSystemResource(imageRoot);

        // 로컬 서버에 저장된 이미지 파일이 없을 경우
        if (!resource.exists()) {
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // 리턴 결과 반환 404
        }

        // 로컬 서버에 저장된 이미지가 있는 경우 로직 처리
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            filePath = Paths.get(imageRoot);
            // 인풋으로 들어온 파일명 .png / .jpg 에 맞게 헤더 타입 설정
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 이미지 리턴 실시 [브라우저에서 get 주소 확인 가능]
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

}
