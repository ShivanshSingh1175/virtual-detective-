import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';
import codeReducer from './slices/codeSlice';

export const store = configureStore({
  reducer: {
    auth: authReducer,
    code: codeReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        // Ignore these action types
        ignoredActions: ['auth/loginSuccess'],
        // Ignore these field paths in all actions
        ignoredActionPaths: ['payload.user'],
        // Ignore these paths in the state
        ignoredPaths: ['auth.user'],
      },
    }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch; 