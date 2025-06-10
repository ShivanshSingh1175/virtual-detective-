import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { Provider } from 'react-redux';
import { store } from './store';
import { ProtectedRoute } from './components/ProtectedRoute';

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
import Dashboard from './pages/Dashboard';
import CodeEditor from './pages/CodeEditor';
import CaseDetails from './pages/CaseDetails';
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
    detective: {
      main: '#2d3748',
      dark: '#1a202c',
      light: '#4a5568',
    },
  },
  typography: {
    fontFamily: '"Playfair Display", serif',
    h1: {
      fontFamily: '"Playfair Display", serif',
    },
    h2: {
      fontFamily: '"Playfair Display", serif',
    },
    h3: {
      fontFamily: '"Playfair Display", serif',
    },
  },
});

// Extend the Theme type to include our custom palette
declare module '@mui/material/styles' {
  interface Palette {
    detective: {
      main: string;
      dark: string;
      light: string;
    };
  }
  interface PaletteOptions {
    detective?: {
      main: string;
      dark: string;
      light: string;
    };
  }
}

function App() {
  return (
    <Provider store={store}>
      <ThemeProvider theme={darkTheme}>
        <CssBaseline />
        <Router>
          <Layout>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/cases" element={<CaseList />} />
              <Route path="/cases/:caseId" element={<CaseDetail />} />
              <Route path="/cases/:caseId/levels/:levelId" element={<Level />} />
              <Route path="/profile" element={<Profile />} />
            </Routes>
          </Layout>
        </Router>
      </ThemeProvider>
    </Provider>
  );
}

export default App; 