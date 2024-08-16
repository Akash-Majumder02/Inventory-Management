import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import  './login.css';
import Environment from "../Environment";
import Form from "./Functions/Form";


const LoginPage = () => {

  const URL = Environment.INVENTORY_API_URL;
  
  const [userDetails, setUserDetails] = useState({
    userName : '',
    password : ''
  });
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      var config = {
        method: 'post',
        url: URL+'/validateLogin',
        headers:{"Content-Type": "application/json"},
        data : userDetails
      };
      const response = await axios(config);
      if(response.data.status === 'SUCCESS'){
        navigate("/home");
      }else{
        setError(response.data.message);
      }  
    } catch (error) {
      setError(error.message + ": Webservices are down!!");
    }
  };
  
return (
  <div className="login-container"> 
    
    <form onSubmit={handleSubmit} className="form"> 
    <h2 className="head">Login</h2>
    <Form props={[userDetails, setUserDetails]} detail={"userName"} type={"text"}/>
    <Form props={[userDetails, setUserDetails]} detail={"password"} type={"password"}/> 
      {/* <label htmlFor="userName" className="label">Username:</label>
      <input
        type="text"
        id="userName"
        value={userDetails.userName}
        onChange={(e) => setUserDetails({ ...userDetails, userName: e.target.value })}
        required
        className="input" 
     />
      <label htmlFor="password" className="label">Password:</label>
      <input
        type="password"
        id="password"
        value={userDetails.password}
        onChange={(e) => setUserDetails({ ...userDetails, password: e.target.value })}
        required
        className="input" 
      /> */}
    <button type="submit" className="button">Log In</button>
    {error && <p className="error">{error}</p>}
    </form>
  </div>
 );
};

export default LoginPage;
