import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { Provider } from 'react-redux';
import { store } from './store';

// Layout Components
import Layout from './components/layout/Layout';

// Page Components
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import CaseList from './pages/CaseList';
import CaseDetail from './pages/CaseDetail';
import Level from './pages/Level';
import Profile from './pages/Profile';
import CodeEditorPage from './pages/CodeEditorPage';
import NotFound from './pages/NotFound';

// Create dark theme
const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#8b5cf6',
    },
    secondary: {
      main: '#e5e5e5',
    },
    background: {
      default: '#1a1a1a',
      paper: '#2d2d2d',
    },
  },
  typography: {
    fontFamily: '"Playfair Display", serif',
  },
});

function App() {
  return <CodeEditorPage />;
}

export default App; 