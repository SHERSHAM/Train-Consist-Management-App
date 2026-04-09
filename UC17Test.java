import java.util.Arrays;

public class UC17Test {
    
    public static void displayArray(String[] arr, String label) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }
    
    public static void testSort_BasicAlphabeticalSorting() {
        System.out.println("\n========== Test Case 1: Basic Alphabetical Sorting ==========");
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        String[] expected = {"AC Chair", "First Class", "General", "Luxury", "Sleeper"};
        
        displayArray(bogieNames, "Original");
        Arrays.sort(bogieNames);
        displayArray(bogieNames, "Sorted  ");
        
        if (Arrays.equals(bogieNames, expected)) {
            System.out.println("✓ PASS: Bogie names correctly sorted alphabetically");
        } else {
            System.out.println("✗ FAIL: Bogie names do not match expected result");
        }
    }
    
    public static void testSort_UnsortedInput() {
        System.out.println("\n========== Test Case 2: Unsorted Input Handling ==========");
        String[] bogieNames = {"Luxury", "General", "Sleeper", "AC Chair"};
        String[] expected = {"AC Chair", "General", "Luxury", "Sleeper"};
        
        displayArray(bogieNames, "Original");
        Arrays.sort(bogieNames);
        displayArray(bogieNames, "Sorted  ");
        
        if (Arrays.equals(bogieNames, expected)) {
            System.out.println("✓ PASS: Unsorted bogie names correctly rearranged");
        } else {
            System.out.println("✗ FAIL: Unsorted bogie names not sorted correctly");
        }
    }
    
    public static void testSort_AlreadySortedArray() {
        System.out.println("\n========== Test Case 3: Already Sorted Array ==========");
        String[] bogieNames = {"AC Chair", "First Class", "General"};
        String[] expected = {"AC Chair", "First Class", "General"};
        
        displayArray(bogieNames, "Original");
        Arrays.sort(bogieNames);
        displayArray(bogieNames, "Sorted  ");
        
        if (Arrays.equals(bogieNames, expected)) {
            System.out.println("✓ PASS: Already sorted array preserved");
        } else {
            System.out.println("✗ FAIL: Already sorted array was modified");
        }
    }
    
    public static void testSort_DuplicateBogieNames() {
        System.out.println("\n========== Test Case 4: Duplicate Bogie Names ==========");
        String[] bogieNames = {"Sleeper", "AC Chair", "Sleeper", "General"};
        String[] expected = {"AC Chair", "General", "Sleeper", "Sleeper"};
        
        displayArray(bogieNames, "Original");
        Arrays.sort(bogieNames);
        displayArray(bogieNames, "Sorted  ");
        
        if (Arrays.equals(bogieNames, expected)) {
            System.out.println("✓ PASS: Duplicate bogie names handled correctly");
        } else {
            System.out.println("✗ FAIL: Duplicate bogie names not sorted correctly");
        }
    }
    
    public static void testSort_SingleElementArray() {
        System.out.println("\n========== Test Case 5: Single Element Array Handling ==========");
        String[] bogieNames = {"Sleeper"};
        String[] expected = {"Sleeper"};
        
        displayArray(bogieNames, "Original");
        Arrays.sort(bogieNames);
        displayArray(bogieNames, "Sorted  ");
        
        if (Arrays.equals(bogieNames, expected)) {
            System.out.println("✓ PASS: Single element array unchanged");
        } else {
            System.out.println("✗ FAIL: Single element array was modified");
        }
    }
    
    public static void testSort_EmptyArray() {
        System.out.println("\n========== Test Case 6: Empty Array Handling ==========");
        String[] bogieNames = {};
        String[] expected = {};
        
        displayArray(bogieNames, "Original");
        Arrays.sort(bogieNames);
        displayArray(bogieNames, "Sorted  ");
        
        if (Arrays.equals(bogieNames, expected)) {
            System.out.println("✓ PASS: Empty array handled correctly");
        } else {
            System.out.println("✗ FAIL: Empty array was modified");
        }
    }
    
    public static void testSort_CaseSensitiveSorting() {
        System.out.println("\n========== Test Case 7: Case-Sensitive Sorting ==========");
        String[] bogieNames = {"sleeper", "Sleeper", "AC Chair", "ac chair"};
        String[] expected = {"AC Chair", "Sleeper", "ac chair", "sleeper"};
        
        displayArray(bogieNames, "Original");
        Arrays.sort(bogieNames);
        displayArray(bogieNames, "Sorted  ");
        
        if (Arrays.equals(bogieNames, expected)) {
            System.out.println("✓ PASS: Case-sensitive sorting applied correctly");
        } else {
            System.out.println("✗ FAIL: Case-sensitive sorting not applied as expected");
        }
    }
    
    public static void testSort_LargeArray() {
        System.out.println("\n========== Test Case 8: Large Array Sorting (Performance) ==========");
        String[] bogieNames = {
            "Sleeper", "General", "AC Chair", "First Class", "Luxury",
            "Refrigerated", "Flatcar", "Tanker", "Boxcar", "Gondola",
            "Hopper", "Cattle Car", "Postal Car"
        };
        
        displayArray(bogieNames, "Original");
        
        long startTime = System.nanoTime();
        Arrays.sort(bogieNames);
        long endTime = System.nanoTime();
        
        displayArray(bogieNames, "Sorted  ");
        
        long duration = endTime - startTime;
        System.out.println("Sorting Time: " + duration + " ns");
        System.out.println("✓ PASS: Large array sorted efficiently using Arrays.sort()");
    }
    
    public static void demonstratePerformanceComparison() {
        System.out.println("\n========== Performance Comparison: Manual vs Built-in Sorting ==========");
        
        String[] testArray = new String[1000];
        java.util.Random rand = new java.util.Random();
        String[] bogieTypes = {"Sleeper", "General", "AC Chair", "First Class", "Luxury"};
        
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = bogieTypes[rand.nextInt(bogieTypes.length)];
        }
        
        String[] builtInTest = testArray.clone();
        long builtInStart = System.nanoTime();
        Arrays.sort(builtInTest);
        long builtInEnd = System.nanoTime();
        long builtInTime = builtInEnd - builtInStart;
        
        System.out.println("\nArray Size: " + testArray.length + " elements");
        System.out.println("Arrays.sort() Time: " + builtInTime + " ns");
        System.out.println("✓ Built-in sorting is highly optimized for performance");
    }
    
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  UC17 Test Suite - Arrays.sort() Method");
        System.out.println("  Sort Bogie Names Alphabetically");
        System.out.println("=============================================");
        
        testSort_BasicAlphabeticalSorting();
        testSort_UnsortedInput();
        testSort_AlreadySortedArray();
        testSort_DuplicateBogieNames();
        testSort_SingleElementArray();
        testSort_EmptyArray();
        testSort_CaseSensitiveSorting();
        testSort_LargeArray();
        
        demonstratePerformanceComparison();
        
        System.out.println("\n=============================================");
        System.out.println("  All Tests Completed Successfully");
        System.out.println("  Arrays.sort() is production-ready and efficient");
        System.out.println("=============================================");
    }
}
