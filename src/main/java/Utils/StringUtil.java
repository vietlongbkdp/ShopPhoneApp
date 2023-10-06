package Utils;

public class StringUtil {
    public static String convertDateTimeFormat(String inputDateTime) {
        String[] parts = inputDateTime.split(" ");
        String datePart = parts[0];
        String timePart = parts[1];

        String[] dateParts = datePart.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];

        String[] timeParts = timePart.split(":");
        String hour = timeParts[0];
        String minute = timeParts[1];

        String outputDateTime = day + "-" + month + "-" + year + " " + hour + ":" + minute;

        return outputDateTime;
    }
}
