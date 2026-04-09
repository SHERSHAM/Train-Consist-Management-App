# TEST CASE CREATION & EXECUTION IN INTELLIJ (JAVA PROJECT)

## 1. WHAT IS A TEST CASE?

A test case is a small piece of code written to check whether your program works correctly.

It works like this:
```
Input → Give some data
Expected Output → What should come
Actual Output → What your code gives

If both match → Test Passed ✅
If not → Test Failed ❌
```

## 2. WHY DO WE WRITE TEST CASES?

- ✅ To verify your code works correctly
- ✅ To catch bugs early in development
- ✅ To make future changes safely
- ✅ To ensure code quality and reliability
- ✅ To document expected behavior

## 3. WHERE TO WRITE TEST CASES IN INTELLIJ?

### Project Structure:
```
src
 ├── main
 │    └── java        (your actual code)
 └── test
      └── java        (your test cases)
```

### If 'test' folder is not present:
1. Right-click on **Project Name** in Project Explorer
2. Click **New** → **Directory**
3. Name it `test`
4. Right-click on `test` folder → **Mark Directory as** → **Test Sources Root**

## 4. HOW TO CREATE A TEST CASE FILE:

### Method 1: Generate Test for Existing Class
1. Open your main class (e.g., `TrainApp.java`)
2. Right-click inside the class
3. Select **Generate** → **Test** (shortcut: `Alt + Insert` / `⌘ + N` on Mac)

### Method 2: Create Test Class Manually
1. Right-click on `test/java` folder
2. Click **New** → **Java Class**
3. Name it `YourClassNameTest` (e.g., `TrainAppTest`)
4. Add JUnit imports and annotations

### JUnit Library Setup
If IntelliJ shows a "Fix" option (missing JUnit library):
- Click **Fix** → Click **OK** to add JUnit dependency
- Choose JUnit 5 (Jupiter) for modern projects
- Select test methods to generate
- Click **OK**

## 5. UNDERSTANDING THE TEST CODE

### Basic Test Structure:
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainAppTest {

    @Test
    public void testBasicFunctionality() {
        // Given (Arrange)
        int expected = 5;

        // When (Act)
        int actual = 2 + 3;

        // Then (Assert)
        assertEquals(expected, actual);
    }
}
```

### Key Annotations & Methods:
- `@Test` → Marks a test method
- `@BeforeEach` → Runs before each test
- `@AfterEach` → Runs after each test
- `@BeforeAll` → Runs once before all tests
- `@AfterAll` → Runs once after all tests

### Common Assertions:
- `assertEquals(expected, actual)` → Checks equality
- `assertTrue(condition)` → Checks boolean true
- `assertFalse(condition)` → Checks boolean false
- `assertNull(object)` → Checks for null
- `assertNotNull(object)` → Checks not null
- `assertThrows(Exception.class, () -> code)` → Checks exceptions

## 6. HOW TO RUN TEST CASES

### Method 1: Run Single Test
- Click the green ▶️ play icon near the test method
- Click **Run** 'testMethodName()'

### Method 2: Run Entire Test Class
- Right-click on test class name
- Click **Run** 'ClassNameTest'

### Method 3: Run All Tests in Project
- Right-click on project root
- Click **Run** → **Run All Tests**

### Method 4: Using Run Configurations
- Go to **Run** → **Edit Configurations**
- Add new **JUnit** configuration
- Select test class or package
- Click **Run**

## 7. HOW TO SEE RESULTS

### Test Results Window:
After running tests, IntelliJ shows:
- 🟢 **Green** → Test Passed
- 🔴 **Red** → Test Failed
- **Test Results** tab appears

### Results Details:
- ✅ **Number of tests run**
- ✅ **Passed / Failed count**
- ✅ **Execution time**
- ✅ **Error details** (if failed)
- ✅ **Stack trace** for failures

### Coverage Reports:
- Right-click on test class
- Click **Run with Coverage**
- View coverage percentage
- See which lines are tested

## 8. EXAMPLE OUTPUT

### Successful Test Run:
```
Test run finished
3 tests passed
0 tests failed
0 tests ignored

Process finished with exit code 0
```

### Failed Test Example:
```
org.opentest4j.AssertionFailedError:
Expected :5
Actual   :3
```

### Test Summary:
```
Tests: 3 passed, 0 failed, 0 ignored
Coverage: 85% class, 78% method, 92% line
```

## 9. IMPORTANT TIPS

### Best Practices:
- ✅ **Test both valid and invalid inputs**
- ✅ **Keep test cases simple and clear**
- ✅ **One test = One scenario** (Single Responsibility)
- ✅ **Use descriptive test method names**
- ✅ **Test edge cases and boundary conditions**
- ✅ **Use @DisplayName** for readable test names

### Naming Conventions:
```java
@Test
void shouldReturnTrue_WhenInputIsValid() {
    // Test implementation
}

@Test
void shouldThrowException_WhenInputIsInvalid() {
    // Test implementation
}
```

### Test Data Management:
- Use **@BeforeEach** for setup
- Use **test data builders** for complex objects
- Consider **parameterized tests** for multiple inputs

## 10. SUMMARY

- ✅ **Test cases verify your code** works as expected
- ✅ **Written using JUnit 5** (modern standard)
- ✅ **Stored in 'test' folder** under test sources root
- ✅ **Run using IntelliJ's Run options** (play button)
- ✅ **Results shown instantly** in Test Results window
- ✅ **Essential for professional Java development**

### Quick Reference:
- **Create Test:** `Alt + Insert` → Test
- **Run Test:** Click ▶️ or `Ctrl + Shift + F10`
- **Run All Tests:** Right-click project → Run All Tests
- **View Coverage:** Run with Coverage

### Pro Tips:
- Use **IntelliJ's test templates** for faster creation
- **Debug tests** by clicking the debug button
- **Use live templates** for common test patterns
- **Integrate with CI/CD** for automated testing

Happy Testing! 🚀