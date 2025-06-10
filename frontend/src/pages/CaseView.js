import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
  Box,
  Paper,
  Typography,
  List,
  ListItem,
  ListItemText,
  ListItemIcon,
  Button,
  Divider,
  CircularProgress,
  Chip,
} from '@mui/material';
import CodeIcon from '@mui/icons-material/Code';
import LockIcon from '@mui/icons-material/Lock';
import LockOpenIcon from '@mui/icons-material/LockOpen';
import axios from 'axios';

const CaseView = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [caseData, setCaseData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCase = async () => {
      try {
        const response = await axios.get(`/api/cases/${id}`);
        setCaseData(response.data);
      } catch (error) {
        console.error('Error fetching case:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchCase();
  }, [id]);

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <CircularProgress />
      </Box>
    );
  }

  if (!caseData) {
    return (
      <Box sx={{ p: 3 }}>
        <Typography variant="h5" color="error">
          Case not found
        </Typography>
      </Box>
    );
  }

  return (
    <Box sx={{ p: 3 }}>
      <Paper sx={{ p: 3, mb: 3, backgroundColor: '#2d2d2d' }}>
        <Typography
          variant="h4"
          gutterBottom
          sx={{ fontFamily: '"Special Elite", cursive', color: '#ff4081' }}
        >
          {caseData.title}
        </Typography>
        <Typography variant="body1" paragraph>
          {caseData.description}
        </Typography>
        <Box sx={{ display: 'flex', gap: 1, mb: 2 }}>
          <Chip
            label={caseData.difficulty}
            color={caseData.difficulty === 'Easy' ? 'success' : caseData.difficulty === 'Medium' ? 'warning' : 'error'}
          />
          <Chip
            label={`${caseData.challenges?.length || 0} Challenges`}
            color="primary"
          />
        </Box>
      </Paper>

      <Paper sx={{ backgroundColor: '#2d2d2d' }}>
        <Typography
          variant="h5"
          sx={{ p: 2, fontFamily: '"Special Elite", cursive' }}
        >
          Coding Challenges
        </Typography>
        <Divider />
        <List>
          {caseData.challenges?.map((challenge, index) => (
            <React.Fragment key={challenge.id}>
              <ListItem
                sx={{
                  '&:hover': {
                    backgroundColor: 'rgba(255, 64, 129, 0.1)',
                  },
                }}
              >
                <ListItemIcon>
                  {challenge.isCompleted ? (
                    <LockOpenIcon color="success" />
                  ) : (
                    <LockIcon color="disabled" />
                  )}
                </ListItemIcon>
                <ListItemText
                  primary={
                    <Typography
                      variant="h6"
                      sx={{ fontFamily: '"Special Elite", cursive' }}
                    >
                      {challenge.title}
                    </Typography>
                  }
                  secondary={challenge.description}
                />
                <Button
                  variant="contained"
                  startIcon={<CodeIcon />}
                  onClick={() => navigate(`/editor/${challenge.id}`)}
                  disabled={!challenge.isCompleted && index > 0 && !caseData.challenges[index - 1].isCompleted}
                  sx={{
                    backgroundColor: '#ff4081',
                    '&:hover': {
                      backgroundColor: '#f50057',
                    },
                  }}
                >
                  Solve
                </Button>
              </ListItem>
              {index < caseData.challenges.length - 1 && <Divider />}
            </React.Fragment>
          ))}
        </List>
      </Paper>
    </Box>
  );
};

export default CaseView; 