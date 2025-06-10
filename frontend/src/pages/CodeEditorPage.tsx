import React, { useState } from 'react';
import CodeEditor from '../components/CodeEditor';
import OutputDisplay from '../components/OutputDisplay';
import LanguageSelector from '../components/LanguageSelector';

const CodeEditorPage: React.FC = () => {
  const [code, setCode] = useState('');
  const [language, setLanguage] = useState('python');
  const [output, setOutput] = useState('');

  const handleRun = async () => {
    const response = await fetch('http://localhost:8081/api/execute', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ sourceCode: code, language }),
    });
    const data = await response.json();
    setOutput(data.output || data.error);
  };

  return (
    <div>
      <LanguageSelector language={language} setLanguage={setLanguage} />
      <CodeEditor code={code} setCode={setCode} />
      <button onClick={handleRun}>Run</button>
      <OutputDisplay output={output} />
    </div>
  );
};

export default CodeEditorPage; 