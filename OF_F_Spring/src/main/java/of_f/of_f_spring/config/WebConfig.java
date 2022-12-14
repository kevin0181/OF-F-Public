package of_f.of_f_spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Value("${img.connect.path.mac}")
    private String uploadUrlByMac;

    @Value("${img.connect.path.windows}")
    private String uploadUrlByWindows;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String os = System.getProperty("os.name").toLowerCase();


        if (os.contains("win")) {
            System.out.println("1");
            registry.addResourceHandler("/data/view/**")
                    .addResourceLocations(uploadUrlByWindows); //리눅스 root에서 시작하는 폴더 경로
        } else if (os.contains("mac")) {
            System.out.println("2");
            registry.addResourceHandler("/data/view/**")
                    .addResourceLocations(uploadUrlByMac); //리눅스 root에서 시작하는 폴더 경로
        } else {

        }


    }
}
