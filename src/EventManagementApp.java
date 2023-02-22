import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventManagementApp {
    final static List<Event> events = new ArrayList<>();
    final static Scanner scanner = new Scanner(System.in);
    final static int nextEventId = 1;
    private static int nextAttendeeId = 1;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("Select an option:");
            System.out.println("1. List all events");
            System.out.println("2. List an individual event");
            System.out.println("3. Edit an event");
            System.out.println("4. Delete an event");
            System.out.println("5. List attendees for an event");
            System.out.println("6. Add an attendee to an event");
            System.out.println("7. Delete an attendee from an event");
            System.out.println("0. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> listAllEvents();
                case 2 -> listIndividualEvent();
                case 3 -> editEvent();
                case 4 -> deleteEvent();
                case 5 -> listAttendees();
                case 6 -> addAttendee();
                case 7 -> deleteAttendee();
                case 0 -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    private static void listAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            System.out.println("Events:");
            for (Event event : events) {
                System.out.println(event.getId() + " - " + event.getName() + " (" + event.getDate() + ")");
            }
        }
    }

    private static void listIndividualEvent() {
        System.out.println("Enter event ID:");
        int eventId = scanner.nextInt();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            System.out.println(event.getId() + " - " + event.getName() + " (" + event.getDate() + ")");
        }
    }

    private static void editEvent() {
        System.out.println("Enter event ID:");
        int eventId = scanner.nextInt();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            System.out.println("Enter new name (or press Enter to keep current name):");
            String newName = scanner.nextLine();
            if (newName.equals("")) {
                newName = event.getName();
            }

            System.out.println("Enter new date (or press Enter to keep current date):");
            LocalDate newDate = LocalDate.parse(scanner.next());
            if (newDate.equals("")) {
                newDate = event.getDate();
            }

            event.setName(newName);
            event.setDate(newDate);

            System.out.println("Event updated.");
        }
    }

    private static void deleteEvent() {
        System.out.println("Enter event ID:");
        int eventId = scanner.nextInt();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            events.remove(event);
            System.out.println("Event deleted.");
        }
    }

    private static void listAttendees() {
        System.out.println("Enter event ID:");
        int eventId = scanner.nextInt();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            List<Attendee> attendees = event.getAttendees();
            if (attendees.isEmpty()) {
                System.out.println("No attendees found for this event.");
            } else {
                System.out.println("Attendees for " + event.getName() + ":");
                for (Attendee attendee : attendees) {
                    System.out.println(attendee.getId() + " - " + attendee.getName());
                }
            }
        }
    }

    private static void addAttendee() {
        System.out.println("Enter event ID:");
        int eventId = scanner.nextInt();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            System.out.println("Enter attendee name:");
            String attendeeName = scanner.nextLine();
            String attendeeEmail = scanner.nextLine();
            if (attendeeName.equals("")) {
                attendeeName = scanner.nextLine();
            }

            Attendee attendee = new Attendee(nextAttendeeId, attendeeName , attendeeEmail);
            nextAttendeeId++;
            event.addAttendee(attendee);

            System.out.println("Attendee added to " + event.getName() + ".");
        }
    }

    private static void deleteAttendee() {
        System.out.println("Enter event ID:");
        int eventId = scanner.nextInt();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found.");
        } else {
            System.out.println("Enter attendee ID:");
            int attendeeId = scanner.nextInt();

            Attendee attendee = event.findAttendee(attendeeId);

            if (attendee == null) {
                System.out.println("Attendee not found for this event.");
            } else {
                event.removeAttendee(attendee);
                System.out.println("Attendee removed from " + event.getName() + ".");
            }
        }
    }

    private static Event findEvent(int eventId) {
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }
}
