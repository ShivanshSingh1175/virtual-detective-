import React, { useState } from 'react';
import { Container, Paper, Box, Button, Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import { useCode } from '../hooks/useCode';
import CodeMirror from '@uiw/react-codemirror';
import { python } from '@codemirror/lang-python';
import { vscodeDark } from '@uiw/codemirror-theme-vscode';

const CodeEditor: React.FC = () => {
  const {
    sourceCode,
    languageId,
    result,
    loading,
    error,
    executeCode,
    changeLanguage,
    updateSourceCode,
  } = useCode();

  const handleExecute = async () => {
    try {
      await executeCode({
        sourceCode,
        languageId,
        testCases: ['test case 1', 'test case 2'],
      });
    } catch (error) {
      console.error('Execution failed:', error);
    }
  };

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Paper sx={{ p: 2 }}>
        <Box sx={{ mb: 2 }}>
          <FormControl fullWidth>
            <InputLabel>Language</InputLabel>
            <Select
              value={languageId}
              label="Language"
              onChange={(e) => changeLanguage(Number(e.target.value))}
            >
              <MenuItem value={71}>Python</MenuItem>
              <MenuItem value={63}>JavaScript</MenuItem>
              <MenuItem value={62}>Java</MenuItem>
            </Select>
          </FormControl>
        </Box>
        <Box sx={{ mb: 2 }}>
          <CodeMirror
            value={sourceCode}
            height="400px"
            theme={vscodeDark}
            extensions={[python()]}
            onChange={(value) => updateSourceCode(value)}
          />
        </Box>
        <Button
          variant="contained"
          color="primary"
          onClick={handleExecute}
          disabled={loading}
        >
          {loading ? 'Running...' : 'Run Code'}
        </Button>
        {error && (
          <Box sx={{ mt: 2, color: 'error.main' }}>
            <Typography>{error}</Typography>
          </Box>
        )}
        {result && (
          <Box sx={{ mt: 2 }}>
            <Typography variant="h6">Output:</Typography>
            <Paper sx={{ p: 2, bgcolor: 'grey.900' }}>
              <pre>{result.stdout}</pre>
            </Paper>
          </Box>
        )}
      </Paper>
    </Container>
  );
};

export default CodeEditor; 