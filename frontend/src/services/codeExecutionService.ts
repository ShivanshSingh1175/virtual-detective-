import axios from 'axios';

const API_URL = '/api/code';

export interface CodeExecutionRequest {
  code: string;
  language: string;
  testCases?: string;
  prompt?: string;
  expectedOutput?: string;
  generateHint?: boolean;
}

export interface TestCaseResult {
  input: string;
  expectedOutput: string;
  actualOutput: string;
  passed: boolean;
  error?: string;
}

export interface CodeExecutionResponse {
  success: boolean;
  output: string;
  error?: string;
  hint?: string;
  testResults: TestCaseResult[];
  explanation: string;
}

export const executeCode = async (request: CodeExecutionRequest): Promise<CodeExecutionResponse> => {
  const response = await axios.post(`${API_URL}/execute`, request);
  return response.data;
};

export const generateHint = async (prompt: string, language: string): Promise<string> => {
  const response = await axios.post(`${API_URL}/hint`, null, {
    params: { prompt, language }
  });
  return response.data;
};

export const validateSolution = async (
  code: string,
  testCases: string,
  language: string
): Promise<string> => {
  const response = await axios.post(`${API_URL}/validate`, null, {
    params: { code, testCases, language }
  });
  return response.data;
}; 