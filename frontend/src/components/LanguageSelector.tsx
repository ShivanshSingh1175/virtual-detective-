import React from 'react';

type Props = {
  language: string;
  setLanguage: (lang: string) => void;
};

const LanguageSelector: React.FC<Props> = ({ language, setLanguage }) => (
  <select value={language} onChange={e => setLanguage(e.target.value)}>
    <option value="python">Python</option>
    <option value="java">Java</option>
    <option value="javascript">JavaScript</option>
  </select>
);

export default LanguageSelector; 