package of_f.of_f_spring.service.config;

import of_f.of_f_spring.domain.entity.store.menu.StoreMenuImg;
import of_f.of_f_spring.domain.entity.user.EmailToken;
import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@Service
public class ImgService {
    public StoreMenuImg saveMenuImg(StoreMenuDTO storeMenuDTO, MultipartFile imgFile) throws IOException {

        String imgName = null;
        String imagePath = null;
        String absolutePath = "";
        String originalFileExtension = null;
        String path = "images/menu";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String contentType = imgFile.getContentType();
        if (ObjectUtils.isEmpty(contentType)) {
            throw new ApiException(ExceptionEnum.NOT_IMG);
        } else {
            if (contentType.contains("image/jpeg")) {
                originalFileExtension = ".jpg";
            } else if (contentType.contains("image/png")) {
                originalFileExtension = ".png";
            } else {
                throw new ApiException(ExceptionEnum.NOT_IMG);
            }
        }

        imgName = createRandomToken() + "_" + notDate();
        file = new File(imgName);
//        try {
        imgFile.transferTo(file);
//        } catch (IOException e) {
//            throw
//        }
        return null;
    }

    public String createRandomToken() {
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
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

        return String.valueOf(temp);
    }

    public String notDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

}
