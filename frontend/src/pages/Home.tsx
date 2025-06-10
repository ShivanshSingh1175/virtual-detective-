import React from 'react';
import { Box, Typography, Button, Stack } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Home: React.FC = () => {
  const navigate = useNavigate();

  return (
    <Box
      sx={{
        minHeight: '80vh',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        textAlign: 'center',
        gap: 4,
      }}
    >
      <Typography variant="h2" fontWeight={700} gutterBottom>
        Virtual Detective: Codebreaker Edition
      </Typography>
      <Typography variant="h5" color="text.secondary" gutterBottom>
        Solve fictional cases, crack code puzzles, and become the ultimate Codebreaker!
      </Typography>
      <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2} justifyContent="center">
        <Button variant="contained" color="primary" size="large" onClick={() => navigate('/login')}>
          Login / Register
        </Button>
        <Button variant="outlined" color="secondary" size="large" onClick={() => navigate('/cases')}>
          Explore Cases
        </Button>
      </Stack>
    </Box>
  );
};

export default Home; 