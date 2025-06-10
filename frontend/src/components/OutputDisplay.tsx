import React from 'react';

type Props = {
  output: string;
};

const OutputDisplay: React.FC<Props> = ({ output }) => (
  <pre style={{ background: '#f5f5f5', padding: 10 }}>{output}</pre>
);

export default OutputDisplay; 