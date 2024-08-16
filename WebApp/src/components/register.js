import React, { useState } from "react";
import './register.css';

const RegistrationForm = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [userRole, setUserRole] = useState("admin"); // Default role
  const [error, setError] = useState(null);

  const handleSubmit = (event) => {
    event.preventDefault();

    // Basic validation
    let isValid = true;
    setError(null); // Clear any previous errors

    if (username.trim() === "") {
      isValid = false;
      setError("Username cannot be empty.");
    } else if (email.trim() === "") {
      isValid = false;
      setError("Email cannot be empty.");
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      isValid = false;
      setError("Invalid email format.");
    } else if (password.length < 8) {
      isValid = false;
      setError("Password must be at least 8 characters long.");
    } else if (password !== confirmPassword) {
      isValid = false;
      setError("Passwords do not match.");
    }

    if (isValid) {
      // Handle form submission (e.g., send data to server)
      console.log("Registration successful:", {
        username,
        email,
        password,
        userRole,
      }); // Replace with actual submission logic
    }
  };

  return (
    <div className="registration-form">
      <h2>Registration</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <label htmlFor="confirmPassword">Confirm Password:</label>
        <input
          type="password"
          id="confirmPassword"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          required
        />
        <label htmlFor="userRole">User Role:</label>
        <select id="userRole" value={userRole} onChange={(e) => setUserRole(e.target.value)}>
          
          <option value="cashier">Cashier</option>
          <option value="product_manager">Product Manager</option>
        </select>
        {error && <p className="error">{error}</p>}
      </form>
      <button type="submit">Register</button>
    </div>
  );
};

export default RegistrationForm;
