package com.javarush.jira.profile.web;

import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProfileTestData {
    public static final Long USER_ID = 1L;

    public static final Set<String> MAIL_NOTIFICATIONS = Stream.of("assigned", "deadline")
            .collect(Collectors.toCollection(HashSet::new));

    public static final Set<ContactTo> CONTACTS = Stream.of(new ContactTo("website", "newUserWebsiteValue"))
            .collect(Collectors.toCollection(HashSet::new));

    public static final ProfileTo UPDATED_TO = new ProfileTo(USER_ID, MAIL_NOTIFICATIONS, CONTACTS);
}
