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

        System.out.println("\n--- UC18 Linear Search for Bogie ID (Array-Based Searching) ---");

        String[] searchBogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        System.out.println("\nBogie IDs in consist: " + Arrays.toString(searchBogieIds));

        String searchKey = "BG309";
        System.out.println("Searching for bogie ID: " + searchKey);

        boolean found = false;
        int position = -1;

        for (int i = 0; i < searchBogieIds.length; i++) {
            if (searchBogieIds[i].equals(searchKey)) {
                found = true;
                position = i;
                break;
            }
        }

        if (found) {
            System.out.println("✓ Bogie ID '" + searchKey + "' found at position " + position);
        } else {
            System.out.println("✗ Bogie ID '" + searchKey + "' not found in the consist");
        }

        System.out.println("\n--- UC18 Additional Search Examples ---");

        String[] searchKeys = {"BG999", "BG101", "BG550", "BG205"};
        for (String key : searchKeys) {
            boolean keyFound = false;
            int keyPosition = -1;

            for (int i = 0; i < searchBogieIds.length; i++) {
                if (searchBogieIds[i].equals(key)) {
                    keyFound = true;
                    keyPosition = i;
                    break;
                }
            }

            System.out.println("Searching for '" + key + "': " +
                (keyFound ? "Found at position " + keyPosition : "Not found"));
        }

        System.out.println("\n--- UC18 Single Element Search ---");
        String[] singleBogie = {"BG101"};
        String singleSearch = "BG101";
        boolean singleFound = false;

        for (int i = 0; i < singleBogie.length; i++) {
            if (singleBogie[i].equals(singleSearch)) {
                singleFound = true;
                break;
            }
        }

        System.out.println("Single bogie search for '" + singleSearch + "': " +
            (singleFound ? "Found" : "Not found"));

        System.out.println("\n--- UC18 Performance Characteristics ---");
        System.out.println("Linear Search: O(n) time complexity");
        System.out.println("Best case: O(1) - element found at first position");
        System.out.println("Worst case: O(n) - element found at last position or not found");
        System.out.println("Works on unsorted data: No preprocessing required");
        System.out.println("Simple and reliable: Easy to implement and understand");

        System.out.println("\n--- UC19 Binary Search for Bogie ID (Optimized Searching) ---");

        String[] sortedBogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        System.out.println("\nSorted Bogie IDs: " + Arrays.toString(sortedBogieIds));

        String binarySearchKey = "BG309";
        System.out.println("Binary searching for bogie ID: " + binarySearchKey);

        boolean binaryFound = false;
        int binaryPosition = -1;
        int low = 0;
        int high = sortedBogieIds.length - 1;
        int iterations = 0;

        while (low <= high) {
            iterations++;
            int mid = low + (high - low) / 2;
            int comparison = binarySearchKey.compareTo(sortedBogieIds[mid]);

            System.out.println("Iteration " + iterations + ": low=" + low + ", high=" + high + ", mid=" + mid +
                ", mid_value='" + sortedBogieIds[mid] + "', comparison=" + comparison);

            if (comparison == 0) {
                binaryFound = true;
                binaryPosition = mid;
                break;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (binaryFound) {
            System.out.println("✓ Bogie ID '" + binarySearchKey + "' found at position " + binaryPosition +
                " (took " + iterations + " iterations)");
        } else {
            System.out.println("✗ Bogie ID '" + binarySearchKey + "' not found (took " + iterations + " iterations)");
        }

        System.out.println("\n--- UC19 Additional Binary Search Examples ---");

        String[] binarySearchKeys = {"BG999", "BG101", "BG550", "BG205", "BG412"};
        for (String key : binarySearchKeys) {
            boolean keyFound = false;
            int keyPosition = -1;
            int keyLow = 0;
            int keyHigh = sortedBogieIds.length - 1;
            int keyIterations = 0;

            while (keyLow <= keyHigh) {
                keyIterations++;
                int mid = keyLow + (keyHigh - keyLow) / 2;
                int comparison = key.compareTo(sortedBogieIds[mid]);

                if (comparison == 0) {
                    keyFound = true;
                    keyPosition = mid;
                    break;
                } else if (comparison < 0) {
                    keyHigh = mid - 1;
                } else {
                    keyLow = mid + 1;
                }
            }

            System.out.println("Binary search for '" + key + "': " +
                (keyFound ? "Found at position " + keyPosition + " (" + keyIterations + " iterations)" :
                 "Not found (" + keyIterations + " iterations)"));
        }

        System.out.println("\n--- UC19 Edge Cases ---");

        String[] singleElement = {"BG101"};
        String binarySingleSearch = "BG101";
        boolean binarySingleFound = binarySearch(singleElement, binarySingleSearch);
        System.out.println("Single element search for '" + binarySingleSearch + "' in " +
            Arrays.toString(singleElement) + ": " + (binarySingleFound ? "Found" : "Not found"));

        String[] emptyArray = {};
        String binaryEmptySearch = "BG101";
        boolean binaryEmptyFound = binarySearch(emptyArray, binaryEmptySearch);
        System.out.println("Empty array search for '" + binaryEmptySearch + "' in " +
            Arrays.toString(emptyArray) + ": " + (binaryEmptyFound ? "Found" : "Not found"));

        String[] unsortedInput = {"BG309", "BG101", "BG550", "BG205", "BG412"};
        System.out.println("\nUnsorted input: " + Arrays.toString(unsortedInput));
        Arrays.sort(unsortedInput);
        System.out.println("Sorted for binary search: " + Arrays.toString(unsortedInput));

        String binaryUnsortedSearch = "BG205";
        boolean binaryUnsortedFound = binarySearch(unsortedInput, binaryUnsortedSearch);
        System.out.println("Binary search for '" + binaryUnsortedSearch + "' in sorted array: " +
            (binaryUnsortedFound ? "Found" : "Not found"));

        System.out.println("\n--- UC19 Performance Comparison ---");
        System.out.println("Binary Search: O(log n) time complexity");
        System.out.println("Linear Search: O(n) time complexity");
        System.out.println("For n=1000 elements:");
        System.out.println("  - Linear Search: ~1000 comparisons (worst case)");
        System.out.println("  - Binary Search: ~10 comparisons (worst case)");
        System.out.println("Efficiency gain: 100x faster for large datasets");
        System.out.println("Precondition: Data must be sorted");
        System.out.println("Divide-and-conquer: Search space halved each iteration");
    }

    private static boolean binarySearch(String[] arr, String key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = key.compareTo(arr[mid]);

            if (comparison == 0) {
                return true;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
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
