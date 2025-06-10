import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost/api';

const api = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true
});

// Add token to requests
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

// Handle response errors
api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            localStorage.removeItem('token');
            localStorage.removeItem('user');
            window.location.href = '/auth';
        }
        return Promise.reject(error);
    }
);

// Auth API
export const authAPI = {
    login: (credentials) => api.post('/auth/login', credentials),
    register: (userData) => api.post('/auth/register', userData),
};

// Cases API
export const casesAPI = {
    getAll: () => api.get('/cases'),
    getById: (id) => api.get(`/cases/${id}`),
    getByUser: (userId) => api.get(`/cases/user/${userId}`),
    getByDifficulty: (difficulty) => api.get(`/cases/difficulty/${difficulty}`),
    create: (caseData) => api.post('/cases', caseData),
    update: (id, caseData) => api.put(`/cases/${id}`, caseData),
    delete: (id) => api.delete(`/cases/${id}`),
};

// Challenges API
export const challengesAPI = {
    getAll: () => api.get('/challenges'),
    getById: (id) => api.get(`/challenges/${id}`),
    getByCase: (caseId) => api.get(`/challenges/case/${caseId}`),
    getByDifficulty: (difficulty) => api.get(`/challenges/difficulty/${difficulty}`),
    create: (challengeData) => api.post('/challenges', challengeData),
    update: (id, challengeData) => api.put(`/challenges/${id}`, challengeData),
    delete: (id) => api.delete(`/challenges/${id}`),
    submitSolution: (id, solution) => api.post(`/challenges/${id}/submit`, solution),
};

// Code Execution API
export const codeExecutionAPI = {
    execute: (code, language) => api.post('/execute', { code, language }),
    validate: (challengeId, code) => api.post(`/challenges/${challengeId}/validate`, { code }),
};

export default api; 