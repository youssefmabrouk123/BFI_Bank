//package com.BFI_Bank.Account_Managment_Service.service;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.services.calendar.Calendar;
//import com.google.api.services.calendar.CalendarScopes;
//import com.google.api.services.calendar.model.Event;
//import com.google.api.services.calendar.model.EventDateTime;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.util.DateTime;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Collections;
//
//@Service
//public class GoogleCalendarService {
//
//    @Value("${google.credentials.path}")
//    private String credentialsPath;
//
//    private Calendar getCalendarService() throws IOException {
//        // Load client secrets.
//        HttpTransport httpTransport = new NetHttpTransport();
//        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//
//        GoogleCredential credential;
//        try (FileInputStream in = new FileInputStream(credentialsPath)) {
//            credential = GoogleCredential.fromStream(in)
//                    .createScoped(Collections.singleton(CalendarScopes.CALENDAR));
//        }
//
//        // Build a new authorized API client service.
//        return new Calendar.Builder(httpTransport, jsonFactory, credential)
//                .setApplicationName("BFI Bank Application")
//                .build();
//    }
//
//    public String createGoogleMeetLink(String summary, String description, DateTime startDateTime, DateTime endDateTime) throws IOException {
//        Calendar service = getCalendarService();
//
//        Event event = new Event()
//                .setSummary(summary)
//                .setDescription(description);
//
//        EventDateTime start = new EventDateTime().setDateTime(startDateTime).setTimeZone("America/Los_Angeles");
//        event.setStart(start);
//
//        EventDateTime end = new EventDateTime().setDateTime(endDateTime).setTimeZone("America/Los_Angeles");
//        event.setEnd(end);
//
//        // Create the event on the user's calendar.
//        event = service.events().insert("primary", event).execute();
//
//        // Print the event details.
//        return event.getHangoutLink();
//    }
//}
