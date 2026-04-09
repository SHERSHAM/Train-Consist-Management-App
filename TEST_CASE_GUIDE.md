# TEST CASE CREATION & EXECUTION IN VS CODE (JAVA PROJECT)

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

## 3. WHERE TO WRITE TEST CASES IN VS CODE?

### Project Structure:
```
src
 ├── main
 │    └── java        (your actual code)
 └── test
      └── java        (your test cases)
```

### If 'test' folder is not present:
1. Right-click in the Explorer panel
2. Click **New Folder**
3. Name it `test`
4. Inside `test`, create `java` folder
5. Right-click on `test/java` → **Mark Directory as Test Sources** (if using Java extension)

## 4. HOW TO CREATE A TEST CASE FILE:

### Method 1: Generate Test for Existing Class
1. Open your main class (e.g., `TrainApp.java`)
2. Use Command Palette (Ctrl+Shift+P) → "Java: Generate Tests"

### Method 2: Create Test Class Manually
1. Right-click on `test/java` folder
2. Click **New File**
3. Name it `YourClassNameTest.java` (e.g., `TrainAppTest.java`)
4. Add JUnit imports and annotations

### JUnit Library Setup
If VS Code shows missing JUnit:
- Ensure Java Extension Pack is installed
- Add JUnit dependency to your `pom.xml` or `build.gradle`
- For Maven: Add to dependencies
- For Gradle: Add to dependencies block
- Reload the project

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
- Open the test file
- Click the ▶️ icon next to the test method in the Test Explorer
- Or use Command Palette: "Java: Run Test"

### Method 2: Run Entire Test Class
- Open the Test Explorer (Ctrl+Shift+T)
- Right-click on the test class
- Click **Run Tests**

### Method 3: Run All Tests in Project
- Open Test Explorer
- Click **Run All Tests** button

### Method 4: Using Command Palette
- Ctrl+Shift+P → "Java: Run All Tests"
- Or "Java: Run Tests" for current file

## 7. HOW TO SEE RESULTS

### Test Results in VS Code:
After running tests:
- 🟢 **Green** → Test Passed
- 🔴 **Red** → Test Failed
- Results appear in the **Test Results** panel

### Results Details:
- ✅ **Number of tests run**
- ✅ **Passed / Failed count**
- ✅ **Execution time**
- ✅ **Error details** (if failed)
- ✅ **Stack trace** for failures

### Coverage Reports:
- Install "Coverage Gutters" extension
- Run tests with coverage
- View coverage in the editor

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
- ✅ **Stored in 'test' folder** under test sources
- ✅ **Run using VS Code's Test Explorer** or Command Palette
- ✅ **Results shown in Test Results panel**
- ✅ **Essential for professional Java development**

### Quick Reference:
- **Create Test:** Command Palette (Ctrl+Shift+P) → "Java: Generate Tests"
- **Run Test:** Click ▶️ in Test Explorer or Command Palette "Java: Run Test"
- **Run All Tests:** Test Explorer → "Run All Tests" button
- **View Coverage:** Install Coverage Gutters extension → Run with coverage

### Pro Tips:
- Use **VS Code's Java test templates** for faster creation
- **Debug tests** by clicking the debug icon in Test Explorer
- **Use snippets** for common test patterns
- **Integrate with CI/CD** for automated testing

Happy Testing! 🚀