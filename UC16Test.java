/**
 * UC16 Test Class - Sort Passenger Bogies by Capacity (Bubble Sort – Algorithm Intro)
 * 
 * This test class demonstrates the implementation of UC16 concepts:
 * - Bubble Sort algorithm implementation (manual sorting without Collections.sort())
 * - Array manipulation and element access using indexes
 * - Nested loop processing for multiple passes
 * - Comparison and swapping logic
 * - Algorithmic thinking and time complexity awareness (O(n²))
 */

public class UC16Test {
    
    /**
     * Bubble Sort Algorithm Implementation
     * Sorts an array of integers in ascending order using comparison and swapping
     * 
     * @param arr the array of passenger bogie capacities to sort
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        // Outer loop for multiple passes
        for (int i = 0; i < n - 1; i++) {
            // Inner loop for comparing adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {
                    // Swap if left element is greater than right element
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * Display array contents in a formatted manner
     * 
     * @param arr the array to display
     * @param label the label for the array
     */
    public static void displayArray(int[] arr, String label) {
        System.out.print(label + ": [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    /**
     * Test Case 1: Basic Sorting Behavior
     * Verifies that the Bubble Sort algorithm correctly sorts a typical unsorted array.
     * Tests: Array {72, 56, 24, 70, 60} becomes {24, 56, 60, 70, 72}.
     */
    public static void testSort_BasicSorting() {
        System.out.println("\n========== Test Case 1: Basic Sorting Behavior ==========");
        int[] capacities = {72, 56, 24, 70, 60};
        int[] expected = {24, 56, 60, 70, 72};
        
        displayArray(capacities, "Original");
        bubbleSort(capacities);
        displayArray(capacities, "Sorted  ");
        
        if (java.util.Arrays.equals(capacities, expected)) {
            System.out.println("✓ PASS: Array correctly sorted to ascending order");
        } else {
            System.out.println("✗ FAIL: Array does not match expected result");
        }
    }
    
    /**
     * Test Case 2: Already Sorted Array
     * Verifies that an already sorted array remains unchanged after sorting.
     * Tests: {24, 56, 60, 70, 72} remains {24, 56, 60, 70, 72}.
     */
    public static void testSort_AlreadySortedArray() {
        System.out.println("\n========== Test Case 2: Already Sorted Array ==========");
        int[] capacities = {24, 56, 60, 70, 72};
        int[] expected = {24, 56, 60, 70, 72};
        
        displayArray(capacities, "Original");
        bubbleSort(capacities);
        displayArray(capacities, "Sorted  ");
        
        if (java.util.Arrays.equals(capacities, expected)) {
            System.out.println("✓ PASS: Already sorted array preserved");
        } else {
            System.out.println("✗ FAIL: Sorted array does not match expected result");
        }
    }
    
    /**
     * Test Case 3: Duplicate Capacity Handling
     * Verifies that duplicate capacities are handled correctly during sorting.
     * Tests: {72, 56, 56, 24} becomes {24, 56, 56, 72}.
     */
    public static void testSort_DuplicateValues() {
        System.out.println("\n========== Test Case 3: Duplicate Capacity Handling ==========");
        int[] capacities = {72, 56, 56, 24};
        int[] expected = {24, 56, 56, 72};
        
        displayArray(capacities, "Original");
        bubbleSort(capacities);
        displayArray(capacities, "Sorted  ");
        
        if (java.util.Arrays.equals(capacities, expected)) {
            System.out.println("✓ PASS: Duplicate values handled correctly");
        } else {
            System.out.println("✗ FAIL: Duplicate values not sorted correctly");
        }
    }
    
    /**
     * Test Case 4: Single Element Array Handling
     * Verifies that sorting a single element array does not modify the array.
     * Tests: {50} remains {50}.
     */
    public static void testSort_SingleElementArray() {
        System.out.println("\n========== Test Case 4: Single Element Array Handling ==========");
        int[] capacities = {50};
        int[] expected = {50};
        
        displayArray(capacities, "Original");
        bubbleSort(capacities);
        displayArray(capacities, "Sorted  ");
        
        if (java.util.Arrays.equals(capacities, expected)) {
            System.out.println("✓ PASS: Single element array unchanged");
        } else {
            System.out.println("✗ FAIL: Single element array modified");
        }
    }
    
    /**
     * Test Case 5: All Equal Values
     * Verifies that arrays containing identical values remain unchanged.
     * Tests: {40, 40, 40} remains {40, 40, 40}.
     */
    public static void testSort_AllEqualValues() {
        System.out.println("\n========== Test Case 5: All Equal Values ==========");
        int[] capacities = {40, 40, 40};
        int[] expected = {40, 40, 40};
        
        displayArray(capacities, "Original");
        bubbleSort(capacities);
        displayArray(capacities, "Sorted  ");
        
        if (java.util.Arrays.equals(capacities, expected)) {
            System.out.println("✓ PASS: All equal values handled correctly");
        } else {
            System.out.println("✗ FAIL: Equal values array not preserved");
        }
    }
    
    /**
     * Test Case 6: Reverse Sorted Array
     * Additional test: Verifies that reverse-sorted array is correctly sorted.
     * Tests: {72, 70, 60, 56, 24} becomes {24, 56, 60, 70, 72}.
     */
    public static void testSort_ReverseOrderArray() {
        System.out.println("\n========== Test Case 6: Reverse Sorted Array ==========");
        int[] capacities = {72, 70, 60, 56, 24};
        int[] expected = {24, 56, 60, 70, 72};
        
        displayArray(capacities, "Original");
        bubbleSort(capacities);
        displayArray(capacities, "Sorted  ");
        
        if (java.util.Arrays.equals(capacities, expected)) {
            System.out.println("✓ PASS: Reverse sorted array correctly sorted");
        } else {
            System.out.println("✗ FAIL: Reverse sorted array not handled correctly");
        }
    }
    
    /**
     * Test Case 7: Large Array Sorting
     * Additional test: Verifies bubble sort works with larger datasets
     * Tests: A larger array is correctly sorted
     */
    public static void testSort_LargeArray() {
        System.out.println("\n========== Test Case 7: Large Array Sorting ==========");
        int[] capacities = {72, 24, 60, 55, 50, 65, 48, 58, 30, 80};
        int[] expected = java.util.Arrays.copyOf(capacities, capacities.length);
        java.util.Arrays.sort(expected);
        
        displayArray(capacities, "Original");
        bubbleSort(capacities);
        displayArray(capacities, "Sorted  ");
        
        if (java.util.Arrays.equals(capacities, expected)) {
            System.out.println("✓ PASS: Large array correctly sorted");
        } else {
            System.out.println("✗ FAIL: Large array not sorted correctly");
        }
    }
    
    /**
     * Demonstrate bubble sort algorithm step-by-step
     */
    public static void demonstrateBubbleSortProcess() {
        System.out.println("\n========== Bubble Sort Algorithm Demonstration ==========");
        int[] capacities = {72, 56, 24, 70};
        System.out.println("Original Array: " + java.util.Arrays.toString(capacities));
        System.out.println("\nBubble Sort Process:");
        
        int n = capacities.length;
        int passNum = 0;
        
        for (int i = 0; i < n - 1; i++) {
            passNum++;
            System.out.println("\nPass " + passNum + ":");
            
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("  Compare " + capacities[j] + " and " + capacities[j + 1]);
                
                if (capacities[j] > capacities[j + 1]) {
                    // Swap
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                    System.out.println(" -> SWAP -> " + java.util.Arrays.toString(capacities));
                } else {
                    System.out.println(" -> No swap needed");
                }
            }
        }
        
        System.out.println("\nFinal Sorted Array: " + java.util.Arrays.toString(capacities));
    }
    
    /**
     * Run all UC16 test cases
     */
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  UC16 Test Suite - Bubble Sort Algorithm");
        System.out.println("  Sort Passenger Bogies by Capacity");
        System.out.println("=============================================");
        
        testSort_BasicSorting();
        testSort_AlreadySortedArray();
        testSort_DuplicateValues();
        testSort_SingleElementArray();
        testSort_AllEqualValues();
        testSort_ReverseOrderArray();
        testSort_LargeArray();
        
        demonstrateBubbleSortProcess();
        
        System.out.println("\n=============================================");
        System.out.println("  All Tests Completed Successfully");
        System.out.println("=============================================");
    }
}
