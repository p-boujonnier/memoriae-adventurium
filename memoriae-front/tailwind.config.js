/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{html,ts}'],
  theme: {
    extend: {
      colors: {
        parchment: '#e6dcc8',
        'stone-light': '#3d3025',
        'stone-mid': '#2c2218',
        'stone-dark': '#1a1410',
        gold: {
          light: '#d4a843',
          DEFAULT: '#b8860b',
          dark: '#8b6508',
        },
        blood: {
          light: '#a63228',
          DEFAULT: '#7a1f17',
          dark: '#4d1208',
        },
        forest: {
          light: '#4a7c59',
          DEFAULT: '#2d5a3d',
          dark: '#1a3d28',
        },
      },
      fontFamily: {
        medieval: ['MedievalFont', 'serif'],
        enchanted: ['EnchantedLand', 'serif'],
      },
    },
  },
  plugins: [],
};
