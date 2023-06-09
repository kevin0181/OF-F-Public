package of_f.of_f_spring.service.config;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenuImg;
import of_f.of_f_spring.domain.entity.store.menu.StoreSideImg;
import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.domain.exception.StoreException;
import of_f.of_f_spring.domain.exception.StoreExceptionEnum;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ImgService {
    private String fileDir;

    @Value("${img.connect.path.mac}")
    private String macUrl;
    @Value("${img.connect.path.windows}")
    private String windowUrl;

    private String os;

    public List<StoreMenuImg> saveMenuImg(List<MultipartFile> imgFile, Store store) {

        try {
            os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                fileDir = windowUrl;
            } else if (os.contains("mac")) {
                fileDir = macUrl;
            } else {
                fileDir = macUrl;
            }

            //폴더 없을 경우, 폴더 생성.
            File file = new File(fileDir + store.getName() + "/menu");
            if (!file.exists()) {
                file.mkdirs();
            }

            List<StoreMenuImg> storeMenuImgs = new ArrayList<>();

            for (MultipartFile fileList : imgFile) {

                // 원래 파일 이름 추출
                String origName = fileList.getOriginalFilename();

                // 파일 이름으로 쓸 uuid 생성
                String uuid = UUID.randomUUID().toString();

                // 확장자 추출(ex : .png)
                String extension = origName.substring(origName.lastIndexOf("."));

                // uuid와 확장자 결합
                String savedName = uuid + extension;

                // 파일을 불러올 때 사용할 파일 경로
                String savedPath = fileDir + store.getName() + "/menu/" + savedName;

                // 실제로 로컬에 uuid를 파일명으로 저장
                fileList.transferTo(new File(savedPath));

                storeMenuImgs.add(StoreMenuImg.builder()
                        .name(savedName)
                        .date(nowDate())
                        .url(savedPath)
                        .extension(extension)
                        .build()
                );

            }

            return storeMenuImgs;

        } catch (IOException e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_SAVE_IMG);
        }

    }

    public List<StoreSideImg> saveSideMenuImg(List<MultipartFile> imgFile, Store store) {

        try {
            os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                fileDir = windowUrl;
            } else if (os.contains("mac")) {
                fileDir = macUrl;
            } else {
                fileDir = macUrl;
            }

            //폴더 없을 경우, 폴더 생성.
            File file = new File(fileDir + store.getName() + "/side");
            if (!file.exists()) {
                file.mkdirs();
            }

            List<StoreSideImg> storeSideImgs = new ArrayList<>();

            for (MultipartFile fileList : imgFile) {

                // 원래 파일 이름 추출
                String origName = fileList.getOriginalFilename();

                // 파일 이름으로 쓸 uuid 생성
                String uuid = UUID.randomUUID().toString();

                // 확장자 추출(ex : .png)
                String extension = origName.substring(origName.lastIndexOf("."));

                // uuid와 확장자 결합
                String savedName = uuid + extension;

                // 파일을 불러올 때 사용할 파일 경로
                String savedPath = fileDir + store.getName() + "/side/" + savedName;

                // 실제로 로컬에 uuid를 파일명으로 저장
                fileList.transferTo(new File(savedPath));

                storeSideImgs.add(StoreSideImg.builder()
                        .name(savedName)
                        .date(nowDate())
                        .url(savedPath)
                        .extension(extension)
                        .build()
                );

            }

            return storeSideImgs;

        } catch (IOException e) {
            throw new StoreException(StoreExceptionEnum.CAN_NOT_SAVE_IMG);
        }

    }

    public String nowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public void deleteMenuImg(List<StoreMenuImg> storeMenuImgs) {

        for (StoreMenuImg storeMenuImg : storeMenuImgs) {

            File file = new File(storeMenuImg.getUrl());

            if (file.exists()) {
                file.delete();
            } else {
                return;
            }

        }

    }

    public void deleteSideMenuImg(List<StoreSideImg> storeSideImgs) {

        for (StoreSideImg storeSideImg : storeSideImgs) {

            File file = new File(storeSideImg.getUrl());

            if (file.exists()) {
                file.delete();
            } else {
                return;
            }

        }

    }
}
