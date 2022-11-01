package com.example.awsuploadimage.profile;

import com.example.awsuploadimage.datastore.FakeUserProfileDataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserProfileDataAccessService {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    List<ProfileUser> getUserProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }
}
