module.exports = {
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false,
  theme: {
    extend: {
      colors: {
        darkmint: '#547d6d',
        mint: '#97cac3',
        syellow: '#f7dc70',
        beige: '#e0c8ae',
        lbeige: '#eadfd9',
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
