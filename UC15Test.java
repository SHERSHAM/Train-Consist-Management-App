/**
 * UC15 Test Class - Safe Cargo Assignment Using try-catch-finally
 * 
 * This test class demonstrates the implementation of UC15 concepts:
 * - try-catch-finally blocks for exception handling
 * - Runtime exceptions (CargoSafetyException)
 * - Validation of cargo and shape compatibility
 * - Graceful failure handling without application crash
 * - Finally block execution for cleanup/logging
 */

public class UC15Test {
    
    /**
     * Test Case 1: Safe Cargo Assignment
     * Verifies that safe cargo assignments are processed successfully.
     * Tests: Cylindrical bogie assigned Petroleum cargo without exception.
     */
    public static void testCargo_SafeAssignment() {
        System.out.println("Test Case 1: Safe Cargo Assignment");
        GoodsBogie cylindricalBogie = new GoodsBogie("Cylindrical");
        
        try {
            validateCargoAssignment("Cylindrical", "Petroleum");
            cylindricalBogie.setCargo("Petroleum");
            System.out.println("✓ PASS: " + cylindricalBogie);
        } catch (CargoSafetyException e) {
            System.out.println("✗ FAIL: Unexpected exception - " + e.getMessage());
        } finally {
            System.out.println("Validation completed for Cylindrical bogie.\n");
        }
    }
    
    /**
     * Test Case 2: Unsafe Cargo Detection
     * Verifies that assigning petroleum to a rectangular bogie triggers safety validation.
     * Tests: CargoSafetyException is raised and handled.
     */
    public static void testCargo_UnsafeAssignmentHandled() {
        System.out.println("Test Case 2: Unsafe Cargo Detection");
        GoodsBogie rectangularBogie = new GoodsBogie("Rectangular");
        
        try {
            validateCargoAssignment("Rectangular", "Petroleum");
            System.out.println("✗ FAIL: Exception should have been thrown");
        } catch (CargoSafetyException e) {
            System.out.println("✓ PASS: Exception caught - " + e.getMessage());
        } finally {
            System.out.println("Validation completed for Rectangular bogie.\n");
        }
    }
    
    /**
     * Test Case 3: Cargo Not Assigned After Failure
     * Verifies that cargo is not assigned when an unsafe combination occurs.
     * Tests: Rectangular bogie does not store Petroleum cargo.
     */
    public static void testCargo_CargoNotAssignedAfterFailure() {
        System.out.println("Test Case 3: Cargo Not Assigned After Failure");
        GoodsBogie rectangularBogie = new GoodsBogie("Rectangular");
        
        try {
            validateCargoAssignment("Rectangular", "Petroleum");
            rectangularBogie.setCargo("Petroleum");
        } catch (CargoSafetyException e) {
            System.out.println("✓ PASS: Cargo not assigned - " + e.getMessage());
        } finally {
            if (rectangularBogie.getCargo() == null) {
                System.out.println("✓ PASS: Rectangular bogie cargo is null");
            } else {
                System.out.println("✗ FAIL: Cargo should not be assigned after exception");
            }
            System.out.println();
        }
    }
    
    /**
     * Test Case 4: Program Continues After Exception
     * Verifies that the application continues execution after exception handling.
     * Tests: Multiple cargo assignments proceed without terminating the program.
     */
    public static void testCargo_ProgramContinuesAfterException() {
        System.out.println("Test Case 4: Program Continues After Exception");
        
        GoodsBogie bogie1 = new GoodsBogie("Rectangular");
        GoodsBogie bogie2 = new GoodsBogie("Cylindrical");
     
        try {
            validateCargoAssignment("Rectangular", "Petroleum");
        } catch (CargoSafetyException e) {
            System.out.println("✓ First cargo assignment failed as expected");
        } finally {
            System.out.println("Cleanup for first assignment");
        }
        

        try {
            validateCargoAssignment("Cylindrical", "Petroleum");
            bogie2.setCargo("Petroleum");
            System.out.println("✓ PASS: Second cargo assignment succeeded");
        } catch (CargoSafetyException e) {
            System.out.println("✗ FAIL: Second assignment should not fail");
        } finally {
            System.out.println("Cleanup for second assignment\n");
        }
    }
    
    /**
     * Test Case 5: Finally Block Execution
     * Verifies that the finally block executes regardless of assignment success or failure.
     * Tests: Validation completion message executes.
     */
    public static void testCargo_FinallyBlockExecution() {
        System.out.println("Test Case 5: Finally Block Execution");
        int finallyExecutionCount = 0;
        

        try {
            validateCargoAssignment("Cylindrical", "Petroleum");
            finallyExecutionCount++;
        } catch (CargoSafetyException e) {
         
        } finally {
            System.out.println("✓ Finally block executed for successful assignment");
        }
        
  
        try {
            validateCargoAssignment("Rectangular", "Petroleum");
        } catch (CargoSafetyException e) {
            finallyExecutionCount++;
        } finally {
            System.out.println("✓ Finally block executed for failed assignment");
        }
        
        if (finallyExecutionCount == 2) {
            System.out.println("✓ PASS: Finally block executed in both cases\n");
        }
    }
    
    /**
     * Validation helper method
     */
    private static void validateCargoAssignment(String type, String cargo) {
        if ("Rectangular".equalsIgnoreCase(type) && "Petroleum".equalsIgnoreCase(cargo)) {
            throw new CargoSafetyException("Petroleum cannot be assigned to a Rectangular bogie.");
        }
    }
    
    /**
     * Run all UC15 test cases
     */
    public static void main(String[] args) {
        System.out.println("========== UC15 Test Suite ==========\n");
        
        testCargo_SafeAssignment();
        testCargo_UnsafeAssignmentHandled();
        testCargo_CargoNotAssignedAfterFailure();
        testCargo_ProgramContinuesAfterException();
        testCargo_FinallyBlockExecution();
        
        System.out.println("========== All Tests Completed ==========");
    }
}

/**
 * GoodsBogie class supporting UC15 safe cargo assignment
 */
class GoodsBogie {
    private String type;
    private String cargo;
    
    public GoodsBogie(String type) {
        this.type = type;
        this.cargo = null;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getCargo() {
        return this.cargo;
    }
    
    public String getType() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return type + " bogie carries " + (cargo == null ? "no cargo" : cargo);
    }
}
