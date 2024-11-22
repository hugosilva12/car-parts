import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import NotFound from '../pages/global/not_found'
import Login from '../pages/global/login'
import CreateClient from'../pages/users/component_create_client'
import Home from '../pages/home/component_home';

function Routing() {

  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={< Login />} />
          <Route path="*" element={< NotFound />} />
          <Route exact path='/register-client' element={< CreateClient />} />
          <Route exact path='/home' element={<Home />} />
        </Routes>
      </Router>
    </>
  );
}

export default Routing;