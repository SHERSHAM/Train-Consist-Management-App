import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

class PassengerBogie {
    String name;
    int capacity;

    PassengerBogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }

    public String toString() {
        return name + " -> " + capacity;
    }
}

class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String toString() {
        return name + " -> " + capacity;
    }
}

class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    GoodsBogie(String type) {
        this(type, null);
    }

    @Override
    public String toString() {
        return type + " bogie carries " + (cargo == null ? "no cargo" : cargo);
    }
}

public class TrainApp {
    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        bogieIds.add("BG103");
        bogieIds.add("BG102");

        System.out.println("Unique Bogie IDs: " + bogieIds);

        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        System.out.println("After adding bogies: " + passengerBogies);

        passengerBogies.remove("AC Chair");

        System.out.println("After removing AC Chair: " + passengerBogies);

        boolean exists = passengerBogies.contains("Sleeper");
        System.out.println("Is Sleeper present? " + exists);

        System.out.println("Final bogie list: " + passengerBogies);

        LinkedList<String> trainConsist = new LinkedList<>();
        trainConsist.add("Engine");
        trainConsist.add("Sleeper");
        trainConsist.add("AC");
        trainConsist.add("Cargo");
        trainConsist.add("Guard");

        trainConsist.add(2, "Pantry Car");

        trainConsist.removeFirst();
        trainConsist.removeLast();

        System.out.println("Final Train Consist: " + trainConsist);

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("Final Formation (Ordered & Unique): " + formation);

        HashMap<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 24);

        System.out.println("Bogie Capacity Details:");
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 60));
        bogieList.add(new Bogie("First Class", 24));
        bogieList.add(new Bogie("Sleeper", 72));

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("Sorted Bogies by Capacity:");
        for (Bogie b : bogieList) {
            System.out.println(b);
        }

        List<Bogie> filteredBogies = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("Filtered Bogies (Capacity > 60):");
        for (Bogie b : filteredBogies) {
            System.out.println(b);
        }

        Map<String, List<Bogie>> grouped = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("Grouped Bogies:");
        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        int totalSeats = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seating Capacity: " + totalSeats);

        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        System.out.println("Train ID Valid: " + trainMatcher.matches());
        System.out.println("Cargo Code Valid: " + cargoMatcher.matches());

        List<GoodsBogie> goodsList = new ArrayList<>();
        goodsList.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsList.add(new GoodsBogie("Box", "Coal"));
        goodsList.add(new GoodsBogie("Open", "Grain"));

        boolean isSafe = goodsList.stream()
                .allMatch(g -> !g.type.equals("Cylindrical") || g.cargo.equals("Petroleum"));

        System.out.println("Safety Compliance: " + isSafe);

        long startLoop = System.nanoTime();

        List<Bogie> loopFiltered = new ArrayList<>();
        for (Bogie b : bogieList) {
            if (b.capacity > 60) {
                loopFiltered.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        long startStream = System.nanoTime();

        List<Bogie> streamFiltered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        System.out.println("Loop Filtering Time: " + loopTime + " ns");
        System.out.println("Stream Filtering Time: " + streamTime + " ns");

        System.out.println("Loop Result Size: " + loopFiltered.size());
        System.out.println("Stream Result Size: " + streamFiltered.size());

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

        System.out.println("\n--- UC15 Safe Cargo Assignment ---");

        GoodsBogie rectangularBogie = new GoodsBogie("Rectangular");
        GoodsBogie cylindricalBogie = new GoodsBogie("Cylindrical");

        safeAssignCargo(rectangularBogie, "Petroleum");
        safeAssignCargo(cylindricalBogie, "Petroleum");
        safeAssignCargo(rectangularBogie, "Coal");

        System.out.println("\nFinal bogie status:");
        System.out.println(rectangularBogie);
        System.out.println(cylindricalBogie);
        System.out.println("\nProgram continued safely after exception handling.");

        System.out.println("\n--- UC17 Sort Bogie Names Using Arrays.sort() ---");

        String[] bogieTypes = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        System.out.println("\nOriginal Bogie Names: " + Arrays.toString(bogieTypes));

        Arrays.sort(bogieTypes);
        System.out.println("Sorted Bogie Names: " + Arrays.toString(bogieTypes));

        System.out.println("\n--- UC17 Additional Sorting Examples ---");

        String[] unsortedBogies = {"Luxury", "General", "Sleeper", "AC Chair"};
        System.out.println("\nUnsorted Bogies: " + Arrays.toString(unsortedBogies));
        Arrays.sort(unsortedBogies);
        System.out.println("Sorted Bogies: " + Arrays.toString(unsortedBogies));

        String[] duplicateBogies = {"Sleeper", "AC Chair", "Sleeper", "General"};
        System.out.println("\nBogie Names with Duplicates: " + Arrays.toString(duplicateBogies));
        Arrays.sort(duplicateBogies);
        System.out.println("Sorted (Duplicates Preserved): " + Arrays.toString(duplicateBogies));

        System.out.println("\n--- UC17 Performance: Arrays.sort() vs Manual Sorting ---");
        System.out.println("Arrays.sort() uses optimized dual-pivot quicksort/TimSort");
        System.out.println("Time Complexity: O(n log n) - much faster than Bubble Sort O(n²)");
        System.out.println("Library Benefit: Highly optimized, tested, and maintained");
        System.out.println("Code Quality: Clean, readable, and production-ready");
    }

    private static void safeAssignCargo(GoodsBogie bogie, String cargo) {
        try {
            System.out.println("\nAttempting to assign cargo: " + cargo + " to " + bogie.type + " bogie");
            validateCargoAssignment(bogie.type, cargo);
            bogie.cargo = cargo;
            System.out.println("Cargo assigned successfully: " + bogie);
        } catch (CargoSafetyException e) {
            System.out.println("Unsafe cargo assignment detected: " + e.getMessage());
        } finally {
            System.out.println("Cargo assignment validation completed for " + bogie.type + " bogie.");
        }
    }

    private static void validateCargoAssignment(String type, String cargo) {
        if ("Rectangular".equalsIgnoreCase(type) && "Petroleum".equalsIgnoreCase(cargo)) {
            throw new CargoSafetyException("Petroleum cannot be assigned to a Rectangular bogie.");
        }
    }
}
