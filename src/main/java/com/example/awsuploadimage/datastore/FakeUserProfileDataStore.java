package com.example.awsuploadimage.datastore;

import com.example.awsuploadimage.profile.ProfileUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<ProfileUser> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new ProfileUser(UUID.randomUUID(), "mucahitBayar", null));
        USER_PROFILES.add(new ProfileUser(UUID.randomUUID(), "antonioJunior", null));
    }

    public List<ProfileUser> getUserProfiles() {
        return USER_PROFILES;
    }
}
