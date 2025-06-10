import React from 'react';

type Props = {
  code: string;
  setCode: (code: string) => void;
};

const CodeEditor: React.FC<Props> = ({ code, setCode }) => (
  <textarea
    value={code}
    onChange={e => setCode(e.target.value)}
    rows={10}
    cols={60}
    style={{ fontFamily: 'monospace', fontSize: 16 }}
  />
);

export default CodeEditor; 