import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Typography, Paper, Box, Grid } from '@mui/material';
import { useCode } from '../hooks/useCode';
import { CodeEditor } from '../components/CodeEditor';

const CaseDetails: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const { result, loading, error, executeCode } = useCode();
  const [code, setCode] = useState('');

  const handleCodeChange = (value: string) => {
    setCode(value);
  };

  const handleExecute = async () => {
    try {
      await executeCode({
        sourceCode: code,
        languageId: 71, // Python
        testCases: ['test case 1', 'test case 2'],
      });
    } catch (error) {
      console.error('Execution failed:', error);
    }
  };

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Case #{id}
      </Typography>
      <Grid container spacing={3}>
        <Grid item xs={12} md={8}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Case Description
            </Typography>
            {/* Add case description here */}
          </Paper>
          <Paper sx={{ p: 2, mt: 2 }}>
            <Typography variant="h6" gutterBottom>
              Your Solution
            </Typography>
            <CodeEditor
              initialCode={code}
              language="python"
              onChange={handleCodeChange}
            />
          </Paper>
        </Grid>
        <Grid item xs={12} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Test Cases
            </Typography>
            {/* Add test cases here */}
          </Paper>
          {error && (
            <Paper sx={{ p: 2, mt: 2, bgcolor: 'error.main' }}>
              <Typography color="white">{error}</Typography>
            </Paper>
          )}
          {result && (
            <Paper sx={{ p: 2, mt: 2 }}>
              <Typography variant="h6" gutterBottom>
                Output
              </Typography>
              <Box sx={{ bgcolor: 'grey.900', p: 2, borderRadius: 1 }}>
                <pre>{result.stdout}</pre>
              </Box>
            </Paper>
          )}
        </Grid>
      </Grid>
    </Container>
  );
};

export default CaseDetails; 