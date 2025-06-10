import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import CodeIcon from '@mui/icons-material/Code';
import SearchIcon from '@mui/icons-material/Search';

const Navbar = () => {
  const navigate = useNavigate();

  return (
    <AppBar position="static" sx={{ background: 'linear-gradient(45deg, #1a1a1a 30%, #2d2d2d 90%)' }}>
      <Toolbar>
        <Box sx={{ display: 'flex', alignItems: 'center', flexGrow: 1 }}>
          <CodeIcon sx={{ mr: 1 }} />
          <Typography
            variant="h6"
            component="div"
            sx={{
              fontFamily: '"Special Elite", cursive',
              cursor: 'pointer',
              '&:hover': {
                color: '#ff4081',
              },
            }}
            onClick={() => navigate('/')}
          >
            Detective Code
          </Typography>
        </Box>
        <Box sx={{ display: 'flex', gap: 2 }}>
          <Button
            color="inherit"
            startIcon={<SearchIcon />}
            onClick={() => navigate('/')}
            sx={{
              '&:hover': {
                backgroundColor: 'rgba(255, 64, 129, 0.1)',
              },
            }}
          >
            Cases
          </Button>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar; 