package com.example.awsuploadimage.profile;

import com.example.awsuploadimage.bucket.BucketName;
import com.example.awsuploadimage.filestore.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.apache.http.entity.ContentType.IMAGE_GIF;
import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        ProfileUser user = getUserProfileOrThrow(userProfileId);
        String path = String.format("%s/%s"
                , BucketName.PROFILE_IMAGE.getBucketName()
                , user.getUserProfileId()
        );
       return  user.getUserProfileImageLink().map(key -> fileStore.download(path, key)).orElse(new byte[0]);
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private ProfileUser getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(useProfile -> useProfile.getUserProfileId().equals(userProfileId))
                .findFirst().orElseThrow(
                        () -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    List<ProfileUser> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    private void isFileProperly(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {

        this.isFileEmpty(file);

        this.isFileProperly(file);

        ProfileUser user = this.getUserProfileOrThrow(userProfileId);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path, filename, Optional.of(this.extractMetaData(file)), file.getInputStream());
            user.setUserProfileImageLink(Optional.of(filename));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
