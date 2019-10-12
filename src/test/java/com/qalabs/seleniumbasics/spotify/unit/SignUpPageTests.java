package com.qalabs.seleniumbasics.spotify.unit;

import com.qalabs.javabasics.spotify.pages.SpotifySignUpPage;
import org.testng.annotations.Test;

public class SignUpPageTests {

    @Test
    public void fillSpotiy() {
        SpotifySignUpPage spotifySignUpPage = new SpotifySignUpPage(this.driver);
    }
}
