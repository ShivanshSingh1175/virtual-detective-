import { AxiosError } from 'axios';

export interface ApiError {
  message: string;
  status: number;
  details?: any;
}

export class ApiException extends Error {
  public status: number;
  public details?: any;

  constructor(error: AxiosError) {
    super(error.message);
    this.status = error.response?.status || 500;
    this.details = error.response?.data;
    this.name = 'ApiException';
  }
}

export const handleApiError = (error: unknown): ApiError => {
  if (error instanceof ApiException) {
    return {
      message: error.message,
      status: error.status,
      details: error.details
    };
  }

  if (error instanceof AxiosError) {
    return {
      message: error.response?.data?.message || error.message,
      status: error.response?.status || 500,
      details: error.response?.data
    };
  }

  return {
    message: 'An unexpected error occurred',
    status: 500
  };
};

export const isApiError = (error: unknown): error is ApiError => {
  return (
    typeof error === 'object' &&
    error !== null &&
    'message' in error &&
    'status' in error
  );
}; 