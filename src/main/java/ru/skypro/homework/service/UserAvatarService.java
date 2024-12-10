package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.UserAvatarException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.model.UserAvatar;
import ru.skypro.homework.repository.UserAvatarRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserAvatarService {

    private final ContextService contextService;
    private final UserAvatarRepository userAvatarRepository;
    private final Path path;

    public UserAvatarService(ContextService contextService,
                             UserAvatarRepository userAvatarRepository,
                             @Value("${application.avatars-dir-name}") String avatarsDirName) {
        this.contextService = contextService;
        this.userAvatarRepository = userAvatarRepository;
        this.path = Path.of(avatarsDirName);
    }

    public void saveAvatarForUser(User user) throws IOException {
        String defaultUserAvatarPath = "defaultUserAvatar/java.png";
        Path pathDefaultAvatar = Paths.get(defaultUserAvatarPath);
        byte[] data = Files.readAllBytes(pathDefaultAvatar);
        String extension = getFileExtension(pathDefaultAvatar.getFileName().toString());
        Path avatarsPathNew = path.resolve(UUID.randomUUID() + "." + extension);

        Files.write(avatarsPathNew, data);

        UserAvatar userAvatar = new UserAvatar();
        userAvatar.setFilePath(avatarsPathNew.toString());
        userAvatar.setPathForEndpoint("/avatar/" + user.getId());
        userAvatar.setMediaType("image/png");
        userAvatar.setUser(user);

        userAvatarRepository.save(userAvatar);
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        }

        return fileName.substring(dotIndex + 1);
    }

    public void updateUserAvatar(MultipartFile image) throws IOException {
        User user = contextService.getRecognizedUserFromDb();

        UserAvatar userAvatarDb = userAvatarRepository.findByUserId(user.getId())
                .orElseThrow(() -> new UserAvatarException("Не найден аватар для пользователя: " + user.getId()));

        deleteExistingAvatar(userAvatarDb);

        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path avatarsPath = path.resolve(UUID.randomUUID() + "." + extension);
        Files.write(avatarsPath, image.getBytes());

        userAvatarDb.setFilePath(avatarsPath.toString());
        userAvatarDb.setMediaType(image.getContentType());

        userAvatarRepository.save(userAvatarDb);
    }

    private void deleteExistingAvatar(UserAvatar userAvatar) throws IOException {
        Path existingPath = Paths.get(userAvatar.getFilePath());

        if (Files.exists(existingPath)) {
            Files.delete(existingPath);
        }
    }

    public Pair<byte[], String> getAvatarFromFs(String pathForEndpoint) throws IOException {
        Integer userId = Integer.parseInt(pathForEndpoint);

        UserAvatar userAvatar = userAvatarRepository.findByUserId(userId)
                .orElseThrow(() -> new UserAvatarException("Не найден аватар для пользователя с ID: " + userId));

        byte[] avatarData = Files.readAllBytes(Paths.get(userAvatar.getFilePath()));

        return Pair.of(avatarData, userAvatar.getMediaType());
    }
}
