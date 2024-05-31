module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    'body-max-line-length': [1, 'always', 5000],
    'footer-max-line-length': [1, 'always', 5000]
  },
};
