package com.example.awsuploadimage.bucket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    PROFILE_IMAGE("opensource-aws-image-upload");

    private final String bucketName;
}
