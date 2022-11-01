package com.example.awsuploadimage.bucket;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BucketName {
    PROFILE_IMAGE("opensource-aws-image-upload");

    private final String bucketName;
}
