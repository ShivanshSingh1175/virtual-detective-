import api from './config';

export interface CodeSubmission {
  sourceCode: string;
  languageId: number;
  testCases: string[];
}

export interface CodeResult {
  id: string;
  status: {
    id: number;
    description: string;
  };
  stdout: string;
  stderr: string;
  compile_output: string;
  time: string;
  memory: string;
}

const codeService = {
  async submitCode(submission: CodeSubmission): Promise<CodeResult> {
    const response = await api.post<CodeResult>('/code/execute', submission);
    return response.data;
  },

  async getSubmissionResult(submissionId: string): Promise<CodeResult> {
    const response = await api.get<CodeResult>(`/code/result/${submissionId}`);
    return response.data;
  },

  async getSupportedLanguages(): Promise<any> {
    const response = await api.get('/code/languages');
    return response.data;
  }
};

export default codeService; 