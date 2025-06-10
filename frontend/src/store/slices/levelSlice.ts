import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

interface Level {
  id: number;
  title: string;
  prompt: string;
  language: string;
  difficulty: number;
  hint: string;
  clue: string;
}

interface LevelState {
  currentLevel: Level | null;
  loading: boolean;
  error: string | null;
}

const initialState: LevelState = {
  currentLevel: null,
  loading: false,
  error: null,
};

export const fetchLevel = createAsyncThunk(
  'level/fetchLevel',
  async ({ caseId, levelId }: { caseId: string; levelId: string }) => {
    const response = await axios.get(`/api/cases/${caseId}/levels/${levelId}`);
    return response.data;
  }
);

export const submitSolution = createAsyncThunk(
  'level/submitSolution',
  async ({
    caseId,
    levelId,
    code,
    language,
  }: {
    caseId: number;
    levelId: number;
    code: string;
    language: string;
  }) => {
    const response = await axios.post(`/api/cases/${caseId}/levels/${levelId}/submit`, {
      code,
      language,
    });
    return response.data;
  }
);

const levelSlice = createSlice({
  name: 'level',
  initialState,
  reducers: {
    clearLevel: (state) => {
      state.currentLevel = null;
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      // Fetch Level
      .addCase(fetchLevel.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchLevel.fulfilled, (state, action) => {
        state.loading = false;
        state.currentLevel = action.payload;
      })
      .addCase(fetchLevel.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || 'Failed to fetch level';
      })
      // Submit Solution
      .addCase(submitSolution.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(submitSolution.fulfilled, (state) => {
        state.loading = false;
      })
      .addCase(submitSolution.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message || 'Failed to submit solution';
      });
  },
});

export const { clearLevel } = levelSlice.actions;
export default levelSlice.reducer; 