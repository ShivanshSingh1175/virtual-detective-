import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { CodeResult } from '../../api/codeService';

interface CodeState {
  result: CodeResult | null;
  loading: boolean;
  error: string | null;
  languageId: number;
  sourceCode: string;
}

const initialState: CodeState = {
  result: null,
  loading: false,
  error: null,
  languageId: 71, // Default to Python
  sourceCode: '',
};

const codeSlice = createSlice({
  name: 'code',
  initialState,
  reducers: {
    executeCodeStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    executeCodeSuccess: (state, action: PayloadAction<CodeResult>) => {
      state.loading = false;
      state.result = action.payload;
      state.error = null;
    },
    executeCodeFailure: (state, action: PayloadAction<string>) => {
      state.loading = false;
      state.error = action.payload;
    },
    setLanguage: (state, action: PayloadAction<number>) => {
      state.languageId = action.payload;
    },
    setSourceCode: (state, action: PayloadAction<string>) => {
      state.sourceCode = action.payload;
    },
    clearResult: (state) => {
      state.result = null;
      state.error = null;
    },
  },
});

export const {
  executeCodeStart,
  executeCodeSuccess,
  executeCodeFailure,
  setLanguage,
  setSourceCode,
  clearResult,
} = codeSlice.actions;

export default codeSlice.reducer; 