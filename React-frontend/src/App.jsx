import { useState } from 'react'
import {BrowserRouter , Routes , Route} from 'react-router-dom'
import './App.css'
import Login from './component/Login'
import Signup from './component/Signup'
import Home from './component/Home'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<Login/>}/>
        <Route path='/signup' element={<Signup/>}/>
        <Route path='/home' element={<Home/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
