import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#8B4513', // Saddle Brown
      light: '#A0522D', // Sienna
      dark: '#654321', // Dark Brown
    },
    secondary: {
      main: '#DAA520', // Goldenrod
      light: '#FFD700', // Gold
      dark: '#B8860B', // Dark Goldenrod
    },
    background: {
      default: '#1A1A1A', // Dark Gray
      paper: '#2D2D2D', // Slightly lighter gray
    },
    text: {
      primary: '#E0E0E0', // Light Gray
      secondary: '#A0A0A0', // Medium Gray
    },
  },
  typography: {
    fontFamily: '"Cinzel", "Roboto", "Helvetica", "Arial", sans-serif',
    h1: {
      fontFamily: '"Cinzel", serif',
      fontWeight: 700,
    },
    h2: {
      fontFamily: '"Cinzel", serif',
      fontWeight: 600,
    },
    h3: {
      fontFamily: '"Cinzel", serif',
      fontWeight: 600,
    },
    button: {
      fontFamily: '"Cinzel", serif',
      fontWeight: 600,
      textTransform: 'none',
    },
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          borderRadius: 8,
          padding: '10px 20px',
          boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
          '&:hover': {
            transform: 'translateY(-2px)',
            boxShadow: '0 6px 8px rgba(0, 0, 0, 0.2)',
          },
        },
      },
    },
    MuiPaper: {
      styleOverrides: {
        root: {
          backgroundImage: 'none',
          borderRadius: 12,
          boxShadow: '0 8px 16px rgba(0, 0, 0, 0.2)',
        },
      },
    },
    MuiCard: {
      styleOverrides: {
        root: {
          borderRadius: 12,
          backgroundImage: 'none',
          boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
          '&:hover': {
            transform: 'translateY(-4px)',
            boxShadow: '0 8px 16px rgba(0, 0, 0, 0.3)',
          },
        },
      },
    },
  },
});

export default theme; 