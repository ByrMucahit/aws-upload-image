package com.example.awsuploadimage.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProfileUser {

    private UUID userProfileId;
    private String userName;
    private Optional<String> userProfileImageLink; // S3 key

}
