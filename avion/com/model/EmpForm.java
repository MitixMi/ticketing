package avion.com.model;

import avion.com.annotation.*;

public class EmpForm {
    @NotNull(message = "Employee name is required")
    private String name;
    
    @NotNull(message = "Employee ID is required")
    @MinLength(value = 5, message = "Employee ID must be at least 5 characters long")
    @MaxLength(value = 10, message = "Employee ID must not exceed 10 characters")
    @Numeric(message = "Employee ID must be numeric")
    private String employeeId;
    
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @Min(value = 18, message = "Employee must be at least 18 years old")
    private int age;
    
    @NotNull(message = "Position is required")
    @Pattern(regex = "^(Manager|Developer|Designer)$", message = "Position must be 'Manager', 'Developer', or 'Designer'")
    private String position;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}


