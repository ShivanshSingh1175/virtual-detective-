import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Box,
  Grid,
  Card,
  CardContent,
  Typography,
  CardActions,
  Button,
  Chip,
  CircularProgress,
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';

const Dashboard = () => {
  const navigate = useNavigate();
  const [cases, setCases] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCases = async () => {
      try {
        const response = await axios.get('/api/cases');
        setCases(response.data);
      } catch (error) {
        console.error('Error fetching cases:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchCases();
  }, []);

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box sx={{ p: 3 }}>
      <Typography
        variant="h4"
        gutterBottom
        sx={{
          fontFamily: '"Special Elite", cursive',
          color: '#ff4081',
          textAlign: 'center',
          mb: 4,
        }}
      >
        Active Cases
      </Typography>

      <Grid container spacing={3}>
        {cases.map((caseItem) => (
          <Grid item xs={12} sm={6} md={4} key={caseItem.id}>
            <Card
              sx={{
                height: '100%',
                display: 'flex',
                flexDirection: 'column',
                backgroundColor: '#2d2d2d',
                transition: 'transform 0.2s',
                '&:hover': {
                  transform: 'scale(1.02)',
                  boxShadow: '0 0 20px rgba(255, 64, 129, 0.3)',
                },
              }}
            >
              <CardContent sx={{ flexGrow: 1 }}>
                <Typography
                  variant="h5"
                  component="h2"
                  gutterBottom
                  sx={{ fontFamily: '"Special Elite", cursive' }}
                >
                  {caseItem.title}
                </Typography>
                <Typography variant="body2" color="text.secondary" paragraph>
                  {caseItem.description}
                </Typography>
                <Box sx={{ display: 'flex', gap: 1, flexWrap: 'wrap' }}>
                  <Chip
                    label={caseItem.difficulty}
                    color={caseItem.difficulty === 'Easy' ? 'success' : caseItem.difficulty === 'Medium' ? 'warning' : 'error'}
                    size="small"
                  />
                  <Chip
                    label={`${caseItem.challenges?.length || 0} Challenges`}
                    color="primary"
                    size="small"
                  />
                </Box>
              </CardContent>
              <CardActions>
                <Button
                  size="small"
                  color="primary"
                  startIcon={<SearchIcon />}
                  onClick={() => navigate(`/case/${caseItem.id}`)}
                  sx={{
                    color: '#ff4081',
                    '&:hover': {
                      backgroundColor: 'rgba(255, 64, 129, 0.1)',
                    },
                  }}
                >
                  Investigate
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};

export default Dashboard; 