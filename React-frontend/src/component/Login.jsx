import React, { useState } from "react";
import {
  MDBContainer,
  MDBCol,
  MDBRow,
  MDBBtn,
  MDBIcon,
  MDBInput,
} from "mdb-react-ui-kit";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigateTo = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8081/auth/login", {
        username: email,
        password: password,
      });

      console.log(response);

      if (response.status === 200) {
        toast.success("Login success");
        localStorage.setItem("token", JSON.stringify(response.data.jwt));
        navigateTo("/home");
      }
    } catch (error) {
      if (error.response.status === 404) {
        toast.error(error.response.data);
      } else {
        toast.error("Internal server error");
      }
    }
  };

  return (
    <>
      <MDBContainer fluid className="p-3 my-5 h-custom">
        <MDBRow>
          <MDBCol col="10" md="6">
            <img
              src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
              className="img-fluid"
              alt="Sample image"
            />
          </MDBCol>

          <MDBCol col="4" md="6">
            <div className="d-flex flex-row align-items-center justify-content-center">
              <p className="lead fw-normal mb-0 me-3">Sign in with</p>

              <MDBBtn floating size="md" tag="a" className="me-2">
                <MDBIcon fab icon="facebook-f" />
              </MDBBtn>

              <MDBBtn floating size="md" tag="a" className="me-2">
                <MDBIcon fab icon="twitter" />
              </MDBBtn>

              <MDBBtn floating size="md" tag="a" className="me-2">
                <MDBIcon fab icon="linkedin-in" />
              </MDBBtn>
            </div>

            <div className="divider d-flex align-items-center justify-content-center my-4" style={{fontSize:"1.5rem"}}>
            <b>Login</b>
            </div>

            <MDBInput
              wrapperClass="mb-4"
              label="Email address"
              id="email"
              type="email"
              size="lg"
              onChange={(e) => setEmail(e.target.value)}
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Password"
              id="password"
              type="password"
              size="lg"
              onChange={(e) => setPassword(e.target.value)}
            />

            <div className="d-flex justify-content-end mb-4">
              <a href="!#">Forgot password?</a>
            </div>

            <div className="text-center text-md-center mt-4 pt-2">
              <MDBBtn className="mb-0 px-5" size="lg" onClick={handleLogin}>
                Login
              </MDBBtn>
              <p className="small fw-bold mt-2 pt-1 mb-2">
                Don't have an account?{" "}
                <Link to="/signup" className="link-danger">
                  Register
                </Link>
              </p>
            </div>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
    </>
  );
}

export default Login;
