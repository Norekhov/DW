package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    private static String avatarsDirName;
    private static String adImagesDirName;

    public static String getPathToAvatars() {
        return "/" + avatarsDirName + "/";
    }

    public static String getPathToAdImages() {
        return "/" + adImagesDirName + "/";
    }

    @Value("${application.avatars-dir-name}")
    public void setAvatarsDirName(String avatarsDirName) {
        ApplicationConfig.avatarsDirName = avatarsDirName;
    }

    @Value("${application.image-dir-name}")
    public void setAdImagesDirName(String adImagesDirName) {
        ApplicationConfig.adImagesDirName = adImagesDirName;
    }
}
