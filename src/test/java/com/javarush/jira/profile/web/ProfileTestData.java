package com.javarush.jira.profile.web;

import com.javarush.jira.MatcherFactory;
import com.javarush.jira.profile.internal.Contact;
import com.javarush.jira.profile.internal.Profile;

import java.util.HashSet;

public class ProfileTestData {
    public static final MatcherFactory.Matcher<Profile> PROFILE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(
            Profile.class, "lastFailedLogin", "mailNotifications", "contacts");

    public static final Long PROFILE_ID = 1L;

    public static final Profile userProfile = new Profile(PROFILE_ID);

    static {
        //"mailNotifications":["assigned","overdue","deadline"]
        HashSet<Contact> contacts = new HashSet<>();
        contacts.add(new Contact(1, "mobile", "+01234567890"));
        contacts.add(new Contact(1, "skype", "userSkype"));
        contacts.add(new Contact(1, "website", "user.com"));

        userProfile.setLastLogin(null);
        userProfile.setLastFailedLogin(null);
        userProfile.setMailNotifications(49);
        userProfile.setContacts(contacts);
    }
}
