import { useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../store';
import {
  executeCodeStart,
  executeCodeSuccess,
  executeCodeFailure,
  setLanguage,
  setSourceCode,
  clearResult,
} from '../store/slices/codeSlice';
import codeService from '../api/codeService';
import { CodeSubmission } from '../api/codeService';

export const useCode = () => {
  const dispatch = useDispatch();
  const { result, loading, error, languageId, sourceCode } = useSelector(
    (state: RootState) => state.code
  );

  const executeCode = useCallback(async (submission: CodeSubmission) => {
    try {
      dispatch(executeCodeStart());
      const response = await codeService.submitCode(submission);
      dispatch(executeCodeSuccess(response));
    } catch (error) {
      dispatch(executeCodeFailure(error instanceof Error ? error.message : 'Code execution failed'));
      throw error;
    }
  }, [dispatch]);

  const changeLanguage = useCallback((languageId: number) => {
    dispatch(setLanguage(languageId));
  }, [dispatch]);

  const updateSourceCode = useCallback((code: string) => {
    dispatch(setSourceCode(code));
  }, [dispatch]);

  const resetResult = useCallback(() => {
    dispatch(clearResult());
  }, [dispatch]);

  return {
    result,
    loading,
    error,
    languageId,
    sourceCode,
    executeCode,
    changeLanguage,
    updateSourceCode,
    resetResult,
  };
}; 