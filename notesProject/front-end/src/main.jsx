import React from 'react';
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App';
//import './App.css';//това го махни, вмъкваш го в апа

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <App />
    </StrictMode>,
)