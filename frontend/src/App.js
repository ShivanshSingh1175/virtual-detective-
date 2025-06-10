import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { ThemeProvider, CssBaseline } from '@mui/material';
import theme from './theme';

// Components
import Auth from './components/Auth';
import Dashboard from './components/Dashboard';
import CaseView from './components/CaseView';
import CodeEditor from './components/CodeEditor';
import ProtectedRoute from './components/ProtectedRoute';

// Layout
import Layout from './components/Layout';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Routes>
          <Route path="/auth" element={<Auth />} />
          <Route path="/" element={<Layout />}>
            <Route index element={<Navigate to="/dashboard" replace />} />
            <Route
              path="dashboard"
              element={
                <ProtectedRoute>
                  <Dashboard />
                </ProtectedRoute>
              }
            />
            <Route
              path="case/:caseId"
              element={
                <ProtectedRoute>
                  <CaseView />
                </ProtectedRoute>
              }
            />
            <Route
              path="challenge/:challengeId"
              element={
                <ProtectedRoute>
                  <CodeEditor />
                </ProtectedRoute>
              }
            />
          </Route>
        </Routes>
      </Router>
    </ThemeProvider>
  );
}

export default App; 