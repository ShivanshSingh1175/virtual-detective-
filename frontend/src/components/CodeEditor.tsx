import React from 'react';
import { Box } from '@mui/material';
import CodeMirror from '@uiw/react-codemirror';
import { python } from '@codemirror/lang-python';
import { javascript } from '@codemirror/lang-javascript';
import { java } from '@codemirror/lang-java';
import { vscodeDark } from '@uiw/codemirror-theme-vscode';

export interface CodeEditorProps {
  initialCode?: string;
  language?: 'python' | 'javascript' | 'java';
  onChange?: (value: string) => void;
}

export const CodeEditor: React.FC<CodeEditorProps> = ({
  initialCode = '',
  language = 'python',
  onChange,
}) => {
  const getLanguageExtension = () => {
    switch (language) {
      case 'javascript':
        return javascript();
      case 'java':
        return java();
      default:
        return python();
    }
  };

  return (
    <Box sx={{ width: '100%' }}>
      <CodeMirror
        value={initialCode}
        height="400px"
        theme={vscodeDark}
        extensions={[getLanguageExtension()]}
        onChange={onChange}
      />
    </Box>
  );
}; 