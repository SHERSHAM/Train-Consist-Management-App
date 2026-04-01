public class TrainApp {
    public static void main(String[] args) {
        try {
            PassengerBogie bogie1 = new PassengerBogie("Sleeper", 72);
            PassengerBogie bogie2 = new PassengerBogie("AC Chair", 60);
            PassengerBogie bogie3 = new PassengerBogie("Invalid", 0);

            System.out.println(bogie1);
            System.out.println(bogie2);
            System.out.println(bogie3);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}