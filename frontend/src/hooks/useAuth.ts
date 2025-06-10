import { useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../store';
import { loginStart, loginSuccess, loginFailure, logout } from '../store/slices/authSlice';
import authService from '../api/authService';
import { AuthRequest, RegisterRequest } from '../api/authService';

export const useAuth = () => {
  const dispatch = useDispatch();
  const { user, isAuthenticated, loading, error } = useSelector((state: RootState) => state.auth);

  const login = useCallback(async (credentials: AuthRequest) => {
    try {
      dispatch(loginStart());
      const response = await authService.login(credentials);
      dispatch(loginSuccess(response));
      localStorage.setItem('token', response.token);
    } catch (error) {
      dispatch(loginFailure(error instanceof Error ? error.message : 'Login failed'));
      throw error;
    }
  }, [dispatch]);

  const register = useCallback(async (data: RegisterRequest) => {
    try {
      dispatch(loginStart());
      const response = await authService.register(data);
      dispatch(loginSuccess(response));
      localStorage.setItem('token', response.token);
    } catch (error) {
      dispatch(loginFailure(error instanceof Error ? error.message : 'Registration failed'));
      throw error;
    }
  }, [dispatch]);

  const logoutUser = useCallback(() => {
    authService.logout();
    dispatch(logout());
    localStorage.removeItem('token');
  }, [dispatch]);

  return {
    user,
    isAuthenticated,
    loading,
    error,
    login,
    register,
    logout: logoutUser,
  };
}; 