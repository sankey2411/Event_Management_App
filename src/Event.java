import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Event {
    private int id;
    private String name;
    private LocalDate date;

    private List<Attendee> attendees;

    public Event(int id, String name) {
        this.id = id;
        this.name = name;
        this.attendees = new ArrayList<>();
    }

//    public String getDateString() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String eventDateString = date.format(formatter);
//        LocalDate eventDate = LocalDate.parse(eventDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        return eventDateString;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }
//
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public void removeAttendee(Attendee attendee) {
        attendees.remove(attendee);
    }

    public Attendee findAttendee(int attendeeId) {
        for (Attendee attendee : attendees) {
            if (attendee.getId() == attendeeId) {
                return attendee;
            }
        }
        return null;
    }
}
