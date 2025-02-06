import "./App.css";
import { Routes, Route } from "react-router-dom";
import Homepage from "./components/Homepage";
import SignUp from "./components/SignUp";
import SignIn from "./components/SignIn";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Homepage />}></Route>
        <Route path="/signUp" element={<SignUp />}></Route>
        <Route path="/signIn" element={<SignIn />}></Route>
      </Routes>
      <Footer />  
    </>
  );
}

export default App;
