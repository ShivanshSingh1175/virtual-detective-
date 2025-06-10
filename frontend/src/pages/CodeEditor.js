import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Box, Paper, Typography, Button, Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import { EditorView, basicSetup } from '@codemirror/basic-setup';
import { javascript } from '@codemirror/lang-javascript';
import { java } from '@codemirror/lang-java';
import { python } from '@codemirror/lang-python';
import { oneDark } from '@codemirror/theme-one-dark';
import axios from 'axios';

const CodeEditor = () => {
  const { challengeId } = useParams();
  const [editor, setEditor] = useState(null);
  const [language, setLanguage] = useState('javascript');
  const [output, setOutput] = useState('');
  const [challenge, setChallenge] = useState(null);

  useEffect(() => {
    // Fetch challenge details
    const fetchChallenge = async () => {
      try {
        const response = await axios.get(`/api/challenges/${challengeId}`);
        setChallenge(response.data);
        initializeEditor(response.data.initialCode);
      } catch (error) {
        console.error('Error fetching challenge:', error);
      }
    };

    fetchChallenge();
  }, [challengeId]);

  const initializeEditor = (initialCode) => {
    const extensions = [
      basicSetup,
      oneDark,
      getLanguageExtension(language),
    ];

    const editorElement = document.getElementById('editor');
    if (editorElement) {
      const view = new EditorView({
        doc: initialCode,
        extensions,
        parent: editorElement,
      });
      setEditor(view);
    }
  };

  const getLanguageExtension = (lang) => {
    switch (lang) {
      case 'javascript':
        return javascript();
      case 'java':
        return java();
      case 'python':
        return python();
      default:
        return javascript();
    }
  };

  const handleLanguageChange = (event) => {
    setLanguage(event.target.value);
    if (editor) {
      editor.destroy();
      initializeEditor(editor.state.doc.toString());
    }
  };

  const handleRunCode = async () => {
    if (!editor) return;

    const code = editor.state.doc.toString();
    try {
      const response = await axios.post('/api/execute', {
        code,
        language,
      });
      setOutput(response.data.output);
    } catch (error) {
      setOutput('Error executing code: ' + error.message);
    }
  };

  return (
    <Box sx={{ height: '100%', display: 'flex', flexDirection: 'column', gap: 2 }}>
      <Paper sx={{ p: 2, backgroundColor: '#2d2d2d' }}>
        <Typography variant="h5" gutterBottom sx={{ fontFamily: '"Special Elite", cursive' }}>
          {challenge?.title || 'Loading...'}
        </Typography>
        <Typography variant="body1" paragraph>
          {challenge?.description}
        </Typography>
        <FormControl sx={{ minWidth: 120, mb: 2 }}>
          <InputLabel>Language</InputLabel>
          <Select
            value={language}
            label="Language"
            onChange={handleLanguageChange}
          >
            <MenuItem value="javascript">JavaScript</MenuItem>
            <MenuItem value="java">Java</MenuItem>
            <MenuItem value="python">Python</MenuItem>
          </Select>
        </FormControl>
      </Paper>

      <Paper sx={{ flexGrow: 1, overflow: 'hidden' }}>
        <div id="editor" style={{ height: '100%' }} />
      </Paper>

      <Paper sx={{ p: 2, backgroundColor: '#2d2d2d' }}>
        <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
          <Typography variant="h6">Output</Typography>
          <Button
            variant="contained"
            color="primary"
            onClick={handleRunCode}
            sx={{
              backgroundColor: '#ff4081',
              '&:hover': {
                backgroundColor: '#f50057',
              },
            }}
          >
            Run Code
          </Button>
        </Box>
        <Paper
          sx={{
            p: 2,
            backgroundColor: '#1a1a1a',
            minHeight: '100px',
            fontFamily: 'monospace',
            whiteSpace: 'pre-wrap',
          }}
        >
          {output || 'No output yet...'}
        </Paper>
      </Paper>
    </Box>
  );
};

export default CodeEditor; 