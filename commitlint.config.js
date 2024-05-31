module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    'body-max-line-length': [1, 'always', 5000],  // 5000 is the maximum line length
    'footer-max-line-length': [1, 'always', 5000] // 5000 is the maximum line length
  },
};
