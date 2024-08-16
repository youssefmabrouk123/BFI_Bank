////package com.BFI_Bank.Account_Managment_Service.service;
////
////
////import com.BFI_Bank.Account_Managment_Service.model.Rdv;
////import com.BFI_Bank.Account_Managment_Service.repository.RdvRepository;
////import jakarta.persistence.criteria.CriteriaBuilder;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.time.LocalDateTime;
////import java.time.ZoneId;
////import java.util.Date;
////import java.util.UUID;
////
////@Service
////public class RdvService {
////
////    @Autowired
////    private RdvRepository rdvRepository;
////
////    public Rdv createRdv(Integer idClient) {
////        Rdv rdv = new Rdv();
////        rdv.setIdClient(idClient);
////        Date date = new Date();
////        LocalDateTime localDateTime = date.toInstant()
////                .atZone(ZoneId.systemDefault())
////                .toLocalDateTime();
////        rdv.setDate(localDateTime);  // You can customize the date as needed
////        rdv.setLienMeet(generateGoogleMeetLink());
////        return rdvRepository.save(rdv);
////    }
////
////    private String generateGoogleMeetLink() {
////        // Placeholder for actual Google Meet link generation logic
////        return "https://meet.google.com/" + UUID.randomUUID().toString();
////    }
////}
//
//
//
//
//package com.BFI_Bank.Account_Managment_Service.service;
//
//import com.BFI_Bank.Account_Managment_Service.model.Rdv;
//import com.BFI_Bank.Account_Managment_Service.repository.RdvRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class RdvService {
//
//    @Autowired
//    private RdvRepository rdvRepository;
//
//    public Rdv createRdv(Integer clientId) {
//        Rdv rdv = new Rdv();
//        rdv.setIdClient(clientId);
//        rdv.setDate(LocalDateTime.now().plusDays(7)); // Example: setting the appointment 7 days from now
//        return rdvRepository.save(rdv);
//    }
//
//    public Rdv updateRdv(Rdv rdv) {
//        return rdvRepository.save(rdv);
//    }
//}



package com.BFI_Bank.Account_Managment_Service.service;

import com.BFI_Bank.Account_Managment_Service.model.Rdv;
import com.BFI_Bank.Account_Managment_Service.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RdvService {

    @Autowired
    private RdvRepository rdvRepository;

    public List<LocalDateTime> getAvailableSlots() {
        List<LocalDateTime> slots = new ArrayList<>();
        LocalDateTime startOfWeek = LocalDateTime.now().with(DayOfWeek.MONDAY).withHour(9).withMinute(0);
        LocalDateTime endOfWeek = startOfWeek.plusDays(7).withHour(15).withMinute(0);

        LocalDateTime slot = startOfWeek;

        while (slot.isBefore(endOfWeek)) {
            if (isWeekday(slot) && isWithinAvailableHours(slot)) {
                if (!rdvRepository.existsByDate(slot)) {
                    slots.add(slot);
                }
            }

            // Incrémente le créneau de 15 minutes
            slot = slot.plusMinutes(60);

            // Réinitialise l'heure à 9h si on dépasse 15h
            if (slot.getHour() == 15 && slot.getMinute() > 0) {
                slot = slot.plusDays(1).withHour(9).withMinute(0);
            }
        }

        return slots;
    }

    private boolean isWeekday(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private boolean isWithinAvailableHours(LocalDateTime dateTime) {
        return dateTime.getHour() >= 9 && dateTime.getHour() < 15;
    }

    public Rdv createRdv(Long clientId, LocalDateTime selectedDate) {
        if (rdvRepository.existsByDate(selectedDate)) {
            throw new IllegalArgumentException("Selected slot is already booked.");
        }
        Rdv rdv = new Rdv();
        rdv.setIdClient(clientId);
        rdv.setDate(selectedDate);
        rdv.setLienMeet(generateGoogleMeetLink());
        return rdvRepository.save(rdv);
    }

    private String generateGoogleMeetLink() {
        return "https://meet.google.com/" + UUID.randomUUID().toString();
    }

    public static String formatIsoDateTime(String isoDateTime) {
        // Define the formatter for the input ISO 8601 date-time string
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;

        // Parse the input date-time string into a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.parse(isoDateTime, isoFormatter);

        // Define the formatter for the desired output format
        DateTimeFormatter humanReadableFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a");

        // Format the LocalDateTime object into the desired output format
        return dateTime.format(humanReadableFormatter);
    }

    public List<Rdv> getAllRdv() {
        return rdvRepository.findAll();
    }

    public Optional<Rdv> setRdvDone(Long id) {
        Optional<Rdv> rdvOptional = rdvRepository.findById(id);
        rdvOptional.ifPresent(rdv -> {
            rdv.setDone(true);
            rdvRepository.save(rdv);
        });
        return rdvOptional;
    }


}
