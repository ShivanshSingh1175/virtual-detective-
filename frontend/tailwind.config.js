/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'detective': {
          'dark': '#1a1a1a',
          'light': '#2d2d2d',
          'accent': '#8b5cf6',
          'text': '#e5e5e5',
          'muted': '#9ca3af',
        },
      },
      fontFamily: {
        'typewriter': ['Courier New', 'monospace'],
        'detective': ['Playfair Display', 'serif'],
      },
      animation: {
        'typewriter': 'typewriter 2s steps(40) forwards',
        'blink': 'blink 1s steps(1) infinite',
      },
      keyframes: {
        typewriter: {
          'to': { width: '100%' },
        },
        blink: {
          '50%': { borderColor: 'transparent' },
        },
      },
    },
  },
  plugins: [],
} 