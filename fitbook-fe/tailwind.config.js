module.exports = {
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false,
  theme: {
    extend: {
      fontSize: {
        xs: '.75rem',
      },
      colors: {
        darkmint: '#547d6d',
        darkermint: '#4e7465',
        mint: '#97cac3',
        syellow: '#f7dc70',
        darksyellow: '#f4d248',
        beige: '#e0c8ae',
        lbeige: '#eadfd9',
      },
      spacing: {
        300: '2000px',
      },
      flex: {
        m: '1 1 200px',
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
