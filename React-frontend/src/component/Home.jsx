import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { getUserFromToken } from "../utils/TokenService";

function Home() {
  const [userName, setUserName] = useState(null);
  const [token, setToken] = useState(JSON.parse(localStorage.getItem("token")));
  const navigateTo = useNavigate();

  useEffect(() => {
    const user = getUserFromToken(token)[0];
    setUserName(user);
  }, [token]);

  const getData = async () => {
    try {
      const headers = {
        Authorization: "Bearer " + token,
      };

      const response = await axios.get("http://localhost:8081/user/hello", { headers });

      console.log(response.data);
    } catch (error) {
      console.log("Error fetching data");
      if (error.response.status === 401) {
        localStorage.removeItem("token");
        toast.error("Session Expired. Login Again")
        navigateTo("/login");
      }
    }
  };

  const logout = () => {
    localStorage.clear();
    navigateTo("/login");
  };

  return (
    <>
      <h1>Logged in user : {userName}</h1>
      <button onClick={getData}>GET DATA</button>
      <button onClick={logout}>LOGOUT</button>
    </>
  );
}

export default Home;
