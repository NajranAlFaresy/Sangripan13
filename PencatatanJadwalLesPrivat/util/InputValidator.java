package util;

public class InputValidator {

    public static boolean isValidPelajaran(int input) {
        return input == 1 || input == 2;
    }

    public static String mapPelajaran(int input) {
        return input == 1 ? "Bahasa Inggris" : "Matematika";
    }
}
