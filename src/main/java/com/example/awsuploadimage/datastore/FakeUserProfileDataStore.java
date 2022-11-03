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
        USER_PROFILES.add(new ProfileUser(UUID.fromString("50710999-2488-44ce-92b6-ec6ca00c6769"), "mucahitBayar", null));
        USER_PROFILES.add(new ProfileUser(UUID.fromString("1fc14223-cf2c-44be-97a4-4d83f332eeb2"), "antonioJunior", null));
    }

    public List<ProfileUser> getUserProfiles() {
        return USER_PROFILES;
    }
}
