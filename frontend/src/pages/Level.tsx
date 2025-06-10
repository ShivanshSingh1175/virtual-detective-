import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
  Box,
  Paper,
  Typography,
  Button,
  Grid,
  CircularProgress,
  Alert,
  Divider,
} from '@mui/material';
import { motion } from 'framer-motion';
import CodeEditor from '../components/editor/CodeEditor';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { submitSolution } from '../store/slices/levelSlice';

const Level: React.FC = () => {
  const { caseId, levelId } = useParams<{ caseId: string; levelId: string }>();
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const { currentLevel, loading, error } = useAppSelector((state) => state.level);

  const [code, setCode] = useState('');
  const [showHint, setShowHint] = useState(false);
  const [showClue, setShowClue] = useState(false);

  useEffect(() => {
    // Fetch level data
    // dispatch(fetchLevel({ caseId, levelId }));
  }, [caseId, levelId, dispatch]);

  const handleSubmit = async () => {
    if (!code.trim()) return;

    try {
      const result = await dispatch(submitSolution({
        caseId: Number(caseId),
        levelId: Number(levelId),
        code,
        language: currentLevel?.language || 'javascript',
      })).unwrap();

      if (result.success) {
        setShowClue(true);
      }
    } catch (error) {
      console.error('Submission failed:', error);
    }
  };

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="80vh">
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Box p={3}>
        <Alert severity="error">{error}</Alert>
      </Box>
    );
  }

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
    >
      <Box p={3}>
        <Grid container spacing={3}>
          {/* Case Information */}
          <Grid item xs={12}>
            <Paper
              sx={{
                p: 3,
                backgroundColor: 'detective.light',
                color: 'detective.text',
              }}
            >
              <Typography variant="h4" gutterBottom fontFamily="detective">
                {currentLevel?.title}
              </Typography>
              <Typography variant="body1" paragraph>
                {currentLevel?.prompt}
              </Typography>
            </Paper>
          </Grid>

          {/* Code Editor */}
          <Grid item xs={12} md={8}>
            <Box height="600px">
              <CodeEditor
                value={code}
                onChange={setCode}
                language={currentLevel?.language || 'javascript'}
              />
            </Box>
          </Grid>

          {/* Controls and Clues */}
          <Grid item xs={12} md={4}>
            <Paper
              sx={{
                p: 3,
                height: '100%',
                backgroundColor: 'detective.light',
                color: 'detective.text',
              }}
            >
              <Typography variant="h6" gutterBottom fontFamily="detective">
                Detective's Toolkit
              </Typography>
              
              <Box mb={3}>
                <Button
                  variant="contained"
                  color="primary"
                  fullWidth
                  onClick={handleSubmit}
                  disabled={loading}
                >
                  Submit Solution
                </Button>
              </Box>

              <Divider sx={{ my: 2 }} />

              <Box mb={3}>
                <Button
                  variant="outlined"
                  color="secondary"
                  fullWidth
                  onClick={() => setShowHint(!showHint)}
                >
                  {showHint ? 'Hide Hint' : 'Show Hint'}
                </Button>
                {showHint && (
                  <Box mt={2} p={2} bgcolor="detective.dark" borderRadius={1}>
                    <Typography variant="body2">
                      {currentLevel?.hint}
                    </Typography>
                  </Box>
                )}
              </Box>

              {showClue && (
                <Box mt={3}>
                  <Typography variant="h6" gutterBottom fontFamily="detective">
                    New Clue Discovered!
                  </Typography>
                  <Box p={2} bgcolor="detective.dark" borderRadius={1}>
                    <Typography variant="body2">
                      {currentLevel?.clue}
                    </Typography>
                  </Box>
                </Box>
              )}
            </Paper>
          </Grid>
        </Grid>
      </Box>
    </motion.div>
  );
};

export default Level; 