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

function Signup() {
  const [name , setName] = useState("")
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigateTo = useNavigate();

  // SIGNUP
  const handleSignup = async () => {
    try {
      const response = await axios.post("http://localhost:8081/auth/register", {
        name:name,
        username: email,
        password: password,
      });

      if (response.status === 200) {
        navigateTo("/login");
      }
    } catch (error) {
      console.log(error);
      if (error.response.status === 401) {
        toast.error(error.response.data);
      } else {
        toast.error("Internal server error");
      }
    }
  };

  return (
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
            <b>Register</b>
          </div>
          <MDBInput
            wrapperClass="mb-4"
            label="Name"
            type="text"
            size="lg"
            onChange={(e) => setName(e.target.value)}
          />
          <MDBInput
            wrapperClass="mb-4"
            label="Email address"
            type="email"
            size="lg"
            onChange={(e) => setEmail(e.target.value)}
          />
          <MDBInput
            wrapperClass="mb-4"
            label="Password"
            type="password"
            size="lg"
            onChange={(e) => setPassword(e.target.value)}
          />

          <div className="d-flex justify-content-end mb-4">
            <a href="!#">Forgot password?</a>
          </div>

          <div className="text-center text-md-center mt-4 pt-2">
            <MDBBtn className="mb-0 px-5" size="lg" onClick={handleSignup}>
              REGISTER
            </MDBBtn>

            <p className="small fw-bold mt-2 pt-1 mb-2">
              Already have an account?{" "}
              <Link to="/login" className="link-danger">
                Login
              </Link>
            </p>
          </div>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}

export default Signup;
